package pe.edu.vallegrande.app.prueba.category;

import java.util.List;

import pe.edu.vallegrande.app.model.Category;
import pe.edu.vallegrande.app.service.CrudCategoryService;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			Category bean = new Category();
			bean.setNames("Literatura");
			// bean.setLast_name("");
			CrudCategoryService categoryService = new CrudCategoryService();
			List<Category> lista = categoryService.get(bean);
			for (Category category : lista) {
				System.out.println(category);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
