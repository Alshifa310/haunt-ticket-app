package ca.sheridancollege.belimal.controllers;



import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.belimal.beans.Ticket;
import ca.sheridancollege.belimal.beans.User;
import ca.sheridancollege.belimal.repository.SecurityRepository;
import ca.sheridancollege.belimal.repository.TicketRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class HomeController {
	
	private TicketRepository ticketRepo;
	private SecurityRepository secRepo;

    @GetMapping("/")
    public String home() {
    	
        return "home.html"; // Home page
    }

    @GetMapping("/add")
    public String addTicketForm(Model model) {
    	List<User> guests = secRepo.getGuests();
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("guests", guests);
        return "add.html"; // Add ticket page
    }

    @PostMapping("/add")
    public String addTicketToDB(@ModelAttribute Ticket ticket) {
        ticketRepo.addTicket(ticket);
        return "redirect:/add"; // Redirect to add page after saving
    }

    @GetMapping("/view")
    public String viewTickets(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    	String username = userDetails.getUsername();
        
        System.out.println("Username: " + username);
    	List<String> roles = new ArrayList<>();
    	for (GrantedAuthority authority : userDetails.getAuthorities()) {
    		roles.add(authority.getAuthority());
    	}
    	System.out.print(roles);
    	
    	List<Ticket> tickets = new ArrayList<>();
    	double subtotal = 0.0;
        double tax = 0.0;
        double total = 0.0;
    	
    	if (roles.contains("ROLE_GUEST")) {
    		 tickets = ticketRepo.getTicketsbyRolename(username); // Fetch guest's tickets
    	        for (Ticket ticket : tickets) {
    	            subtotal += ticket.getPrice(); // Assuming getPrice() returns the price of each ticket
    	        }
    	        tax = subtotal * 0.13;  // 13% tax
    	        total = subtotal + tax; // Subtotal + Tax = Total

    	        // Add ticket list and calculations to the model
    	        model.addAttribute("ticketList", tickets);
    	        model.addAttribute("subtotal", subtotal);
    	        model.addAttribute("tax", tax);
    	        model.addAttribute("total", total);
    	} else {
    		 tickets = ticketRepo.getTickets(); // For other roles, show all tickets
    	        model.addAttribute("ticketList", tickets);
    	}
       

//    	model.addAttribute("ticketList", tickets);
        return "view.html"; // View all tickets page
    }

    @GetMapping("/edit/{id}")
    public String editTicketForm(@PathVariable int id, Model model) {
        Ticket ticket = ticketRepo.getTicketById(id);
        
            model.addAttribute("ticket", ticket);
        
        return "edit.html"; // Edit ticket page
    }

    @PostMapping("/edit")
    public String editTicket(@ModelAttribute Ticket ticket) {
        ticketRepo.editTicket(ticket);
        return "redirect:/view"; // Redirect to view page after updating
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable int id) {
        ticketRepo.deleteTicket(id);
        return "redirect:/view"; // Redirect to view page after deletion
    }


	
}