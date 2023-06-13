package otinu.pf.pertch.form;

import java.sql.Timestamp;
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
	private String username;
	
	/* ログイン用パスワード */
	private String password;
	
	/* 名前 */
	private String ownerName;
	
	/* メッセージ */
	@Size(max = 200, message = "メッセージは{max}文字までです")
	private String message;
	
	/* 連絡先1 */
	@Size(max = 100, message = "連絡先は{max}文字までです")
	private String contact;
	
	/* 連絡先2 */
	@Size(max = 100, message = "連絡先は{max}文字までです")
	private String subContact;

	/* 登録日時 */
	private Timestamp createdAt;
	
	/* 更新日時 */
	private Timestamp updatedAt;

}
