package pe.edu.vallegrande.app.prueba.loanDetail;

import pe.edu.vallegrande.app.service.CrudLoanDetailService;

public class Eliminar {

	public static void main(String[] args) {
		try {
			CrudLoanDetailService loanDetailService = new CrudLoanDetailService();
			loanDetailService.deleteGeneral("1", "1");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
