package pe.edu.vallegrande.app.model;

public class Reservation {

	private Integer identifier, amount;
	private String dates, user_identifier, book_identifier, active;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(Integer identifier, String dates, String user_identifier,
			String book_identifier, Integer amount, String active) {
		super();
		this.user_identifier = user_identifier;
		this.dates = dates;
		this.identifier = identifier;
		this.book_identifier = book_identifier;
		this.amount = amount;
		this.active = active;
	}

	public Reservation(String dates, String user_identifier,
			String book_identifier, Integer amount, String active) {
		super();
		this.dates = dates;
		this.user_identifier = user_identifier;
		this.book_identifier = book_identifier;
		this.amount = amount;
		this.active = active;
	}

	public Reservation(Integer identifier, String dates,
			String user_identifier, String book_identifier, Integer amount) {
		super();
		this.identifier = identifier;
		this.dates = dates;
		this.user_identifier = user_identifier;
		this.book_identifier = book_identifier;
		this.amount = amount;
	}

	public Reservation(String dates, String user_identifier, String book_identifier, Integer amount) {
		super();
		this.dates = dates;
		this.user_identifier = user_identifier;
		this.book_identifier = book_identifier;
		this.amount = amount;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getUser_identifier() {
		return user_identifier;
	}

	public void setUser_identifier(String user_identifier) {
		this.user_identifier = user_identifier;
	}

	public String getBook_identifier() {
		return book_identifier;
	}

	public void setBook_identifier(String book_identifier) {
		this.book_identifier = book_identifier;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		String data = "[ID: " + this.identifier;
		data += ", Fecha: " + this.dates;
		data += ", Usuario: " + this.user_identifier;
		data += ", Libro: " + this.book_identifier;
		data += ", Cantidad: " + this.amount;
		data += ", Estado: " + this.active + "]";
		return data;
	}

}
