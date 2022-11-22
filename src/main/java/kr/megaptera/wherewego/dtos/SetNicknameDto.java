package kr.megaptera.wherewego.dtos;

import javax.validation.constraints.*;

public class SetNicknameDto {
    @Pattern(regexp = "(?=.*[a-zA-Z0-9가-힣ㄱ-ㅎ])[a-zA-Z0-9가-힣ㄱ-ㅎ]{3,7}",
        message = "3~7자 이내의 한글, 영문, 숫자로 이루어진 닉네임을 입력해주세요(공백 불가)")
    private String nickname;

    public SetNicknameDto() {
    }

    public SetNicknameDto(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
