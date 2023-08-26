package pe.edu.vallegrande.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.app.db.AccesoDB;
import pe.edu.vallegrande.app.model.Book;
import pe.edu.vallegrande.app.service.spec.CrudServiceSpec;
import pe.edu.vallegrande.app.service.spec.RowMapper;

public class CrudBookService implements CrudServiceSpec<Book>, RowMapper<Book> {
	
	private final String SQL_SELECT_ACTIVE = "SELECT * FROM book_active";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM book_inactive";
	private final String SQL_SELECT_LIKE = "SELECT b.identifier,b.title,b.stock,b.ISBN,c.names AS 'category_identifier',CONCAT(a.names,' ',UPPER(a.last_name)) AS 'author_identifier',b.active FROM book AS b INNER JOIN category AS c ON b.category_identifier = c.identifier INNER JOIN author AS a ON b.author_identifier = a.identifier WHERE b.active='A' AND b.title LIKE ? AND c.names LIKE ?";
	private final String SQL_INSERT = "INSERT INTO book (title, stock, ISBN, category_identifier, author_identifier) VALUES (?,?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE book SET title=?, stock=?, ISBN=?, category_identifier=?, author_identifier=? WHERE identifier=?";
	private final String SQL_DELETE = "UPDATE book SET active='I' WHERE identifier=?";
	private final String SQL_RESTORE = "UPDATE book SET active='A' WHERE identifier=?";
	private final String SQL_ELIMINATE = "DELETE FROM book WHERE identifier=?";

	@Override
	public List<Book> getActive() {
		List<Book> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				Book bean = mapRow(rs);
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
	public List<Book> getInactive() {
		List<Book> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				Book bean = mapRow(rs);
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
	public Book getForId(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Book bean = null;
		String sql;
		try {
			cn = AccesoDB.getConnection();
			sql = SQL_SELECT_ACTIVE + " WHERE identifier=?";
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
	public List<Book> get(Book bean) {
		Connection cn = null;
		List<Book> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Book item;
		String title, category;
		title = "%" + UtilService.setStringVacio(bean.getTitle()) + "%";
		category = "%" + UtilService.setStringVacio(bean.getCategory_identifier()) + "%";
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_LIKE);
			pstm.setString(1, title);
			pstm.setString(2, category);
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
	public void insert(Book bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getTitle());
			pstm.setString(2, bean.getStock());
			pstm.setString(3, bean.getIsbn());
			pstm.setString(4, bean.getCategory_identifier());
			pstm.setString(5, bean.getAuthor_identifier());
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
	public void update(Book bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getTitle());
			pstm.setString(2, bean.getStock());
			pstm.setString(3, bean.getIsbn());
			pstm.setString(4, bean.getCategory_identifier());
			pstm.setString(5, bean.getAuthor_identifier());
			pstm.setInt(6, bean.getIdentifier());
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
	public Book mapRow(ResultSet rs) throws SQLException {
		Book bean = new Book();
		bean.setIdentifier(rs.getInt("identifier"));
		bean.setTitle(rs.getString("title"));
		bean.setStock(rs.getString("stock"));
		bean.setIsbn(rs.getString("ISBN"));
		bean.setCategory_identifier(rs.getString("category_identifier"));
		bean.setAuthor_identifier(rs.getString("author_identifier"));
		bean.setActive(rs.getString("active"));
		return bean;
	}

}
