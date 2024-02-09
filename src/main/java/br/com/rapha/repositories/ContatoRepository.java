package br.com.rapha.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rapha.entities.Categoria;
import br.com.rapha.entities.Contato;
import br.com.rapha.factories.ConnectionFactory;

public class ContatoRepository {

	public void insert(Contato contato)throws Exception{
	
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO contato (id, nome, telefone, email, categoria_id) VALUES (?, ?, ?, ?, ?)");
		
		statement.setObject(1, contato.getId());
		statement.setObject(2, contato.getNome());
		statement.setObject(3, contato.getTelefone());
		statement.setObject(4, contato.getEmail());
		statement.setObject(5, contato.getCategoria().getId());
		
		statement.execute();
		
		connection.close();
	}
	public void update(Contato contato)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("UPDATE contato SET nome=?, telefone=?, email=?, categoria_id=? WHERE id=?");
		statement.setObject(1, contato.getNome());
		statement.setObject(2, contato.getTelefone());
		statement.setObject(3, contato.getEmail());
		statement.setObject(4, contato.getCategoria().getId());
		statement.setObject(5, contato.getId());
		
		statement.execute();
		
		connection.close();
		
		
	}
	public void delete(Contato contato)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM contato WHERE id = ?");
		
		statement.setObject(1, contato.getId());
		
		statement.execute();
		
		connection.close();
		
	}
	public List<Contato> findAll()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM contato ORDER BY nome");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Contato> lista = new ArrayList<Contato>();
		
		while(resultSet.next()) {
			
			Contato contato = new Contato();
			contato.setCategoria(new Categoria());
			
			contato.setId(UUID.fromString(resultSet.getString("id")));
			contato.setNome(resultSet.getString("nome"));
			contato.setTelefone(resultSet.getString("telefone"));
			contato.setEmail(resultSet.getString("email"));
			contato.getCategoria().setId(UUID.fromString(resultSet.getString("categoria_id")));
			
			lista.add(contato);
			
		}
		
		connection.close();
		
		return lista;
	}
	public Contato findById(UUID id)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM contato WHERE id = ?");
		
		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Contato contato = null;
		
		if (resultSet.next()) {
			
			contato = new Contato();
			
			contato.setId(UUID.fromString(resultSet.getString("id")));
			contato.setNome(resultSet.getString("nome"));
			
		}
		
		connection.close();
		return contato;
	}
}
