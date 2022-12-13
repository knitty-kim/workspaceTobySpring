package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {			//��ü�� �����ϰ� �����ϴ� ���� -> ���丮
													//UserDaoTest���� ���� UserDao�� ������ �ʿ䰡 �����
													
	
//	public UserDao userDao() {	//UserDao��ü�� �����ϴ� �޼ҵ�
//		ConnectionMaker connectionMaker = new DConnectionMaker();
//		UserDao userDao = new UserDao(connectionMaker);
//		return userDao;
//	}

//�ٸ� Dao���� �����Ѵٸ�? -> �� �ڵ� �ߺ� -> �޼ҵ� �̱�

//	public UserDao userDao() {	//UserDao��ü�� �����ϴ� �޼ҵ�
//		return new UserDao(connectionMaker());
//	}


	
//DaoFactory�� ���ø����̼� ���ؽ�Ʈ�� ���� ������ �������� -> @��, @���ǱԷ��̼� �̿��� �����丮�� ����

	
//	@Bean
//	public ConnectionMaker connectionMaker() {
//		return new DConnectionMaker();
//	}
//	DataSource�������̽��� �̿��ϸ鼭 �Ʒ��� ���� �����ȴ�
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("scott");
		dataSource.setPassword("tiger");
		
		return dataSource;
	}
//	
//
//	@Bean
//	public UserDao userDao() {
//		//return new UserDao(connectionMaker());
//		//�����ڷ� �������踦 ���Թ޴� �� ������ �޼ҵ�� ���Թ����� �ְ� ����
//		
//		UserDao userDao = new UserDao();
//		userDao.setConnectionMaker(connectionMaker());
//		return userDao;
//	}		
//	DataSource�������̽��� �̿��ϸ鼭 �Ʒ��� ���� �����ȴ�
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}
}
