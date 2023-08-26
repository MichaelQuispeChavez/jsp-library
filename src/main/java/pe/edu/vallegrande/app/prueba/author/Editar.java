package pe.edu.vallegrande.app.prueba.author;

import pe.edu.vallegrande.app.model.Author;
import pe.edu.vallegrande.app.service.CrudAuthorService;

public class Editar {

	public static void main(String[] args) {
		try {
			Author bean = new Author(1,"Mario","Vargas Llosa","Peru");
			CrudAuthorService authorService = new CrudAuthorService();
			authorService.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
