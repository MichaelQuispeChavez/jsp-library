package pe.edu.vallegrande.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.app.db.AccesoDB;
import pe.edu.vallegrande.app.model.LoanDetail;

public class CrudLoanDetailService {

	public List<LoanDetail> getActive() {
		List<LoanDetail> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		try {
			cn = AccesoDB.getConnection();
			sql = "SELECT ld.identifier,ld.amount,ld.loan_identifier,b.title AS 'book_identifier',ld.active FROM loan_detail AS ld INNER JOIN book AS b ON ld.book_identifier = b.identifier WHERE ld.active='A';";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				LoanDetail bean = new LoanDetail();
				bean.setIdentifier(rs.getInt(1));
				bean.setAmount(rs.getInt(2));
				bean.setLoan_identifier(rs.getString(3));
				bean.setBook_identifier(rs.getString(4));
				bean.setActive(rs.getString(5));
				lista.add(bean);
			}
		} catch (Exception e) {
			throw new RuntimeException("Hay un error.");
		}
		return lista;
	}

	public List<LoanDetail> getInactive() {
		List<LoanDetail> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		try {
			cn = AccesoDB.getConnection();
			sql = "SELECT * FROM loan_detail WHERE active='I'";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				LoanDetail bean = new LoanDetail();
				bean.setIdentifier(rs.getInt(1));
				bean.setAmount(rs.getInt(2));
				bean.setLoan_identifier(rs.getString(3));
				bean.setBook_identifier(rs.getString(4));
				bean.setActive(rs.getString(5));
				lista.add(bean);
			}
		} catch (Exception e) {
			throw new RuntimeException("Hay un error.");
		}
		return lista;
	}
	
	public List<LoanDetail> getForId() {
		Connection cn = null;
		List<LoanDetail> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		String loan_detail;
		try {
			cn = AccesoDB.getConnection();
			sql = "SELECT MAX(identifier) AS 'idLoan' FROM loan WITH (ROWLOCK, XLOCK)";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(!rs.next()) {
				throw new SQLException();
			}
			loan_detail = rs.getString("idLoan");
			rs.close();
			pstm.close();
			sql = "SELECT * FROM loan_detail WHERE loan_identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(loan_detail));
			rs = pstm.executeQuery();
			while(rs.next()) {
				LoanDetail item = new LoanDetail();
				item.setIdentifier(rs.getInt("identifier"));
				item.setAmount(rs.getInt("amount"));
				item.setLoan_identifier(rs.getString("loan_identifier"));
				item.setBook_identifier(rs.getString("book_identifier"));
				item.setActive(rs.getString("active"));
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

	public void insert(int amount, String book) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		int filas, stock;
		String idLoan;
		try {
			cn = AccesoDB.getConnection();
			cn.getAutoCommit();
			sql = "SELECT stock FROM book WITH (ROWLOCK, XLOCK) WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, book);
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException();
			}
			stock = rs.getInt("stock");
			rs.close();
			pstm.close();
			sql = "SELECT MAX(identifier) AS 'idLoan' FROM loan WITH (ROWLOCK, XLOCK)";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException();
			}
			idLoan = rs.getString("idLoan");
			rs.close();
			pstm.close();
			if (amount > stock) {
				throw new SQLException("No hay stock suficiente.");
			}
			stock = stock - amount;
			sql = "UPDATE book SET stock=? WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, stock);
			pstm.setString(2, book);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No existe la publicacion.");
			}
			sql = "INSERT INTO loan_detail (amount, loan_identifier, book_identifier) VALUES (?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, amount);
			pstm.setString(2, idLoan);
			pstm.setString(3, book);
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

	public void deleteGeneral(String book, String loan) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql;
		int filas, stock, count, amount;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Obtener stock actual del libro
			sql = "SELECT stock FROM book WITH (ROWLOCK, XLOCK) WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, book);
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException("No se encontró el libro.");
			}
			stock = rs.getInt("stock");
			rs.close();
			pstm.close();
			sql = "SELECT SUM(amount) AS 'amount' FROM loan_detail WHERE loan_identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, loan);
			rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException("No se encontró el prestamo.");
			}
			amount = rs.getInt("amount");
			// Actualizar stock del libro
			stock += amount;
			sql = "SELECT count(1) cont FROM loan WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, loan);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt("CONT");
			rs.close();
			pstm.close();
			if (count != 1) {
				throw new SQLException("No existe.");
			}
			sql = "UPDATE book SET stock=? WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, stock);
			pstm.setString(2, book);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo actualizar el stock.");
			}
			// Eliminar registro de préstamo
			sql = "UPDATE loan_detail SET active='I' WHERE loan_identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, loan);
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

	public void delete(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		String sql;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			sql = "DELETE FROM loan_detail WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error al eliminar el registro.");
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

}
