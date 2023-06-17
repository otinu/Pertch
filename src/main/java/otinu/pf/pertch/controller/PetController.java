package otinu.pf.pertch.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
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
import org.springframework.web.util.UriComponentsBuilder;

import otinu.pf.pertch.Constant;
import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.entity.PetComment;
import otinu.pf.pertch.form.PetCommentForm;
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
	public ModelAndView insertPet(@Validated PetForm petForm, BindingResult bindingResult,
			@RequestParam("upload_file") MultipartFile multipartFile, RedirectAttributes redirectAttributes,
			Principal principal) {

		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldError());
			mv.setViewName("pet/new");
			return mv;
		}

		if (multipartFile.getSize() > Constant.UPLOAD_FILE_MAX_SIZE) {
			String errorMessages = "ファイルアップロードに失敗しました。ファイルは4MBまでです";
			mv.addObject("errorMessages", errorMessages);
			mv.setViewName("pet/new");
			return mv;
		}

		Pet pet = new Pet();
		petService.setFormToPet(pet, petForm);
		mv.setViewName("redirect:index");
		try {
			petService.settingImage(pet, multipartFile);

			Owner relationOwner = ownerService.getCurrentUser(principal);
			pet.setOwner(relationOwner);
			petService.insertPet(pet);
			redirectAttributes.addFlashAttribute("insertMessage", "登録が完了しました");
			return mv;
		} catch (IOException e) {
			System.out.println("イメージデータのエンコーディング時に問題が発生しました。");
			e.printStackTrace();
			return mv;
		}
	}

	@GetMapping("/show/{id}")
	public String showPet(PetForm petForm, @PathVariable Integer id, Model model) {
		Optional<Pet> petOpt = petService.findById(id);
		Optional<PetForm> petFormOpt = petOpt.map(t -> petService.makePetForm(t));

		if (petFormOpt.isPresent()) {
			petForm = petFormOpt.get();
			model.addAttribute("petForm", petForm);

			List<PetComment> petCommentList = petService.findPetComment(id);
			model.addAttribute("petCommentList", petCommentList);

			PetCommentForm petCommentForm = new PetCommentForm();
			model.addAttribute("petId", id);
			model.addAttribute("petCommentForm", petCommentForm);
		} else {
			model.addAttribute("selectMessage", "該当のデータが見つかりませんでした");
		}
		return "pet/show";
	}

	@PostMapping("/edit/{id}")
	public String editPet(PetForm petForm, @PathVariable Integer id, Model model) {
		Optional<Pet> petOpt = petService.findById(id);
		Optional<PetForm> petFormOpt = petOpt.map(t -> petService.makePetForm(t));

		if (petFormOpt.isPresent()) {
			petForm = petFormOpt.get();
			model.addAttribute("petForm", petForm);
		} else {
			model.addAttribute("selectMessage", "該当のペットデータが見つかりませんでした");
		}
		return "pet/edit";
	}

	@PostMapping("/update")
	public ModelAndView update(@Validated PetForm petForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, @RequestParam("upload_file") MultipartFile multipartFile,
			Principal principal, UriComponentsBuilder uriBuilder) {

		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldError());

			// リダイレクト先で@PathVariableを使ってる場合、対象の変数を.addObjectで追加する
			mv.addObject("id", petForm.getId().toString());
			mv.setViewName("/pet/edit");
			return mv;
		}
		
		if (multipartFile.getSize() > Constant.UPLOAD_FILE_MAX_SIZE) {
			String errorMessages = "ファイルアップロードに失敗しました。ファイルは4MBまでです";
			mv.addObject("errorMessages", errorMessages);
			mv.addObject("id", petForm.getId().toString());
			mv.setViewName("pet/edit");
			return mv;
		}

		Pet pet = petService.makePet(new Pet(), petForm, multipartFile, principal);
		petService.updatePet(pet);
		redirectAttributes.addFlashAttribute("updateMessage", "ペット情報の更新が完了しました");
		mv.setViewName("redirect:/pet/index");
		return mv;
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		petService.deleteById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("deleteMessage", "削除が完了しました");
		return "redirect:/pet/index";
	}

}