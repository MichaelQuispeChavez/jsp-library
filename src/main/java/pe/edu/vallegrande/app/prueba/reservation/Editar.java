package pe.edu.vallegrande.app.prueba.reservation;

import pe.edu.vallegrande.app.model.Reservation;
import pe.edu.vallegrande.app.service.CrudReservationService;

public class Editar {

	public static void main(String[] args) {
		try {
			Reservation bean = new Reservation(1,"2023-10-10", "4", "5", 10);
			CrudReservationService bookService = new CrudReservationService();
			bookService.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
