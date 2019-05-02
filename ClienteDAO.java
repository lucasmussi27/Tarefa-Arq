package main.java.arq.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import main.java.arq.dto.Cliente;
import main.java.arq.dao.AbstractDAO;
import main.java.arq.dto.Pais;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends AbstractDAO<Cliente> {
    private Connection conn;

    public ClienteDAO() {
        try(conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {
            conn.createStatement().executeUpdate(
                "create table cliente ("
                + "id int not null generated always as identity constraint cliente_id primary key,"
                + "nome varchar(255) not null,"
                + "telefone varchar(30) not null,"
                + "idade int not null,"
                + "credito double not null,"
                + "pais_id int)");
        } catch(SQLException se) {
            System.err.println(se.getCause());
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
    }

    public void create(Cliente cli) {
        String sql = "insert into cliente values (?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cli.getNome());
        stmt.setString(2, cli.getTelefone());
        stmt.setInt(3, cli.getIdade());
        stmt.setDouble(4, cli.getCredito());
        stmt.setInt(5, cli.getPais().getId());
    }

    public List<Pais> read() {
        String sql = "select * from pais";
        PreparedStatement stmt = conn.prepareStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Cliente> list = new ArrayList<>();
        list.add(Cliente.builder()
                    .id(result.getInt("id"))
                    .nome(result.getString("nome"))
                    .telefone(result.getString("telefone"))
                    .idade(result.getInt("idade"))
                    .limiteCredito(result.getDouble("limiteCredito"))
                    .pais(Pais.builder().id(result.getInt("id_pais")).build())
                    .build());
        return list;
    }

    public void update(Pais pais) {
        String sql = "update cliente set nome = ?,"
                    + "telefone = ?, idade = ?"
                    + "credito = ?, pais = ?"
                    + "where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cli.getNome());
        stmt.setString(2, cli.getTelefone());
        stmt.setInt(3, cli.getIdade());
        stmt.setDouble(4, cli.getCredito());
        stmt.setInt(5, cli.getPais().getId());
    }

    public void delete(int id) {
        String sql = "delete from cliente where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
    }
}
