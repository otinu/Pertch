package otinu.pf.pertch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import otinu.pf.pertch.form.OwnerForm;
import otinu.pf.pertch.service.OwnerService;

@Controller
public class OwnerController {
	
	@Autowired
	private OwnerService ownerRegistrationService;
	
	@GetMapping("/")
	public String redirectPetIndex() {
		return "redirect:/pet/index";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String showRegistration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String ownerRegistration(@ModelAttribute("ownerform") OwnerForm form) {
		ownerRegistrationService.ownerRegistration(form.getUsername(), form.getPassword(), form.getName(), form.getMessage(), form.getContact());
		return "redirect:/pet/index";
	}
	
	@GetMapping("/owner/show/{id}")
	public ModelAndView ownerShow(OwnerForm ownerForm,@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/owner/show"); 
		return mv;
	}
	
	@GetMapping("/owner/mypage")
	public ModelAndView ownerMyPage() {
		ModelAndView mv = new ModelAndView("/owner/mypage"); 
		return mv;
	}

}
