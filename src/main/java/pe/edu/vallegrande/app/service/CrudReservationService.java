package pe.edu.vallegrande.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.app.db.AccesoDB;
import pe.edu.vallegrande.app.model.Reservation;
import pe.edu.vallegrande.app.service.spec.CrudServiceSpec;
import pe.edu.vallegrande.app.service.spec.RowMapper;

public class CrudReservationService implements CrudServiceSpec<Reservation>, RowMapper<Reservation> {
	
	private final String SQL_SELECT_ACTIVE = "SELECT * FROM reservation_active";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM reservation_inactive";
	private final String SQL_SELECT_LIKE = "";
	///private final String SQL_SELECT_LIKE = "r.identifier, b.title AS 'book_identifier', CONCAT(UPPER(u.last_name),', ',u.names) AS 'user_identifier', r.dates AS 'reservation_date', r.amount, r.active FROM reservation AS r JOIN users AS u ON r.user_identifier = u.identifier JOIN book AS b ON r.book_identifier = b.identifier WHERE r.active='A'";
	private final String SQL_INSERT = "INSERT INTO reservation (dates, user_identifier, book_identifier, amount) VALUES (?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE reservation SET dates=?, user_identifier=?, book_identifier=?, amount=? WHERE identifier=?";
	private final String SQL_DELETE = "UPDATE reservation SET active='I' WHERE identifier=?";
	private final String SQL_RESTORE = "UPDATE reservation SET active='A' WHERE identifier=?";
	private final String SQL_ELIMINATE = "DELETE FROM reservation WHERE identifier=?";

	@Override
	public List<Reservation> getActive() {
		List<Reservation> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				Reservation bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Reservation> getInactive() {
		List<Reservation> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				Reservation bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Reservation getForId(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Reservation bean = null;
		String sql;
		try {
			cn = AccesoDB.getConnection();
			sql = "SELECT * FROM reservation WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(identifier));
			rs = pstm.executeQuery();
			if(rs.next()) {
				bean = mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return bean;
	}

	@Override
	public List<Reservation> get(Reservation bean) {
		Connection cn = null;
		List<Reservation> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Reservation item;
		String user, book;
		user = "%" + UtilService.setStringVacio(bean.getUser_identifier()) + "%";
		book = "%" + UtilService.setStringVacio(bean.getBook_identifier()) + "%";
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_LIKE);
			pstm.setString(1, user);
			pstm.setString(2, book);
			rs = pstm.executeQuery();
			while(rs.next()) {
				item = mapRow(rs);
				lista.add(item);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return lista;
	}

	@Override
	public void insert(Reservation bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getDates());
			pstm.setString(2, bean.getUser_identifier());
			pstm.setString(3, bean.getBook_identifier());
			pstm.setInt(4, bean.getAmount());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void update(Reservation bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getDates());
			pstm.setString(2, bean.getUser_identifier());
			pstm.setString(3, bean.getBook_identifier());
			pstm.setInt(4, bean.getAmount());
			pstm.setInt(5, bean.getIdentifier());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_DELETE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void restore(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_RESTORE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo restaurar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void eliminate(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_ELIMINATE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public Reservation mapRow(ResultSet rs) throws SQLException {
		Reservation bean = new Reservation();
		bean.setIdentifier(rs.getInt("identifier"));
		bean.setDates(rs.getString("dates"));
		bean.setUser_identifier(rs.getString("user_identifier"));
		bean.setBook_identifier(rs.getString("book_identifier"));
		bean.setAmount(rs.getInt("Amount"));
		bean.setActive(rs.getString("active"));
		return bean;
	}

}
