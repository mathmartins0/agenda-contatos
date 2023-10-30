package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAO {

	/**
	 * Módulo de conexão
	 */
	
	// Parâmetros de conexão
	private String driverJdbc = "com.mysql.cj.jdbc.Driver";
	private String port = "3306";
	private String dataBase = "dbAgenda";
	private String params = "?useTimezone=true&serverTimezone=UTC";
	private String url = "jdbc:mysql://localhost:"
			.concat(port)
			.concat("/" + dataBase)
			.concat(params);
	private String user = "root";
	private String passwd = "root";
	
	// Método de conexão
	public Connection conectar() {
		Connection conn = null;
		
		try {
			Class.forName(driverJdbc);
			conn = DriverManager.getConnection(url, user, passwd);
			return conn;
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean testeConexao() {
		Boolean statusConexao = false;
		try {			
			Connection conn = conectar();
			
			if(Optional.ofNullable(conn).isPresent()) {
				statusConexao = true;
				conn.close();
			}
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		return statusConexao;
	}
	
	public void insert(Contato contato) {
		String query = 
				  "insert into contatos "
				+ "(nome, fone, email) "
				+ "values (?, ?, ?)";
		
		try {
			
			Connection conn = conectar();
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			
			ps.executeUpdate();
			
			conn.close();
			
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
	}
	
	public List<Contato> contatos() {
		List<Contato> contatos = new ArrayList<>();
		
		String query = 
				"select 				"
				+ " id, 				"
				+ "	nome,				"
				+ "	fone,				"
				+ "	email				"
				+ "	from contatos		"
				+ " order by nome asc	";
		
		try {
			Connection conn = conectar();
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Long id = rs.getLong(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				
				contatos.add(new Contato(id, nome, fone, email));
			}
			
			conn.close();
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		return contatos;
	}
	
	public Contato getById(Contato contato) {
		StringBuilder query = new StringBuilder();
		
		query.append(
				"select "
				+ "	id,"
				+ "	nome,"
				+ "	fone,"
				+ "	email"
				+ "	from contatos"
				+ "	where id = ?;"
				);
		
		try {
			Connection conn = conectar();
			
			PreparedStatement ps = conn.prepareStatement(query.toString());
			
			ps.setLong(1, contato.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				contato.setId(rs.getLong(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		
		return contato;
	}
	
	public void update(Contato contato) {
		String query = 
				"update contatos"
				+ "	set "
				+ " 		  nome = ?"
				+ " 		, fone = ?"
				+ " 		, email = ?"
				+ "	where id = ?"
				;
		
		try {
			Connection conn = conectar();
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			ps.setLong(	 4, contato.getId());
			
			ps.executeUpdate();
			
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
