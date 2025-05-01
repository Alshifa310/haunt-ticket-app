package ca.sheridancollege.belimal.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.belimal.beans.Ticket;
import ca.sheridancollege.belimal.beans.User;
import lombok.AllArgsConstructor;

@Repository 
@AllArgsConstructor
public class SecurityRepository {
private NamedParameterJdbcTemplate jdbc;
	
	//Import user from beans package
	public User findUserAccount(String userName) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user WHERE userName=:name";
		params.addValue("name", userName);
		ArrayList<User> users = (ArrayList<User>) jdbc.query(query,
		params, new BeanPropertyRowMapper<User>(User.class));
		return (users.size() > 0) ? users.get(0) : null;
		}

	public List<String> getRolesById(long userId) {
		
		MapSqlParameterSource params = new MapSqlParameterSource(); 
		String query = "SELECT user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role WHERE "
				+ "user_role.roleId=sec_role.roleId and userId=:id";
		params.addValue("id", userId);
		ArrayList<String> roles = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, params);
		for (Map<String, Object> row : rows) {
		roles.add((String) row.get("roleName"));
		}
		return roles;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(); 
	}
	
	
	
	public void addUser(String username, String password) {
	
		
			MapSqlParameterSource params = new MapSqlParameterSource();
			
		
			String query = "insert into SEC_User (userName, encryptedPassword, ENABLED)"
					+ "values (:un, :ps , 1)";
		
			params.addValue("un", username);
			params.addValue("ps", passwordEncoder().encode(password));
			
			
			jdbc.update(query, params);
	}

	public void addRole(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId)"
		+ "values (:userId, :roleId);";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);
		}
	
	public List<User> getGuests() {
		MapSqlParameterSource params = new MapSqlParameterSource();
	    String query = "select userName from SEC_USER where userID IN (SELECT userId FROM USER_ROLE WHERE roleId = 1) ORDER BY userName ASC"; 
	    ArrayList<User> user = (ArrayList<User>) jdbc.query(query, params, new BeanPropertyRowMapper<>(User.class));
	    System.out.print(user);
        return user;
	    
	}
	
	
}


