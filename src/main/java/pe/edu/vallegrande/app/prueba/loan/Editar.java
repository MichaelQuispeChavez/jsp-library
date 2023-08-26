package pe.edu.vallegrande.app.prueba.loan;

import pe.edu.vallegrande.app.model.Loan;
import pe.edu.vallegrande.app.service.CrudLoanService;

public class Editar {

	public static void main(String[] args) {
		try {
			Loan bean = new Loan(2,"4","2","25","2023-08-05");
			CrudLoanService service = new CrudLoanService();
			service.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
