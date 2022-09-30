package com.example.pet.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private Integer id;
	private String name;
	private String message;		// 飼い主からの発信に一定の自由度をもたせたい
	private String contact;		// Petから持ち越し
	private String sub_contact; // 	   〃
	
	private Date created_at;
	private Date updated_at;
}
