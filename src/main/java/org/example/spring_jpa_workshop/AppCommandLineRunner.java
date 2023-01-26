package org.example.spring_jpa_workshop;

import org.example.spring_jpa_workshop.data.*;
import org.example.spring_jpa_workshop.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    AppUserDao appUserDao;

    @Autowired
    DetailsDao detailsDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    BookLoanDao bookLoanDao;

    @Autowired
    AuthorDao authorDao;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //System.out.println("-----------------------------");
    }

    public void ex1(){
        Details detail1 = new Details("leslie3@gmail.com", "Leslie Green", LocalDate.parse("1986-04-05"));
        Details detail2 = new Details("juan@gmail.com", "Juana Hensson", LocalDate.parse("2000-07-15"));
        AppUser user1 = new AppUser("lesreen", "password", detail1);
        AppUser user2 = new AppUser("juana", "Hen1jUan", detail2);
        appUserDao.create(user1);
        appUserDao.create(user2);

        Book book1 = new Book("123476", "Master of the game",14);
        Book book2 = new Book("564879", "Java for dummies",30);
        Book book3 = new Book("894563", "Redemption",12);
        Book book4 = new Book("683974", "Lord of the rings",15);
        bookDao.create(book1);

        BookLoan loanBook1 = new BookLoan(LocalDate.parse("2023-01-23"), LocalDate.parse("2023-01-30"), false,user1,book1);
        bookLoanDao.create(loanBook1);
        BookLoan loanBook2 = new BookLoan(LocalDate.parse("2023-01-20"), LocalDate.parse("2023-02-03"), false);
        BookLoan loanBook3 = new BookLoan(LocalDate.parse("2023-01-25"), LocalDate.parse("2023-02-06"), false);

        Set<Book> bookSet1 = new HashSet<>();
        bookSet1.add(new Book("123476", "Master of the game",14));
        bookSet1.add(new Book("564783", "The sky is falling",7));


        Set<Book> bookSet2 = new HashSet<>();
        bookSet2.add(new Book("145385", "Redeeming Love",21));
        bookSet2.add(new Book("256483", "Scarlet Thread",13));

        Set<Book> bookSet3 = new HashSet<>();
        bookSet3.add(new Book("764830", "Vortex",12));
        bookSet3.add(new Book("975273", "Reckoning",7));

        Author author1 = new Author("Sydney", "Sheldon", bookSet1);
        authorDao.create(author1);
        //authorDao.delete(author1.getAuthorId());

        Author author2 = new Author("Francine", "Rivers", bookSet2);
        authorDao.create(author2);

        Author author3 = new Author("Catherine", "Coulter", bookSet3);
        authorDao.create(author3);
    }

    public void ex2(){
        appUserDao.findById(1);
    }
}
