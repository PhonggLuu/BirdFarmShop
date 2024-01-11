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
@WebServlet(name = "UpdateActivityController", urlPatterns = {"/UpdateActivityController"})
public class UpdateActivityController extends HttpServlet {

    private static final String DEST_NAV_ACCOUNTS = "RenderAccountsController";
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
        String url = DEST_NAV_ACCOUNTS;
        try {
            String user = request.getParameter("username");
            String type = request.getParameter("type");
            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("LOGIN_USER");
            UserDAO ud = new UserDAO();
            User u = ud.getUserByUsername(user);
            if (sessionUser != null) {
                if (sessionUser.getRole().equals("admin") || sessionUser.getRole().equals("manager")) {
                    if (u != null) {
                        switch (type) {
                            case "lock": {
                                int result = ud.updateActive(user, "lock");
                                if (result == 0) {
                                    session.setAttribute("ERROR", "Khóa tài khoản thất bại");
                                } else {
                                    session.setAttribute("SUCCESS", "Khóa tài khoản thành công");
                                }
                                break;
                            }
                            case "open": {
                                int result = ud.updateActive(user, "active");
                                if (result == 0) {
                                    session.setAttribute("ERROR", "Mở khóa tài khoản thất bại");
                                } else {
                                    session.setAttribute("SUCCESS", "Mở khóa tài khoản thành công");
                                }
                                break;
                            }
                            default: {
                                String token = "Thegioivetcanh123@";
                                String password = JWTUtils.encodeJWT(token);
                                int result = ud.updatePassword(user, password);
                                if (result == 0) {
                                    session.setAttribute("SUCCESS", "Cấp lại mật khẩu thất bại");
                                } else {
                                    session.setAttribute("SUCCESS", "Cấp lại mật khẩu thành công");
                                }
                                break;
                            }
                        }
                    } else {
                        session.setAttribute("ERROR", "Không tìm thấy tài khoản");
                    }
                } else {
                    session.setAttribute("ERROR", "Bạn không phải là quản lí hoặc quản trị viên");
                    url = DEST_NAV_LOGIN;
                }
            } else {
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
                url = DEST_NAV_LOGIN;
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
