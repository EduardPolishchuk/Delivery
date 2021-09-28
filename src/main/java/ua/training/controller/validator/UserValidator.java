package ua.training.controller.validator;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static ua.training.constants.Constants.*;

public class UserValidator {

    public Optional<User> updateValidation(HttpServletRequest request){
        return Optional.ofNullable(validate(request));

    }

    public Optional<User> singUpValidation(HttpServletRequest request){
        String login = request.getParameter("login");
        User user = validate(request);
        if(user == null){
            return Optional.empty();
        }else if (login == null || !login.matches(LOGIN_REG)){
            request.getSession().setAttribute("error",login);
            return Optional.empty();
        }
        user.setLogin(login.trim());
        return Optional.of(user);
    }

    private User validate(HttpServletRequest request) {
        String error;
        Map<String, String> map = new LinkedHashMap<>();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        map.put(EMAIL_REG, email);
        map.put(FIRST_NAME_REG, firstName);
        map.put(LAST_NAME_REG, lastName);
        map.put(PASSWORD_REG, password);
        for (String str : map.keySet()) {
            if (!map.get(str).matches(str)) {
                error = str.equals(password) ? "passwordInvalid" : map.get(str);
                request.getSession().setAttribute("error", error);
                return null;
            }
        }
        return User.builder()
                .lastName(lastName)
                .password(password)
                .firstName(firstName)
                .email(email)
                .build();
    }
}
