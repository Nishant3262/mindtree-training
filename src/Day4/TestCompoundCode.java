package Day4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Day3.Email;
import Day3.Message;
import Day3.TestCode;

public class TestCompoundCode {

	 public SessionFactory factory;
		
	 public void setup(){
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
			srBuilder.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
			factory = configuration.buildSessionFactory(serviceRegistry);
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestCompoundCode tc = new TestCompoundCode();
		tc.setup();
		
		Session session = tc.factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
        customer.name = "Jack";
        
		session.save(customer);
		
		tx.commit();
		session.close();

	}

}
