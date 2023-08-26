package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.model.Users;
import pe.edu.vallegrande.app.service.CrudUsersService;

public class Editar {

	public static void main(String[] args) {
		try {
			Users bean = new Users(2,"Miguel","Cuadros","DNI","70000002","mcuadros@gmail.com","900000002");
			CrudUsersService userService = new CrudUsersService();
			userService.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
