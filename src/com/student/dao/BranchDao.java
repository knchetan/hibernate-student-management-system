package com.student.dao;

import com.student.exception.StudentException;
import com.student.models.Branch;
import com.student.util.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
        * BranchDao handles CRUD operations for the Branch entity using Hibernate.
        */
public class BranchDao
{
        /**
                * Saves a new branch record.
                *
                * @param branch the Branch object to be saved.
                * @throws StudentException if an error occurs.
                */
        public void saveBranch(Branch branch) throws StudentException
        {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {

                        tx = session.beginTransaction();
                        session.save(branch);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null) tx.rollback();
                        throw new StudentException("Error saving branch: " + se.getMessage());
                }
        }

        /**
                * Retrieves all branch records.
                *
                * @return a list of Branch objects.
                * @throws StudentException if an error occurs.
                */
        //@SuppressWarnings("unchecked")
        public List<Branch> getAllBranches() throws StudentException
        {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return session.createQuery("from Branch").list();
                } catch (Exception se) {
                        throw new StudentException("Error retrieving branches: " + se.getMessage());
                }
        }
        
        /**
                * Retrieves a branch by ID.
                *
                * @param id the branch ID.
                * @return the Branch object.
                * @throws StudentException if an error occurs.
                */
        public Branch getBranchById(int id) throws StudentException
        {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return session.get(Branch.class, id);
                } catch (Exception se) {
                        throw new StudentException("Error retrieving branch with ID " + id + ": " + se.getMessage());
                }
        }
        
        /**
                * Retrieves a branch by name.
                *
                * @param branchName the branch name.
                * @return the Branch object.
                * @throws StudentException if an error occurs.
                */
        public Branch getBranchByName(String branchName) throws StudentException
        {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return (Branch) session.createQuery("from Branch where branchName = :name")
                                .setParameter("name", branchName)
                                .uniqueResult();
                } catch (Exception se) {
                        throw new StudentException("Error retrieving branch with name " + branchName + ": " + se.getMessage());
                }
        }
        
        /**
                * Updates an existing branch record.
                *
                * @param branch the Branch object with updated details.
                * @throws StudentException if an error occurs.
                */
        public void updateBranch(Branch branch) throws StudentException
        {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        session.update(branch);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null) tx.rollback();
                        throw new StudentException("Error updating branch: " + se.getMessage());
                }
        }
        
        /**
                * Deletes a branch record.
                *
                * @param branchId the Branch ID used to delete.
                * @throws StudentException if an error occurs.
                */
        public int  deleteBranch(int branchId) throws StudentException
        {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        session.delete(branchId);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null) tx.rollback();
                        throw new StudentException("Error deleting branch: " + se.getMessage());
                }
            return 0;
        }
}
