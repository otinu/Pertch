package otinu.pf.pertch.form;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OwnerForm {
	
	@Id
	@Column(name = "id")
	@NotBlank
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "contact")
	private String contact;

	@Column(name = "created_at")
	private Date created_at;
	
	@Column(name = "updated_at")
	private Date updated_at;

}
