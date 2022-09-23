package com.example.pet.controller;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pet.form.PetForm;
import com.example.pet.model.Pet;
import com.example.pet.service.PetService;

@Controller
@RequestMapping("/pet")
public class PetController {

	@Autowired
	PetService service;
	
	@ModelAttribute
	public PetForm setUpFprm() {
		PetForm form = new PetForm();
		return form;
	}
	
	@GetMapping
	public String showIndex(PetForm petForm, Model model) {
		Iterable<Pet> list = service.selectAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	/*
	@PostMapping("insert")
	public String registerPet(@Validated PetForm petForm, BindingResult bindingResult, 
								Model model, MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		
		
		Pet pet = new Pet();
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setContact(petForm.getContact());
		// pet.setImage(petForm.getImage());
		if (!bindingResult.hasErrors()) {
			service.insertPet(pet);

			redirectAttributes.addFlashAttribute("insertMessage", "登録が完了しました");
			return "redirect:/pet";
		} else {
			// System.out.println(petForm.getImage());
			System.out.println(bindingResult.getFieldError().getDefaultMessage());
			redirectAttributes.addFlashAttribute("insertMessage", "登録に失敗しました");
			return "redirect:/pet";
		}
	}
	*/
	
	@PostMapping("insert")
	public String registerPet(PetForm petForm, BindingResult bindingResult, 
								Model model, @RequestParam("upload_file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
		
		
		Pet pet = new Pet();
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setContact(petForm.getContact());
		
		String base64 = new String(Base64.encodeBase64(multipartFile.getBytes()),"ASCII");
		String imageType = multipartFile.getContentType();
		if(imageType == "image/png") {
			pet.setImage("data:image/png;base64," + base64);
		} else if(imageType == "image/jpeg") {
			pet.setImage("data:image/jpeg," + base64);
		} else if(imageType == "image/gif") {
			pet.setImage("data:image/gif;base64," + base64);
		}
		
		if (!bindingResult.hasErrors()) {
			service.insertPet(pet);

			redirectAttributes.addFlashAttribute("insertMessage", "登録が完了しました");
			return "redirect:/pet";
		} else {
			// System.out.println(petForm.getImage());
			System.out.println(bindingResult.getFieldError().getDefaultMessage());
			redirectAttributes.addFlashAttribute("insertMessage", "登録に失敗しました");
			return "redirect:/pet";
		}
		
		
		
		
	}
}









