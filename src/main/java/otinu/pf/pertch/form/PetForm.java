package otinu.pf.pertch.form;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetForm {

	private Integer id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private Integer age; 
	
	@NotNull
	private Boolean sex;
	
	private String post_cord;
	private String address;
	private String comment;
	
	@NotBlank
	private String contact;
	
	private String sub_contact;
	private String image;
	private Date created_at;
	private Date updated_at;
}
