package kr.megaptera.wherewego.errorDtos;

public class NicknameDuplicatedErrorDto extends ErrorDto{
    public NicknameDuplicatedErrorDto() {
        super(1001, "이미 다른 분이 사용 중이예요");
    }
}
