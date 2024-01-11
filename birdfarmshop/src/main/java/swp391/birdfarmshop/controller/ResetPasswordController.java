/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import swp391.birdfarmshop.dao.UserDAO;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.services.EmailService;
import swp391.birdfarmshop.util.EmailUtils;
import swp391.birdfarmshop.util.JWTUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/ResetPasswordController"})
public class ResetPasswordController extends HttpServlet {

    private static final String ERROR = "/WEB-INF/errorpages/error.jsp";
    public static final String DEST_NAV_RESET = "/authentication/reset.jsp";
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
        response.setContentType("text/html;charset=UTF-8");
        String url = "MainController?action=NavToReset"; 
        try {
            String email = request.getParameter("email");
            UserDAO user = new UserDAO();
            User u = user.findUser("", email);
            String token = JWTUtils.randomPasswordToken();
            HttpSession session = request.getSession();
            if (u != null && "customer".equals(u.getRole())) {
                String password = u.getPassword();
                if (password.isEmpty()) {
                    session.setAttribute("ERROR", "Email này đã đăng kí bằng tài khoản google");
                } else {
                    int resultSendMail = EmailService.sendEmail(u.getEmail(), "Cấp lại mật khẩu cho tài khoản", EmailUtils.sendPassword(u.getFullName(),token));
                    String newPassword = JWTUtils.encodeJWT(token);
                    if (resultSendMail == 1) {
                        int result = user.updatePassword(u.getUsername(), newPassword);
                        if (result == 0) {
                        } else {
                            session.setAttribute("SUCCESS", "Kiểm tra email để nhận mật khẩu mới");
                            url ="MainController?action=NavToLogin";
                        }
                    }else{
                        session.setAttribute("ERROR", "Gửi email thất bại");
                    }
                }
            } else {
                session.setAttribute("ERROR", "Email này không trùng khớp với email đăng kí");   
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
