package otinu.pf.pertch.entity;

import java.util.Date;
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

	/* ID */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/* ログイン用メールアドレス */
	// ログインに利用。メールアドレスを入力
	// ログイン中のユーザーをnameから一意に取得する必要があるため、ユニーク制約を設定
	@Column(name = "username")
	private String username;		
	
	/* ログイン用パスワード */
	@Column(name = "password")
	private String password;
	
	/* 名前 */
	@Column(name = "owner_name", unique = true)
	private String ownerName;
	
	/* メッセージ */
	@Column(name = "message")
	private String message;		// 飼い主からの発信に一定の自由度をもたせたい
	
	/* 連絡先1 */
	@Column(name = "contact")
	private String contact;
	
	/* 連絡先2 */
	@Column(name = "sub_contact")
	private String subContact;

	/* 登録日時 */
	@Column(name = "created_at")
	private Date createdAt;
	
	/* 更新日時 */
	@Column(name = "updated_at")
	private Date updatedAt;
	
	
	/* 
	 * mappedByには、1対N の1側を指定
	 * 　⇒N側の更新に連動して、結び付けられたOwner側の対象オブジェクトも削除される
	 * 　　⇒Petを削除すれば、対象Ownerも自動で削除される
	 * 
	 * cascadeには、1対N の1側の変更(更新や削除など)に伴って、N側も同様の処理を連動させるかを指定*/
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	@OrderBy("id asc")
	private List<Pet> petList;
	
	public void addPet(Pet pet) {
		pet.setOwner(this);
		petList.add(pet);
	}
	
	public Owner(String username, String password, String name, String message, String contact, String subContact, Date created_at, Date updated_at) {
		this.username = username;
		this.password = password;
		this.ownerName = name;
		this.message = message;
		this.contact = contact;
		this.subContact = subContact;
		this.createdAt = created_at;
		this.updatedAt = updated_at;
	}

}
