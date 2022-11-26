package otinu.pf.pertch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/* 未ログイン時、このパターンは「/login」に変換されてる
	@GetMapping({"", "/"})
	public String redirectRoot() {
		return "top";
	}
	*/
	
	@GetMapping("/login")
	public String redirectTop() {
		return "redirect:/top";
	}
	
	@GetMapping("/login/error")
	public String loginFailure(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("signUpMessage","ログインに失敗しました");
		return "redirect:/loginForm";
	}
	
	@GetMapping("/loginForm")
	public ModelAndView showLogin(@ModelAttribute("signUpMessage") String signUpMessage) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("signUpMessage", signUpMessage);
		return mv;
	}
	
	@GetMapping("/registration")
	public ModelAndView  showRegistration() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("registration");
		mv.addObject("ownerForm", new OwnerForm());
		mv.addObject("errorMessages", "");
		return mv;

	}
		
	@PostMapping("/registration")
	public ModelAndView ownerRegistration(@Validated OwnerForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ownerRegistrationService.ownerRegistration(form.getUsername(), form.getPassword(), form.getName(), form.getMessage(), form.getContact());
		ModelAndView mv = new ModelAndView("redirect:/loginForm");
		// ModelAndView mv = new ModelAndView("redirect:/pet/index");
		if (!bindingResult.hasErrors()) {
			// mv.addObject("errorMessages", "");
			// mv.addObject("signUpMessage","登録に成功しました");
			redirectAttributes.addFlashAttribute("signUpMessage","登録に成功しました");
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
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("/owner/show");
		return mv;
	}
	
	@GetMapping("/owner/mypage")
	public ModelAndView ownerMyPage() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("/owner/mypage");
		return mv;
	}

}
