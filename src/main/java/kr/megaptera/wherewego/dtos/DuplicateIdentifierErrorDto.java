package kr.megaptera.wherewego.dtos;

import kr.megaptera.wherewego.errorDtos.*;

public class DuplicateIdentifierErrorDto extends ErrorDto {
    public DuplicateIdentifierErrorDto() {
        super(7004, "이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요");
    }
}
