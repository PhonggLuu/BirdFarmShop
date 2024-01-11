/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "CreateAccountController", urlPatterns = {"/CreateAccountController"})
public class CreateAccountController extends HttpServlet {

    private static final String ERROR = "/WEB-INF/errorpages/error.jsp";
    private static final String SUCCESS = "RenderAccountsController";
    private static final String DEST_NAV_LOGIN = "/authentication/login.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        boolean check = true;
        try {
            UserDAO userDao = new UserDAO();
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            if(u != null){
                if(u.getRole().equals("admin")||u.getRole().equals("manager") ){
                    String full_name = request.getParameter("fullname");
                    String username = request.getParameter("newusername");
                    String role = request.getParameter("role");
                    String token = "Thegioivetcanh123@";
                    String password = JWTUtils.encodeJWT(token);
                    u = userDao.getUserByUsername(username);
                    if (u == null) {
                        int result = userDao.createUser(username, "", password, full_name, "", role, "form", "active");
                        if (result == 0) {
                            session.setAttribute("ERROR", "Tạo tài khoản thất bại");
                        } else {
                            session.setAttribute("SUCCESS", "Tạo tài khoản thành công");
                            check = false;
                            response.sendRedirect("MainController?action=NavToAccounts");
                        }
                    } else {
                        session.setAttribute("ERROR", "Tài khoản này đã sử dụng");
                    }
                    url = SUCCESS;
                } else {
                    session.setAttribute("ERROR", "Bạn không phải là quản lí hoặc quản trị viên");
                    url = DEST_NAV_LOGIN;
                }
            } else {
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
                url = DEST_NAV_LOGIN;
            }
        } catch (Exception ex) {
            log("Error at CreateAccountController: " + ex.toString());
        } finally {
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
