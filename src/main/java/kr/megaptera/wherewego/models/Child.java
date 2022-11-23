package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Entity
public class Child {
    public static final String BOY = "왕자님";
    public static final String GIRL = "공주님";
    public static final String UNDEFINED = "아직 몰라요";

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String gender;

    private String birthday;

    public Child() {
    }

    public Child(Long userId, String gender, String birthday) {
        this.userId = userId;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Child(Long id, Long userId, String gender, String birthday) {
        this.id = id;
        this.userId = userId;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Long id() {
        return id;
    }

    public Long userId() {
        return userId;
    }

    public String gender() {
        return gender;
    }

    public String birthday() {
        return birthday;
    }

    public ChildDto toChildDto() {
        return new ChildDto(id, userId, gender, birthday);
    }
}
