package kr.megaptera.wherewego.dtos;

import java.util.*;

public class UsersDto {
    private List<UserDto> users;

    public UsersDto() {
    }

    public UsersDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }
}
