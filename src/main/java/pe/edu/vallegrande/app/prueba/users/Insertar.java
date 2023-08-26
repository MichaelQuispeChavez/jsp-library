package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.model.Users;
import pe.edu.vallegrande.app.service.CrudUsersService;

public class Insertar {

	public static void main(String[] args) {
		try {
			Users bean = new Users("Benito Jesus", "Canales Guando", "DNI", "70000008", "jesus@gmail.com", "900000008");
			CrudUsersService userService = new CrudUsersService();
			userService.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
