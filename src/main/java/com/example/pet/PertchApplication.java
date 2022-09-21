package com.example.pet;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.pet.model.Pet;
import com.example.pet.service.PetService;

@SpringBootApplication
public class PertchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PertchApplication.class, args)
		.getBean(PertchApplication.class).execute();
	}
	
	@Autowired
	PetService service;
	
	private void execute() {
		check();
	}
	
	private void check() {
		// DB登録テスト
		Pet pet = new Pet(null, "peco", 3, false, "763-0093", "香川県丸亀市", "コメント_テストです", Date.valueOf("2022-09-21"), Date.valueOf("2022-09-21"), "080-1987-9009", "https://twitter.com/test/aiuw334hofhqrqhkjqh31hs");
		service.registerPet(pet);
	}

}
