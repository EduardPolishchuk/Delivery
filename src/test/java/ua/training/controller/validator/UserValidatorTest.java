package ua.training.controller.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class UserValidatorTest {
    private UserValidator validator ;
    private User user ;


    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

        validator = new UserValidator();
        user = User.builder()
                .id(1)
                .password("password")
                .login("login")
                .email("email@gmail.com")
                .firstName("FirstName")
                .lastName("LastName")
                .balance(BigDecimal.valueOf(300))
                .role(User.Role.USER)
                .build();
    }

    @Test
    public void shouldReturnUserOptional() {
        Mockito.when(request.getParameter("login")).thenReturn(user.getLogin());
        Mockito.when(request.getParameter("password")).thenReturn(user.getPassword());
        Mockito.when(request.getParameter("firstName")).thenReturn(user.getFirstName());
        Mockito.when(request.getParameter("lastName")).thenReturn(user.getLastName());
        Mockito.when(request.getParameter("balance")).thenReturn(user.getBalance().toString());
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        Mockito.when(request.getParameter("email")).thenReturn(user.getEmail());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<User> optionalUpdate = validator.updateValidation(request);
        Optional<User> optionalSingUp = validator.singUpValidation(request);
        Assertions.assertTrue(optionalUpdate.isPresent());
        Assertions.assertTrue(optionalSingUp.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptional() {
        Mockito.when(request.getParameter("login")).thenReturn("");
        Mockito.when(request.getParameter("password")).thenReturn("");
        Mockito.when(request.getParameter("firstName")).thenReturn(user.getFirstName());
        Mockito.when(request.getParameter("lastName")).thenReturn(user.getLastName());
        Mockito.when(request.getParameter("balance")).thenReturn(user.getBalance().toString());
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        Mockito.when(request.getParameter("email")).thenReturn(user.getEmail());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<User> optionalUpdate = validator.updateValidation(request);
        Optional<User> optionalSingUp = validator.singUpValidation(request);
        Assertions.assertFalse(optionalUpdate.isPresent());
        Assertions.assertFalse(optionalSingUp.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptional2() {
        Mockito.when(request.getParameter("login")).thenReturn("");
        Mockito.when(request.getParameter("password")).thenReturn(user.getPassword());
        Mockito.when(request.getParameter("firstName")).thenReturn(user.getFirstName());
        Mockito.when(request.getParameter("lastName")).thenReturn(user.getLastName());
        Mockito.when(request.getParameter("balance")).thenReturn(user.getBalance().toString());
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        Mockito.when(request.getParameter("email")).thenReturn(user.getEmail());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<User> optionalUpdate = validator.updateValidation(request);
        Optional<User> optionalSingUp = validator.singUpValidation(request);
        Assertions.assertTrue(optionalUpdate.isPresent());
        Assertions.assertFalse(optionalSingUp.isPresent());
    }
}
