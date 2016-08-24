package Day5;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Day4.TestCompoundCode;

public class TestListCollection {
	
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
		TestListCollection tc = new TestListCollection();
		tc.setup();
		
		Session session = tc.factory.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Car> cars = new ArrayList<>(); 
		cars.add(new Car("Ford","White"));
		cars.add(new Car("Fiat","Yellow"));
		cars.add(new Car("Maruti","Red"));
		
//		Showroom showroom = new Showroom();
//		showroom.setCars(cars);
		
		CarParlour carparlour = new CarParlour();
		carparlour.setCars(cars);
		session.save(carparlour);
		
		List<Car> newcars = new ArrayList<>(); 
		newcars.add(new Car("Ford","White"));
		newcars.add(new Car("Fiat","Yellow"));
		newcars.add(new Car("Maruti","Red"));
		
		CarParlour newcarparlour = new CarParlour();
		newcarparlour.setCars(newcars);
		
		//session.save(showroom);
		
		session.save(newcarparlour);
		tx.commit();
		session.close();
		
	}

}
