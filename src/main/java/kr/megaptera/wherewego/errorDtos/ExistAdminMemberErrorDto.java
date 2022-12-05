package kr.megaptera.wherewego.errorDtos;

public class ExistAdminMemberErrorDto extends ErrorDto {
    public ExistAdminMemberErrorDto() {
        super(7002, "이미 어드민 권한을 가진 사원입니다. 로그인을 진행해주세요");
    }
}
