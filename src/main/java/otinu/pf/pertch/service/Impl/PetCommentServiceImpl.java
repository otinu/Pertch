package otinu.pf.pertch.service.Impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.entity.PetComment;
import otinu.pf.pertch.form.PetCommentForm;
import otinu.pf.pertch.repository.PetCommentRepository;
import otinu.pf.pertch.service.PetCommentService;

@Service
@Transactional
public class PetCommentServiceImpl implements PetCommentService {
	
	@Autowired
	PetCommentRepository repository;

	@Override
	public PetComment makePetComment(PetCommentForm petCommentForm, Pet pet) {
		PetComment petComment = new PetComment();
		petComment.setPet(pet);
		petComment.setEventTime(petCommentForm.getEventTime());
		petComment.setEventPlace(petCommentForm.getEventPlace());
		petComment.setEventInformation(petCommentForm.getEventInformation());
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		petComment.setCreatedAt(timestamp);
		petComment.setUpdatedAt(timestamp);
		return petComment;
	}

	@Override
	public void insertPetComment(PetComment petComment) {
		repository.saveAndFlush(petComment);
	}

}
