package kr.megaptera.wherewego.errorDtos;

public class AddressMissingErrorDto extends ErrorDto {
    public AddressMissingErrorDto() {
        super(7004, "주소를 입력해주세요");
    }
}
