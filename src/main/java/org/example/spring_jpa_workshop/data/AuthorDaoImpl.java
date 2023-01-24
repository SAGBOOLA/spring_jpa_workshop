package org.example.spring_jpa_workshop.data;

import org.example.spring_jpa_workshop.entity.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDaoImpl implements AuthorDao{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public Author findById(Integer integer) {
        return entityManager.find(Author.class, integer);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return entityManager.createQuery("select au from Author au", Author.class).getResultList();
    }

    @Override
    @Transactional
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        Author createdAuthor = entityManager.find(Author.class, integer);
        if (createdAuthor != null) entityManager.remove(createdAuthor);
    }
}
