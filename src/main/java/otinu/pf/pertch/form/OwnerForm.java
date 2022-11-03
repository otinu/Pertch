package otinu.pf.pertch.form;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OwnerForm {
	
	/* ID */
	@Id
	@Column(name = "id")
	@NotBlank
	private Integer id;
	
	/* ログイン用メールアドレス */
	@Column(name = "username")
	private String username;
	
	/* ログイン用パスワード */
	@Column(name = "password")
	private String password;
	
	/* 名前 */
	@Column(name = "name")
	private String name;
	
	/* メッセージ */
	@Column(name = "message")
	private String message;
	
	/* 連絡先 */
	@Column(name = "contact")
	private String contact;

	/* 登録日時 */
	@Column(name = "created_at")
	private Date created_at;
	
	/* 更新日時 */
	@Column(name = "updated_at")
	private Date updated_at;
	
	// private List<PetForm> petList;

}
