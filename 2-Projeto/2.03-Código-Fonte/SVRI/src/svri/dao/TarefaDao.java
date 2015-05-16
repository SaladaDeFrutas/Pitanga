package svri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TarefaDao {
	
	private Connection con;
	
	public TarefaDao(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Tarefa tarefa){
		
		String sql = "insert into tarefas " +
					"(descricao,finalizado) " +
					"values (?,?)";
		
		try{
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			//stmt.setDate(3, new Date(
							//tarefa.getDataFinalizacao().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	public void exclui(Tarefa tarefa){
		String sql = "delete from tarefas where id=?";
		
		try{
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, tarefa.getId());
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Tarefa buscaPorId(Long id){
		String sql = "select * from tarefas where id = ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			Tarefa tarefa = new Tarefa();
			while(rs.next()){
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setFinalizado(rs.getBoolean("finalizado"));
			tarefa.setId(rs.getLong("id"));
			Calendar data = Calendar.getInstance();
			Date resultData = rs.getDate("dataFinalizacao");
			
			if (resultData != null) {  
				data.setTime(resultData);   

				tarefa.setDataFinalizacao(data);
			}
			}
			stmt.close();
			rs.close();
			return tarefa;
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void altera(Tarefa tarefa){
		exclui(buscaPorId(tarefa.getId()));
		adiciona(tarefa);
	}
	
	public List<Tarefa> getLista(){
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tarefas;");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Tarefa tarefa = new Tarefa();
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				tarefa.setId(rs.getLong("id"));
				Calendar data = Calendar.getInstance();
				Date resultData = rs.getDate("dataFinalizacao");				
				if (resultData != null) {  
				    data.setTime(resultData);   
				  
				tarefa.setDataFinalizacao(data);
				}
				tarefas.add(tarefa);
			}
			
			stmt.close();
			rs.close();
			return tarefas;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
