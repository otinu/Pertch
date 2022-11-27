package otinu.pf.pertch.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.form.PetForm;
import otinu.pf.pertch.service.OwnerService;
import otinu.pf.pertch.service.PetService;

@Controller
@RequestMapping("pet")
public class PetController {

	@Autowired
	PetService petService;

	@Autowired
	OwnerService ownerService;

	@GetMapping("/index")
	public ModelAndView showIndex(PetForm petForm, Principal principal) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("pet/index");
		Iterable<Pet> list = petService.selectAll();
		mv.addObject("list", list);

		Owner currentUser = ownerService.getCurrentUser(principal);
		mv.addObject("currentUser", currentUser);
		return mv;
	}

	@GetMapping("/new")
	public ModelAndView showNew() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pet/new");
		mv.addObject("petForm", new PetForm());
		return mv;
	}

	@PostMapping("/insert")
	public ModelAndView insertPet(@Validated PetForm petForm, 
									BindingResult bindingResult,
									@RequestParam("upload_file") MultipartFile multipartFile,
									RedirectAttributes redirectAttributes,
									Principal principal) {

		Pet pet = new Pet();
		petService.setFormToPet(pet, petForm);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:index");
		try {
			petService.settingImage(pet, multipartFile);

			if (!bindingResult.hasErrors()) {
				Owner relationOwner = ownerService.getCurrentUser(principal);
				pet.setOwner(relationOwner);
				petService.insertPet(pet);
				redirectAttributes.addFlashAttribute("insertMessage", "登録が完了しました");
				return mv;
			} else {
				System.out.println(bindingResult.getFieldError().getDefaultMessage());
				redirectAttributes.addFlashAttribute("insertMessage", "登録に失敗しました");
				mv.setViewName("pet/new");
				return mv;
			}
		} catch (IOException e) {
			System.out.println("イメージデータのエンコーディング時に問題が発生しました。");
			e.printStackTrace();
			return mv;
		}
	}
	
	@PostMapping("/show/{id}")
	public String showPet(PetForm petForm, @PathVariable Integer id, Model model) {
		Optional<Pet> petOpt = petService.findById(id);
		Optional<PetForm> petFormOpt = petOpt.map(t -> petService.makePetForm(t));

		if (petFormOpt.isPresent()) {
			petForm = petFormOpt.get();
			model.addAttribute("petForm", petForm);
		} else {
			model.addAttribute("selectMessage", "該当のデータが見つかりませんでした");
		}
		return "pet/edit";
	}

	@PostMapping("/edit/{id}")
	public String editPet(PetForm petForm, @PathVariable Integer id, Model model) {
		Optional<Pet> petOpt = petService.findById(id);
		Optional<PetForm> petFormOpt = petOpt.map(t -> petService.makePetForm(t));

		if (petFormOpt.isPresent()) {
			petForm = petFormOpt.get();
			model.addAttribute("petForm", petForm);
		} else {
			model.addAttribute("selectMessage", "該当のデータが見つかりませんでした");
		}
		return "pet/edit";
	}

	@PostMapping("/update")
	public String update(@Validated PetForm petForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes,
			@RequestParam("upload_file") MultipartFile multipartFile, Principal principal) {

		Pet pet = petService.makePet(new Pet(), petForm, multipartFile, principal);
		if (!bindingResult.hasErrors()) {
			petService.updatePet(pet);
			redirectAttributes.addFlashAttribute("updateMessage", "更新が完了しました");
		} else {
			System.out.println(bindingResult.getFieldError().getDefaultMessage());
			redirectAttributes.addFlashAttribute("updateMessage", "更新に失敗しました");
		}
		return "redirect:/pet/index";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		petService.deleteById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("deleteMessage", "削除が完了しました");
		return "redirect:/pet/index";
	}

}