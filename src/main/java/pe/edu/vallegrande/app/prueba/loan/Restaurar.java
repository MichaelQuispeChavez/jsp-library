package pe.edu.vallegrande.app.prueba.loan;

import pe.edu.vallegrande.app.service.CrudLoanService;

public class Restaurar {

	public static void main(String[] args) {
		try {
			CrudLoanService service = new CrudLoanService();
			service.restore("2");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
