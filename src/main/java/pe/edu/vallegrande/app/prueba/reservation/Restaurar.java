package pe.edu.vallegrande.app.prueba.reservation;

import pe.edu.vallegrande.app.service.CrudReservationService;

public class Restaurar {

	public static void main(String[] args) {
		try {
			CrudReservationService reservationService = new CrudReservationService();
			reservationService.restore("3");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
