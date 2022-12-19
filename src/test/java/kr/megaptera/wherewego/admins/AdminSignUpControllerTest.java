package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.annotations.*;
import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminSignUpController.class)
@MockMvcEncoding
class AdminSignUpControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminSignUpService adminSignUpService;

    @Test
    void signUp() throws Exception {
        given(adminSignUpService.signUp(any(AdminRequestDto.class)))
            .willReturn(Admin.fake("socialLoginId"));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"name\":\"전민지\", \"socialLoginId\":\"socialLoginId\", " +
                    "\"employeeIdentificationNumber\":1234, \"password\":\"Tester123!\", " +
                    "\"profileImage\":\"imageUrl\"" +
                    "}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("전민지")
            ));
    }

    @Test
    void alreadyExistAdminAccount() throws Exception {
        given(adminSignUpService.signUp(any(AdminRequestDto.class)))
            .willThrow(ExistAdminMemberException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"name\":\"전민지\", \"socialLoginId\":\"socialLoginId\", " +
                    "\"employeeIdentificationNumber\":1234, \"password\":\"Tester123!\", " +
                    "\"profileImage\":\"imageUrl\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("이미 어드민 권한을 가진 사원입니다. 로그인을 진행해주세요")
            ));
    }

    @Test
    void alreadyExistId() throws Exception {
        given(adminSignUpService.signUp(any(AdminRequestDto.class)))
            .willThrow(DuplicateIdentifierException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin-signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"name\":\"전민지\", \"socialLoginId\":\"socialLoginId\", " +
                    "\"employeeIdentificationNumber\":1234, \"password\":\"Tester123!\", " +
                    "\"profileImage\":\"imageUrl\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요")
            ));
    }
}
