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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import swp391.birdfarmshop.dao.OrderDAO;
//import swp391.birdfarmshop.model.Order;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CancelBirdPairController", urlPatterns = {"/CancelBirdPairController"})
public class CancelBirdPairController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "MainController?action=NavToCustomerOrder";
    private static final String HOME = "MainController?action=NavToHome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        OrderDAO orderDao = new OrderDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");
        try {
            if (user != null && user.getRole().equals("customer")) {
                String txtOrderId = request.getParameter("orderId");
                String reason = request.getParameter("reason");
                if (reason != null) {
                    if (reason.isEmpty()) {
                        reason = null;
                    } else {
                        reason = reason.replace("Tôi", "Khách");
                        reason += " (" + user.getUsername() + ")";
                    }
                }
                boolean isUpdated = orderDao.updateOrderStatus(txtOrderId, reason, "Đã hủy", request);
                if (isUpdated) {
                    session.setAttribute("SUCCESS", "Bạn đã hủy đơn hàng " + txtOrderId + " thành công");
                } else {
                    session.setAttribute("ERROR", "Bạn hủy đơn hàng thất bại. Liên hệ nhân viên để biết thêm thông tin.");
                }
                url = SUCCESS;
            } else {
                url = HOME;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CancelBirdPairController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CancelBirdPairController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CancelBirdPairController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CancelBirdPairController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
