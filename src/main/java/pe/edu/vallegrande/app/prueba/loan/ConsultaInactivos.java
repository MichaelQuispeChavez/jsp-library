package pe.edu.vallegrande.app.prueba.loan;

import java.util.List;

import pe.edu.vallegrande.app.model.Loan;
import pe.edu.vallegrande.app.service.CrudLoanService;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			CrudLoanService service = new CrudLoanService();
			List<Loan> lista = service.getInactive();
			for (Loan loan : lista) {
				System.out.println(loan);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
