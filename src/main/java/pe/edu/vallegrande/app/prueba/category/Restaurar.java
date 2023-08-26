package pe.edu.vallegrande.app.prueba.category;

import pe.edu.vallegrande.app.service.CrudCategoryService;

public class Restaurar {

	public static void main(String[] args) {
		try {
			CrudCategoryService categoryService = new CrudCategoryService();
			categoryService.restore("1");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
