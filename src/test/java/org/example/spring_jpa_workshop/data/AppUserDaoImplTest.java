package org.example.spring_jpa_workshop.data;

import org.example.spring_jpa_workshop.entity.AppUser;
import org.example.spring_jpa_workshop.entity.Book;
import org.example.spring_jpa_workshop.entity.BookLoan;
import org.example.spring_jpa_workshop.entity.Details;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.soap.Detail;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class AppUserDaoImplTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    AppUserDaoImpl testObject;
    @Autowired
    BookDaoImpl testObject1;
    @Autowired
    BookLoanDaoImpl testObject2;

    AppUser createdUser1;
    AppUser createdUser2;
    AppUser user1;
    Details details1;
    Book createdBook;
    BookLoan loanedBook;

    int userId;

    @BeforeEach
    public void setup(){
        details1 = new Details("george@gmail.com", " George Miles", LocalDate.parse("1980-01-22"));
        Details details2 = new Details("smart@gmail.com", " Smart Will", LocalDate.parse("1981-09-12"));
        user1 = new AppUser("geomile", "password", details1);
        AppUser user2 = new AppUser("smartkid", "wordpass", details2);
        createdUser1 = testObject.create(user1);
        userId = createdUser1.getAppUserId();
        createdUser2 = testObject.create(user2);
        Book book1 = new Book("903456728", "The act of writing", 8);
        createdBook = testObject1.create(book1);
        BookLoan bookLoan1 = new BookLoan(LocalDate.parse("2023-01-20"), LocalDate.parse("2023-02-03"), false, createdBook);
        loanedBook = testObject2.create(bookLoan1);
    }

    @Test
    public void create(){
        AppUser user = new AppUser("greg", "test");
        AppUser createdUser = testObject.create(user);
        assertNotNull(createdUser);
    }

    @Test
    public void findById(){
        assertNotNull(testObject.findById(createdUser1.getAppUserId()));
    }

    @Test
    public void findAll(){
        int expected = 2;
        int actual = testObject.findAll().size();
        assertSame(expected,actual);
    }

    @Test
    public void delete(){
        testObject.delete(createdUser1.getAppUserId());
        assertNull(em.find(AppUser.class, createdUser1.getAppUserId()));
    }

    @Test
    public void find_all(){
        assertNotNull(testObject.findAll());
    }

    @Test
    public void find_all_list(){
        int expected = 1;
        int actual = testObject.findAll().size();
        assertNotEquals(expected,actual);
    }

    @Test
    public void regDate(){
        LocalDate expected = LocalDate.parse("2023-01-26");
        LocalDate actual = createdUser1.getRegDate();
        assertEquals(expected,actual);
    }

    @Test
    public void update(){
        AppUser expected = new AppUser(userId,"geomil", "password", details1);
        AppUser user = testObject.findById(userId);
        user.setUsername("geomil");
        AppUser actual = testObject.update(user1);
        assertEquals(expected,actual);
    }

    @Test
    public void addBookLoan(){
        createdUser1.addBookLoan(loanedBook);
        assertNotNull(createdUser1.getLoans());
    }

    @Test
    public void removeBookLoan(){
        createdUser1.removeBookLoan(loanedBook);
        assertEquals(0,createdUser1.getLoans().size());
    }

}
