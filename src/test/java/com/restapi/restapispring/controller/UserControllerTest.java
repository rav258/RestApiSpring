package com.restapi.restapispring.controller;

import com.restapi.restapispring.model.User;
import com.restapi.restapispring.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private User user;

    @Test
    void getAllUsers() {
        String name = "Rafal";

        Mockito.when(userRepository.findAll()).thenReturn(List.of(User.builder().FirstName(name).build()));
        final List<User> all = userRepository.findAll();
        final String firstName = all.stream().findFirst().orElseThrow().getFirstName();

        Assertions.assertThat(firstName).isEqualTo(name);
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void createUser() {
        // given
        Mockito.when(userRepository.save(user))
                .thenReturn(User.builder().FirstName("Rafal").build());

        // when
        final User savedUser = userRepository.save(user);
        final String firstName = savedUser.getFirstName();

        //then
        Assertions.assertThat(firstName).isEqualTo("Rafal");
    }

    @Test
    void getUserById() {
        long id = 1;
        String firstName = "Rafal";
        String lastName = "w";
        String email = "raf@wp.pl";
        Mockito.when(userRepository.getById(id))
                .thenReturn(User.builder().id(id).FirstName(firstName).lastName(lastName).email(email).build());

        final User UserById = userRepository.getById(id);

        Assertions.assertThat(UserById).isEqualTo(new User(id, firstName, lastName, email));
        Mockito.verify(userRepository, Mockito.times(1)).getById(ArgumentMatchers.any());
    }

}
