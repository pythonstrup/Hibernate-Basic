package com.bigbell.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.bigbell.hibernate.demo.entity.Course;
import com.bigbell.hibernate.demo.entity.Instructor;
import com.bigbell.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// option2: Hibernate query with HQL
			
			// get the instructor from db
			int id = 1;

			Query<Instructor> query =
					session.createQuery("select i from Instructor i "
								+ " JOIN FETCH i.courses "
								+ " WHERE i.id=:theInstructorId ",
							Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", id);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("bigbell: Instructor: " + tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\nbigbell: The session is now closed!~\n");
			
			// get course for the instructor
			System.out.println("bigbell: Courses: " + tempInstructor.getCourses());

			
			System.out.println("bigbell: Done!");
			
		} finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
