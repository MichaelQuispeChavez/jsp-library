package pe.edu.vallegrande.app.prueba.book;

import pe.edu.vallegrande.app.model.Book;
import pe.edu.vallegrande.app.service.CrudBookService;

public class Editar {

	public static void main(String[] args) {
		try {
			Book bean = new Book(2,"Miguel","Cuadros","DNI", "4", "5");
			CrudBookService bookService = new CrudBookService();
			bookService.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
