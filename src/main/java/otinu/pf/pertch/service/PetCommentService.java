package otinu.pf.pertch.service;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.entity.PetComment;
import otinu.pf.pertch.form.PetCommentForm;

public interface PetCommentService {
	PetComment makePetComment(PetCommentForm petCommentForm, Pet pet);
	void insertPetComment(PetComment petComment);
}
