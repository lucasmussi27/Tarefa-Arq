package main.java.arq.dao;
import main.java.arq.dao.AbstractDAO;
import main.java.arq.dto.Pais;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO extends AbstractDAO<Pais> {
    private Connection conn;

    public PaisDAO() {
        try(conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {
            conn.createStatement().executeUpdate(
                "create table pais ("
                + "id int not null generated always as identity constraint pais_id primary key,"
                + "nome varchar(255) not null,"
                + "sigla varchar(3) not null,"
                + "codigo int)");
        } catch(SQLException se) {
            System.err.println(se.getCause());
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
    }

    public void create(Pais pais) {
        String sql = "insert into pais values (?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, pais.getNome());
        stmt.setString(2, pais.getSigla());
        stmt.setInt(3, pais.getCodigo());
    }

    public List<Pais> read() {
        String sql = "select * from pais";
        PreparedStatement stmt = conn.prepareStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Pais> list = new ArrayList<>();
        list.add(Pais.builder()
                    .id(rs.getInt("id"))
                    .nome(rs.getString("nome"))
                    .sigla(rs.getString("sigal"))
                    .codigo(rs.getInt("codigo"))
                    .build());
        return list;
    }

    public void update(Pais pais) {
        String sql = "update pais set nome = ?,"
                    + "sigla = ?, codigo = ?"
                    + "where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, pais.getNome());
        stmt.setString(2, pais.getSigla());
        stmt.setInt(3, pais.getCodigo());
    }

    public void delete(int id) {
        String sql = "delete from pais where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
    }
}
