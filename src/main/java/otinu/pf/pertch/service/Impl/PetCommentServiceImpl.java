package otinu.pf.pertch.service.Impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		String eventTimeString = petCommentForm.getEventTime();
		eventTimeString = eventTimeString.replace("T", " ");
		eventTimeString = eventTimeString.replace("-", "/");
		
		Date eventTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			eventTime = sdf.parse(eventTimeString);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		
		PetComment petComment = new PetComment();
		petComment.setPet(pet);
		petComment.setEventTime(new Timestamp(eventTime.getTime()));
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
