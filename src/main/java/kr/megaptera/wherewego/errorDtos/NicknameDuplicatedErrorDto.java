package kr.megaptera.wherewego.errorDtos;

public class NicknameDuplicatedErrorDto extends ErrorDto{
    public NicknameDuplicatedErrorDto() {
        super(1001, "이미 사용중인 닉네임입니다. 다른 닉네임을 입력해주세요");
    }
}
