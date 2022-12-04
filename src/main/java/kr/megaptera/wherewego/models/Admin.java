package kr.megaptera.wherewego.models;

import org.hibernate.annotations.*;
import org.springframework.security.crypto.password.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue
    private Long id;

    private String identifier;

    private String encodedPassword;

    private String name;

    private Long employeeIdentificationNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Admin(String identifier, String encodedPassword, String name,
                 Long employeeIdentificationNumber) {
        this.identifier = identifier;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
    }

    public Admin(Long id, String identifier, String encodedPassword, String name, Long employeeIdentificationNumber, LocalDateTime createdAt) {
        this.id = id;
        this.identifier = identifier;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.createdAt = createdAt;
    }

    public static Admin fake(String identifier) {
        return new Admin(1L, identifier, "encodedPassword", "전민지",
            12345L, LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0));
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }
}
