package com.bigbell.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bigbell.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			//create 3 student objects
			System.out.println("Creating new student object...");
			Student temp1 = new Student("John", "Doe", "john@luv2code.com");
			Student temp2 = new Student("Mary", "Public", "mary@luv2code.com");
			Student temp3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student information...");
			session.save(temp1);
			session.save(temp2);
			session.save(temp3);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
