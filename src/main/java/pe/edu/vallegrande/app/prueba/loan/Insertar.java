package pe.edu.vallegrande.app.prueba.loan;

import pe.edu.vallegrande.app.model.Loan;
import pe.edu.vallegrande.app.service.CrudLoanService;

public class Insertar {

	public static void main(String[] args) {
		try {
			Loan bean = new Loan("4", "2", "17", "2023-07-05");
			CrudLoanService service = new CrudLoanService();
			service.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
