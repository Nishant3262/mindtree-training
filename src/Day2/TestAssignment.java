package Day2;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Day1.Employee;
import Day1.TestPerson;

public class TestAssignment {

	SessionFactory factory;
	
	public void setup(){
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	
	public Skill findSkill(Session session, String name){
		Query query = session.createQuery("from Skill s where s.name=:name");
		query.setParameter("name", name);
		Skill skill = (Skill) query.uniqueResult();
		return skill;
	}
	
	public Skill saveSkill(Session session, String name){
		Skill skill = findSkill(session,name); 
		if (skill == null){
			skill = new Skill();
			skill.setName(name);
			session.save(skill);
		}
		return skill;
	}
	
	public Student findStudent(Session session, String name){
		Query query = session.createQuery("from Student s where s.name=:name");
		query.setParameter("name", name);
		Student student = (Student) query.uniqueResult();
		return student;
	}
	
	public Student saveStudent(Session session, String name){
		Student student = findStudent(session,name); 
		if (student == null){
			student = new Student();
			student.setName(name);
			session.save(student);
		}
		return student;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestAssignment tp = new TestAssignment();
		tp.setup();
		
		Session session = tp.factory.openSession();
		Transaction tx = session.beginTransaction();
		Student s1 = tp.saveStudent(session, "Awantik Das");
		Student s2 = tp.saveStudent(session, "Jack Sparrow");
		
		Skill skill = tp.saveSkill(session, "Hibernate");
		
		Ranking ranking = new Ranking();
		ranking.setSubject(s1);
		ranking.setObserver(s2);
		ranking.setSkill(skill);
		ranking.setRating(7);
		
		session.save(ranking);
		
		
		tx.commit();
		session.close();

	}

}
