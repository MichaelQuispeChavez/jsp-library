package pe.edu.vallegrande.app.prueba.author;

import java.util.List;

import pe.edu.vallegrande.app.model.Author;
import pe.edu.vallegrande.app.service.CrudAuthorService;

public class ConsultaActivos {

	public static void main(String[] args) {
		try {
			CrudAuthorService authorService = new CrudAuthorService();
			List<Author> lista = authorService.getActive();
			for (Author user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
