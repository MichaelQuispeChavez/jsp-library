package pe.edu.vallegrande.app.model;

public class Category {

	private Integer identifier;
	private String names, descriptions, active;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(Integer identifier, String names, String descriptions, String active) {
		super();
		this.identifier = identifier;
		this.names = names;
		this.descriptions = descriptions;
		this.active = active;
	}

	public Category(String names, String descriptions, String active) {
		super();
		this.names = names;
		this.descriptions = descriptions;
		this.active = active;
	}

	public Category(Integer identifier, String names, String descriptions) {
		super();
		this.identifier = identifier;
		this.names = names;
		this.descriptions = descriptions;
	}

	public Category(String names, String descriptions) {
		super();
		this.names = names;
		this.descriptions = descriptions;
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

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
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
		data += ", " + this.descriptions;
		data += ", " + this.active + "]";
		return data;
	}
}
