package pe.edu.vallegrande.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.vallegrande.app.model.Reservation;
import pe.edu.vallegrande.app.service.CrudReservationService;

@WebServlet({ "/ReservationBuscar", "/ReservationProcesar", "/ReservationActualizar", "/ReservationHistorial" })
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CrudReservationService service = new CrudReservationService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/ReservationBuscar":
			buscar(request, response);
			break;
		case "/ReservationProcesar":
			procesar(request, response);
			break;
		case "/ReservationActualizar":
			actualizar(request, response);
			break;
		case "/ReservationHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		Reservation bean = new Reservation();
		bean.setIdentifier(Integer.parseInt(request.getParameter("identifier")));;
		bean.setDates(request.getParameter("dates"));
		bean.setUser_identifier(request.getParameter("user_identifier"));
		bean.setBook_identifier(request.getParameter("book_identifier"));
		bean.setAmount(Integer.parseInt(request.getParameter("amount")));;
		// Proceso
		try {
			switch (accion) {
			case ControllerUtil.CRUD_NUEVO:
				service.insert(bean);
				break;
			case ControllerUtil.CRUD_EDITAR:
				service.update(bean);
				break;
			case ControllerUtil.CRUD_ELIMINAR:
				service.delete(bean.getIdentifier().toString());
				break;
			case ControllerUtil.CRUD_RESTAURAR:
				service.restore(bean.getIdentifier().toString());
				break;
			case ControllerUtil.CRUD_ELIMINATE: 
				service.eliminate(bean.getIdentifier().toString());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			ControllerUtil.responseJson(response, "Proceso ok.");
		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String user_identifier = request.getParameter("user_identifier");
		String book_identifier = request.getParameter("book_identifier");
		// Proceso
		Reservation bean = new Reservation();
		bean.setUser_identifier(user_identifier);
		bean.setBook_identifier(book_identifier);
		List<Reservation> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) {
		List<Reservation> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		List<Reservation> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}
