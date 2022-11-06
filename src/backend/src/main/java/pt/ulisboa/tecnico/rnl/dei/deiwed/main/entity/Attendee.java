package pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import pt.ulisboa.tecnico.rnl.dei.deiwed.main.dto.AttendeeDto;

@Entity
@Table(name = "attendees")
public class Attendee {

	public enum ATTENDEE_TYPE {
		TEACHER, GRANTEE
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "ist_id", nullable = false)
	private String istId;

	@Column(name = "type", nullable = false)
	private ATTENDEE_TYPE type;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "vegetarian", nullable = false)
	private boolean vegetarian;

	@Column(name = "intolerances", nullable = false)
	private String intolerances;

	@Column(name = "observations", nullable = false)
	private String observations;

	protected Attendee() {
	}

	public Attendee(String name, String istId, ATTENDEE_TYPE type, String email, boolean vegetarian, String intolerances, String observations) {
		this.name = name;
		this.istId = istId;
		this.type = type;
		this.email = email;
		this.vegetarian = vegetarian;
		this.intolerances = intolerances;
		this.observations = observations;
	}

	public Attendee(AttendeeDto attendeeDto) {
		this(attendeeDto.getName(), attendeeDto.getIstId(),
				ATTENDEE_TYPE.valueOf(attendeeDto.getType().toUpperCase()), attendeeDto.getEmail(), attendeeDto.isVegetarian(), attendeeDto.getIntolerances(), attendeeDto.getObservations());
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIstId() {
		return this.istId;
	}

	public void setIstId(String istId) {
		this.istId = istId;
	}

	public ATTENDEE_TYPE getType() {
		return this.type;
	}

	public void setType(ATTENDEE_TYPE type) {
		this.type = type;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVegetarian() {
		return this.vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public String getIntolerances() {
		return this.intolerances;
	}

	public void setIntolerances(String intolerances) {
		this.intolerances = intolerances;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Override
	public String toString() {
		return String.format(
				"Attendee[id=%d, name='%s', istId='%s', type='%s', email='%s', vegetarian='%s', intolerances='%s', observations='%s']", id, name, istId, type, email, vegetarian, intolerances, observations);
	}
}
