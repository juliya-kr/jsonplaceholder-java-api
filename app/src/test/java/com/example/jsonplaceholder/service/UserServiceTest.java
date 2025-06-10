package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = User.builder().id(1L).name("User One").username("userone").email("userone@example.com").build();
        user2 = User.builder().id(2L).name("User Two").username("usertwo").email("usertwo@example.com").build();
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<User> users = userService.findAll();
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals(user1, users.get(0));
        assertEquals(user2, users.get(1));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Optional<User> foundUser = userService.findById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(user1, foundUser.get());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findById_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<User> foundUser = userService.findById(1L);
        assertFalse(foundUser.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(userRepository.save(user1)).thenReturn(user1);
        User savedUser = userService.save(user1);
        assertNotNull(savedUser);
        assertEquals(user1, savedUser);
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void delete() {
        doNothing().when(userRepository).deleteById(1L);
        userService.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
