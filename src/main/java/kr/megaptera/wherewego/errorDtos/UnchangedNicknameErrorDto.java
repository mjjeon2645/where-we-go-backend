package kr.megaptera.wherewego.errorDtos;

public class UnchangedNicknameErrorDto extends ErrorDto{
    public UnchangedNicknameErrorDto() {
        super(1002, "현재 사용하고 계시는 닉네임과 동일합니다. 다른 닉네임을 입력해주세요");
    }
}
