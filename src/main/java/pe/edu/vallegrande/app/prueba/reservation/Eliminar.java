package pe.edu.vallegrande.app.prueba.reservation;

import pe.edu.vallegrande.app.service.CrudReservationService;

public class Eliminar {

	public static void main(String[] args) {
		try {
			CrudReservationService reservationService = new CrudReservationService();
			reservationService.delete("3");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
