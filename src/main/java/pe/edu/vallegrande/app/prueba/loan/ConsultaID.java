package pe.edu.vallegrande.app.prueba.loan;

import pe.edu.vallegrande.app.model.Loan;
import pe.edu.vallegrande.app.service.CrudLoanService;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			CrudLoanService service = new CrudLoanService();
			Loan bean = service.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
