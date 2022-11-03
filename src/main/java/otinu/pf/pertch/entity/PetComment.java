package otinu.pf.pertch.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet_comment")
public class PetComment {

	/* ID */
	@Id
	@Column(name = "id")
	private Integer id;
	
	/* ペットID */
	@Column(name = "pet_id")
	private Integer pet_id;
	
	/* 本文 */
	@Column(name = "text")
	private String text;
	
	/* 登録日時 */
	@Column(name = "created_at")
	private Timestamp created_at;
	
	/* 更新日時 */
	@Column(name = "updated_at")
	private Timestamp updated_at;
}
