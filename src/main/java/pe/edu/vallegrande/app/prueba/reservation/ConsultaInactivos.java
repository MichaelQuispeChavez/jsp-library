package pe.edu.vallegrande.app.prueba.reservation;

import java.util.List;

import pe.edu.vallegrande.app.model.Reservation;
import pe.edu.vallegrande.app.service.CrudReservationService;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			CrudReservationService reservationService = new CrudReservationService();
			List<Reservation> lista = reservationService.getInactive();
			for (Reservation reservation : lista) {
				System.out.println(reservation);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
