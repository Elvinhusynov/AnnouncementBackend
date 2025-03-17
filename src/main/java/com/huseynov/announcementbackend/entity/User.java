package com.huseynov.announcementbackend.entity;

import com.huseynov.announcementbackend.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USERS")

public class User { // saytin security hissesidi,login,parol.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING) // enum istifade etdikde bu anotasiya yazmaq lazimdirki database e qeyd olunsun.
                                 //burada Role enum di.
    private Role role; // - programda olan user,moderator,admin umumilikde role adlanir.


    private boolean locked; // ban olunmus istifadeci ucundur

    private boolean enabled; // user nomresin,emailin ve sair yazib ancaq qeydiyyatdan kecmiyib onun ucundur.

    private LocalDate createdDate;
    private LocalDateTime modifiedDate;


}
