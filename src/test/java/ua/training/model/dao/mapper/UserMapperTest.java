package ua.training.model.dao.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperTest {
    private UserMapper userMapper = new UserMapper();

    @Mock
    private ResultSet resultSet;

    @Test
    public void shouldReturnCorrectUser() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(resultSet.getLong("user_id")).thenReturn(1L);
        Mockito.when(resultSet.getBigDecimal("balance")).thenReturn(BigDecimal.valueOf(200));
        Mockito.when(resultSet.getString("login")).thenReturn("Login");
        Mockito.when(resultSet.getString("role_name")).thenReturn("USER");
        Mockito.when(resultSet.getString("email")).thenReturn("email");
        Mockito.when(resultSet.getString("first_name")).thenReturn("Name");
        Mockito.when(resultSet.getString("last_name")).thenReturn("LastName");

        User user = userMapper.extractFromResultSet(resultSet);
        Assertions.assertEquals(1,user.getId());
        Assertions.assertEquals(BigDecimal.valueOf(200),user.getBalance());
        Assertions.assertEquals("Login",user.getLogin());
        Assertions.assertEquals(User.Role.USER,user.getRole());
        Assertions.assertEquals("Name",user.getFirstName());
        Assertions.assertEquals("LastName",user.getLastName());
        Assertions.assertEquals("email",user.getEmail());
    }
}
