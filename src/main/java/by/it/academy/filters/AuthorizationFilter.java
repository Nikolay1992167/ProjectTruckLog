package by.it.academy.filters;

import by.it.academy.services.ValidationInFilterService;
import lombok.SneakyThrows;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/create"})
public class AuthorizationFilter extends HttpFilter {
    ValidationInFilterService filterService = new ValidationInFilterService();

    @SneakyThrows
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        filterService.checkForDuplicates(req, res);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
