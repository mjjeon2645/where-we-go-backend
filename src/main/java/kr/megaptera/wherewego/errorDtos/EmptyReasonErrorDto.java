package kr.megaptera.wherewego.errorDtos;

public class EmptyReasonErrorDto extends ErrorDto {
    public EmptyReasonErrorDto() {
        super(7009, "사유를 입력해주세요");
    }
}
