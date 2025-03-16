package com.student.services;

import com.student.dao.StudentDao;
import com.student.exception.StudentException;
import com.student.models.Student;
import java.util.List;

/**
        * StudentService handles business operations related to students,
        * including registration, retrieval, update, and deletion.
        */
public class StudentService {
        private StudentDao studentDao = new StudentDao();

        /**
         * Registers a new student in the database and returns the generated student ID.
         *
         * @param student the Student object containing the student's details.
         * 
         * @return the generated student ID.
         * 
         * @throws StudentException if registration fails.
         */
        public int  registerStudent(Student student) throws StudentException {
                return studentDao.registerStudent(student);
        }



        /**
         * Retrieves all student records.
         *
         * @return a list of Student objects.
         * @throws StudentException if retrieval fails.
         */
        public List<Student> getAllStudents() throws StudentException {
                try {
                        return studentDao.getAllStudents();
                } catch (StudentException se) {
                        throw new StudentException("Error fetching student records: " + se.getMessage());
                }
        }

        /**
         * Retrieves a student by their ID.
         *
         * @param id the student ID.
         * @return the Student object.
         * @throws StudentException if retrieval fails.
         */
        public Student getStudentById(int id) throws StudentException {
                try { 
                        return studentDao.getStudentById(id);
                } catch (StudentException se) {
                        throw new StudentException("Error fetching student with ID: " + id + "." + se.getMessage());
                }
        }

        /**
         * Checks if a student exists with the given ID.
         *
         * @param studentId the student ID.
         * @return true if the student exists; false otherwise.
         * @throws StudentException if an error occurs.
         */
        public boolean isStudentExists(int studentId) throws StudentException {
                try {
                        return studentDao.isStudentExists(studentId);
                } catch (StudentException se) {
                        throw new StudentException("Error checking if student with ID: " + studentId + "exists:" + se.getMessage());
                }
        }

        /**
         * Updates an existing student record.
         *
         * @param student the Student object with updated details.
         * @throws StudentException if an error occurs during update.
         */
        public void updateStudent(Student student) throws StudentException {
                try { 
			studentDao.updateStudent(student);
		} catch (StudentException se) {
			throw new StudentException("Error updating student records: " + se.getMessage());
		}
        }

        /**
         * Deletes a student record.
         *
         * @param student the Student object to delete.
         * @throws StudentException if an error occurs during deletion.
         */
        public void deleteStudent(int studentId) throws StudentException {
                try {
                        studentDao.deleteStudent(studentId);
		} catch (StudentException se) {
			throw new StudentException ("Error deleting student records: " + se.getMessage());
		}                
        }
}
