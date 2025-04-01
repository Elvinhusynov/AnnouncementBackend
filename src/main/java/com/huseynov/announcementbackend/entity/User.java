package com.huseynov.announcementbackend.entity;

import com.huseynov.announcementbackend.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")

public class User implements UserDetails { //User-> saytin security hissesidi,login,parol.
    //UserDetailə ona görə implement edikirikki user login oldugda avtamatik parolun username ve s yoxluyur,
    //aşağıdakı methodları var

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

    private boolean enabled; // user nomresin,emailin ve s. yazib ancaq qeydiyyatdan kecmiyib onun ucundur.

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
//Nəyə icazə verilir methodudu.Məs:maderatorun icazələri nədir? vəya adminin və s
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
//Hesabin istifadə müddəti bitməyib.
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
//Hesab bloka düşməyib.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//Bu method user ə hər ay məsələn sifrəni dəyiş dedikdə istifadə olunur.
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
