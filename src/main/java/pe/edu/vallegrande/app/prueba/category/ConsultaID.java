package pe.edu.vallegrande.app.prueba.category;

import pe.edu.vallegrande.app.model.Category;
import pe.edu.vallegrande.app.service.CrudCategoryService;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			CrudCategoryService categoryService = new CrudCategoryService();
			Category bean = categoryService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
