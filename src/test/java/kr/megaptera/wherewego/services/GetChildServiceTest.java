package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetChildServiceTest {
    private ChildRepository childRepository;
    private GetChildService getChildService;

    @BeforeEach
    void setUp() {
        childRepository = mock(ChildRepository.class);
        getChildService = new GetChildService(childRepository);

        given(childRepository.findAllByUserId(1L))
            .willReturn(List.of(
                new Child(1L, 1L, "공주님", "2022-01-01"),
                new Child(2L, 1L, "공주님", "2022-01-01")
            ));

        given(childRepository.findAllByUserId(2L)).willReturn(List.of());
    }

    @Test
    void children() {
        assertThat(getChildService.children(1L)).hasSize(2);
        assertThat(getChildService.children(2L)).hasSize(0);
    }
}
