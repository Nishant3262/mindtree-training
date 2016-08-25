package Day6;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class TestGoodEmployee {
	
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
		TestGoodEmployee tc = new TestGoodEmployee();
		tc.setup();
		
		Session session = tc.factory.openSession();
		Transaction tx = session.beginTransaction();
		
		GoodEmployee e1 = new GoodEmployee("Awantik","Das",new Date(),"7022251144");
		
		session.save(e1);
		tx.commit();
		session.close();
	}

}
