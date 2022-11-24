package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

class DeleteChildServiceTest {
    private DeleteChildService deleteChildService;
    private ChildRepository childRepository;

    @BeforeEach
    void setUp() {
        childRepository = mock(ChildRepository.class);
        deleteChildService = new DeleteChildService(childRepository);
        given(childRepository.findById(2L)).willReturn(any());
    }

    @Test
    void delete() {
        deleteChildService.delete(new ChildDeleteDto(2L));

        verify(childRepository).deleteById(2L);
    }
}
