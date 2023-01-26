package org.example.spring_jpa_workshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int appUserId;
    @Column(nullable = false, length = 100, unique = true)
    private String username;
    @Column(nullable = false, length = 20)
    private String password;
    private LocalDate regDate;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "details_id")
    private Details userDetails;
    @OneToMany(mappedBy = "borrower", orphanRemoval = true)
    private List<BookLoan> loans;

    //constructors
    public AppUser(){
        this.regDate = LocalDate.now();
    }

    public AppUser(String username, String password, Details userDetails) {
        this();
        this.username = username;
        this.password = password;
        this.userDetails = userDetails;
    }

    public AppUser(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public AppUser(int appUserId, String username, String password) {
        this();
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
    }

    public AppUser(int appUserId, String username, String password, Details userDetails) {
        this();
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
        this.userDetails = userDetails;
    }

    //getter & setter
    public int getAppUserId() {
        return appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details details) {
        this.userDetails = details;
    }

    public List<BookLoan> getLoans() {
        if (loans == null) loans = new ArrayList<>();
        return loans;
    }

    public void setLoans(List<BookLoan> loans) {
        this.loans = loans;
    }

    public void addBookLoan(BookLoan bookLoan){
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        if (loans == null) loans = new ArrayList<>();

        loans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

    public void removeBookLoan(BookLoan bookLoan){
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        if (loans !=null){
            bookLoan.setBorrower(null);
            loans.remove(bookLoan);
        }
    }

    //equals & hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(userDetails, appUser.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, username, password, regDate, userDetails);
    }

    //toString
    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
