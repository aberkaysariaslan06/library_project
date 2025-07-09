package com.javalibproject.Repo.user;
/*
 * repo katmanının görevi sorgu, veritabanı yönetimi gibi işlemleri gerçekleştirmektir.
 * Bu katman, uygulamanın iş mantığından ayrıdır ve veri erişim katmanı olarak görev yapar.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User extends SystemUser {
    /*
     * final keywordu ile ilk basta deger verdikten sonra degistirilemez.
     * ornegin id degeri icin final sadece constructor'da deger verilebilir. boylece bu nesne degistirilemez olur ancak adress  sonradan degistirilebilir oldugu icin final yapmaya gerek gormedim.
     */ 
 
   
    public User(Integer userId, String username, String password, String firstName, String lastName, String address, String postCode, String city, String email) {
        super(userId, username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postCode = postCode;
        this.city = city;
        this.email = email;
    }

    private final String firstName;
    private final String lastName;
    private String address;
    private String postCode;
    private String city;
    private String email;  
    private final boolean isAdmin=false; // true ise admin, false ise user olarak tanimlanir. 
    
    
}
