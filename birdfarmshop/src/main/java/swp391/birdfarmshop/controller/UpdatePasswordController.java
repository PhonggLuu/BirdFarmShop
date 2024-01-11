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
import swp391.birdfarmshop.util.JWTUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdatePasswordController", urlPatterns = {"/UpdatePasswordController"})
public class UpdatePasswordController extends HttpServlet {

    private static final String DEST_NAV_UPDATE_PASSWORD = "/profile/change-password.jsp";
    private static final String DEST_NAV_PROFILE = "RenderProfileController";

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
        String url = DEST_NAV_UPDATE_PASSWORD;
        try {
            HttpSession session = request.getSession();
            String u = request.getParameter("username");
            String oldpassword = request.getParameter("oldpassword");
            String newpassword = request.getParameter("newpassword");
            UserDAO ud = new UserDAO();
            User user = ud.findUser(u, "");
            if (user != null) {
                String decodePassword = JWTUtils.decodeJWT(user.getPassword());
                if (decodePassword.equals(oldpassword)) {
                    String encodePassword = JWTUtils.encodeJWT(newpassword);
                    int result = ud.updatePassword(u, encodePassword);
                    if (result == 0) {
                        session.setAttribute("ERROR", "Cập nhật mật khẩu thất bại");
                    } else {
                        session.setAttribute("SUCCESS", "Cập nhật mật khẩu thành công");
                        url = DEST_NAV_PROFILE;
                    }
                } else {
                    session.setAttribute("ERROR", "Mật khẩu cũ không đúng");
                }
            } else {
                session.setAttribute("ERROR", "Không tìm thấy tài khoản của bạn");
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
