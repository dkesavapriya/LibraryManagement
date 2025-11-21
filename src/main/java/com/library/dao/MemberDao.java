package com.library.dao;

import com.library.entity.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MemberDao {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void saveMember(Member member) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(member);
            tx.commit();
        } catch(Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Member getMember(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Member.class, id);
        }
    }

    public List<Member> getAllMembers() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Member", Member.class).list();
        }
    }
}
