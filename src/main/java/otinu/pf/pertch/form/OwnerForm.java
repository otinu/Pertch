package otinu.pf.pertch.form;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class OwnerForm {
	
	/* ID */
	private Integer id;
	
	/* ログイン用メールアドレス */
	@NotBlank(message="Emailは入力が必要です")
	@Email(message = "Emailにはメールアドレスの入力が必要です")
	private String username;
	
	/* ログイン用パスワード */
	@NotBlank(message="パスワードが未入力です")
	private String password;
	
	/* 名前 */
	@NotBlank(message="名前は入力が必要です")
	private String name;
	
	/* メッセージ */
	@Size(max = 200, message = "メッセージは{max}文字までです")
	private String message;
	
	/* 連絡先 */
	@Size(max = 100, message = "連絡先は{max}文字までです")
	private String contact;

	/* 登録日時 */
	
	private Date created_at;
	
	/* 更新日時 */
	
	private Date updated_at;
	
	// private List<PetForm> petList;

}
