package pe.edu.vallegrande.app.prueba.loanDetail;

import pe.edu.vallegrande.app.service.CrudLoanDetailService;

public class Insertar {

	public static void main(String[] args) {
		try {
			CrudLoanDetailService service = new CrudLoanDetailService();
			service.insert(15, "1");
			System.out.println("Se inserto el registro.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
