package otinu.pf.pertch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import otinu.pf.pertch.form.OwnerForm;
import otinu.pf.pertch.service.OwnerService;

@Controller
public class OwnerController {
	
	@Autowired
	private OwnerService ownerRegistrationService;
	
	@GetMapping("/top")
	public String redirectPetIndex() {
		return "top";
	}
	
	@GetMapping({"", "/"})
	public String redirectRoot() {
		return "top";
	}
	
	@GetMapping("/login")
	public String redirectTop() {
		return "redirect:/top";
	}
	
	@GetMapping("/loginForm")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/registration")
	public ModelAndView  showRegistration() {
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("ownerForm", new OwnerForm());
		mv.addObject("errorMessages", "");
		return mv;

	}
		
	@PostMapping("/registration")
	public ModelAndView ownerRegistration(@Validated OwnerForm form, 
									BindingResult bindingResult) {
		ownerRegistrationService.ownerRegistration(form.getUsername(), form.getPassword(), form.getName(), form.getMessage(), form.getContact());
		ModelAndView mv = new ModelAndView("redirect:/pet/index");
		if (!bindingResult.hasErrors()) {
			mv.addObject("errorMessages", "");
			return mv;
		} else {
			String errorMessages = "";
			for(FieldError error: bindingResult.getFieldErrors()) {
				errorMessages += error.getDefaultMessage() + "\n";
			}
			mv.addObject("errorMessages", errorMessages);
			mv.setViewName("registration");
			return mv;
		}
		
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
