package com.student.services;

import com.student.dao.BranchDao;
import com.student.exception.StudentException;
import com.student.models.Branch;
import java.util.List;

/**
        * BranchService handles business operations related to branch records,
        * including creation, retrieval, update, and deletion.
        */
public class BranchService {
        private BranchDao branchDao = new BranchDao();

        /**
         * Adds a new branch record.
         *
         * @param branchName the branch name.
         * @throws StudentException if an error occurs while adding.
         */
        public void addBranch(String branchName) throws StudentException {
                try {
                        Branch branch = new Branch(branchName);
                        branchDao.saveBranch(branch);
                } catch (StudentException se) {
                        throw new StudentException("Error adding branch to database.");
                }
        }

        /**
         * Checks if a branch exists by its name.
         *
         * @param branchName the branch name.
         * @return true if the branch exists; false otherwise.
         * @throws StudentException if an error occurs.
         */
        public boolean isBranchExistsByName(String branchName) throws StudentException {
                try {
                        Branch b = branchDao.getBranchByName(branchName);
                        return (b != null);
                } catch (StudentException se) {
                        throw new StudentException("No branch found with name: " + branchName);
                }
        }

        /**
         * Checks if any branch records exist.
         *
         * @return true if at least one branch exists; false otherwise.
         * @throws StudentException if an error occurs.
         */
        public boolean isBranchExists() throws StudentException {
                try {
                        List<Branch> branches = branchDao.getAllBranches();
                        return (branches != null && !branches.isEmpty());
                } catch (StudentException se) {
                        throw new StudentException("Branch does not exist in database.");
                }
        }

        /**
         * Retrieves all branch records.
         *
         * @return a list of Branch objects.
         * @throws StudentException if an error occurs.
         */
        public List<Branch> getAllBranches() throws StudentException {
                try {
                        return branchDao.getAllBranches();
                } catch (StudentException se) {
                        throw new StudentException("No branch records retrieved.");
                }
        }

        /**
         * Retrieves the branch ID for a given branch name.
         *
         * @param branchName the branch name.
         * @return the branch ID.
         * @throws StudentException if the branch is not found.
         */
        public int getBranchByName(String branchName) throws StudentException {
                try {
                        Branch branch = branchDao.getBranchByName(branchName);
                        if (branch != null) {
                                return branch.getBranchId();
                        }
                } catch (StudentException se) {
                        throw new StudentException("No branch found with name: " + branchName);
                }
            return 0;
        }

        /**
         * Retrieves the branch name for a given branch ID.
         *
         * @param branchId the branch ID.
         * @return the branch name.
         * @throws StudentException if the branch is not found.
         */
        public String getBranchById(int branchId) throws StudentException {
                try {
                        Branch branch = branchDao.getBranchById(branchId);
                        if (branch != null) {
                                return branch.getBranchName();
                        }
                } catch (StudentException se) {
                        throw new StudentException("No branch found with ID: " + branchId);
                }
            return null;
        }

        /**
         * Updates an existing branch record.
         *
         * @param branch the Branch object with updated details.
         * @throws StudentException if an error occurs during update.
         */
        public void updateBranch(Branch branch) throws StudentException {
                try {
                        branchDao.updateBranch(branch);
                } catch (StudentException se) {
                        throw new StudentException("Error updating branch records: " + se.getMessage());
                }
        }

        /**
         * Deletes a branch record.
         *
         * @param branch the Branch object to delete.
         * @throws StudentException if an error occurs during deletion.
         */
        public void deleteBranch(int branchId) throws StudentException {
                try {
                        branchDao.deleteBranch(branchId);
                } catch (StudentException se) {
                        throw new StudentException("Error deleting branch records: " + se.getMessage());
                }
        }
}
