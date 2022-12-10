package kr.megaptera.wherewego.errorDtos;

public class AddressMissingErrorDto extends ErrorDto {
    public AddressMissingErrorDto() {
        super(7004, "주소 찾기를 진행해주세요");
    }
}
