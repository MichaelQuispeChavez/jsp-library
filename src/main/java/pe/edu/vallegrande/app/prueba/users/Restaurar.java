package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.service.CrudUsersService;

public class Restaurar {

	public static void main(String[] args) {
		try {
			CrudUsersService userService = new CrudUsersService();
			userService.restore("7");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
