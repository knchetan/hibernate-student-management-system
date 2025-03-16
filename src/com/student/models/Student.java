package com.student.models;

import java.sql.Date;
import java.util.Set;

/**
 *    Represents a student entity.
 *    Each student is linked to one branch and can have multiple events.
 */
public class Student
{
        private int id;
        private String firstName;
        private String lastName;
        private String phoneNo;
        private String email;
        private String address;
        private Date dob;
        private int age;
        private Branch branch;
        private Set<Event> events;
        
        public Student() {}
        
        public Student(String firstName, String lastName, String phoneNo, String email, String address, Date dob, int age) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.phoneNo = phoneNo;
                this.email = email;
                this.address = address;
                this.dob = dob;
                this.age = age;
        }
        
        public Student(int id, String firstName, String lastName, String phoneNo, String email, String address, Date dob, int age) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.phoneNo = phoneNo;
                this.email = email;
                this.address = address;
                this.dob = dob;
                this.age = age;
        }
        
        public int getId() {
                return id;
        }
        
        public void setId(int id) {
                this.id = id;
        }
        
        public String getFirstName() {
                return firstName;
        }
        
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }
        
        public String getLastName() {
                return lastName;
        }
        
        public void setLastName(String lastName) {
                this.lastName = lastName;
        }
        
        public String getPhoneNo() {
                return phoneNo;
        }
        
        public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
        }
        
        public String getEmail() {
                return email;
        }
        
        public void setEmail(String email) {
                this.email = email;
        }
        
        public String getAddress() {
                return address;
        }
        
        public void setAddress(String address) {
                this.address = address;
        }
        
        public Date getDob() {
                return dob;
        }
        
        public void setDob(Date dob) {
                this.dob = dob;
        }
        
        public int getAge() {
                return age;
        }
        
        public void setAge(int age) {
                this.age = age;
        }
        
        public Branch getBranch() {
                return branch;
        }
        
        public void setBranch(Branch branch) {
                this.branch = branch;
        }
        
        public Set<Event> getEvents() {
                return events;
        }
        
        public void setEvents(Set<Event> events) {
                this.events = events;
        }
        
        @Override
        public String toString() {
                return "Student: id=" + id 
                        + ", firstName=" + firstName 
                        + ", lastName=" + lastName 
                        + ", phoneNo=" + phoneNo 
                        + ", email=" + email 
                        + ", address=" + address 
                        + ", dob=" + dob 
                        + ", age=" + age ;
        }
}
