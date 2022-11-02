package pt.ulisboa.tecnico.rnl.dei.deiwed.main.dto;

import java.io.Serializable;

import pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity.Session;

// Data Transfer Object, to communicate with frontend
public class SessionDto implements Serializable {
	private long id;
	private String date;
	private String speaker;
	private String topic;

	public SessionDto() {
	}

	public SessionDto(long id, String date, String speaker, String topic) {
		this.id = id;
		this.date = date;
        this.speaker = speaker;
        this.topic = topic;
	}

	public SessionDto(Session session) {
		this(session.getId(), session.getDate(), session.getSpeaker(),
            session.getTopic());
	}

	public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
				"SessionDto[id=%d, date='%s', speaker='%s', topic='%s']", id, date, speaker, topic);
	}
}
