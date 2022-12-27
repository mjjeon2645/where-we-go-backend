package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetAdminServiceTest {
    private GetAdminService getAdminService;
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        adminRepository = mock(AdminRepository.class);
        getAdminService = new GetAdminService(adminRepository);

        Admin foundAdmin = Admin.fake("socialLoginId");

        given(adminRepository.findBySocialLoginId("socialLoginId"))
            .willReturn(Optional.of(foundAdmin));

        given(adminRepository.findBySocialLoginId("xxx"))
            .willThrow(AuthenticationError.class);
    }

    @Test
    void admin() {
        Admin foundAdmin = getAdminService.admin("socialLoginId");

        assertThat(foundAdmin.name()).isEqualTo("전민지");
        assertThat(foundAdmin.employeeIdentificationNumber()).isEqualTo(1234L);

        verify(adminRepository).findBySocialLoginId("socialLoginId");
    }

    @Test
    void adminWithWrongSocialLoginId() {
        assertThrows(AuthenticationError.class, () -> {
            getAdminService.admin("xxx");
        });
    }
}
