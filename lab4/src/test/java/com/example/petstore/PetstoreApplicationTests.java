package com.example.petstore;

import com.example.petstore.controller.PetController;
import com.example.petstore.model.Pet;
import com.example.petstore.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PetstoreApplicationTests {

    @Autowired
    private PetController petController;

    @MockBean
    private PetService petService;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddPet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Doggie");

        when(petService.addOrUpdatePet(pet)).thenReturn(pet);

        ResponseEntity<Pet> response = petController.addPet(pet);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Doggie", response.getBody().getName());
    }

    @Test
    void testUpdatePet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Doggie");

        when(petService.addOrUpdatePet(pet)).thenReturn(pet);

        ResponseEntity<Pet> response = petController.updatePet(pet);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Doggie", response.getBody().getName());
    }

    @Test
    void testGetPetById() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Doggie");

        when(petService.getPetById(1L)).thenReturn(Optional.of(pet));

        ResponseEntity<Pet> response = petController.getPetById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Doggie", response.getBody().getName());
    }

    @Test
    void testGetPetByIdNotFound() {
        when(petService.getPetById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Pet> response = petController.getPetById(1L);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeletePet() {
        doNothing().when(petService).deletePet(1L);

        ResponseEntity<Void> response = petController.deletePet(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(petService, times(1)).deletePet(1L);
    }
}