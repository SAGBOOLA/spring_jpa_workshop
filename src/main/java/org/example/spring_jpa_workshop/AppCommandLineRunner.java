package org.example.spring_jpa_workshop;

import org.example.spring_jpa_workshop.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

        System.out.println("-----------------------------");
    }
}
