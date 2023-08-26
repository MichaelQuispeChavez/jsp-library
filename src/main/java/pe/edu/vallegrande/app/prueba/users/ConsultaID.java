package pe.edu.vallegrande.app.prueba.users;

import pe.edu.vallegrande.app.model.Users;
import pe.edu.vallegrande.app.service.CrudUsersService;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			CrudUsersService userService = new CrudUsersService();
			Users bean = userService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
