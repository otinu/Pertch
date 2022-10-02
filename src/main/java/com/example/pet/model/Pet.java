package com.example.pet.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class Pet { //★
	
	/* ★
	 * Java16以上なら、「class」ではなく、「record」が使えるなら便利なため活用していくべき。
	 * ただし、今回は下記の理由からrecordは使わない。
	 * ①現状はrecordとJPAなどを組み合わせる方法が双方で用意されていない。
	 * ②recordはgetter()やtoString()などを自動生成してくれるが、setter()は用意されない
	 * 　⇒Spring使うなら現状はlombok活用の方が情報量多い
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // これを付けるカラムSerialにする必要がある
	@Column(name = "id")
	private Integer id;
	
	/*
	 *  User has many Pet にするための外部キー
	 *  外部キー制約をもたせたい
	 *  ⇒UserId 1番を「no_owner」などに設定し、目撃情報を投稿する際はno_ownerと結びつける
	 *  　⇒⇒no_ownerのデータ群は「eyewitness report」テーブルと見なして扱いたい
	 */
	//private Integer user_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "sex")
	private Boolean sex;
	
	@Column(name = "charm_point")
	private String charm_point; // 目視でないと確認しにくい情報などを共有できるようにする
	
	@Column(name = "post_cord") // おばあちゃんの犬を別居の娘が代わりに探している場合など、Userに郵便番号もたせると不便になりそう
	private String post_cord;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "created_at")
	private Date created_at;
	
	@Column(name = "updated_at")
	private Date updated_at;
}
