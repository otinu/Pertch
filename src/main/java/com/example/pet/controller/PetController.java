package com.example.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("insert")
	public String registerPet(@Validated PetForm petForm, BindingResult bindingResult, 
								Model model, RedirectAttributes redirectAttributes) {
		
		Pet pet = new Pet();
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setContact(petForm.getContact());
		if (!bindingResult.hasErrors()) {
			service.insertPet(pet);

			redirectAttributes.addFlashAttribute("insertMessage", "登録が完了しました");
			return "redirect:/pet";
		} else {
			redirectAttributes.addFlashAttribute("insertMessage", "登録に失敗しました");
			return "redirect:/pet";
		}
		
		
		
		
	}
}









