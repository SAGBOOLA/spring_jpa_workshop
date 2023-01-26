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
public class BookDaoImplTest {

    @Autowired
    TestEntityManager em;
    @Autowired
    BookDaoImpl testObject;
    @Autowired
    AuthorDaoImpl testObject1;

    Book createdBook1;
    Book createdBook2;
    Author createdAuthor;

    @BeforeEach
    public void setup(){
        Book book1 = new Book("903456728", "The act of writing", 8);
        Book book2 = new Book("908453786", "Java for dummies", 14);
        createdBook1 = testObject.create(book1);
        createdBook2 = testObject.create(book2);
        Author author1 = new Author("Francine", "Rivers");
        createdAuthor = testObject1.create(author1);
    }
    @Test
    public void create(){
        Book result = new Book("1234", "Scarlet Thread", 10);
        assertNotNull(testObject.create(result));
    }
    @Test
    public void create_check(){
        Book actual = testObject.create(new Book("1234", "Scarlet Thread", 10));
        assertNotNull(actual);
    }
    @Test
    public void findById(){
        Book expected = new Book(1,"903456728", "The act of writing", 8);
        Book actual = testObject.findById(createdBook1.getBookId());
        assertEquals(expected,actual);
    }
    @Test
    public void findAll(){
       int expected = 2;
       int actual = testObject.findAll().size();
       assertSame(expected,actual);
    }
    @Test
    public void update(){
        Book expected = new Book(createdBook2.getBookId(), "908453786", "Java for dummies", 30);
        Book actual = testObject.update(new Book(createdBook2.getBookId(), "908453786", "Java for dummies", 30));
        assertEquals(expected, actual);
    }
    @Test
    public void delete(){
        testObject.delete(createdBook1.getBookId());
        assertNull(em.find(Book.class, createdBook1.getBookId()));
    }

    @Test
    public void addAuthor(){
        createdBook1.addAuthor(createdAuthor);
        assertNotNull(createdBook1.getAuthors());
    }

    @Test
    public void removeAuthor(){
        createdBook1.removeAuthor(createdAuthor);
        assertNotEquals(1, createdBook1.getAuthors().size());
    }
}
