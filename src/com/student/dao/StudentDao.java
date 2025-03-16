package com.student.dao;

import com.student.exception.StudentException;
import com.student.models.Student;
import com.student.util.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
        * StudentDao handles CRUD operations for the Student entity using Hibernate.
        * The Student entity directly contains its associations to Branch and Event
        * (as defined in the XML mapping files), so no intermediate join classes are used.
        */
public class StudentDao {
        
        /**
         * Registers a new student by saving the Student object into the database.
         * The Student object includes direct associations to Branch and a collection of Event objects.
         *
         * @param student the Student object containing the student's details.
         * @return the generated student ID.
         * @throws StudentException if an error occurs during registration.
         */
        public int registerStudent(Student student) throws StudentException {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        int id = (int) session.save(student);
                        tx.commit();
                        return id;
                } catch (Exception se) {
                        if (tx != null) {
                                tx.rollback();
                        }
                        throw new StudentException("Failed to register student: " + se.getMessage());
                }
        }

        /**
         * Checks if a student exists with the specified student ID.
         *
         * @param studentId the ID of the student to check.
         * @return true if the student exists; false otherwise.
         * @throws StudentException if an error occurs during retrieval.
         */
        public boolean isStudentExists(int studentId) throws StudentException {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        Student student = session.get(Student.class, studentId);
                        return student != null;
                } catch (Exception se) {
                        throw new StudentException("Error checking if student exists with ID: " + studentId + ". " + se.getMessage());
                }
        }

        
        /**
         * Retrieves a student record by its ID.
         *
         * @param studentId the student ID.
         * @return the Student object, or null if not found.
         * @throws StudentException if an error occurs during retrieval.
         */
        public Student getStudentById(int studentId) throws StudentException {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return session.get(Student.class, studentId);
                } catch (Exception se) {
                        throw new StudentException("Error retrieving student record with ID: " + studentId + ". " + se.getMessage());
                }
        }
        
        /**
         * Retrieves all student records from the database.
         *
         * @return a List of Student objects.
         * @throws StudentException if an error occurs during retrieval.
         */
        public List<Student> getAllStudents() throws StudentException {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return session.createQuery("from Student", Student.class).list();
                } catch (Exception se) {
                        throw new StudentException("Failed to retrieve student records: " + se.getMessage());
                }
        }
        
        /**
         * Updates an existing student record.
         *
         * @param student the Student object with updated details.
         * @throws StudentException if an error occurs during update.
         */
        public void updateStudent(Student student) throws StudentException {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        session.update(student);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null) {
                                tx.rollback();
                        }
                        throw new StudentException("Failed to update student: " + se.getMessage());
                }
        }
        
        /**
         * Deletes a student record using the student's ID.
         *
         * @param studentId the ID of the student to delete.
         * @throws StudentException if an error occurs during deletion.
         */
        public void deleteStudent(int studentId) throws StudentException {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        Student student = session.get(Student.class, studentId);
                        if (student == null) {
                                throw new StudentException("Student not found with ID: " + studentId);
                        }
                        session.delete(student);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null) {
                                tx.rollback();
                        }
                        throw new StudentException("Failed to delete student: " + se.getMessage());
                }
        }
}
