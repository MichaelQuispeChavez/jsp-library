package pe.edu.vallegrande.app.prueba.book;

import pe.edu.vallegrande.app.model.Book;
import pe.edu.vallegrande.app.service.CrudBookService;

public class Insertar {

	public static void main(String[] args) {
		try {
			Book bean = new Book("La distancia que nos separa", "30", "9783906910567", "2", "2");
			CrudBookService bookService = new CrudBookService();
			bookService.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}