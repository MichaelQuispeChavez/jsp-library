package pe.edu.vallegrande.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.app.db.AccesoDB;
import pe.edu.vallegrande.app.model.Loan;
import pe.edu.vallegrande.app.service.spec.RowMapper;

public class CrudLoanService implements RowMapper<Loan> {
	
	private final String SQL_ACTIVE = "SELECT * FROM loan_active";
	private final String SQL_INACTIVE = "SELECT * FROM loan_inactive";
	private final String SQL_INSERT = "INSERT INTO loan (user_identifier,book_identifier,amount,start_dates,return_date) VALUES (?,?,?,GETDATE(),?)";
	private final String SQL_UPDATE = "UPDATE loan SET user_identifier=?, book_identifier=?, amount=?, return_date=? WHERE identifier=?";
	private final String SQL_DELETE = "UPDATE loan SET active='I' WHERE identifier=?";
	private final String SQL_RESTORE = "UPDATE loan SET active='A' WHERE identifier=?";
	private final String SQL_ELIMINATE = "DELETE FROM loan WHERE identifier=?";

	public List<Loan> getActive() {
		List<Loan> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_ACTIVE);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Loan bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Loan> getInactive() {
		List<Loan> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_INACTIVE);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Loan bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Loan getForId(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Loan bean = null;
		String sql;
		try {
			cn = AccesoDB.getConnection();
			sql = SQL_ACTIVE + " WHERE identifier=?";
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

	public List<Loan> get(Loan bean) {
		Connection cn = null;
		List<Loan> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Loan item;
		String sql, user, book;
		user = "%" + UtilService.setStringVacio(bean.getUser_identifier()) + "%";
		book = "%" + UtilService.setStringVacio(bean.getBook_identifier()) + "%";
		try {
			cn = AccesoDB.getConnection();
			sql = SQL_ACTIVE + " WHERE user_identifier LIKE ? AND book_identifier LIKE ?";
			pstm = cn.prepareStatement(sql);
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

	public void insert(Loan bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		int filas, stock;
		try {
			cn = AccesoDB.getConnection();
			cn.getAutoCommit();
			sql = "SELECT stock FROM book WITH (ROWLOCK, XLOCK) WHERE identifier=? AND active='A'";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getBook_identifier());
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException();
			}
			stock = rs.getInt("stock");
			rs.close();
			pstm.close();
			if (Integer.parseInt(bean.getAmount()) > stock) {
				throw new SQLException("No hay stock suficiente.");
			}
			stock = stock - Integer.parseInt(bean.getAmount());
			sql = "UPDATE book SET stock=? WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, stock);
			pstm.setString(2, bean.getBook_identifier());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No existe la publicacion.");
			}
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getUser_identifier());
			pstm.setString(2, bean.getBook_identifier());
			pstm.setString(3, bean.getAmount());
			pstm.setString(4, bean.getReturn_date());
			pstm.executeQuery();
			pstm = null;
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

	public void update(Loan bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		int filas, stock;
		try {
			cn = AccesoDB.getConnection();
			cn.getAutoCommit();
			sql = "SELECT stock FROM book WITH (ROWLOCK, XLOCK) WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getBook_identifier());
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException();
			}
			stock = rs.getInt("stock");
			rs.close();
			pstm.close();
			if (Integer.parseInt(bean.getAmount()) > stock) {
				throw new SQLException("No hay stock suficiente.");
			}
			stock = stock - Integer.parseInt(bean.getAmount());
			sql = "UPDATE book SET stock=? WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, stock);
			pstm.setString(2, bean.getBook_identifier());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No existe la publicacion.");
			}
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getUser_identifier());
			pstm.setString(2, bean.getBook_identifier());
			pstm.setString(3, bean.getAmount());
			pstm.setString(4, bean.getReturn_date());
			pstm.setInt(4, bean.getIdentifier());
			pstm.executeQuery();
			pstm = null;
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

	public void delete(Loan bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		int filas, stock;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			sql = "SELECT stock FROM book WITH (ROWLOCK, XLOCK) WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getBook_identifier());
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException("No se encontró el libro.");
			}
			stock = rs.getInt("stock");
			rs.close();
			pstm.close();
			stock += Integer.parseInt(bean.getAmount());
			sql = "UPDATE book SET stock=? WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, stock);
			pstm.setString(2, bean.getBook_identifier());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo actualizar el stock.");
			}
			pstm = cn.prepareStatement(SQL_DELETE);
			pstm.setInt(1, bean.getIdentifier());
			pstm.executeUpdate();
			pstm.close();
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

	public void restore(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_RESTORE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo restaurar el prestamo.");
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

	public void eliminate(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_ELIMINATE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el prestamo.");
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
	public Loan mapRow(ResultSet rs) throws SQLException {
		Loan bean = new Loan();
		bean.setIdentifier(rs.getInt("identifier"));
		bean.setUser_identifier(rs.getString("user_identifier"));
		bean.setBook_identifier(rs.getString("book_identifier"));
		bean.setAmount(rs.getString("amount"));
		bean.setStart_dates(rs.getDate("start_dates"));
		bean.setReturn_date(rs.getString("return_date"));
		bean.setActive(rs.getString("active"));
		return bean;
	}

}
