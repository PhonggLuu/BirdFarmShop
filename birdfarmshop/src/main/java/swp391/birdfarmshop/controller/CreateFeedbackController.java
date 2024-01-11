/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import swp391.birdfarmshop.dao.FeedbackDAO;
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.dao.OrderItemDAO;
import swp391.birdfarmshop.dto.OrderItemDTO;
import swp391.birdfarmshop.model.Feedback;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CreateFeedbackController", urlPatterns = {"/CreateFeedbackController"})
public class CreateFeedbackController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "MainController?action=NavToCustomerOrder";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            if(user != null  && user.getRole().equals("customer")) {
                String status = request.getParameter("status");
                int rating = Integer.parseInt(request.getParameter("star"));
                String comment = request.getParameter("feedback");
                int order_item_id = Integer.parseInt(request.getParameter("order_item_id"));
                OrderItemDAO itemDao = new OrderItemDAO();
                OrderItemDTO item = itemDao.getOrderItem(order_item_id);
                Feedback feedback = new Feedback(null, user.getUsername(), order_item_id, rating, comment, null);
                FeedbackDAO feedbackDAO = new FeedbackDAO();
                boolean fb = feedbackDAO.addFeedback(feedback);
                OrderDAO orderDao = new OrderDAO();
                if (fb) {
                    if(item != null) {
                        orderDao.updateOrderStatus(item.getOrder_id(), null, "Đã đánh giá", request);
                    }
                    session.setAttribute("SUCCESS", "Đã thêm đánh giá thành công");
                    url = SUCCESS;
                }
                if(status != null && !status.isEmpty()) {
                    request.setAttribute("status", status);
                } else {
                    request.setAttribute("status", null);
                }
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException | ParseException e) {
            log("Error at CreateFeedbackController: " + e.toString());
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
