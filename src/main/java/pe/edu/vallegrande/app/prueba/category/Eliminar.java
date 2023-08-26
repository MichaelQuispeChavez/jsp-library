package pe.edu.vallegrande.app.prueba.category;

import pe.edu.vallegrande.app.service.CrudCategoryService;

public class Eliminar {

	public static void main(String[] args) {
		try {
			CrudCategoryService categoryService = new CrudCategoryService();
			categoryService.delete("1");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
