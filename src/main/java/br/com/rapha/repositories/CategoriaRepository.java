package br.com.rapha.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rapha.entities.Categoria;
import br.com.rapha.factories.ConnectionFactory;

public class CategoriaRepository {
	
	public void insert(Categoria categoria)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO categoria (id, nome) values (?, ?)");
		
		statement.setObject(1, categoria.getId());
		statement.setObject(2, categoria.getNome());
		
		statement.execute();
		
		
		connection.close();
				
	}
	public void delete(Categoria categoria)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM categoria WHERE id = ?");
		statement.setObject(1, categoria.getId());
		
		statement.execute();
		
		
		connection.close();
		
	}
	
	public List<Categoria> findAll()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM categoria ORDER BY nome");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Categoria> list = new ArrayList<Categoria>();
		
		while (resultSet.next()) {
			
			Categoria categoria = new Categoria();
			
			
			categoria.setId(UUID.fromString(resultSet.getString("id")));
			categoria.setNome(resultSet.getString("nome"));
			
			list.add(categoria);
			
		}
		
		connection.close();
		
		
		return list;
		
		
	}

}
