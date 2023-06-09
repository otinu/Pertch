package otinu.pf.pertch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PertchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PertchApplication.class, args);
		//.getBean(PertchApplication.class).insert();
	}
	
	/*
	@Autowired
	PetService service;
	
	private void insert() {
		Pet pet = new Pet(null, "otinu", 3, false, "763-0093", "香川県丸亀市", "コメント_テストです", "080-1987-9009", "https://twitter.com/test/aiuw334hofhqrqhkjqh31hs",Date.valueOf("2022-09-21"), Date.valueOf("2022-09-21"));
		service.insertPet(pet);
	}
	*/

}