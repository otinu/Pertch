package otinu.pf.pertch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.entity.PetComment;
import otinu.pf.pertch.form.PetCommentForm;
import otinu.pf.pertch.form.PetForm;
import otinu.pf.pertch.service.PetCommentService;
import otinu.pf.pertch.service.PetService;

@Controller
public class PetCommentController {

	@Autowired
	PetCommentService petCommentService;

	@Autowired
	PetService petService;

	@PostMapping("/petComment/insert")
	public ModelAndView insertPetComment(@Validated PetCommentForm petCommentForm, BindingResult bindingResult,
			Integer petId, PetForm petForm) {
		ModelAndView mv = new ModelAndView();
		String nextPath = "/pet/show";
		String errorMessages = "";

		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessages += error.getDefaultMessage() + "\n";
			}
			mv.addObject("errorMessages", errorMessages);
			mv.addObject("id", petId);

			Optional<Pet> petOpt = petService.findById(petId);
			Optional<PetForm> petFormOpt = petOpt.map(t -> petService.makePetForm(t));
			petForm = petFormOpt.get();
			mv.addObject("petForm", petForm);
			
			List<PetComment> petCommentList = petService.findPetComment(petId);
			mv.addObject("petCommentList", petCommentList);

			mv.setViewName(nextPath);
			return mv;
		}

		Pet pet = petService.findByIdToPet(petId);

		PetComment petComment = petCommentService.makePetComment(petCommentForm, pet);
		petCommentService.insertPetComment(petComment);
		mv.setViewName("redirect:" + nextPath);
		return mv;
	}

}
