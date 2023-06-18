package otinu.pf.pertch.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerForm {
	
	/* ID */
	private Integer id;
	
	/* ログイン用メールアドレス */
	@NotBlank(message="Emailは入力が必要です")
	@Email(message = "Emailにはメールアドレスの入力が必要です")
	private String username;
	
	/* ログイン用パスワード */
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{4,12}$", message = "パスワードは4～12文字、英語小文字・大文字を含めて入力が必要です")
	@NotBlank(message="パスワードは入力が必要です")
	private String password;
	
	/* 名前 */
	@NotBlank(message="名前は入力が必要です")
	@Size(max = 20, min = 2, message = "名前は{min}～{max}文字までです")
	private String ownerName;
	
	/* メッセージ */
	@Size(max = 500, message = "メッセージは{max}文字までです")
	private String message;
	
	/* 連絡先1 */
	@Size(max = 30, message = "連絡先は{max}文字までです")
	private String contact;
	
	/* 連絡先2 */
	@Size(max = 30, message = "連絡先は{max}文字までです")
	private String subContact;

	/* 登録日時 */
	private Date createdAt;
	
	/* 更新日時 */
	private Date updatedAt;

}
