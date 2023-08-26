package pe.edu.vallegrande.app.prueba.reservation;

import pe.edu.vallegrande.app.model.Reservation;
import pe.edu.vallegrande.app.service.CrudReservationService;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			CrudReservationService reservationService = new CrudReservationService();
			Reservation bean = reservationService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
