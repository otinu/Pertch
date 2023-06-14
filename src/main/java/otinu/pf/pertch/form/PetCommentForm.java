package otinu.pf.pertch.form;

import java.util.Date;

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
	private Date eventTime;

	/* 場所 */
	private String eventPlace;

	/* 情報 */
	private String eventInformation;

	/* 登録日時 */
	private Date createdAt;

	/* 更新日時 */
	private Date updatedAt;

}
