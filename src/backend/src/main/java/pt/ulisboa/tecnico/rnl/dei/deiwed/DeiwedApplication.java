package pt.ulisboa.tecnico.rnl.dei.deiwed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pt.ulisboa.tecnico.rnl.dei.deiwed.main.repository.AttendeeRepository;
import pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity.Attendee;;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class DeiwedApplication implements CommandLineRunner {

	@Autowired
	AttendeeRepository attendeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DeiwedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Attendee attendee = new Attendee("João", "ist199832", Attendee.ATTENDEE_TYPE.TEACHER, "joao@email.com", false, "none", "none"); 
		attendeeRepository.save(attendee); //FIXME: clean when done
	}

}