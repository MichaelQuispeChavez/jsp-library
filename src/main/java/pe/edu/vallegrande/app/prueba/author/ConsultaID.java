package pe.edu.vallegrande.app.prueba.author;

import pe.edu.vallegrande.app.model.Author;
import pe.edu.vallegrande.app.service.CrudAuthorService;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			CrudAuthorService authorService = new CrudAuthorService();
			Author bean = authorService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
