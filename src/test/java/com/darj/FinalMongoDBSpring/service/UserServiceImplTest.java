package com.darj.FinalMongoDBSpring.service;

import com.darj.FinalMongoDBSpring.dto.UserResponseDto;
import com.darj.FinalMongoDBSpring.model.User;
import com.darj.FinalMongoDBSpring.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.of;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_all_users_test() {
        List<User> users = new ArrayList<>();
        users.add(new User("Juan", "Perez", LocalDate.of(1950, 2, 15), "mail@mail.com", "hola"));
        users.add(new User("Karina", "Lopez", LocalDate.of(1950, 2, 15), "mail@mail.com", "hola"));
        Mockito.when(userRepository.getAllUsers()).thenReturn(users);

        List<UserResponseDto> response = userService.getAllUsers();

        // Afirmar que la lista de usuarios no está vacía
        Assertions.assertFalse(response.isEmpty());
    }

     @Test
     void testFindUserById() {
            // Given
            User user = new User("Juan", "Perez", LocalDate.of(1950, 2, 15), "mail@mail.com", "hola");
            OngoingStubbing<User> juan = Mockito.when(userRepository.findUserById("Juan")).thenReturn(user);
            // When
            String result = userService.findUserById("Juan").getName();
            // Then
            assertNotNull(result);
            assertEquals("Juan", result);
     }

     @Test
     void testFindByEmail() {
         // Given
         User user = new User("Juan", "Perez", LocalDate.of(1950, 2, 15), "mail@mail.com", "hola");
         Mockito.when(userRepository.findByEmail("mail@mail.com")).thenReturn(of(user));

         // When
         String result = userService.findByEmail("mail@mail.com").getName();

         // Then
         assertNotNull(result);
         assertEquals("Juan", result);
     }
     @Test
    void testDeleteUser() {
         List<User> users = new ArrayList<>();
         users.add( new User("Juan", "Perez", LocalDate.of(1950, 2, 15), "mail@mail.com", "hola"));
         Mockito.when(userRepository.deleteUser("Juan")).thenReturn(users.isEmpty());

         List<UserResponseDto> response = userService.getAllUsers();

         assert(response.isEmpty());

     }
}

/*

    @Test
    void testFindByEmail() {
        // Given
        User user = new User("1", "John Doe", "john@example.com");
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        // When
        User result = userService.findByEmail("john@example.com");

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }*/

    // Similar tests for createUser, createUserAdmin, updateUser, and deleteUser methods
