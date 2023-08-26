package pe.edu.vallegrande.app.prueba.reservation;

import pe.edu.vallegrande.app.model.Reservation;
import pe.edu.vallegrande.app.service.CrudReservationService;

public class Insertar {

	public static void main(String[] args) {
		try {
			Reservation bean = new Reservation("2023-10-11", "1", "5", 2);
			CrudReservationService reservationService = new CrudReservationService();
			reservationService.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}