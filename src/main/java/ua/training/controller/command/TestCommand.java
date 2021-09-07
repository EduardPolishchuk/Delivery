package ua.training.controller.command;

import ua.training.model.entity.City;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.training.constants.Constants.*;


public class TestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("city"));
        List<City> list = (List) request.getSession().getAttribute("cityList");
        System.out.println(list.size() + "size");
        System.out.println(id);
        return INDEX_JSP;
    }
}
