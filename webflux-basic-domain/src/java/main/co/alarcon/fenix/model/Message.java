package co.alarcon.fenix.model;

import java.util.Date;

//@Document(collection = "messages")
public class Message {
	//@Id
	private String id;
	
	//@NotBlank
	private String text;
	
	//@NotNull
	private Date createdAt = new Date();
	
	public Message(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
