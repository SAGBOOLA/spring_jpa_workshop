package org.example.spring_jpa_workshop.data;

import org.example.spring_jpa_workshop.entity.BookLoan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookLoanDaoImpl implements BookLoanDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public BookLoan findById(Integer integer) {
        return entityManager.find(BookLoan.class, integer);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("select bl from BookLoan bl", BookLoan.class).getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        BookLoan loanedBook = entityManager.find(BookLoan.class, integer);
        if (loanedBook != null) entityManager.remove(loanedBook);
    }
}
