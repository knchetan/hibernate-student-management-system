package com.student.models;

import java.util.Set;

/**
 *    Represents an event entity.
 */
public class Event
{
        private int eventId;
        private String eventName;
        private Set<Student> students;
        
        public Event() {}
        
        public Event(String eventName) {
                this.eventName = eventName;
        }
        
        public Event(int eventId, String eventName) {
                this.eventId = eventId;
                this.eventName = eventName;
        }
        
        public int getEventId() {
                return eventId;
        }
        
        public void setEventId(int eventId) {
                this.eventId = eventId;
        }
        
        public String getEventName() {
                return eventName;
        }
        
        public void setEventName(String eventName) {
                this.eventName = eventName;
        }

        public Set<Student> getStudents() {
                return students;
        }

        public void setStudents(Set<Student> students) {
                this.students = students;
        }
        
        @Override
        public String toString() {
                return "Event [eventId=" + eventId + ", eventName=" + eventName + "]";
        }
}
