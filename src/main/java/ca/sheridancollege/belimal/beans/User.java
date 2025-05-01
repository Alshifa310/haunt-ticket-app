package ca.sheridancollege.belimal.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long userID;
	private String userName;
	private String encryptedPassword;
}
