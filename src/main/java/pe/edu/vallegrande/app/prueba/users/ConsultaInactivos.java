package pe.edu.vallegrande.app.prueba.users;

import java.util.List;

import pe.edu.vallegrande.app.model.Users;
import pe.edu.vallegrande.app.service.CrudUsersService;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			CrudUsersService userService = new CrudUsersService();
			List<Users> lista = userService.getInactive();
			for (Users user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
