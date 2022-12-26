package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

class DeleteChildServiceTest {
    private DeleteChildService deleteChildService;
    private ChildRepository childRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        childRepository = mock(ChildRepository.class);
        userRepository = mock(UserRepository.class);
        deleteChildService = new DeleteChildService(childRepository, userRepository);

        given(userRepository.findBySocialLoginId("socialLoginId"))
            .willReturn(Optional.of(User.fake1("angel2645@naver.com")));

        Child child = new Child(2L, 1L, "공주님", "2022-01-01");

        given(childRepository.findById(2L)).willReturn(Optional.of(child));
        given(childRepository.findById(3L)).willThrow(ChildNotFoundException.class);
    }

    @Test
    void delete() {
        deleteChildService.delete("socialLoginId", new ChildDeleteDto(2L));

        verify(childRepository).deleteById(2L);

        assertThrows(ChildNotFoundException.class, () -> {
           deleteChildService.delete("socialLoginId", new ChildDeleteDto(3L));
        });
    }
}
