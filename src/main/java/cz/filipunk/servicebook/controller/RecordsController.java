package cz.filipunk.servicebook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cz.filipunk.servicebook.entity.Auta;
import cz.filipunk.servicebook.entity.Opravy;
import cz.filipunk.servicebook.entity.Users;
import cz.filipunk.servicebook.repository.OpravyRepository;
import cz.filipunk.servicebook.service.AutoService;
import cz.filipunk.servicebook.service.OpravaService;
import cz.filipunk.servicebook.service.UserService;

@Controller
public class RecordsController {

	@Autowired
	private AutoService auta_service;

	@Autowired
	private OpravaService opravy_service;

	@Autowired
	private UserService user_service;
	
	@GetMapping("/add-record")
	public String showAddRecordForm(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
			model.addAttribute("currentUser", auth.getName());
		} else {
			model.addAttribute("currentUser", null);
		}
		String username = auth.getName();
		Users user = user_service.findByUsername(username);
		List<Auta> cars = auta_service.findByUzivatelId(user.getId());
		model.addAttribute("cars", cars);
		model.addAttribute("opravy", new Opravy());
		return "add-record";
	}

	@PostMapping("/add-record")
	public String registerSubmit(@ModelAttribute Opravy opravy) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
	        String username = auth.getName();
	        Users user = user_service.findByUsername(username);
	        opravy.setUzivatel(user); // Nastavení uživatele pro opravu
	        opravy_service.save(opravy);
	    }
	    return "redirect:/";
	}

	@GetMapping("/")
	public String showRecords(Model model) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
	        String username = auth.getName();
	        Users user = user_service.findByUsername(username);
	        List<Opravy> allRepairs = opravy_service.findByUzivatelId(user.getId());
	        List<Auta> cars = auta_service.findByUzivatelId(user.getId()); // Získání aut uživatele
	        model.addAttribute("records", allRepairs);
	        model.addAttribute("cars", cars); // Přidání seznamu aut do modelu
	        model.addAttribute("currentUser", username);
	    } else {
	        model.addAttribute("records", new ArrayList<>());
	        model.addAttribute("cars", new ArrayList<>()); // Pokud není uživatel přihlášen, prázdný seznam aut
	        model.addAttribute("currentUser", null);
	    }
	    return "index";
	}
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		opravy_service.deleteById(id);
		return "redirect:/";
	}

}
