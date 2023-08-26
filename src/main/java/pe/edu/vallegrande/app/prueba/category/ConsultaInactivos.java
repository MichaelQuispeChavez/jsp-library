package pe.edu.vallegrande.app.prueba.category;

import java.util.List;

import pe.edu.vallegrande.app.model.Category;
import pe.edu.vallegrande.app.service.CrudCategoryService;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			CrudCategoryService categoryService = new CrudCategoryService();
			List<Category> lista = categoryService.getInactive();
			for (Category category : lista) {
				System.out.println(category);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
