package otinu.pf.pertch.service.Impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.JoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.repository.OwnerRepository;
import otinu.pf.pertch.repository.PetRepository;
import otinu.pf.pertch.service.PetSearchService;

@Service
@Transactional
public class PetSearchServiceImpl implements PetSearchService {

	@Autowired
	PetRepository petRepository;

	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public List<Pet> searchPet(String name, Integer age, Boolean sex, String charmPoint, String postCord,
			String address, String ownerName) {
		
		if(this.isNonInput(name, age, sex, charmPoint, postCord, address, ownerName)) {
			return null;
		}

		Owner searchOwner = new Owner();

		if (!ownerName.isEmpty()) {
			searchOwner = ownerRepository.findByOwnerName(ownerName);
			if (Objects.isNull(searchOwner)) {
				return null;
			}
		}

		// owner_nameが未入力の場合はLEFT OUTER JOIN抜きで検索
		if (ownerName.isEmpty()) {
			Specification<Pet> spec = Specification.where(this.nameEquals(name)).and(this.ageEquals(age))
					.and(this.sexEquals(sex)).and(this.charmPointLike(charmPoint)).and(this.postCordEquals(postCord))
					.and(this.addressEquals(address));
			return petRepository.findAll(spec);
		}

		Specification<Pet> spec = Specification.where(this.nameEquals(name)).and(this.ageEquals(age))
				.and(this.sexEquals(sex)).and(this.charmPointLike(charmPoint)).and(this.postCordEquals(postCord))
				.and(this.addressEquals(address)).and(this.ownerNameJoinEquals(ownerName));
		return petRepository.findAll(spec);
	}

	public Specification<Pet> nameEquals(String name) {
		return name.isEmpty() ? null : (root, query, builder) -> builder.equal(root.get("name"), name);
	}

	public Specification<Pet> ageEquals(Integer age) {
		return age == null ? null : (root, query, builder) -> builder.equal(root.get("age"), age);
	}

	public Specification<Pet> sexEquals(Boolean sex) {
		return sex == null ? null : (root, query, builder) -> builder.equal(root.get("sex"), sex);
	}

	public Specification<Pet> charmPointLike(String charmPoint) {
		String likePattern = "%" + charmPoint + "%";
		return charmPoint.isEmpty() ? null
				: (root, query, builder) -> builder.like(root.get("charmPoint"), likePattern);
	}

	public Specification<Pet> postCordEquals(String postCord) {
		return postCord.isEmpty() ? null : (root, query, builder) -> builder.equal(root.get("postCord"), postCord);
	}

	public Specification<Pet> addressEquals(String address) {
		return address.isEmpty() ? null : (root, query, builder) -> builder.equal(root.get("address"), address);
	}

	public Specification<Pet> ownerNameJoinEquals(String ownerName) {
		return (root, query, builder) -> builder.equal(root.join("owner", JoinType.LEFT).get("ownerName"), ownerName);
	}

	@Override
	public boolean isNonInput(String name, Integer age, Boolean sex, String charmPoint, String postCord,
			String address, String ownerName) {
		if (name.isEmpty() && Objects.isNull(age) && Objects.isNull(sex) && charmPoint.isEmpty() && postCord.isEmpty()
				&& address.isEmpty() && ownerName.isEmpty()) {
			return true;
		}
		return false;
	}

}
