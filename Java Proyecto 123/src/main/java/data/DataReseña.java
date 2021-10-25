package data;

import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class DataRese�a {

	public Rese�a getOne(int res) {
		
		Rese�a r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,titulo,descripcion,puntuacion"
					+ " from rese�a where id=? and habilitado=1"
					);
			
			stmt.setInt(1, res);			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Rese�a();		
				r.setId(rs.getInt("id"));
				r.setTitulo(rs.getString("titulo"));
				r.setDescripcion(rs.getString("descripcion"));
				r.setPuntuacion(rs.getInt("puntuacion"));
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	public LinkedList<Rese�a> getAll(){		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Rese�a> rems= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id,titulo,descripcion,puntuacion from rese�a where habilitado=1");			
			if(rs!=null) {
				while(rs.next()) {
					Rese�a r=new Rese�a();
					r.setId(rs.getInt("id"));
					r.setTitulo(rs.getString("titulo"));
					r.setDescripcion(rs.getString("descripcion"));
					r.setPuntuacion(rs.getInt("puntuacion"));						
					rems.add(r);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return rems;
	}

	public Rese�a add(Rese�a r) {


		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into rese�a(titulo,descripcion,puntuacion) "
							+ "values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, r.getTitulo());
			stmt.setString(2, r.getDescripcion());
			stmt.setInt(3, r.getPuntuacion());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                r.setId(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }        
		}
		  return r; 
    }

	public void update(Rese�a r) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update rese�a set titulo=?,descripcion=?,puntuacion=?"
							+ "where id=?");
			
			stmt.setString(1, r.getTitulo());
			stmt.setString(2, r.getDescripcion());
			stmt.setInt(3, r.getPuntuacion());			
			stmt.setInt(4, r.getId());
		
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void delete(Rese�a r) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update rese�a set habilitado=0 where id=?");
			stmt.setInt(1, r.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
}
