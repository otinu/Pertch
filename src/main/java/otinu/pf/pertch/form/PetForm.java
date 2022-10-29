package otinu.pf.pertch.form;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetForm {
	
	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private Boolean sex;
	
	private String charmPoint;
	
	private String postCord;
	
	private String address;
	
	private String image;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
}
