package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import springbook.user.domain.User;

public class UserDao {
	
//	private ConnectionMaker connectionMaker;		//(외부에서 들어오는) ConnectionMaker 구현 객체를 담는 인터페이스 참조변수
//	자바에서는 DB커넥션을 가져오는 오브젝트의 기능을 이미 DataSource라는 인터페이스로 만들어두었다. 이를 이용해보자
//	DataSource는 인터페이스이므로 구현클래스가 필요하다 -> 그 중에 SimpleDriverDataSource클래스를 이용해보자
		private DataSource dataSource;
	
//	public UserDao(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
//	의존관계 주입을 꼭 생성자로만 할 수있는 것은 아니며, 일반 메소드로 의존관계를 주입받는게 더 보편적이다
//	-> 이 때 쓰는 메소드가 set메소드 = 수정자 메소드
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c = dataSource.getConnection();
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
	
}
