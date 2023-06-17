package otinu.pf.pertch.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import otinu.pf.pertch.entity.Pet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetCommentForm {
	/* ID */
	private Integer id;

	/* ペットID */
	private Pet pet;

	/* 時間 */
	@NotBlank(message="時間は入力が必要です")
	private String eventTime;

	/* 場所 */
	@Size(max = 80, message = "場所は{max}文字までです")
	@NotBlank(message="場所は入力が必要です")
	private String eventPlace;

	/* 情報 */
	@Size(max = 200, message = "場所は{max}文字までです")
	@NotBlank(message="情報は入力が必要です")
	private String eventInformation;

	/* 登録日時 */
	private Date createdAt;

	/* 更新日時 */
	private Date updatedAt;

}
