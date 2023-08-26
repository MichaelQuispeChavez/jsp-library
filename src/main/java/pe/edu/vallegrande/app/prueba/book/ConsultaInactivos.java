package pe.edu.vallegrande.app.prueba.book;

import java.util.List;

import pe.edu.vallegrande.app.model.Book;
import pe.edu.vallegrande.app.service.CrudBookService;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			CrudBookService bookService = new CrudBookService();
			List<Book> lista = bookService.getInactive();
			for (Book book : lista) {
				System.out.println(book);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
