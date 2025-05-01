package ca.sheridancollege.belimal.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.belimal.repository.SecurityRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private SecurityRepository secRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Find the user based on their username 
		ca.sheridancollege.belimal.beans.User user = secRepo.findUserAccount(username);
		System.out.println(user);
		
		// if the user does not exist then throw the exception
		if (user == null) {
			System.out.println("User could not be found");
			throw new UsernameNotFoundException("User not found");
			}
		
		//get a list of roles for that user
		List<String> roles = secRepo.getRolesById(user.getUserID());
		
		//change the lit of roles into a list of granted authorities
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (String role: roles) {
			grantList.add(new SimpleGrantedAuthority(role));
		}
		
		//Create a spring user based on the above information.
		//Import user from spring security user
		// import org.springframework.security.core.userdetails.User;
		User springUser = new User(user.getUserName(),
				user.getEncryptedPassword(),grantList);
		
		return (UserDetails)springUser;
	}

}
