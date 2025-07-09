package com.javalibproject.Repo.user;
/*
 * repo katmanının görevi sorgu, veritabanı yönetimi gibi işlemleri gerçekleştirmektir.
 * Bu katman, uygulamanın iş mantığından ayrıdır ve veri erişim katmanı olarak görev yapar.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class SystemUser {
    /*
     * final keywordu ile ilk basta deger verdikten sonra degistirilemez.
     * ornegin id degeri icin final sadece constructor'da deger verilebilir. boylece bu nesne degistirilemez olur ancak adress  sonradan degistirilebilir oldugu icin final yapmaya gerek gormedim.
     */ 
    private final Integer userId;
    private final String username;    
    private final String password;
    
}
