package com.example.petstore;

import com.example.petstore.model.Pet;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {
    private final PetRepository repository = mock(PetRepository.class);
    private final PetService service = new PetService(repository);

    @Test
    void testGetPetById() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Doggie");

        when(repository.getPetById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> result = service.getPetById(1L);
        assertTrue(result.isPresent());
        assertEquals("Doggie", result.get().getName());
    }

    @Test
    void testAddOrUpdatePet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Doggie");

        when(repository.save(pet)).thenReturn(pet);

        Pet result = service.addOrUpdatePet(pet);
        assertNotNull(result);
        assertEquals("Doggie", result.getName());
    }

    @Test
    void testDeletePet() {
        doNothing().when(repository).deleteById(1L);

        service.deletePet(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}