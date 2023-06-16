package otinu.pf.pertch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import otinu.pf.pertch.entity.PetComment;

public interface PetCommentRepository extends JpaRepository<PetComment, Integer> {
	@Query(value = "SELECT * FROM pet_comment WHERE pet_id = ?1", nativeQuery=true)
	public List<PetComment> findAllByPetId(Integer id);
}
