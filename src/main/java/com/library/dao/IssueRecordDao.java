package com.library.dao;

import com.library.entity.IssueRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class IssueRecordDao {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void saveIssueRecord(IssueRecord record) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(record);
            tx.commit();
        } catch(Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateIssueRecord(IssueRecord record) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(record);
            tx.commit();
        } catch(Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public IssueRecord getIssueRecord(int id) {
        try (Session session = factory.openSession()) {
            return session.get(IssueRecord.class, id);
        }
    }

    public List<IssueRecord> getAllIssueRecords() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from IssueRecord", IssueRecord.class).list();
        }
    }
}
