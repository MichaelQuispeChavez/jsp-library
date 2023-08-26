package pe.edu.vallegrande.app.prueba.book;

import pe.edu.vallegrande.app.service.CrudBookService;

public class Restaurar {

	public static void main(String[] args) {
		try {
			CrudBookService bookService = new CrudBookService();
			bookService.restore("10");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
