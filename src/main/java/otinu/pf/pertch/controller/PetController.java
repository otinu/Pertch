package otinu.pf.pertch.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.form.PetForm;
import otinu.pf.pertch.service.PetService;

@Controller
@RequestMapping("pet")
public class PetController {

	@Autowired
	PetService service;
	
	@ModelAttribute
	public PetForm setUpForm() {
		PetForm form = new PetForm();
		return form;
	}
	
	@GetMapping("/index")
	public ModelAndView showIndex(PetForm petForm) {
		ModelAndView mv = new ModelAndView("pet/index");   
		Iterable<Pet> list = service.selectAll();
	    mv.addObject("list", list); 
	    mv.addObject("pet", new Pet());	// リレーション付きPetを作成するための準備
	    return mv;  
	}
	
	@PostMapping("/insert")
	public ModelAndView registerPet(@Validated PetForm petForm, BindingResult bindingResult, ModelAndView mv, 
								@RequestParam("upload_file") MultipartFile multipartFile, 
								RedirectAttributes redirectAttributes){
		
		Pet pet = new Pet();
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setCharmPoint(petForm.getCharmPoint());
		pet.setPostCord(petForm.getPostCord());
		pet.setAddress(petForm.getAddress());
		
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		pet.setCreatedAt(timeStamp);
		pet.setUpdatedAt(timeStamp);
		
		ModelAndView model = new ModelAndView("redirect:index"); 
		try {
			String base64 = new String(Base64.encodeBase64(multipartFile.getBytes()),"ASCII");
			String imageType = multipartFile.getContentType();
			if(imageType.equals("image/png")) {
				pet.setImage("data:image/png;base64," + base64);
			} else if(imageType.equals("image/jpeg")) {
				pet.setImage("data:image/jpeg," + base64);
			} else if(imageType.equals("image/gif")) {
				pet.setImage("data:image/gif;base64," + base64);
			}
			
			if (!bindingResult.hasErrors()) {
				service.insertPet(pet);
				redirectAttributes.addFlashAttribute("insertMessage", "登録が完了しました");
				return model;
			} else {
				// System.out.println(petForm.getImage());
				System.out.println(bindingResult.getFieldError().getDefaultMessage());
				redirectAttributes.addFlashAttribute("insertMessage", "登録に失敗しました");
				return model;
			}
		} catch (IOException e) {
			System.out.println("イメージデータのエンコーディング時に問題が発生しました。");
			e.printStackTrace();
			return model;
		}
	}
	
	@GetMapping("/edit/{id}")
	public String editPet(PetForm petForm,@PathVariable Integer id, Model model) {
		Optional<Pet> petOpt = service.selectById(id);
		Optional<PetForm> petFormOpt = petOpt.map(t -> makePetForm(t));
		/*
		 * ↑ここで、ラムダとmakePetForm()を使って、PetをPetFormに詰めなおしている
		 *  ①この方法で詰め替えをするのは、Lombokのゲッター・セッター自動生成のような便利な機能がないため
		 *  　⇒一つひとつ人力で詰め替える処理を記述する必要がある
		 *  
		 *  ②ここの処理では最終的にPetFormを渡したい。理由はバリデーションを使いたいから。
		 *  　「model.addAttribute("petForm", pet)」のように渡すと画面遷移時にエラーになってしまう。
		 *  　⇔「なら、Pet.javaにもバリデーション用のアノテーションを付ければいい」という考えもある。
		 *  　　　その場合、そもそもModel用とForm用でファイルを分ける意味がなくなってしまい、実用的な実装でなくなってしまう。
		 */

		if (petFormOpt.isPresent()) {
			petForm = petFormOpt.get();
			model.addAttribute("petForm", petForm);
		} else {
			model.addAttribute("selectMessage", "該当のデータが見つかりませんでした");
		}
		return "pet/edit";
	}
	
	private PetForm makePetForm(Pet pet) {
		PetForm form = new PetForm();
		form.setId(pet.getId());
		form.setName(pet.getName());
		form.setAge(pet.getAge());
		form.setSex(pet.getSex());
		form.setCharmPoint(pet.getCharmPoint());
		form.setPostCord(pet.getPostCord());
		form.setAddress(pet.getAddress());
		form.setImage(pet.getImage());
		form.setCreatedAt(pet.getCreatedAt());
		form.setUpdatedAt(pet.getUpdatedAt());
		return form;
	}
	
	@PostMapping("/update")
	public String update(@Validated PetForm petForm, BindingResult bindingResult, 
						Model model, RedirectAttributes redirectAttributes) {
		
		Pet pet = makePet(petForm);
		if(!bindingResult.hasErrors()) {
			service.updatePet(pet);
			redirectAttributes.addFlashAttribute("updateMessage", "更新が完了しました");
		} else {
			System.out.println(bindingResult.getFieldError().getDefaultMessage());
			redirectAttributes.addFlashAttribute("updateMessage", "更新に失敗しました");
		}
		return "redirect:/pet/index";
	}
	
	private Pet makePet(PetForm petForm) {
		Pet pet = new Pet();
		pet.setId(petForm.getId());
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		return pet;
	}
	
	@PostMapping("/delete")	
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		service.deleteById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("deleteMessage", "削除が完了しました");
		return "redirect:/pet/index";
	}
}









