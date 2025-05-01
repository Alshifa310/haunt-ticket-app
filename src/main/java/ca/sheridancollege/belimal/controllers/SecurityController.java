package ca.sheridancollege.belimal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.belimal.beans.User;
import ca.sheridancollege.belimal.repository.SecurityRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class SecurityController {
	private SecurityRepository secRepo;
	
	@GetMapping("/login")
    public String login() {
        return "login.html"; 
    }
	
	@GetMapping("/access-denied")
    public String denied() {
        return "access-denied.html"; 
    }
	@GetMapping("/register")
    public String register() {
        return "registration.html"; 
    }
	
	@PostMapping("/register")
    public String doregister(@RequestParam String username, @RequestParam String password) {
		
		secRepo.addUser(username, password);
		
		User user = secRepo.findUserAccount(username);
		secRepo.addRole(user.getUserID(), 1 );
        return "redirect:/"; 
    }
	
	
	
}
