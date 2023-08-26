package pe.edu.vallegrande.app.prueba.loan;

import java.util.List;

import pe.edu.vallegrande.app.model.Loan;
import pe.edu.vallegrande.app.service.CrudLoanService;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			Loan bean = new Loan();
			bean.setUser_identifier("");
			bean.setBook_identifier("");
			CrudLoanService service = new CrudLoanService();
			List<Loan> lista = service.get(bean);
			for (Loan loan : lista) {
				System.out.println(loan);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
