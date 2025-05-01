package ca.sheridancollege.belimal.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.belimal.beans.Ticket;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class TicketRepository {
	private NamedParameterJdbcTemplate jdbc;

    // Add a ticket to the database
    public void addTicket(Ticket ticket) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        String query = "INSERT INTO tickets (name, price, phonenum, email, age, progday) "
                     + "VALUES (:name, :price, :phone, :email, :age, :progday)";

        params.addValue("name", ticket.getName());
        params.addValue("price", ticket.getPrice());
        params.addValue("phone", ticket.getPhonenum());
        params.addValue("email", ticket.getEmail());
        params.addValue("age", ticket.getAge());
        params.addValue("progday", ticket.getProgday());

        jdbc.update(query, params);
    }

    // Get all tickets from the database
    public ArrayList<Ticket> getTickets() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String query = "SELECT * FROM tickets";
        ArrayList<Ticket> ticket = (ArrayList<Ticket>) jdbc.query(query, params, new BeanPropertyRowMapper<>(Ticket.class));
        return ticket;
    }

    // Get a ticket by ID
    public Ticket getTicketById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String query = "SELECT * FROM tickets WHERE id=:id";
        params.addValue("id", id);

        ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc.query(query, params, new BeanPropertyRowMapper<>(Ticket.class));

        if (tickets.size() > 0) {
            return tickets.get(0);
        } else {
            return null;
        }
    }

    // Update a ticket
    public void editTicket(Ticket ticket) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        String query = "UPDATE tickets SET name=:name, price=:price, phonenum=:phone, email=:email, age=:age, progday=:progday WHERE id=:id";

        params.addValue("id", ticket.getId());
        params.addValue("name", ticket.getName());
        params.addValue("price", ticket.getPrice());
        params.addValue("phone", ticket.getPhonenum());
        params.addValue("email", ticket.getEmail());
        params.addValue("age", ticket.getAge());
        params.addValue("progday", ticket.getProgday());

        jdbc.update(query, params);
    }

    // Delete a ticket by ID
    public void deleteTicket(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String query = "DELETE FROM tickets WHERE id=:id";
        params.addValue("id", id);

        jdbc.update(query, params);
    }

    // Get statistics: Total tickets
    public long getTotalTickets() {
        String query = "SELECT COUNT(*) FROM tickets";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbc.queryForObject(query, params, Long.class);
    }
    
    
    public ArrayList<Ticket> getTicketsbyRolename(String uname) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String query = "SELECT * FROM tickets where name = :name";
        params.addValue("name", uname);
        ArrayList<Ticket> ticket = (ArrayList<Ticket>) jdbc.query(query, params, new BeanPropertyRowMapper<>(Ticket.class));
        System.out.print(ticket);
        return ticket;
    }

	
}
