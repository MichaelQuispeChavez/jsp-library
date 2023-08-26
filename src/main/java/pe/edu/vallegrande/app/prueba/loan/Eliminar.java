package pe.edu.vallegrande.app.prueba.loan;

import pe.edu.vallegrande.app.service.CrudBookService;

public class Eliminar {

	public static void main(String[] args) {
		try {
			CrudBookService service = new CrudBookService();
			service.delete("2");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
