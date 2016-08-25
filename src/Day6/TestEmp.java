package Day6;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class TestEmp {
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
		TestEmp tc = new TestEmp();
		tc.setup();
		
		Session session = tc.factory.openSession();
		Transaction tx = session.beginTransaction();
				
        //Employee e1 = new Employee("Awantik","Das",new Date(),"7022251144");
		Employee e1 = (Employee)session.get(Employee.class, new Long(1));
		
		session.evict(e1);
		Employee e2 = (Employee)session.get(Employee.class, new Long(1));
		
		//session.save(e1);
		tx.commit();
		session.close();
		

	}

}
