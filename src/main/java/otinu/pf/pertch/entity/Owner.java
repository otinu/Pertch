package otinu.pf.pertch.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
@ToString(exclude = "petList")
public class Owner {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// ログインに利用。代わりにsub_contactは削除し、usernameにはメールアドレスを入力
	// @Column(unique = true)
	@Column(name = "username")
	private String username;		
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "message")
	private String message;		// 飼い主からの発信に一定の自由度をもたせたい
	
	@Column(name = "contact")
	private String contact;		// Petから持ち越し

	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Column(name = "updated_at")
	private Timestamp updated_at;
	
	private List<Pet> petList = new ArrayList<>();
	
	public void addPet(Pet pet) {
		pet.setOwner(this);
		petList.add(pet);
	}
	
	public Owner(String username, String password, String name, String message, String contact, Timestamp created_at, Timestamp updated_at) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.message = message;
		this.contact = contact;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
}
