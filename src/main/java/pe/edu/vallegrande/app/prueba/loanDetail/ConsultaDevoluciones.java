package pe.edu.vallegrande.app.prueba.loanDetail;

import java.util.List;

import pe.edu.vallegrande.app.model.LoanDetail;
import pe.edu.vallegrande.app.service.CrudLoanDetailService;

public class ConsultaDevoluciones {

	public static void main(String[] args) {
		try {
			CrudLoanDetailService service = new CrudLoanDetailService();
			List<LoanDetail> lista = service.getInactive();
			for(LoanDetail loanDetail : lista) {
				System.out.println(loanDetail);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
