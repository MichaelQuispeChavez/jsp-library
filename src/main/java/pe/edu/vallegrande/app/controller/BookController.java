package pe.edu.vallegrande.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.vallegrande.app.model.Book;
import pe.edu.vallegrande.app.service.CrudBookService;

@WebServlet({ "/BookBuscar", "/BookProcesar", "/BookActualizar", "/BookHistorial" })
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CrudBookService service = new CrudBookService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/BookBuscar":
			buscar(request, response);
			break;
		case "/BookProcesar":
			procesar(request, response);
			break;
		case "/BookActualizar":
			actualizar(request, response);
			break;
		case "/BookHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		Book bean = new Book();
		bean.setIdentifier(Integer.parseInt(request.getParameter("identifier")));;
		bean.setTitle(request.getParameter("title"));
		bean.setStock(request.getParameter("stock"));
		bean.setIsbn(request.getParameter("isbn"));
		bean.setCategory_identifier(request.getParameter("category_identifier"));
		bean.setAuthor_identifier(request.getParameter("author_identifier"));
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
		String title = request.getParameter("title");
		String category_identifier = request.getParameter("category_identifier");
		// Proceso
		Book bean = new Book();
		bean.setTitle(title);
		bean.setCategory_identifier(category_identifier);
		List<Book> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) {
		List<Book> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		List<Book> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}
