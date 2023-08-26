package pe.edu.vallegrande.app.prueba.author;

import java.util.List;

import pe.edu.vallegrande.app.model.Author;
import pe.edu.vallegrande.app.service.CrudAuthorService;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			CrudAuthorService authorService = new CrudAuthorService();
			List<Author> lista = authorService.getInactive();
			for (Author author : lista) {
				System.out.println(author);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
