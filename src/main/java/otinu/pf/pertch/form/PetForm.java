package otinu.pf.pertch.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import otinu.pf.pertch.entity.Owner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetForm {
	
	/* ID */
	private Integer id;
	
	/* 名前 */
	@NotBlank(message="名前は入力が必要です")
	@Size(min = 2, max = 20, message = "名前は{min}～{max}文字までです")
	private String name;
	
	/* 年齢 */
	//@NotNull	// Integerの場合はNotNull
	@Range(min = 0, max = 99, message = "年齢は{min}～{max}歳までです")
	private Integer age;
	
	/* 性別 */
	@NotNull(message="性別はどちらか選択が必要です")
	private Boolean sex;
	
	/* 特徴 */
	@Size(max = 1000, message = "特徴は{max}文字までです")
	private String charmPoint;
	
	/* 住処の郵便番号 */
	@Pattern(regexp = "^(?=.*[0-9]).{7,7}$", message = "郵便番号はハイフン抜きの7桁数字で入力が必要です")
	private String postCord;
	
	/* 住処の住所 */
	@Size(max = 50, message = "住所は{max}文字までです")
	private String address;
	
	/* イメージ画像 */
	private String image;
	
	/* 登録日時 */
	private Date createdAt;
	
	/* 更新日時 */
	private Date updatedAt;
	
	/* 飼い主 */
	private Owner owner;
}
