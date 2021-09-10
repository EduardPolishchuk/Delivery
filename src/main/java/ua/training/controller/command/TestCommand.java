package ua.training.controller.command;

import ua.training.model.entity.City;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.training.constants.Constants.*;


public class TestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {


        return "redirect:WEB-INF/success.jsp";
    }
}
