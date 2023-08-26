package pe.edu.vallegrande.app.model;

import java.sql.Date;

public class Loan {

	private Integer identifier;
	private String user_identifier;
	private String book_identifier;
	private String amount;
	private Date start_dates;
	private String return_date;
	private String active;
	
	public Loan() {
	}

	public Loan(Integer identifier, String user_identifier, String book_identifier, String amount, Date start_dates,
			String return_date, String active) {
		super();
		this.identifier = identifier;
		this.user_identifier = user_identifier;
		this.book_identifier = book_identifier;
		this.amount = amount;
		this.start_dates = start_dates;
		this.return_date = return_date;
		this.active = active;
	}

	public Loan(String user_identifier, String book_identifier, String amount, String return_date) {
		super();
		this.user_identifier = user_identifier;
		this.book_identifier = book_identifier;
		this.amount = amount;
		this.return_date = return_date;
	}

	public Loan(Integer identifier, String user_identifier, String book_identifier, String amount, String return_date) {
		super();
		this.identifier = identifier;
		this.user_identifier = user_identifier;
		this.book_identifier = book_identifier;
		this.amount = amount;
		this.return_date = return_date;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getStart_dates() {
		return start_dates;
	}

	public void setStart_dates(Date start_dates) {
		this.start_dates = start_dates;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Loan [identifier=" + identifier + ", user_identifier=" + user_identifier + ", book_identifier="
				+ book_identifier + ", amount=" + amount + ", start_dates=" + start_dates + ", return_date="
				+ return_date + ", active=" + active + "]";
	}
	
	
}
