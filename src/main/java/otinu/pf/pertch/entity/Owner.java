package otinu.pf.pertch.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
	
	// ユーザー様の画面に実際に表示させるユーザー名
	@Column(name = "username")
	private String username;		
	
	@Column(name = "password")
	private String password;
	
	// ログインに利用。メールアドレスを入力
	// ログイン中のユーザーをnameから一意に取得する必要があるため、ユニーク制約を設定
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "message")
	private String message;		// 飼い主からの発信に一定の自由度をもたせたい
	
	@Column(name = "contact")
	private String contact;		// Petから持ち越し

	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Column(name = "updated_at")
	private Timestamp updated_at;
	
	// mappedByには、1対N の1側を指定
	// cascadeには、1対N の1側の変更(更新や削除など)に伴って、N側も同様の処理を連動させるかを指定
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	@OrderBy("id asc")
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
