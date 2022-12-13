package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {			//객체를 생성하고 리턴하는 역할 -> 팩토리
													//UserDaoTest에서 직접 UserDao를 생성할 필요가 사라짐
													
	
//	public UserDao userDao() {	//UserDao객체를 생성하는 메소드
//		ConnectionMaker connectionMaker = new DConnectionMaker();
//		UserDao userDao = new UserDao(connectionMaker);
//		return userDao;
//	}

//다른 Dao들을 생성한다면? -> 또 코드 중복 -> 메소드 뽑기

//	public UserDao userDao() {	//UserDao객체를 생성하는 메소드
//		return new UserDao(connectionMaker());
//	}


	
//DaoFactory를 어플리케이션 컨텍스트의 설정 정보로 설정하자 -> @빈, @컨피규레이션 이용해 빈팩토리로 변경

	
//	@Bean
//	public ConnectionMaker connectionMaker() {
//		return new DConnectionMaker();
//	}
//	DataSource인터페이스를 이용하면서 아래와 같이 수정된다
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
//		//생성자로 의존관계를 주입받던 걸 수정자 메소드로 주입받을수 있게 변경
//		
//		UserDao userDao = new UserDao();
//		userDao.setConnectionMaker(connectionMaker());
//		return userDao;
//	}		
//	DataSource인터페이스를 이용하면서 아래와 같이 수정된다
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}
}
