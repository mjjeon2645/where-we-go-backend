package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;
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

    //TODO. jwt로 인해 임시로 socialLoginId라는 명칭을 붙임. 추후 identifier로 user, admin 모두 통일 할 것
    private String socialLoginId;

    private String encodedPassword;

    private String name;

    private Long employeeIdentificationNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Admin() {
    }

    public Admin(String socialLoginId, String encodedPassword, String name,
                 Long employeeIdentificationNumber) {
        this.socialLoginId = socialLoginId;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
    }

    public Admin(Long id, String socialLoginId, String encodedPassword, String name,
                 Long employeeIdentificationNumber, LocalDateTime createdAt) {
        this.id = id;
        this.socialLoginId = socialLoginId;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.createdAt = createdAt;
    }

    public static Admin fake(String socialLoginId) {
        return new Admin(1L, socialLoginId, "encodedPassword", "전민지",
            12345L, LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0));
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }

    public CreatedAdminDto toDto() {
        return new CreatedAdminDto(socialLoginId, name, employeeIdentificationNumber);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.encodedPassword = passwordEncoder.encode(password);
    }
}
