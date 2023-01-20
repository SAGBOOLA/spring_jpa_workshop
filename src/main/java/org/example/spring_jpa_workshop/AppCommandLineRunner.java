package org.example.spring_jpa_workshop;

import org.example.spring_jpa_workshop.data.AppUserDao;
import org.example.spring_jpa_workshop.data.DetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    AppUserDao appUserDao;

    @Autowired
    DetailsDao detailsDao;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("-----------------------------");
    }
}
