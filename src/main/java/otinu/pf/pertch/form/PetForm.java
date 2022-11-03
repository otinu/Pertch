package otinu.pf.pertch.form;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetForm {
	
	/* ID */
	private Integer id;
	
	/* 名前 */
	@NotBlank(message="名前の入力が必要です")
	@Size(min = 2, max = 20, message = "{min}-{max}文字まで")
	private String name;
	
	/* 年齢 */
	//@NotNull	// Integerの場合はNotNull
	private Integer age;
	
	/* 性別 */
	@NotNull
	private Boolean sex;
	
	/* 特徴 */
	private String charmPoint;
	
	/* 住処の郵便番号 */
	@NotBlank
	private String postCord;
	
	/* 住処の住所 */
	private String address;
	
	/* 飼い主 */
	private String image;
	
	/* 登録日時 */
	private Timestamp createdAt;
	
	/* 更新日時 */
	private Timestamp updatedAt;
}
