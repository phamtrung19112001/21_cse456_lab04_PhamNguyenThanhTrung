package edu.eiu.cse456.lab04.repository.impl;

import edu.eiu.cse456.lab04.entity.Invoice;
import edu.eiu.cse456.lab04.repository.InvoiceRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {
    private final SessionFactory sessionFactory;

    public InvoiceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Invoice save(Invoice invoice) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(invoice);
            tx.commit();
            return invoice;
        } catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Invoice.class, id));
        }
    }
}
