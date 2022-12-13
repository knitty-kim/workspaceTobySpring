package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		//UserDao dao = new DaoFactory().userDao();		//DaoFactory가 UserDao객체를 생성하게하여 받아 쓰기만 하면된다
		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		//어플리케이션 컨텍스트에 DaoFactory를 설정정보로 사용
		//applicationContext.xml과 GenericXmlApplicationContext를 사용하면서 아래와 같이 작성
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		//어플리케이션 컨텍스트의 메소드 getBean을 사용해서 userDao라는 이름의 메소드를 DaoFactory에서 실행해서 가져온다는 것
		
		User user = new User();
		user.setId("lastTest");
		user.setName("1장테스트");
		user.setPassword("1234");
		
		
		dao.add(user);
		System.out.println(user.getId() + " 등록 성공!");		//add메소드 test
		
		
		User user2 = dao.get("lastTest");
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user.getId() + " 조회 성공!");		//get메소드 test
		
	}

}
