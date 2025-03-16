package com.student.services;

import com.student.dao.EventDao;
import com.student.exception.StudentException;
import com.student.models.Event;
import java.util.List;

/**
        * EventService handles business operations related to event records,
        * including creation, retrieval, update, and deletion.
        */
public class EventService {
        private EventDao eventDao = new EventDao();

        /**
         * Adds a new event record.
         *
         * @param eventName the event name.
         * @throws StudentException if an error occurs while adding.
         */
        public void addEvent(String eventName) throws StudentException {
                try {
                        Event event = new Event(eventName);
                        eventDao.saveEvent(event);
                } catch (StudentException se) {
                        throw new StudentException("Error in adding event to database.");
                }
        }

        /**
         * Checks if any event records exist.
         *
         * @return true if at least one event exists; false otherwise.
         * @throws StudentException if an error occurs.
         */
        public boolean isEventsExist() throws StudentException {
                try {
                        List<Event> events = eventDao.getAllEvents();
                        return (events != null && !events.isEmpty());
                } catch (StudentException se) {
                        throw new StudentException("Event does not exist in database.");
                }
        }

        /**
         * Checks if an event exists by its name.
         *
         * @param eventName the event name.
         * @return true if the event exists; false otherwise.
         * @throws StudentException if an error occurs.
         */
        public boolean isEventExistsByName(String eventName) throws StudentException {
                try {
                        Event event = eventDao.getEventByName(eventName);
                        return (event != null);
                } catch (StudentException se) {
                        throw new StudentException("No event found with name: " + eventName);
                }
        }

        /**
         * Retrieves all event records.
         *
         * @return a list of Event objects.
         * @throws StudentException if an error occurs.
         */
        public List<Event> getAllEvents() throws StudentException {
                try {
                        return eventDao.getAllEvents();
                } catch (StudentException se) {
                        throw new StudentException("No event records retrieved.");
                }
        }

        /**
         * Retrieves the event ID for a given event name.
         *
         * @param eventName the event name.
         * @return the event ID.
         * @throws StudentException if the event is not found.
         */
        public int getEventByName(String eventName) throws StudentException {
                Event event = eventDao.getEventByName(eventName);
                if (event != null) {
                        return event.getEventId();
                } else {
                        throw new StudentException("No event found with name: " + eventName);
                }
        }

        /**
         * Retrieves the event name for a given event ID.
         *
         * @param eventId the event ID.
         * @return the event name.
         * @throws StudentException if the event is not found.
         */
        public String getEventById(int eventId) throws StudentException {
                Event e = eventDao.getEventById(eventId);
                if (e != null) {
                        return e.getEventName();
                } else {
                        throw new StudentException("No event found with ID: " + eventId);
                }
        }

        /**
         * Updates an existing event record.
         *
         * @param event the Event object with updated details.
         * @throws StudentException if an error occurs during update.
         */
        public void updateEvent(Event event) throws StudentException {
                try {
                        eventDao.updateEvent(event);
                }
                 catch (StudentException se) {
                        throw new StudentException("Error updating event records: " + se.getMessage());
                 }
        }

        /**
         * Deletes an event record.
         *
         * @param event the Event object to delete.
         * @throws StudentException if an error occurs during deletion.
         */
        public void deleteEvent(int eventId) throws StudentException {
                try {
                        eventDao.deleteEvent(eventId);
                } catch (StudentException se) {
                        throw new StudentException("Error deleting event records: " + se.getMessage());
                }
        }
}
