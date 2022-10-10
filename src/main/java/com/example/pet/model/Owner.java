package com.example.pet.model;

import java.sql.Date;

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
@Table(name = "owner")
//@ToString(exclude = "petList")
public class Owner {

	@Id
	@Column(name = "id")
	private Integer id;
	
	// @Column(unique = true)
	@Column(name = "email")
	private String email;		// ログインにも利用。代わりにsub_contactは削除
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "message")
	private String message;		// 飼い主からの発信に一定の自由度をもたせたい
	
	@Column(name = "contact")
	private String contact;		// Petから持ち越し

	@Column(name = "created_at")
	private Date created_at;
	
	@Column(name = "updated_at")
	private Date updated_at;
	
	// List<String> roleList; // ログイン機能(ロール設定用)
	
	/*

	// mappedByには、1対N の1側を指定
	// cascadeには、1対N の1側の変更(更新や削除など)に伴って、N側も同様の処理を連動させるかを指定
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Pet> petList;
	
	*/
}
