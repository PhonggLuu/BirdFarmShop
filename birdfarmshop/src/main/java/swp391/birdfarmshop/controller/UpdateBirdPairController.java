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
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.dao.TrackingBirdPairDAO;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateBirdPairController", urlPatterns = {"/UpdateBirdPairController"})
public class UpdateBirdPairController extends HttpServlet {

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
        String pairId = request.getParameter("pairId");
        String type = request.getParameter("type");
        String order = request.getParameter("orderId");
        HttpSession session = request.getSession();
        TrackingBirdPairDAO trackingDao = new TrackingBirdPairDAO();
        OrderDAO o = new OrderDAO();
        User u = (User) session.getAttribute("LOGIN_USER");
        try {
            if (u != null) {
                if (type.endsWith("repair")) {
                    int result = trackingDao.updateTrackingBirdPair(pairId, "Khách hàng yêu cầu ghép cặp lại", "Đang ghép cặp");
                    if (result == 0) {
                        session.setAttribute("ERROR", "Ghép lại thất bại");
                    } else {
                        session.setAttribute("SUCCESS", "Ghép lại thành công");
                    }
                } else {
                    boolean result = o.updateOrderStatus(order, "Trứng không nở thành con", "Đã hủy", request);
                    if (result == false) {
                        session.setAttribute("ERROR", "Hủy thất bại");
                    } else {
                        session.setAttribute("SUCCESS", "Hủy thành công");
                    }
                }
            } else {
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect("MainController?action=NavToBirdPairDetail&order_id=" + order + "&pair_id=" + pairId);
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
