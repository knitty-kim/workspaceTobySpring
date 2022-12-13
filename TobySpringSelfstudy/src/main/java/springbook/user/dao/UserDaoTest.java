package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		//UserDao dao = new DaoFactory().userDao();		//DaoFactory�� UserDao��ü�� �����ϰ��Ͽ� �޾� ���⸸ �ϸ�ȴ�
		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		//���ø����̼� ���ؽ�Ʈ�� DaoFactory�� ���������� ���
		//applicationContext.xml�� GenericXmlApplicationContext�� ����ϸ鼭 �Ʒ��� ���� �ۼ�
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		//���ø����̼� ���ؽ�Ʈ�� �޼ҵ� getBean�� ����ؼ� userDao��� �̸��� �޼ҵ带 DaoFactory���� �����ؼ� �����´ٴ� ��
		
		User user = new User();
		user.setId("lastTest");
		user.setName("1���׽�Ʈ");
		user.setPassword("1234");
		
		
		dao.add(user);
		System.out.println(user.getId() + " ��� ����!");		//add�޼ҵ� test
		
		
		User user2 = dao.get("lastTest");
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user.getId() + " ��ȸ ����!");		//get�޼ҵ� test
		
	}

}
