/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package swp391.birdfarmshop.fillter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import swp391.birdfarmshop.dao.UserDAO;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.util.JWTUtils;

/**
 *
 * @author Admin
 */
@WebFilter(filterName = "FillterCookie", servletNames = {"MainController", "RenderHomeController"}, dispatcherTypes = {DispatcherType.REQUEST})
public class FillterLogin implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        Throwable problem = null;

        HttpServletRequest res = (HttpServletRequest) request;
        try {
            Cookie[] cookie = res.getCookies();
            HttpSession session = res.getSession();
            String action = request.getParameter("action");
            User u = (User) session.getAttribute("LOGIN_USER");
            UserDAO user = new UserDAO();
            if(u == null && (action != "ACT_NAV_LOGIN" || action != "ACT_NAV_REGISTER"))
            if (cookie != null) {
                for (Cookie c : cookie) {
                    if (c.getName().equals("token")) {
                        String decodeEmail = JWTUtils.decodeJWT(c.getValue());
                        u = user.findUser("", decodeEmail);
                        u.setEmail(c.getValue());
                        session.setAttribute("LOGIN_USER", u);
                        break;
                    }
                }
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
    }

}
