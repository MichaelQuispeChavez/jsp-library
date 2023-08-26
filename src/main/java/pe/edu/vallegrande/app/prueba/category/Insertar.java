package pe.edu.vallegrande.app.prueba.category;

import pe.edu.vallegrande.app.model.Category;
import pe.edu.vallegrande.app.service.CrudCategoryService;

public class Insertar {

	public static void main(String[] args) {
		try {
			Category bean = new Category("Literatura", "Expresión artística que se vale del uso del lenguaje oral o escrito.");
			CrudCategoryService categoryService = new CrudCategoryService();
			categoryService.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
