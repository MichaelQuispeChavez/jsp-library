package pe.edu.vallegrande.app.prueba.reservation;

import java.util.List;

import pe.edu.vallegrande.app.model.Reservation;
import pe.edu.vallegrande.app.service.CrudReservationService;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			Reservation bean = new Reservation();
			bean.setUser_identifier("cuadros");
			bean.setBook_identifier("");
			CrudReservationService reservationService = new CrudReservationService();
			List<Reservation> lista = reservationService.get(bean);
			for (Reservation reservation : lista) {
				System.out.println(reservation);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
