package org.example.spring_jpa_workshop.data;

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

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class DetailsDaoImplTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    DetailsDaoImpl testObject;

    Details createdDetail1;

    Details createdDetail2;


    @BeforeEach
    public void setup(){
        Details details1 = new Details("george@gmail.com", " George Miles", LocalDate.parse("1980-01-22"));
        Details details2 = new Details("smart@gmail.com", " Smart Will", LocalDate.parse("1981-09-12"));
        createdDetail1 = testObject.create(details1);
        createdDetail2 = testObject.create(details2);
    }

    @Test
    public void create(){
        assertNotNull(createdDetail1);
    }

    @Test
    public void findById(){
        Details detail = new Details("test@test.com", "test test", LocalDate.parse("2020-02-03"));
        assertNull(testObject.findById(detail.getDetailsId()));
    }

    @Test
    public void findAll(){
        assertEquals(2, testObject.findAll().size());
    }

    @Test
    public void delete(){
        testObject.delete(createdDetail2.getDetailsId());
        assertNull(em.find(Details.class, createdDetail2.getDetailsId()));
    }

    @Test
    public void update(){
        Details expected = new Details(createdDetail2.getDetailsId(), "will01@gmail.com", " Smart Will", LocalDate.parse("1981-09-12"));
        Details actual = testObject.update(new Details(createdDetail2.getDetailsId(), "will01@gmail.com", " Smart Will", LocalDate.parse("1981-09-12")));
        assertEquals(expected,actual);
    }
}
