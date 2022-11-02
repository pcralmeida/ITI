package pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import pt.ulisboa.tecnico.rnl.dei.deiwed.main.dto.SessionDto;

@Entity
@Table(name = "sessions")
public class Session {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "date", nullable = false)
	private String date;

	@Column(name = "speaker", nullable = false)
	private String speaker;

	@Column(name = "topic", nullable = false)
	private String topic;


	// TODO: maybe add more fields? ...or maybe not? what makes sense here?

	protected Session() {
	}

	public Session(String date, String speaker, String topic) {
		this.date= date;
        this.speaker = speaker;
        this.topic = topic;
	}

	public Session(SessionDto sessionDto) {
		this(sessionDto.getDate(), sessionDto.getSpeaker(),
			sessionDto.getTopic());
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpeaker() {
        return this.speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
 

	@Override
	public String toString() {
		return String.format(
				"Session[id=%d, date='%s', speaker='%s', topic='%s']", id, date, speaker, topic);
	}
}