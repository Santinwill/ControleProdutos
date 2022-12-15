package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;


public class TestUsuarioDAO {
		public boolean checkLogin (String login, String password){
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			boolean check = false;
			
			try {
				stmt = con.prepareStatement("Select * FROM usuario where login = ? and password = ?");
				stmt.setString(1, login);
				stmt.setString(2, password);
				rs = stmt.executeQuery();
				if (rs.next()) {
					check = true;
				}
			}catch (SQLException e) {
				Logger.getLogger(TestUsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
			}finally {
				ConnectionFactory.closeConnection(con, stmt, rs);
			}
			return check;
		}
}
