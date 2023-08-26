package pe.edu.vallegrande.app.prueba.book;

import java.util.List;

import pe.edu.vallegrande.app.model.Book;
import pe.edu.vallegrande.app.service.CrudBookService;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			Book bean = new Book();
			bean.setTitle("");
			bean.setCategory_identifier("Ficción");
			CrudBookService bookService = new CrudBookService();
			List<Book> lista = bookService.get(bean);
			for (Book book : lista) {
				System.out.println(book);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
