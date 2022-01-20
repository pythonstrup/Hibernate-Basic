package com.bigbell.hibernate.demo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bigbell.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> students = session.createQuery("from Student").list();
			
			// display the students
			displayStudents(students);
			
			// query students: lastName="Doe"
			students = session.createQuery("from Student s where s.lastName='Doe'").list();
			
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(students);
			
			// query students: lastName='Doe' OR firstName='Daffy'
			students = session.createQuery("from Student s where "
						+ " s.lastName='Doe' OR s.firstName='Daffy'").list();
			
			System.out.println("\n\nStudents who have last name of Doe OR first name of Daffy");
			displayStudents(students);
			
			// query students where email LIKE '%luv2code.com'
			students = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").list();
			
			System.out.println("\n\nStudents who email ends with luv2code.com");
			displayStudents(students);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> Students) {
		for (Student temp: Students) {
			System.out.println(temp);
		}
	}

}
