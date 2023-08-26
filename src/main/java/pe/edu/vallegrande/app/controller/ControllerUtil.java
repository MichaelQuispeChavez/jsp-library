package pe.edu.vallegrande.app.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {
	
	private ControllerUtil() {
	}
	
	// Constantes del CRUD
	public final static String CRUD_NUEVO = "NUEVO";
	public final static String CRUD_EDITAR = "EDITAR";
	public final static String CRUD_ELIMINAR = "ELIMINAR";
	public final static String CRUD_RESTAURAR = "RESTAURAR";
	public final static String CRUD_ELIMINATE = "ELIMINATE";
	
	
	public static void responseJson(HttpServletResponse response, String data) {
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(data);
			out.flush();
		} catch (Exception e) {
		}
	}

}
