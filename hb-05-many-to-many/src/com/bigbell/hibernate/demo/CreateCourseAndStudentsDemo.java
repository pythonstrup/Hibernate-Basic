package com.bigbell.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bigbell.hibernate.demo.entity.Course;
import com.bigbell.hibernate.demo.entity.Instructor;
import com.bigbell.hibernate.demo.entity.InstructorDetail;
import com.bigbell.hibernate.demo.entity.Review;
import com.bigbell.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

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
			
			// create a course
			Course tempCourse = new Course("Pacman - How to Score one million points");
			
			// save the course
			System.out.println("\nSaving the course ...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("mary", "public", "mary@luv2code.com");
			
			// add students to the course
			System.out.println("\nSaving students");
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			System.out.println("Saved students: " + tempCourse.getStudents());
			
			// save the students
			session.save(tempStudent1);
			session.save(tempStudent2);
			
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
