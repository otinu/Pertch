package otinu.pf.pertch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.entity.PetComment;
import otinu.pf.pertch.form.PetCommentForm;
import otinu.pf.pertch.service.PetCommentService;
import otinu.pf.pertch.service.PetService;

@Controller
public class PetCommentController {
	
	@Autowired
	PetCommentService petCommentService;
	
	@Autowired
	PetService petService;
	
	@PostMapping("/petComment/insert")
	public String insertPetComment(@Validated PetCommentForm petCommentForm, Integer petId) {
		Pet pet = petService.findByIdToPet(petId);
		
		PetComment petComment = petCommentService.makePetComment(petCommentForm, pet);
		petCommentService.insertPetComment(petComment);
		
		String redirectPath = "/pet/show/" + petId;
		return "redirect:" + redirectPath;
	}

}
