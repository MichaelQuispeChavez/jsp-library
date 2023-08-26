package pe.edu.vallegrande.app.model;

public class Librarian {

	private Integer identifier;
	private String names, last_name, document_type, document_number, email, pass, cellphone, active;
	
	public Librarian() {
		// TODO Auto-generated constructor stub
	}

	public Librarian(Integer identifier, String names, String last_name, String document_type, String document_number,
			String email, String pass, String cellphone, String active) {
		super();
		this.identifier = identifier;
		this.names = names;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.email = email;
		this.pass = pass;
		this.cellphone = cellphone;
		this.active = active;
	}

	public Librarian(Integer identifier, String names, String last_name, String document_type, String document_number,
			String email, String pass, String cellphone) {
		super();
		this.identifier = identifier;
		this.names = names;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.email = email;
		this.pass = pass;
		this.cellphone = cellphone;
	}

	public Librarian(String names, String last_name, String document_type, String document_number, String email,
			String pass, String cellphone) {
		super();
		this.names = names;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.email = email;
		this.pass = pass;
		this.cellphone = cellphone;
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

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Librarian [identifier=" + identifier + ", names=" + names + ", last_name=" + last_name
				+ ", document_type=" + document_type + ", document_number=" + document_number + ", email=" + email
				+ ", pass=" + pass + ", cellphone=" + cellphone + ", active=" + active + "]";
	}
}
