package net.codejava.hibernate;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * BookManager.java
 * A Hibernate hello world program
 * @author www.codejava.net
 *
 */
public class BookManager {
	protected SessionFactory sessionFactory;

	protected void setup() {
		
		System.out.println("################");
		
		/*Configuration cfg = new Configuration()
				.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")  
				.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@BL4UL20J:1570:L118DB")  
				.setProperty("hibernate.connection.username", "crmuser")  
				.setProperty("hibernate.connection.password", "crmuser")  
				.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");  
		cfg.addAnnotatedClass(Book.class);
		sessionFactory = cfg.buildSessionFactory();  
		*/
		
		//Session session = sessionFactory.openSession();  
        
		String hibernatePropsFilePath = "D:\\\\Softwares\\\\Eclipse\\\\workspace_study\\\\HibernateHelloExample\\\\src\\\\main\\\\resources\\\\hibernate.cfg.xml";

		File hibernatePropsFile = new File(hibernatePropsFilePath);
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure(hibernatePropsFile) // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			
			System.out.println("################");
		} catch (Exception ex) {
			ex.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected void exit() {
		sessionFactory.close();
	}

	protected void create() {
		Book book = new Book();
		book.setTitle("Effective Java");
		book.setAuthor("Joshua Bloch");
		book.setPrice(32.59f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);
		System.out.println("-->"+book.getId());
		session.getTransaction().commit();
		session.close();
	}

	protected void read() {
		Session session = sessionFactory.openSession();

		long bookId = 1;
		Book book = session.get(Book.class, bookId);

		System.out.println("Title: " + book.getTitle());
		System.out.println("Author: " + book.getAuthor());
		System.out.println("Price: " + book.getPrice());

		session.close();
	}

	protected void update() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Ultimate Java Programming");
		book.setAuthor("Nam Ha Minh");
		book.setPrice(19.99f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void delete() {
		Book book = new Book();
		book.setId(20);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(book);

		session.getTransaction().commit();
		session.close();
	}
	
	public static void main(String[] args) {
		System.out.println("################ 1");
		
		BookManager manager = new BookManager();
		System.out.println("################ 2");
		manager.setup();
		System.out.println("################ 3");
		manager.create();
		System.out.println("################ 4");		
		manager.update();
		System.out.println("################ 5");
		manager.read();
		System.out.println("################ 6");
		//manager.exit();
	}

}
