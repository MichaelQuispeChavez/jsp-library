package pe.edu.vallegrande.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.vallegrande.app.model.Loan;
import pe.edu.vallegrande.app.model.LoanDetail;
import pe.edu.vallegrande.app.service.CrudLoanDetailService;
import pe.edu.vallegrande.app.service.CrudLoanService;

@WebServlet({ "/LoanDetailProcesar", "/LoanDetailActualizar", "/LoanDetailHistorial", "/", "/LoanDetailID" })
public class LoanDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CrudLoanDetailService service = new CrudLoanDetailService();
	private CrudLoanService loanService = new CrudLoanService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/LoanDetailProcesar":
			procesar(request, response);
			break;
		case "/LoanDetailActualizar":
			actualizar(request, response);
			break;
		case "/LoanDetailHistorial":
			historial(request, response);
			break;
		case "/LoanLista":
			loanActualizar(request, response);
			break;
		case "/LoanDetailID":
			listaId(request, response);
			break;
		default:
			break;
		}
	}

	private void listaId(HttpServletRequest request, HttpServletResponse response) {
		List<LoanDetail> lista = service.getForId();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void loanActualizar(HttpServletRequest request, HttpServletResponse response) {
		List<Loan> lista = loanService.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) {
		List<LoanDetail> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		List<LoanDetail> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		String accion = request.getParameter("accion");
		LoanDetail bean = new LoanDetail();
		bean.setIdentifier(Integer.parseInt(request.getParameter("identifier")));
		bean.setAmount(Integer.parseInt(request.getParameter("amount")));
		bean.setLoan_identifier(request.getParameter("loan"));
		bean.setBook_identifier(request.getParameter("book"));
		bean.setActive(request.getParameter("active"));
		try {
			switch (accion) {
			case ControllerUtil.CRUD_NUEVO:
				service.insert(bean.getAmount(), bean.getBook_identifier());
				break;
			case ControllerUtil.CRUD_ELIMINAR:
				service.delete(bean.getIdentifier().toString());
				break;
			case ControllerUtil.CRUD_ELIMINATE:
				service.deleteGeneral(bean.getBook_identifier().toString(), bean.getLoan_identifier().toString());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			ControllerUtil.responseJson(response, "Proceso ok.");
		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}
}
