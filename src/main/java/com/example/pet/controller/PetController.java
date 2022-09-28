package com.example.pet.controller;

import java.io.IOException;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pet.form.PetForm;
import com.example.pet.model.Pet;
import com.example.pet.service.PetService;

@Controller
public class PetController {

	@Autowired
	PetService service;
	
	@ModelAttribute
	public PetForm setUpFprm() {
		PetForm form = new PetForm();
		return form;
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/index")
	public String showIndex(PetForm petForm, Model model) {
		Iterable<Pet> list = service.selectAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	@PostMapping("insert")
	public String registerPet(@Validated PetForm petForm, BindingResult bindingResult, Model model, 
								@RequestParam("upload_file") MultipartFile multipartFile, 
								RedirectAttributes redirectAttributes){
		
		Pet pet = new Pet();
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setContact(petForm.getContact());
		
		try {
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
		} catch (IOException e) {
			System.out.println("イメージデータのエンコーディング時に問題が発生しました。");
			e.printStackTrace();
			return "redirect:/pet";
		}
		
		
		
		
		
	}
	
	@GetMapping("/edit/{id}")
	public String editPet(PetForm petForm,@PathVariable Integer id, Model model) {
		Optional<Pet> petOpt = service.selectById(id);
		Optional<PetForm> petFormOpt = petOpt.map(t -> makePetForm(t));
		/*
		 * ↑ここで、ラムダとmakePetForm()を使って、PetをPetFormに詰めなおしている
		 *  ①この方法で詰め替えをするのは、Lombokのゲッター・セッター自動生成のような便利な機能がないため
		 *  　⇒一つひとつ人力で詰め替える処理を記述する必要がある
		 *  
		 *  ②ここの処理では最終的にPetFormを渡したい。理由はバリデーションを使いたいから。
		 *  　「model.addAttribute("petForm", pet)」のように渡すと画面遷移時にエラーになってしまう。
		 *  　⇔「なら、Pet.javaにもバリデーション用のアノテーションを付ければいい」という考えもある。
		 *  　　　その場合、そもそもModel用とForm用でファイルを分ける意味がなくなってしまい、実用的な実装でなくなってしまう。
		 */

		if (petFormOpt.isPresent()) {
			petForm = petFormOpt.get();
			model.addAttribute("petForm", petForm);
		} else {
			model.addAttribute("selectMessage", "該当のデータが見つかりませんでした");
		}

		return "edit";
	}
	
	private PetForm makePetForm(Pet pet) {
		PetForm form = new PetForm();
		form.setId(pet.getId());
		form.setName(pet.getName());
		form.setAge(pet.getAge());
		form.setSex(pet.getSex());
		form.setContact(pet.getContact());
		return form;
	}
	
	@PostMapping("/update")
	public String update(@Validated PetForm petForm, BindingResult bindingResult, 
						Model model, RedirectAttributes redirectAttributes) {
		
		Pet pet = makePet(petForm);
		if(!bindingResult.hasErrors()) {
			service.updatePet(pet);
			redirectAttributes.addFlashAttribute("updateMessage", "更新が完了しました");
		} else {
			System.out.println(bindingResult.getFieldError().getDefaultMessage());
			redirectAttributes.addFlashAttribute("updateMessage", "更新に失敗しました");
		}
		return "redirect:/index";
	}
	
	private Pet makePet(PetForm petForm) {
		Pet pet = new Pet();
		pet.setId(petForm.getId());
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setContact(petForm.getContact());
		return pet;
	}
	
	@PostMapping("/delete")	
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		service.deleteById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("deleteMessage", "削除が完了しました");
		return "redirect:/index";
	}
}









