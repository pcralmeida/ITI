package pt.ulisboa.tecnico.rnl.dei.deiwed.main.dto;

import java.io.Serializable;

import pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity.Attendee;

// Data Transfer Object, to communicate with frontend
public class AttendeeDto implements Serializable {
	private long id;
	private String name;
	private String istId;
	private String type;
	private String email;
	private boolean vegetarian;
	private String intolerances;
	private String observations;

	public AttendeeDto() {
	}

	public AttendeeDto(long id, String name, String istId, String type, String email, boolean vegetarian, String intolerances, String observations) {
		this.id = id;
		this.name = name;
		this.istId = istId;
		this.type = type;
		this.email = email;
		this.vegetarian = vegetarian;
	}

	public AttendeeDto(Attendee attendee) {
		this(attendee.getId(), attendee.getName(), attendee.getIstId(),
				attendee.getType().toString(), attendee.getEmail(), attendee.isVegetarian(), attendee.getIntolerances(), attendee.getObservations());
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
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
				"AttendeeDto[id=%d, name='%s', istId='%s', type='%s', email='%s', vegetarian='%s', intolerances='%s', observations='%s']", id, name, istId, type, email, vegetarian, intolerances, observations);
	}
}
