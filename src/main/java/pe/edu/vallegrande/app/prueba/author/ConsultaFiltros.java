package pe.edu.vallegrande.app.prueba.author;

import java.util.List;

import pe.edu.vallegrande.app.model.Author;
import pe.edu.vallegrande.app.service.CrudAuthorService;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			Author bean = new Author();
			bean.setNames("");
			bean.setLast_name("");
			CrudAuthorService authorService = new CrudAuthorService();
			List<Author> lista = authorService.get(bean);
			for (Author author : lista) {
				System.out.println(author);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
