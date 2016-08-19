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
	
	public void createData(Session session, String subjectName, String observerName, String skillName, int rank){
		Student subject = saveStudent(session,subjectName);
		Student observer = saveStudent(session,observerName);
		Skill skill = saveSkill(session,skillName);
		
		Ranking ranking = new Ranking();
		ranking.setSubject(subject);
		ranking.setObserver(observer);
		ranking.setSkill(skill);
		ranking.setRating(rank);
		
		session.save(ranking);
		
	}
	
	public void changeRank(Session session, String subjectName, String observerName, String skillName, int newRating){
		Query query = session.createQuery("from Ranking r "
				+ "where r.subject.name=:subject and "
				+ "r.observer.name=:observer and "
				+ "r.skill.name=:skill");
		query.setString("subject", subjectName);
		query.setString("observer",observerName);
		query.setString("skill", skillName);
		
		Ranking ranking = (Ranking) query.uniqueResult();
		
		if(ranking == null){
			System.out.println("Invalid Change Request");
		}
		
		ranking.setRating(newRating);
		
	    
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestAssignment tp = new TestAssignment();
		tp.setup();
		
		Session session = tp.factory.openSession();
		Transaction tx = session.beginTransaction();
		
		// Add ranks
//		tp.createData(session, "Amit","Vijay","Python",5);
//		tp.createData(session, "Ajit","Nilesh","Django",9);
		//tp.createData(session, "Amway","Dash","Spring",8);
		
		tp.changeRank(session, "Amway","Dash","Spring",12);
		// Update rating assigned by Jack Sparrow to Awantik Das
		
//		session.evict(arg0);
//		session.load(arg0, arg1)
//		session.refresh(arg0);
//		session.merge(arg0)
//		session.persist(arg0);
//		session.save(arg0)
		
		//tx.commit();
		//session.close();

	}

}
