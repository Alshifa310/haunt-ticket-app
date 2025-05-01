package ca.sheridancollege.belimal.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	private int id;
	private String name;
	private double price;
	private String phonenum;
	private String email;
	private int age;
	private String progday;
	
	private String[] progdays = {"Weekdays 5PM - 10 PM","Friday 6PM - 12AM", "Weekends 10AM - 12 PM"};
	
}