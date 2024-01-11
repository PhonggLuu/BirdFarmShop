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
 * @author tlminh
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private static final String DEST_NAV_REGISTER = "/authentication/register.jsp";
    private static final String DEST_NAV_LOGIN = "MainController?action=NavToLogin";
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
        String url = DEST_NAV_REGISTER;
        boolean check = true;
        try {
            String name = request.getParameter("name").trim();
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            UserDAO user = new UserDAO();
            User u = user.findUser("", email);
            HttpSession session = request.getSession();
            if (u != null) {
                session.setAttribute("ERROR", "Email đã được sử dụng");
            } else {
                User username = user.findUser(account, "");
                if (username == null) {
                    String endcodePassword = JWTUtils.encodeJWT(password);
                    String token = JWTUtils.encodeJWT(email);
                    int resultSendMail = EmailService.sendEmail(email, "Kích hoạt tài khoản", EmailUtils.sendActive(name, token));
                    if (resultSendMail == 1) {
                        int result = user.createUser(account, email, endcodePassword, name, mobile, "customer", "form", "inactive");
                        if (result == 0) {
                            session.setAttribute("ERROR", "Tạo tài khoản không thành công");
                        } else {
                            session.setAttribute("SUCCESS", "Tạo tài khoản thành công. Kiểm tra email để kích hoạt tài khoản");
                            check = false;
                            response.sendRedirect(DEST_NAV_LOGIN);
                        }
                    } else {
                        session.setAttribute("ERROR", "Tạo tài khoản không thành công");
                    }
                } else {
                    session.setAttribute("ERROR", "Tên đăng nhập đã được sử dụng");
                }
            }  
        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
        }finally {
           if(check){
               request.getRequestDispatcher(url).forward(request, response);
           }
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
