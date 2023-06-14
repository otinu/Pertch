package otinu.pf.pertch.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetComment {

	/* ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/* ペットID */
	@ManyToOne(optional = true)
	@JoinColumn(name = "pet_id")
	private Pet pet;

	/* 時間 */
	@Column(name = "event_time")
	private Date eventTime;

	/* 場所 */
	@Column(name = "event_place")
	private String eventPlace;

	/* 情報 */
	@Column(name = "event_information")
	private String eventInformation;

	/* 登録日時 */
	@Column(name = "created_at")
	private Date createdAt;

	/* 更新日時 */
	@Column(name = "updated_at")
	private Date updatedAt;

}
