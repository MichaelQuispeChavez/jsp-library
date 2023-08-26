package pe.edu.vallegrande.app.prueba.book;

import pe.edu.vallegrande.app.service.CrudBookService;

public class Eliminar {

	public static void main(String[] args) {
		try {
			CrudBookService bookService = new CrudBookService();
			bookService.delete("1");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
