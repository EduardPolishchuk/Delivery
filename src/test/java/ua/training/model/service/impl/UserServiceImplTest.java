package ua.training.model.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.dao.impl.JDBCUserDao;
import ua.training.model.entity.User;

import java.util.ArrayList;
import java.util.Optional;


class UserServiceImplTest {
    @Mock
    private JDBCDaoFactory daoFactoryMock;
    @Mock
    private JDBCUserDao userDaoMock;
    @InjectMocks
    private UserServiceImpl userServiceInstance;


    @Test
    void shouldReturnUserByID() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.findById(1)).thenReturn(Optional.of(User.builder().id(1).build()));
        User user = userServiceInstance.findById(1).get();
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    void shouldReturnUserIfValid() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.findIfValid("login", "pass"))
                .thenReturn(Optional.of(User.builder().login("login").password("pass").build()));
        Optional<User> optional = userServiceInstance.findIfValid("login", "pass");
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals("login", optional.get().getLogin());
        Assertions.assertEquals("pass", optional.get().getPassword());
    }

    @Test
    void shouldReturnEmptyOptional() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.findIfValid("login", "pass"))
                .thenReturn(Optional.empty());
        Optional<User> optional = userServiceInstance.findIfValid("login", "pass");
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void shouldReturnUserList() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertTrue(userServiceInstance.findAll().isEmpty());
    }

    @Test
    void shouldReturnTrueIsCreate() {
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.create(user)).thenReturn(true);
        Assertions.assertTrue(userServiceInstance.create(user));
    }

    @Test
    void shouldReturnTrueIsUpdate() {
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.update(user)).thenReturn(true);
        Assertions.assertTrue(userServiceInstance.update(user));
    }

}
