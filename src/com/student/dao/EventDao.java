package com.student.dao;

import com.student.exception.StudentException;
import com.student.models.Event;
import com.student.util.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * EventDao handles CRUD operations for the Event entity using Hibernate.
 */
public class EventDao
{
        /**
                * Saves a new event record.
                *
                * @param event the Event object to be saved.
                * @throws StudentException if an error occurs.
                */
        public void saveEvent(Event event) throws StudentException
        {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        session.save(event);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null) tx.rollback();
                        throw new StudentException("Error saving event: " + se.getMessage());
                }
        }

        /**
                * Retrieves all event records.
                *
                * @return a list of Event objects.
                * @throws StudentException if an error occurs.
                */
        //@SuppressWarnings("unchecked")
        public List<Event> getAllEvents() throws StudentException
        {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return session.createQuery("from Event").list();
                } catch (Exception se) {
                        throw new StudentException("Error retrieving events: " + se.getMessage());
                }
        }
        
        /**
                * Retrieves an event by ID.
                *
                * @param id the event ID.
                * @return the Event object.
                * @throws StudentException if an error occurs.
                */
        public Event getEventById(int id) throws StudentException
        {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return session.get(Event.class, id);
                } catch (Exception se) {
                        throw new StudentException("Error retrieving event with ID: " + id + ": " + se.getMessage());
                }
        }
        
        /**
                * Retrieves an event by name.
                *
                * @param eventName the event name.
                * @return the Event object.
                * @throws StudentException if an error occurs.
                */
        public Event getEventByName(String eventName) throws StudentException
        {
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        return (Event) session.createQuery("from Event where eventName = :name")
                                .setParameter("name", eventName)
                                .uniqueResult();
                } catch (Exception se) {
                        throw new StudentException("Error retrieving event with name " + eventName + ": " + se.getMessage());
                }
        }
        
        /**
                * Updates an existing event record.
                *
                * @param event the Event object with updated details.
                * @throws StudentException if an error occurs.
                */
        public void updateEvent(Event event) throws StudentException
        {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        session.update(event);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null)
			tx.rollback();
                        throw new StudentException("Error updating event: " + se.getMessage());
                }
        }
        
        /**
                * Deletes an event record.
                *
                * @param event the Event object to delete.
                * @throws StudentException if an error occurs.
                */
        public void deleteEvent(int eventId) throws StudentException
        {
                Transaction tx = null;
                try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
                        tx = session.beginTransaction();
                        session.delete(eventId);
                        tx.commit();
                } catch (Exception se) {
                        if (tx != null) 
			tx.rollback();
                        throw new StudentException("Error deleting event: " + se.getMessage());
                }
        }
}
