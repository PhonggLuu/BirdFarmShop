/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import swp391.birdfarmshop.dao.UserDAO;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.util.JWTUtils;

/**
 *
 * @author tlminh
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String DEST_NAV_HOME = "RenderHomeController";
    private static final String DEST_NAV_DASHBOARD = "RenderReportsController";
    private static final String DEST_NAV_LOGIN = "/authentication/login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = DEST_NAV_LOGIN;
        try {
            String username = request.getParameter("account");
            String password = request.getParameter("password");
            String save = request.getParameter("checkbox");
            String encodePassword = JWTUtils.encodeJWT(password);
            UserDAO user = new UserDAO();
            User u = user.findUser(username, username);
            HttpSession session = request.getSession(true);
            if (u != null) {
                String decodePassword = JWTUtils.decodeJWT(u.getPassword());
                if (password.equals(decodePassword)) {
                    if (u.getStatus().equals("active")) {
                        if (session != null) {
                            session.setAttribute("LOGIN_USER", u);
                            session.setAttribute("SUCCESS", "Đăng nhập thành công");
                            if (save != null) {
                                String encodeEmail = JWTUtils.encodeJWT(u.getEmail());
                                Cookie cookie = new Cookie("token", encodeEmail);
                                cookie.setMaxAge(24 * 60 * 60);
                                response.addCookie(cookie);
                            }

                            url =  DEST_NAV_HOME;
                            if(u.getRole().equals("admin")||u.getRole().equals("manager")){
                                url = DEST_NAV_DASHBOARD;
                            }
                        }
                    } else if (u.getStatus().equals("inactive")) {
                        session.setAttribute("ERROR", "Vui lòng kích hoạt tài khoản của bạn bằng cách nhấn vào liên kết trong email đã đăng ký");
                    } else {
                        session.setAttribute("ERROR", "Tài khoản của bạn đã bị khóa, vui lòng liên hệ với cửa hàng");
                    }
                } else {
                    session.setAttribute("ERROR", "Email/tên đăng nhập hoặc mật khẩu không chính xác");
                }
            } else {
                session.setAttribute("ERROR", "Email/tên đăng nhập hoặc mật khẩu không chính xác");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
