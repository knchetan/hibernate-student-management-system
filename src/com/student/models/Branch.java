package com.student.models;

import java.util.Set;

/**
 *    Represents a branch entity.
 */
public class Branch
{
        private int branchId;
        private String branchName;
        private Set<Student> students;
        
        public Branch() {}
        
        public Branch(String branchName) {
                this.branchName = branchName;
        }
        
        public Branch(int branchId, String branchName) {
                this.branchId = branchId;
                this.branchName = branchName;
        }
        
        public int getBranchId() {
                return branchId;
        }
        
        public void setBranchId(int branchId) {
                this.branchId = branchId;
        }
        
        public String getBranchName() {
                return branchName;
        }
        
        public void setBranchName(String branchName) {
                this.branchName = branchName;
        }
        
        public Set<Student> getStudents() {
                return students;
        }

        public void setStudents(Set<Student> students) {
                this.students = students;
        }
        
        @Override
        public String toString() {
                return "Branch [branchId=" + branchId + ", branchName=" + branchName + "]";
        }
}
