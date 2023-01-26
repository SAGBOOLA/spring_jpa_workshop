package org.example.spring_jpa_workshop.data;

import static org.junit.jupiter.api.Assertions.*;

import org.example.spring_jpa_workshop.entity.BookLoan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class BookLoanDaoImplTest {
    @Autowired
    TestEntityManager em;
    @Autowired
    BookLoanDaoImpl testObject;

    BookLoan createdLoanedBook1;
    BookLoan createdLoanedBook2;

    @BeforeEach
    public void setup(){
        BookLoan bookLoan1 = new BookLoan(LocalDate.parse("2023-01-20"), LocalDate.parse("2023-02-03"), false);
        BookLoan bookLoan2 = new BookLoan(LocalDate.parse("2023-01-15"), LocalDate.parse("2023-01-28"), false);
        createdLoanedBook1 = testObject.create(bookLoan1);
        createdLoanedBook2 = testObject.create(bookLoan2);
    }
    @Test
    public void create(){
        assertNotNull(createdLoanedBook1);
    }
    @Test
    public void findById(){
        assertNotNull(testObject.findById(createdLoanedBook2.getLoanId()));
    }
    @Test
    public void findAll(){
        int expected = 1;
        int actual = testObject.findAll().size();
        assertNotEquals(expected,actual);
    }
    @Test
    public void update(){
        BookLoan expected = new BookLoan(createdLoanedBook1.getLoanId(),LocalDate.parse("2023-01-20"), LocalDate.parse("2023-02-03"), false);
        BookLoan actual = testObject.update(new BookLoan(createdLoanedBook1.getLoanId(),LocalDate.parse("2023-01-20"), LocalDate.parse("2023-02-03"), true));
        assertNotEquals(expected,actual);
    }
    @Test
    public void delete(){
        testObject.delete(createdLoanedBook2.getLoanId());
        assertNull(em.find(BookLoan.class, createdLoanedBook2.getLoanId()));
    }
}
