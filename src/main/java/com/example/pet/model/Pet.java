package com.example.pet.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

	@Id
	private Integer id;
	private String name;
	private Integer age;
	private Boolean sex;
	private String post_cord;
	private String address;
	private String comment;
	private String contact;
	private String sub_contact;
	private String image;
	private Date created_at;
	private Date updated_at;
}
