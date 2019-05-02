package main.java.arq.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T>{
    public void create(T obj) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:memory:database");
        PreparedStatement stmt = null;
        if(conn) {
            // Aqui ficam as operações de cada DAO
            stmt.executeUpdate();
        } else {
            throw new SQLException('Error during creating Pais!');
            System.err.println(SQLException.getCause());
            System.err.println(SQLException.getMessage());
        }
    }

    public List<T> read() {
        List<T> list = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:derby:memory:database");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        if(conn) {
            while(rs.next()) {
                list.add(T novo);
            }
        } else {
            throw new SQLException('Error during creating Pais!');
            System.err.println(SQLException.getCause());
            System.err.println(SQLException.getMessage());
        }
        return list;
    }

    public void update(T obj) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:memory:database");
        PreparedStatement stmt = null;
        if(conn) {
            // Aqui ficam as operações de cada DAO
            stmt.executeUpdate();
        } else {
            throw new SQLException('Error during updating Pais!');
            System.err.println(SQLException.getCause());
            System.err.println(SQLException.getMessage());
        }
    }

    public void delete(int id) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:memory:database");
        if(conn) {
            PreparedStatement stmt = conn.prepareStatement(String sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } else {
            throw new SQLException('Error during updating Pais!');
            System.err.println(SQLException.getCause());
            System.err.println(SQLException.getMessage());
        }
    }
}
