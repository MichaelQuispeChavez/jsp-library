package pe.edu.vallegrande.app.prueba.author;

import pe.edu.vallegrande.app.model.Author;
import pe.edu.vallegrande.app.service.CrudAuthorService;

public class Insertar {

	public static void main(String[] args) {
		try {
			Author bean = new Author("Gabriel", "Garcia Marquez", "Colombia");
			CrudAuthorService authorService = new CrudAuthorService();
			authorService.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
