package pe.edu.vallegrande.app.model;

public class Author {

	private Integer identifier;
	private String names, last_name, nacionality, active;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(Integer identifier, String names, String last_name, String nacionality, String active) {
		super();
		this.identifier = identifier;
		this.names = names;
		this.last_name = last_name;
		this.nacionality = nacionality;
		this.active = active;
	}

	public Author(String names, String last_name, String nacionality, String active) {
		super();
		this.names = names;
		this.last_name = last_name;
		this.nacionality = nacionality;
		this.active = active;
	}

	public Author(Integer identifier, String names, String last_name, String nacionality) {
		super();
		this.identifier = identifier;
		this.names = names;
		this.last_name = last_name;
		this.nacionality = nacionality;
	}

	public Author(String names, String last_name, String nacionality) {
		super();
		this.names = names;
		this.last_name = last_name;
		this.nacionality = nacionality;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		String data = "[" + this.identifier;
		data += ", " + this.names;
		data += ", " + this.last_name;
		data += ", " + this.nacionality;
		data += ", " + this.active + "]";
		return data;
	}
}
