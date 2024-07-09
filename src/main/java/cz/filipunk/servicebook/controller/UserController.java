package cz.filipunk.servicebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cz.filipunk.servicebook.entity.Auta;
import cz.filipunk.servicebook.entity.Users;
import cz.filipunk.servicebook.service.AutoService;
import cz.filipunk.servicebook.service.UserService;

@Controller
public class UserController {

	@Autowired
	private AutoService auta_service;

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new Users());
		return "register";
	}

	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute Users user) {
		userService.save(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/account")
	public String showAccountSettings(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
			model.addAttribute("currentUser", auth.getName());
		} else {
			model.addAttribute("currentUser", null);
		}

		String username = auth.getName();
		Users user = userService.findByUsername(username);
		List<Auta> cars = auta_service.findByUzivatelId(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("cars", cars);
		model.addAttribute("newCar", new Auta());
		return "account";
	}

	@PostMapping("/update-account")
	public String updateAccount(@ModelAttribute("user") Users user, Model model) {
		if (user != null) {
			userService.updateUser(user);
			return "redirect:/showDialog";
		}else
		{
			System.out.println("User je NULL");
			return "redirect:/";
		}
		
	}

	@PostMapping("/add-car")
	public String addCar(@ModelAttribute("newCar") Auta newCar) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users user = userService.findByUsername(username);
		newCar.setUzivatel(user);
		auta_service.save(newCar);
		return "redirect:/account";
	}
	
    @GetMapping("/showDialog")
    public String showDialog() {
        return "dialog";
    }
}
