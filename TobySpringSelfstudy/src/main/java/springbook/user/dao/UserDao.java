package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDao {
	
	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDao() {
		simpleConnectionMaker = new SimpleConnectionMaker();
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c = simpleConnectionMaker.makeNewConnection();
		PreparedStatement ps = c.prepareStatement("insert into user(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(1, user.getName());
		ps.setString(1, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c = simpleConnectionMaker.makeNewConnection();
		PreparedStatement ps = c.prepareStatement("select * from users where id=?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("Gitsun");
		user.setName("�����");
		user.setPassword("1234");
		
		dao.add(user);
		
		System.out.println(user.getId() + " ��� ����!");
		
		User user2 = dao.get("Gitsun");
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user.getId() + " ��ȸ ����!");
		
	}
}