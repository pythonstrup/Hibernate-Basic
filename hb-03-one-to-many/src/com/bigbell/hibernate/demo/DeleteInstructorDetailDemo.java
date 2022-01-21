package com.bigbell.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bigbell.hibernate.demo.entity.Instructor;
import com.bigbell.hibernate.demo.entity.InstructorDetail;
import com.bigbell.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor detail object
			int id = 3;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, id);
			
			// print the instructor detail
			System.out.println("tempInstructionDetail: " + tempInstructorDetail);
			
			
			// print the associated instructor
			System.out.println("the associated instructor: " +
								tempInstructorDetail.getInstructor());
			
			
			// now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
			// remove the associated object reference
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// handle connection leak issue
			session.close();
			
			
			factory.close();
		}
	}

}
