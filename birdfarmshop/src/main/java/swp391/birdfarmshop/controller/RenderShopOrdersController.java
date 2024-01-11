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
import java.util.ArrayList;
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.model.Order;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author tlminh
 */
@WebServlet(name = "RenderShopOrdersController", urlPatterns = {"/RenderShopOrdersController"})
public class RenderShopOrdersController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "management/shop-orders.jsp";
    private static final String HOME = "MainController?action=NavToHome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            OrderDAO orderDao = new OrderDAO();
            ArrayList<Order> orderList;
            int recordsPerPage = 20;
            int numberOfOrder;
            int noOfPages;
            if (user != null && !user.getRole().equals("customer")) {
                url = SUCCESS;
                String page = "1";
                if(request.getParameter("page") != null) {
                    page = request.getParameter("page");
                }
                String date = request.getParameter("date");
                String startDay = request.getParameter("startDay");
                String endDay = request.getParameter("endDay");
                String search = request.getParameter("search");
                String filterStatus = request.getParameter("filterStatus");
                numberOfOrder = orderDao.numberOfOrder(date, startDay, endDay, filterStatus, search);
                noOfPages = (int) Math.ceil(numberOfOrder * 1.0 / recordsPerPage);
                orderList = orderDao.getAllOfOrder(date, startDay, endDay, filterStatus, search, page, recordsPerPage);
                request.setAttribute("ORDERLIST", orderList);
                request.setAttribute("date", date);
                request.setAttribute("startDay", startDay);
                request.setAttribute("endDay", endDay);
                request.setAttribute("search", search);
                request.setAttribute("filterStatus", filterStatus);
                request.setAttribute("page", page);
                request.setAttribute("noOfPages", noOfPages);
            } else {
                response.sendRedirect(HOME);
            }
        } catch (IOException | SQLException  e) {
            log("Error at RenderShopOrdersController: " + e.toString());
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
