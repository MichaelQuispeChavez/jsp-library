package pe.edu.vallegrande.app.model;

public class LoanDetail {

	private Integer identifier;
	private Integer amount;
	private String loan_identifier;
	private String book_identifier;
	private String active;

	public LoanDetail() {
	}

	public LoanDetail(Integer identifier, Integer amount, String loan_identifier, String book_identifier,
			String active) {
		super();
		this.identifier = identifier;
		this.amount = amount;
		this.loan_identifier = loan_identifier;
		this.book_identifier = book_identifier;
		this.active = active;
	}

	public LoanDetail(Integer identifier, Integer amount, String loan_identifier, String book_identifier) {
		super();
		this.identifier = identifier;
		this.amount = amount;
		this.loan_identifier = loan_identifier;
		this.book_identifier = book_identifier;
	}

	public LoanDetail(Integer amount, String loan_identifier, String book_identifier) {
		super();
		this.amount = amount;
		this.loan_identifier = loan_identifier;
		this.book_identifier = book_identifier;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getLoan_identifier() {
		return loan_identifier;
	}

	public void setLoan_identifier(String loan_identifier) {
		this.loan_identifier = loan_identifier;
	}

	public String getBook_identifier() {
		return book_identifier;
	}

	public void setBook_identifier(String book_identifier) {
		this.book_identifier = book_identifier;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "LoanDetail [identifier=" + identifier + ", amount=" + amount + ", loan_identifier=" + loan_identifier
				+ ", book_identifier=" + book_identifier + ", active=" + active + "]";
	}

}
