package pe.edu.vallegrande.app.prueba.author;

import pe.edu.vallegrande.app.service.CrudAuthorService;

public class Eliminar {

	public static void main(String[] args) {
		try {
			CrudAuthorService authorService = new CrudAuthorService();
			authorService.delete("2");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
