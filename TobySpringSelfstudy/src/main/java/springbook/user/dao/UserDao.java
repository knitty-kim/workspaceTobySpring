package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import springbook.user.domain.User;

public class UserDao {
	
//	private ConnectionMaker connectionMaker;		//(�ܺο��� ������) ConnectionMaker ���� ��ü�� ��� �������̽� ��������
//	�ڹٿ����� DBĿ�ؼ��� �������� ������Ʈ�� ����� �̹� DataSource��� �������̽��� �����ξ���. �̸� �̿��غ���
//	DataSource�� �������̽��̹Ƿ� ����Ŭ������ �ʿ��ϴ� -> �� �߿� SimpleDriverDataSourceŬ������ �̿��غ���
		private DataSource dataSource;
	
//	public UserDao(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
//	�������� ������ �� �����ڷθ� �� ���ִ� ���� �ƴϸ�, �Ϲ� �޼ҵ�� �������踦 ���Թ޴°� �� �������̴�
//	-> �� �� ���� �޼ҵ尡 set�޼ҵ� = ������ �޼ҵ�
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
