package com.bigbell.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bigbell.hibernate.demo.entity.Course;
import com.bigbell.hibernate.demo.entity.Instructor;
import com.bigbell.hibernate.demo.entity.InstructorDetail;
import com.bigbell.hibernate.demo.entity.Review;
import com.bigbell.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the student mary from database
			int id = 2;
			Student mary = session.get(Student.class, id);
			
			System.out.println("\nLoaded student: " + mary);
			System.out.println("Course: " + mary.getCourses());
			
			// create more courses
			Course course1 = new Course("Rubik's cube - How to speed cube");
			Course course2 = new Course("Atari 2600 - Game Development");
			
			// add student to courses
			course1.addStudent(mary);
			course2.addStudent(mary);
			
			// save the courses
			System.out.println("\nSaving the courses ...");
			session.save(course1);
			session.save(course2);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
