package pe.edu.vallegrande.app.prueba.author;

import pe.edu.vallegrande.app.service.CrudAuthorService;

public class Restaurar {

	public static void main(String[] args) {
		try {
			CrudAuthorService authorService = new CrudAuthorService();
			authorService.restore("2");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
