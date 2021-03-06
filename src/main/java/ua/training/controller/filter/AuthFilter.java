package ua.training.controller.filter;

import ua.training.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User.Role role1 = (User.Role) request.getSession().getAttribute("role");
        String[] uri = request.getRequestURI().split("/");
        if (access(role1, uri[uri.length - 1])) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/error/noAccess.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean access(User.Role role, String page) {
        return role != null && page.contains(role.toString().toLowerCase());
    }
}
