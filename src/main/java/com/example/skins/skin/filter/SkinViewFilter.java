package com.example.skins.skin.filter;

import com.example.skins.skin.service.SkinService;
import com.example.skins.user.entity.UserRoles;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class SkinViewFilter implements Filter {

    @EJB
    private SkinService skinService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String username = httpRequest.getUserPrincipal().getName();
        try {
            UUID skinId = UUID.fromString(httpRequest.getParameter("id"));

            if (httpRequest.isUserInRole(UserRoles.ADMIN) || skinService.find(skinId).get().getUser().getLogin().equals(username)) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied: Not an admin or the author.");
            }
        } catch (Exception ex) {
            chain.doFilter(request, response);
        }

    }

}
