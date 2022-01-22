package com.bigbell.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bigbell.hibernate.demo.entity.Course;
import com.bigbell.hibernate.demo.entity.Instructor;
import com.bigbell.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			// get the instructor from db
			int id = 1;
			Instructor tempInstructor = session.get(Instructor.class, id);
			
			System.out.println("bigbell: Instructor: " + tempInstructor);
			
			System.out.println("bigbell: Courses: " + tempInstructor.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\nbigbell: The session is now closed!~\n");
			
			// option1: call getter method while session is open
			
			// since courses are lazy loaded .. this should fail
			
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
