package otinu.pf.pertch.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

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

import otinu.pf.pertch.Constant;
import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.form.OwnerForm;
import otinu.pf.pertch.service.OwnerService;

@Controller
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@GetMapping("/top")
	public String redirectPetIndex() {
		return "top";
	}

	@GetMapping("/login")
	public String redirectTop() {
		return "redirect:/top";
	}

	@GetMapping("/login/error")
	public String loginFailure(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("signUpMessage", Constant.LOGIN_ERROR);
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
	public ModelAndView showRegistration() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("registration");
		mv.addObject("ownerForm", new OwnerForm());
		mv.addObject("errorMessages", "");
		return mv;

	}

	@PostMapping("/registration")
	public ModelAndView ownerRegistration(@Valid OwnerForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("registration");
		String errorMessages = "";

		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessages += error.getDefaultMessage() + "\n";
			}
			mv.addObject("errorMessages", errorMessages);
			return mv;
		}

		if (ownerService.countRecordByUserName(form.getUsername()) > 0) {
			mv.addObject("errorMessages", Constant.MAIL_ADDRESS_OVERLAPPING_ERROR);
			return mv;
		}

		ownerService.insertOwner(form.getUsername(), form.getPassword(), form.getOwnerName(), form.getMessage(),
				form.getContact());
		redirectAttributes.addFlashAttribute("signUpMessage", Constant.FINISH_USER_REGISTRATION);
		return mv;
	}

	@GetMapping("/owner/show/{id}")
	public ModelAndView ownerShow(OwnerForm ownerForm, @PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		Owner owner = ownerService.findByOwnerId(id);
		mv.addObject("owner", owner);
		mv.setViewName("/owner/show");
		return mv;
	}

	@GetMapping("/owner/mypage")
	public ModelAndView ownerMyPage(OwnerForm ownerForm, Principal principal, RedirectAttributes redirectAttributes) {
		Owner currentUser = ownerService.getCurrentUser(principal);
		Optional<Owner> ownerOpt = Optional.of(ownerService.findByOwnerId(currentUser.getId()));
		Optional<OwnerForm> ownerFormOpt = ownerOpt.map(t -> ownerService.makeOwnerForm(t));

		ModelAndView mv = new ModelAndView();
		if (ownerFormOpt.isPresent()) {
			ownerForm = ownerFormOpt.get();
			mv.addObject("ownerId", currentUser.getId());
			mv.addObject("ownerForm", ownerForm);
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", Constant.MYPAGE_MOVE_ERROR);
			mv.setViewName("/pet/inex");
			return mv;
		}

		mv.setViewName("owner/mypage");
		return mv;
	}

	@PostMapping("/owner/update")
	public ModelAndView ownerUpdate(@Validated OwnerForm ownerForm, BindingResult bindingResult, Principal principal,
			RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();
		Owner currentUser = ownerService.getCurrentUser(principal);
		Owner owner = ownerService.makeOwner(ownerForm, currentUser);
		String errorMessages = "";

		// OwnerFormのメールアドレスなどは必ずバリデーションにかかるため、エラーを選別する
		for (FieldError error : bindingResult.getFieldErrors()) {
			if ("message".equals(error.getField()) || "contact".equals(error.getField())
					|| "subContact".equals(error.getField())) {
				errorMessages += error.getDefaultMessage() + "\n";
			}
		}
		
		if(!errorMessages.isEmpty()) {
			mv.setViewName("owner/mypage");
			mv.addObject("errorMessages", errorMessages);
			return mv;
		}
		
		ownerService.updateOwner(owner);
		redirectAttributes.addFlashAttribute("errorMessage", Constant.FINISH_MYPAGE_UPDATE);

		mv.addObject("owner", owner);
		mv.setViewName("owner/show");
		return mv;
	}

}
