package com.example.pet.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class Pet {

	@Id
	private Integer id;
	
	/*
	 *  User has many Pet にするための外部キー
	 *  外部キー制約をもたせたい
	 *  ⇒UserId 1番を「no_owner」などに設定し、目撃情報を投稿する際はno_ownerと結びつける
	 *  
	 */
	//private Integer user_id;
	
	/* Data JPA に切り替えるか検討
	@ManyToOne
	@JoinColumn(name = "user_id")
	*/
	private User user;
	
	private String name;
	private Integer age;
	private Boolean sex;
	//private String charm_point; // 目視でないと確認しにくい情報などを共有できるようにする
	private String post_cord;
	private String address;
	private String comment;		// コメントはPet has many Commentのエンティティに修正したい
	private String contact;		// コンタクト・サブコンタクトはUserテーブルへ移行
	private String sub_contact; // 			〃
	private String image;
	private Date created_at;
	private Date updated_at;
}
