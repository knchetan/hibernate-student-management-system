package com.student.controllers;

import com.student.exception.StudentException;
import com.student.models.Branch;
import com.student.models.Event;
import com.student.models.Student;
import com.student.services.BranchService;
import com.student.services.EventService;
import com.student.services.StudentService;
import com.student.util.Validator;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class StudentController
{
        private StudentService studentService = new StudentService();
        private BranchService branchService = new BranchService();
        private EventService eventService = new EventService();
        private Scanner scanner = new Scanner(System.in);
        
        /**
         * Starts the main loop for the console application.
         */
        public void start()
        {
                while (true) {
                        showMenu();
                        System.out.print("Enter your choice: ");
                        int choice = getInteger();
                        switch (choice) {
                                case 1:
                                        addBranches();
                                        break;
                                case 2:
                                        addEvents();
                                        break;
                                case 3:
                                        registerStudent();
                                        break;
                                case 4:
                                        viewStudentRecords();
                                        break;
                                case 5:
                                        updateStudent();
                                        break;
                                case 6:
                                        deleteStudent();
                                        break;
                                case 7:
                                        viewStudentBranch();
                                        break;
                                case 8:
                                        updateBranch();
                                        break;
                                case 9:
                                        deleteBranch();
                                        break;
                                case 10:
                                        viewStudentEvents();
                                        break;
                                case 11:
                                        updateEvent();
                                        break;
                                case 12:
                                        deleteEvent();
                                        break;
                                case 13:
                                        System.out.println("Exiting registration. Thank you.");
                                        scanner.close();
                                        System.exit(0);
                                        break;
                                default:
                                        System.out.println("Invalid choice, please try again.");
                        }

                }
        }
        
        /**
         * Displays the main menu options.
         */
        private void showMenu()
        {
                System.out.println("\n----- Student Registration Menu -----");
                System.out.println("1. Add branches");
                System.out.println("2. Add events");
                System.out.println("3. Register Student");
                System.out.println("4. View Student Records");
                System.out.println("5. Update Student Records");
                System.out.println("6. Delete Student Records");
                System.out.println("7. View Branch of Student");
                System.out.println("8. Update Branch");
                System.out.println("9. Delete Branch");
                System.out.println("10. View Events of Student");
                System.out.println("11. Update Events");
                System.out.println("12. Delete Events");
                System.out.println("13. Exit Registration");
        }
        
        /**
         * Prompts the user with a message and returns the input string.
         *
         * @param prompt the message to display.
         * @return the user input.
         */
        private String getInput(String prompt)
        {
                System.out.print(prompt);
                return scanner.nextLine();
        }
        
        /**
         * Prompts the user until a valid integer is provided.
         *
         * @return the integer value.
         */
        private int getInteger()
        {
                String input = scanner.nextLine();
                while (!Validator.isValidInteger(input)) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        input = scanner.nextLine();
                }
                return Integer.parseInt(input);
        }
        
        /**
         * Prompts the user until a valid non-empty string is provided.
         *
         * @return the validated string.
         */
        private String getString()
        {
                String input = scanner.nextLine();
                while (!Validator.isValidString(input)) {
                        System.out.println("Invalid input. Please enter a valid string.");
                        input = scanner.nextLine();
                }
                return input;
        }
        
        /**
         * Prompts the user until a valid phone number (10 digits) is provided.
         *
         * @return the validated phone number.
         */
        private String getPhone()
        {
                String input = scanner.nextLine();
                while (!Validator.isValidPhone(input)) {
                        System.out.println("Invalid phone number. Please enter 10 digits only.");
                        input = scanner.nextLine();
                }
                return input;
        }
        
        /**
         * Prompts the user until a valid email address is provided.
         *
         * @return the validated email address.
         */
        private String getEmail()
        {
                String input = scanner.nextLine();
                while (!Validator.isValidEmail(input)) {
                        System.out.println("Invalid email format. Please enter a valid email address (e.g. user@gmail.com).");
                        input = scanner.nextLine();
                }
                return input;
        }
        
        /**
         * Prompts the user until a valid date (yyyy-MM-dd) is provided.
         *
         * @return the validated Date.
         */
        private Date getDate()
        {
                String input = scanner.nextLine();
                Date date = Validator.parseDate(input);
                while (date == null) {
                        System.out.println("Invalid date format. Please enter a valid date (format: yyyy-MM-dd): ");
                        input = scanner.nextLine();
                        date = Validator.parseDate(input);
                }
                return date;
        }
        
        /**  
         *  This method allows users to add academic branches.
         * 	
         *  @return void 
         */  
        private void addBranches()
        {
                boolean continueAdd = true;
                try {
                        while (continueAdd) {
                                String branchInput = getInput("\nEnter a branch (enter 'back' to go back to menu): ").trim();
                                if (!"back".equalsIgnoreCase(branchInput)) {
                                        if (!branchInput.isEmpty()) {
                                                if (branchService.isBranchExistsByName(branchInput)) {
                                                        System.out.println("Branch already present, please add a new branch.");
                                                } else {
                                                        branchService.addBranch(branchInput);
                                                        System.out.println("Branch added successfully.");
                                                }
                                        } else {
                                                System.out.println("No branch entered, please enter a valid branch.");
                                                continue;
                                        }
                                        String moreBranch = getInput("Do you want to add more branches? (Y/N): ");
                                        continueAdd = "Y".equalsIgnoreCase(moreBranch);
                                } else {
                                        continueAdd = false;
                                }
                        }
                } catch (StudentException se) {
                        System.out.println("Error adding branch: " + se.getMessage());
                }
        }
        
        /**  
         *  This method allows users to add extracurricular events, and updates the events events records.
         *  	
         *  @return void
         */  
        private void addEvents()
        {
                boolean continueAdd = true;
                try {
                        while (continueAdd) {
                                String eventInput = getInput("\nEnter an event (enter 'back' to go back to menu): ").trim();
                                if (!"back".equalsIgnoreCase(eventInput)) {
                                        if (!eventInput.isEmpty()) {
                                                if (eventService.isEventExistsByName(eventInput)) {
                                                        System.out.println("Event already present, please add a new event.");
                                                } else {
                                                        eventService.addEvent(eventInput);
                                                        System.out.println("Event added successfully.");
                                                }
                                        } else {
                                                System.out.println("No event entered, please enter a valid event.");
                                                continue;
                                        }
                                        String moreEvent = getInput("Do you want to add more events? (Y/N): ");
                                        continueAdd = "Y".equalsIgnoreCase(moreEvent);
                                } else {
                                        continueAdd = false;
                                }
                        }
                } catch (StudentException se) {
                        System.out.println("Error adding event: " + se.getMessage());
                }
        }
        
        /**  	
         *  This method collects students details. i.e., first name, last name, date of birth, email ID, phone number, address, branch, and event(s).
         *  It validates the selected branch and events before creating and storing the student record.
         *  
         *	@return void
        */ 
        private void registerStudent()
        {
                try {
                        if (!branchService.isBranchExists() || !eventService.isEventsExist()) {
                                System.out.println("Please ensure that branches and events are added before registering students.");
                                return;
                        }
                        
                        System.out.println("\nEnter student details:");
                        System.out.print("Enter first name: ");
                        String firstName = getString();
                        System.out.print("Enter last name: ");
                        String lastName = getString();
                        System.out.print("Enter date of birth (yyyy-MM-dd): ");
                        Date dob = getDate();
                        System.out.print("Enter age: ");
                        int age = getInteger();
                        System.out.print("Enter email ID: ");
                        String email = getEmail();
                        System.out.print("Enter phone number: ");
                        String phone = getPhone();
                        System.out.print("Enter address: ");
                        String address = getString();
                        
                        // Retrieve available branches.
                        List<Branch> branches = branchService.getAllBranches();
                        System.out.println("\n---- Available Branches ----");
                        for (Branch branch : branches) {
                                System.out.println("ID: " + branch.getBranchId() + ", Name: " + branch.getBranchName());
                        }
                        
                        Branch selectedBranch = null;
                        while (true) {
                                System.out.print("Please select a branch (enter branch id or branch name): ");
                                String branchInput = getInput("").trim();
                                if (!branchInput.isEmpty()) {
                                        if (Validator.isValidInteger(branchInput)) {
                                                int branchId = Integer.parseInt(branchInput);
                                                for (Branch branch : branches) {
                                                        if (branch.getBranchId() == branchId) {
                                                                selectedBranch = branch;
                                                                break;
                                                        }
                                                }
                                                if (selectedBranch != null) {
                                                        break;
                                                } else {
                                                        System.out.println("No branch found with ID: " + branchId);
                                                }
                                        } else {
                                                for (Branch branch : branches) {
                                                        if (branch.getBranchName().equalsIgnoreCase(branchInput)) {
                                                                selectedBranch = branch;
                                                                break;
                                                        }
                                                }
                                                if (selectedBranch != null) {
                                                        break;
                                                } else {
                                                        System.out.println("Branch '" + branchInput + "' does not exist.");
                                                }
                                        }
                                } else {
                                        System.out.println("Branch selection is mandatory. Please select a branch.");
                                }
                        }
                        
                        // Retrieve available events.
                        List<Event> allevents = eventService.getAllEvents();
                        List<Event> selectedEvents = new ArrayList<>();
                        System.out.println("\n---- Available Events ----");
                        for (Event event : allevents) {
                                System.out.println("ID: " + event.getEventId() + ", Name: " + event.getEventName());
                        }
                        
                        String eventInput = "";
                        do {
                                System.out.print("Please select event(s) (enter 'none' if not choosing any; for multiple, use comma separated values; enter event id(s) or event name(s)): ");
                                eventInput = getInput("").trim();
                                if (eventInput.isEmpty()) {
                                        System.out.println("Event selection cannot be blank. Please enter valid event(s) or 'none'.");
                                }
                        } while (eventInput.isEmpty());
                        
                        if (!"none".equalsIgnoreCase(eventInput)) {
                                String[] events = eventInput.split(",");
                                for (String event : events) {
                                        event = event.trim();
                                        Event selectedEvent = null;
                                        if (Validator.isValidInteger(event)) {
                                                int eventId = Integer.parseInt(event);
                                                for (Event selectEvent : allevents) {
                                                        if (selectEvent.getEventId() == eventId) {
                                                                selectedEvent = selectEvent;
                                                                break;
                                                        }
                                                }
                                                if (selectedEvent == null) {
                                                        System.out.println("No event found with ID: " + eventId);
                                                        continue;
                                                }
                                        } else {
                                                for (Event selectEvent : allevents) {
                                                        if (selectEvent.getEventName().equalsIgnoreCase(event)) {
                                                                selectedEvent = selectEvent;
                                                                break;
                                                        }
                                                }
                                                if (selectedEvent == null) {
                                                        System.out.println("Event '" + event + "' does not exist.");
                                                        continue;
                                                }
                                        }
                                        selectedEvents.add(selectedEvent);
                                        System.out.println("Event with ID " + selectedEvent.getEventId() + " selected.");
                                }
                        } else {
                                System.out.println("No events selected.");
                        }
                        
                        Student student = new Student(firstName, lastName, phone, email, address, dob, age);
                        student.setBranch(selectedBranch);
                        student.setEvents(new HashSet<>(selectedEvents));
                        
                        int studentId = studentService.registerStudent(student);
                        System.out.println("Student registered successfully with ID: " + studentId);
                } catch (StudentException se) {
                        System.out.println("Error registering student: " + se.getMessage());
                }
        }
        
        /**
         * 	This method checks if student records exist; if not, displays a message prompting the user
         * 	to register students. Otherwise, it calls the service layer to print all student records.
         *
         * 	@return void
         */
        private void viewStudentRecords() {
                try {
                        List<Student> students = studentService.getAllStudents();
                        if (students.isEmpty()) {
                                System.out.println("Student records are empty. Please complete registration of students.");
                        } else {
                                System.out.println("\n---- Student Records ----");
                                for (Student student : students) {
                                        System.out.println(student);
                                        if (student.getBranch() != null) {
                                                System.out.println("  Branch: " + student.getBranch().getBranchName() +
                                                                                        " (ID: " + student.getBranch().getBranchId() + ")");
                                        } else {
                                                System.out.println("  Branch: Not assigned");
                                        }
                                        if (student.getEvents() != null && !student.getEvents().isEmpty()) {
                                                System.out.println("  Events:");
                                                for (Event event : student.getEvents()) {
                                                        System.out.println("     - " + event.getEventName() +
                                                                                                " (ID: " + event.getEventId() + ")");
                                                }
                                        } else {
                                                System.out.println("  Events: None assigned");
                                        }
                                        System.out.println("---------------------------------------------------");
                                }
                        }
                } catch (StudentException se) {
                        System.out.println("Error viewing student records: " + se.getMessage());
                }
        }
        
        /**
         * Prompts the user to update a student's details.
         * 
         * @return void.
         */
        private void updateStudent() {
                try {
                        System.out.print("Enter student ID to update: ");
                        int studentId = getInteger();
                        if (!studentService.isStudentExists(studentId)) {
                                System.out.println("No student found with ID: " + studentId);
                                return;
                        }
                        Student student = studentService.getStudentById(studentId);
                        System.out.println("Current details: " + student);
                        
                        System.out.print("Enter new first name (or press Enter to keep [" + student.getFirstName() + "]): ");
                        String firstName = getInput("");
                        if (!firstName.isEmpty()) {
                                student.setFirstName(firstName);
                        }
                        
                        System.out.print("Enter new last name (or press Enter to keep [" + student.getLastName() + "]): ");
                        String lastName = getInput("");
                        if (!lastName.isEmpty()) {
                                student.setLastName(lastName);
                        }

                        System.out.print("Enter new address (or press Enter to keep [" + student.getAddress() + "]): ");
                        String address = getInput("");
                        if (!address.isEmpty()) {
                                student.setAddress(address);
                        }                        

                        System.out.print("Enter new phone number (or press Enter to keep [" + student.getPhoneNo() + "]): ");
                        String phoneInput = getInput("");
                        if (!phoneInput.isEmpty()) {
                                if (Validator.isValidPhone(phoneInput)) {
                                        student.setPhoneNo(phoneInput);
                                } else {
                                        System.out.println("Keeping old phone number.");
                                }
                        }

                        System.out.print("Enter new date of birth (yyyy-MM-dd) (or press Enter to keep [" + student.getDob() + "]): ");
                        String dobInput = getInput("");
                        if (!dobInput.isEmpty()) {
                                java.sql.Date newDob = Validator.parseDate(dobInput);
                                if (newDob != null) {
                                        student.setDob(newDob);
                                } else {
                                        System.out.println("Keeping old date of birth.");
                                }
                        }

                        System.out.print("Enter new age (or press Enter to keep [" + student.getAge() + "]): ");
                        String ageInput = getInput("");
                        if (!ageInput.isEmpty()) {
                                if (Validator.isValidInteger(ageInput)) {
                                        student.setAge(Integer.parseInt(ageInput));
                                } else {
                                        System.out.println("Keeping previous age.");
                                }
                        }

                        studentService.updateStudent(student);
                        System.out.println("Student updated successfully.");
                } catch (StudentException se) {
                        System.out.println("Error updating student: " + se.getMessage());
                }
        }
        
        /**
         * Prompts the user to delete a student record.
         * 
         * @return void.
         */
        private void deleteStudent() {
                try {
                        System.out.print("Enter student ID to delete: ");
                        int studentId = getInteger();
                        if (!studentService.isStudentExists(studentId)) {
                                System.out.println("No student found with ID: " + studentId);
                                return;
                        }
                        studentService.deleteStudent(studentId);
                        System.out.println("Student deleted successfully.");
                } catch (StudentException se) {
                        System.out.println("Error deleting student: " + se.getMessage());
                }
        }
        
        
        /**
         * 	This method is for viewing student branch records.
         * 	If branch records are empty, displays a message and returns to the menu.
         * 	Otherwise, retrieves the branch assigned to the specified student.
         * 	If no branch is assigned, prompts the user to assign a branch.
         *
         * 	@return void.
         */
        private void viewStudentBranch() {
                try {
                        if (!branchService.isBranchExists()) {
                                System.out.println("Branch records are empty. Please complete adding branches.");
                                return;
                        }
                        System.out.print("Enter student ID to view branch: ");
                        int studentId = getInteger();
                        if (!studentService.isStudentExists(studentId)) {
                                System.out.println("No student registered with ID: " + studentId + ". Please register the student first.");
                                return;
                        }
                        Student student = studentService.getStudentById(studentId);
                        if (student.getBranch() == null) {
                                System.out.println("No branch assigned for student with ID: " + studentId);
                                String assignBranch = getInput("Would you like to assign a branch now? (Y/N): ");
                                if ("Y".equalsIgnoreCase(assignBranch)) {
                                        List<Branch> branches = branchService.getAllBranches();
                                        System.out.println("\n---- Available Branches ----");
                                        for (Branch b : branches) {
                                                System.out.println("ID: " + b.getBranchId() + ", Name: " + b.getBranchName());
                                        }
                                        System.out.print("Select a branch (enter branch id or branch name): ");
                                        String selectedBranch = getInput("").trim();
                                        Branch branch = null;
                                        if (Validator.isValidInteger(selectedBranch)) {
                                                int branchId = Integer.parseInt(selectedBranch);
                                                for (Branch b : branches) {
                                                        if (b.getBranchId() == branchId) {
                                                                branch = b;
                                                                break;
                                                        }
                                                }
                                        } else {
                                                for (Branch b : branches) {
                                                        if (b.getBranchName().equalsIgnoreCase(selectedBranch)) {
                                                                branch = b;
                                                                break;
                                                        }
                                                }
                                        }
                                        if (branch == null) {
                                                System.out.println("No valid branch selected.");
                                                return;
                                        }
                                        student.setBranch(branch);
                                        studentService.updateStudent(student);
                                        System.out.println("Branch assigned successfully.");
                                }
                        } else {
                                Branch branch = student.getBranch();
                                System.out.println("Student ID " + studentId + " is assigned to branch: " + branch.getBranchName() + " (ID: " + branch.getBranchId() + ")");
                        }
                } catch (StudentException se) {
                        System.out.println("Error viewing student branch: " + se.getMessage());
                }
        }
        
        /**
         * Prompts the user to update a branch record.
         */
        private void updateBranch() {
                try {
                        List<Branch> branches = branchService.getAllBranches();
                        System.out.println("\n---- Available Branches ----");
                        for (Branch b : branches) {
                                System.out.println("ID: " + b.getBranchId() + ", Name: " + b.getBranchName());
                        }
                        System.out.print("Enter branch ID or branch name to update: ");
                        String input = getInput("").trim();
                        Branch branch = null;
                        if (!input.isEmpty()) {
                                if (Validator.isValidInteger(input)) {
                                        int branchId = Integer.parseInt(input);
                                        for (Branch b : branches) {
                                                if (b.getBranchId() == branchId) {
                                                        branch = b;
                                                        break;
                                                }
                                        }
                                } else {
                                        for (Branch b : branches) {
                                                if (b.getBranchName().equalsIgnoreCase(input)) {
                                                        branch = b;
                                                        break;
                                                }
                                        }
                                }
                                if (branch == null) {
                                        System.out.println("No branch found for the given input.");
                                        return;
                                }
                        } else {
                                System.out.println("Branch selection is mandatory.");
                                return;
                        }
                        System.out.println("Current Branch Details: " + branch);
                        System.out.print("Enter new branch name: ");
                        String newName = getInput("");
                        if (!newName.isEmpty()) {
                                branch.setBranchName(newName);
                                branchService.updateBranch(branch);
                                System.out.println("Branch updated successfully.");
                        } else {
                                System.out.println("No new name entered. Update aborted.");
                        }
                } catch (StudentException se) {
                        System.out.println("Error updating branch: " + se.getMessage());
                }
        }
        
        /**
         * Prompts the user to delete a branch record.
         */
        private void deleteBranch() {
                try {
                        List<Branch> branches = branchService.getAllBranches();
                        System.out.println("\n---- Available Branches ----");
                        for (Branch b : branches) {
                                System.out.println("ID: " + b.getBranchId() + ", Name: " + b.getBranchName());
                        }
                        System.out.print("Enter branch ID or branch name to delete: ");
                        String input = getInput("").trim();
                        Branch branch = null;
                        if (!input.isEmpty()) {
                                if (Validator.isValidInteger(input)) {
                                        int branchId = Integer.parseInt(input);
                                        for (Branch b : branches) {
                                                if (b.getBranchId() == branchId) {
                                                        branch = b;
                                                        break;
                                                }
                                        }
                                } else {
                                        for (Branch b : branches) {
                                                if (b.getBranchName().equalsIgnoreCase(input)) {
                                                        branch = b;
                                                        break;
                                                }
                                        }
                                }
                                if (branch == null) {
                                        System.out.println("No branch found for the given input.");
                                        return;
                                }
                        } else {
                                System.out.println("Branch selection is mandatory.");
                                return;
                        }
                        branchService.deleteBranch(branch.getBranchId());
                        System.out.println("Branch deleted successfully.");
                } catch (StudentException se) {
                        System.out.println("Error deleting branch: " + se.getMessage());
                }
        }
        
        /**
         * Displays events assigned to a student and optionally allows adding more events.
         *
         * @throws StudentException if an error occurs during retrieval or update.
         */
        private void viewStudentEvents() {
                try {
                        System.out.print("Enter student ID to view events: ");
                        int studentId = getInteger();
                        if (!studentService.isStudentExists(studentId)) {
                                System.out.println("No student registered with ID: " + studentId + ". Please register the student first.");
                                return;
                        }
                        Student student = studentService.getStudentById(studentId);
                        Set<Event> studentEvents = student.getEvents();
                        if (studentEvents == null || studentEvents.isEmpty()) {
                                System.out.println("No events registered for student with ID: " + studentId);
                        } else {
                                System.out.println("Events registered for student ID " + studentId + ":");
                                for (Event e : studentEvents) {
                                        System.out.println(" - " + e.getEventName() + " (ID: " + e.getEventId() + ")");
                                }
                        }
                        
                        String moreEvents = getInput("Would you like to add more events? (Y/N): ");
                        if ("Y".equalsIgnoreCase(moreEvents)) {
                                List<Event> availableEvents = eventService.getAllEvents();
                                System.out.println("\n---- Available Events ----");
                                for (Event e : availableEvents) {
                                        System.out.println("ID: " + e.getEventId() + ", Name: " + e.getEventName());
                                }
                                System.out.print("Enter event ID or event name to add (for multiple, use comma separated values): ");
                                String selectedEvents = getInput("").trim();
                                if (!"none".equalsIgnoreCase(selectedEvents)) {
                                        String[] parts = selectedEvents.split(",");
                                        boolean eventAdded = false;
                                        for (String part : parts) {
                                                part = part.trim();
                                                Event selectedEvent = null;
                                                if (Validator.isValidInteger(part)) {
                                                        int eventId = Integer.parseInt(part);
                                                        for (Event event : availableEvents) {
                                                                if (event.getEventId() == eventId) {
                                                                        selectedEvent = event;
                                                                        break;
                                                                }
                                                        }
                                                } else {
                                                        for (Event event : availableEvents) {
                                                                if (event.getEventName().equalsIgnoreCase(part)) {
                                                                        selectedEvent = event;
                                                                        break;
                                                                }
                                                        }
                                                }
                                                if (selectedEvent == null) {
                                                        System.out.println("No event found for input: " + part);
                                                        continue;
                                                }
                                                // Check if the event is already assigned.
                                                boolean alreadyAssigned = false;
                                                if (studentEvents != null) {
                                                        for (Event event : studentEvents) {
                                                                if (event.getEventId() == selectedEvent.getEventId()) {
                                                                        alreadyAssigned = true;
                                                                        break;
                                                                }
                                                        }
                                                }
                                                if (alreadyAssigned) {
                                                        System.out.println("Event " + selectedEvent.getEventName() +
                                                                                                " (ID: " + selectedEvent.getEventId() + ") is already assigned.");
                                                } else {
                                                        if (studentEvents == null) {
                                                                studentEvents = new HashSet<>();
                                                        }
                                                        studentEvents.add(selectedEvent);
                                                        eventAdded = true;
                                                        System.out.println("Event " + selectedEvent.getEventName() +
                                                                                                " (ID: " + selectedEvent.getEventId() + ") assigned successfully.");
                                                }
                                        }
                                        if (eventAdded) {
                                                student.setEvents(studentEvents);
                                                studentService.updateStudent(student);
                                        }
                                } else {
                                        System.out.println("No new events selected.");
                                }
                        } else {
                                System.out.println("No additional events added. Returning to main menu.");
                        }
                } catch (StudentException se) {
                        System.out.println("Error viewing student events: " + se.getMessage());
                }
        }

        
        /**
         * Prompts the user to update an event record.
         */
        private void updateEvent() {
                try {
                        List<Event> events = eventService.getAllEvents();
                        System.out.println("\n---- Available Events ----");
                        for (Event event : events) {
                                System.out.println("ID: " + event.getEventId() + ", Name: " + event.getEventName());
                        }
                        System.out.print("Enter event ID or event name to update: ");
                        String input = getInput("").trim();
                        Event eventChoice = null;
                        if (!input.isEmpty()) {
                                if (Validator.isValidInteger(input)) {
                                        int eventId = Integer.parseInt(input);
                                        for (Event event : events) {
                                                if (event.getEventId() == eventId) {
                                                        eventChoice = event;
                                                        break;
                                                }
                                        }
                                } else {
                                        for (Event event : events) {
                                                if (event.getEventName().equalsIgnoreCase(input)) {
                                                        eventChoice = event;
                                                        break;
                                                }
                                        }
                                }
                                if (eventChoice == null) {
                                        System.out.println("No event found for the given input.");
                                        return;
                                }
                        } else {
                                System.out.println("Event selection is mandatory.");
                                return;
                        }
                        System.out.println("Current Event Details: " + eventChoice);
                        System.out.print("Enter new event name: ");
                        String newName = getInput("");
                        if (!newName.isEmpty()) {
                                eventChoice.setEventName(newName);
                                eventService.updateEvent(eventChoice);
                                System.out.println("Event updated successfully.");
                        } else {
                                System.out.println("No new name entered. Update aborted.");
                        }
                } catch (StudentException se) {
                        System.out.println("Error updating event: " + se.getMessage());
                }
        }
        
        /**
         * Prompts the user to delete an event record.
         */
        private void deleteEvent() {
                try {
                        List<Event> events = eventService.getAllEvents();
                        System.out.println("\n---- Available Events ----");
                        for (Event e : events) {
                                System.out.println("ID: " + e.getEventId() + ", Name: " + e.getEventName());
                        }
                        System.out.print("Enter event ID or event name to delete: ");
                        String input = getInput("").trim();
                        Event event = null;
                        if (!input.isEmpty()) {
                                if (Validator.isValidInteger(input)) {
                                        int eventId = Integer.parseInt(input);
                                        for (Event e : events) {
                                                if (e.getEventId() == eventId) {
                                                        event = e;
                                                        break;
                                                }
                                        }
                                } else {
                                        for (Event e : events) {
                                                if (e.getEventName().equalsIgnoreCase(input)) {
                                                        event = e;
                                                        break;
                                                }
                                        }
                                }
                                if (event == null) {
                                        System.out.println("No event found for the given input.");
                                        return;
                                }
                        } else {
                                System.out.println("Event selection is mandatory.");
                                return;
                        }
                        eventService.deleteEvent(event.getEventId());
                        System.out.println("Event deleted successfully.");
                } catch (StudentException se) {
                        System.out.println("Error deleting event: " + se.getMessage());
                }
        }
}
