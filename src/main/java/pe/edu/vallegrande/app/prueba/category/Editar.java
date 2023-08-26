package pe.edu.vallegrande.app.prueba.category;

import pe.edu.vallegrande.app.model.Category;
import pe.edu.vallegrande.app.service.CrudCategoryService;

public class Editar {

	public static void main(String[] args) {
		try {
			Category bean = new Category(1,"Ciencias Sociales","Se relacionan con el comportamiento humano y la sociedad en general.");
			CrudCategoryService categoryService = new CrudCategoryService();
			categoryService.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
