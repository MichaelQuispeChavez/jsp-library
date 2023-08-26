package pe.edu.vallegrande.app.prueba.book;

import pe.edu.vallegrande.app.model.Book;
import pe.edu.vallegrande.app.service.CrudBookService;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			CrudBookService bookService = new CrudBookService();
			Book bean = bookService.getForId("1");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
