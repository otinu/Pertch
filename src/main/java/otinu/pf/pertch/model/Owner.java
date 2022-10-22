package otinu.pf.pertch.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
//@ToString(exclude = "petList")
public class Owner {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// @Column(unique = true)
	@Column(name = "username")
	private String username;		// ログインにも利用。代わりにsub_contactは削除
	
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
	
	public Owner(String username, String password, String name, String message, String contact, Timestamp created_at, Timestamp updated_at) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.message = message;
		this.contact = contact;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	// List<String> roleList; // ログイン機能(ロール設定用) ⇒ N:Nの中間テーブル用
	
	/*

	// mappedByには、1対N の1側を指定
	// cascadeには、1対N の1側の変更(更新や削除など)に伴って、N側も同様の処理を連動させるかを指定
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Pet> petList;
	
	*/
}
