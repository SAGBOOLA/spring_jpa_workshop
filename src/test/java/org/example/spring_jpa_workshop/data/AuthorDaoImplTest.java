package org.example.spring_jpa_workshop.data;

import static org.junit.jupiter.api.Assertions.*;

import org.example.spring_jpa_workshop.entity.Author;
import org.example.spring_jpa_workshop.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class AuthorDaoImplTest {
    @Autowired
    TestEntityManager em;
    @Autowired
    AuthorDaoImpl testObject;
    @Autowired
    BookDaoImpl testObject1;

    Author createdAuthor1;
    Author createdAuthor2;
    Book createdBook;

    @BeforeEach
    public void setup(){
        Author author1 = new Author("Francine", "Rivers");
        Author author2 = new Author("Sydney", "Sheldon");
        createdAuthor1 = testObject.create(author1);
        createdAuthor2 = testObject.create(author2);
        Book book1 = new Book("908453786", "Java for dummies", 14);
        createdBook = testObject1.create(book1);
    }
    @Test
    public void findAll(){
        assertNotSame(1, testObject.findAll().size());
    }
    @Test
    public void create(){
        Author author3 = new Author("John", "Grisham");
        assertNotNull(testObject.create(author3));
    }
    @Test
    public void find(){
        Author author3 = new Author("John", "Grisham");
        assertNull(testObject.findById(author3.getAuthorId()));
    }
    @Test
    public void update(){
        Author expected = new Author(createdAuthor1.getAuthorId(), "Francine", "Kinsbury");
        Author actual = testObject.update(new Author(createdAuthor1.getAuthorId(),"Francine", "Kinsbury"));
        assertEquals(expected,actual);
    }
    @Test
    public void delete(){
        testObject.delete(createdAuthor1.getAuthorId());
        assertNull(em.find(Author.class, createdAuthor1.getAuthorId()));
    }

    @Test
    public void addBook(){
        createdAuthor2.addBook(createdBook);
        assertEquals(1, createdAuthor2.getWrittenBooks().size());
    }

    @Test
    public void removeBook(){
        createdAuthor2.removeBook(createdBook);
        assertEquals(0,createdAuthor1.getWrittenBooks().size());
    }
}
