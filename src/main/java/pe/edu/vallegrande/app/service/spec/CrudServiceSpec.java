package pe.edu.vallegrande.app.service.spec;

import java.util.List;

public interface CrudServiceSpec<T> {

	/**
	 * Consulta todos los registros activos de la tabla.
	 * @return Retorna una lista.
	 */
	List<T> getActive();
	
	/**
	 * Consulta todos los registros inactivos de la tabla.
	 * @return Retorna una lista.
	 */
	List<T> getInactive();
	
	/**
	 * Retorna un registro basado en el id.
	 * @param id
	 * @return
	 */
	T getForId(String identifier);

	/**
	 * Consulta datos en base a un criterio.
	 * @param bean Datos para establecer el criterio.
	 * @return Retorna una lista.
	 */
	List<T> get(T bean);

	/**
	 * Insertar nuevo registro.
	 * @param bean Datos del nuevo registro.
	 */
	void insert(T bean);

	/**
	 * Actualiza datos de un registro especifico.
	 * @param bean Datos del registro.
	 */
	void update(T bean);

	/**
	 * Elimina un registro de la base de datos de manera logica.
	 * @param id El id del registro a eliminar.
	 */
	void delete(String identifier);

	/**
	 * Restaura un registro eliminado de la base de datos.
	 * @param id El id del registro a eliminar.
	 */
	void restore(String identifier);

	/**
	 * Elimina un registro de la base de datos.
	 * @param id El id del registro a eliminar.
	 */
	void eliminate(String identifier);

}
