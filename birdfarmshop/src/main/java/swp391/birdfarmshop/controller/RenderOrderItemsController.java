/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import swp391.birdfarmshop.dao.BirdBreedDAO;
import swp391.birdfarmshop.dao.OrderItemDAO;
import swp391.birdfarmshop.dto.OrderItemDTO;
import swp391.birdfarmshop.model.BirdBreed;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author phong pc
 */
public class RenderOrderItemsController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "management/shop-orderItems.jsp";
    private static final String HOME = "MainController?action=NavToHome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String orderId = request.getParameter("order_id");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            if (user != null && !user.getRole().equals("customer")) {
                OrderItemDAO orderItemDao = new OrderItemDAO();
                ArrayList<OrderItemDTO> itemList = orderItemDao.getItemOrder(orderId);
                int count = 0;
                OrderItemDTO item = null;
                for (OrderItemDTO orderItemDTO : itemList) {
                    count++;
                    if(orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                       item = itemList.get(count-1);
                       itemList.remove(itemList.get(count-1));
                       break;
                    }
                }
                BirdBreedDAO breedDao = new BirdBreedDAO();
                List<BirdBreed> breeds = breedDao.getBirdBreeds();
                request.setAttribute("BREEDS", breeds);
                request.setAttribute("ITEMLIST", itemList);
                if(item != null) {
                    request.setAttribute("FREEITEM", item);
                }
                url = SUCCESS;
            } else {
                response.sendRedirect(HOME);
            }
        } catch (IOException | SQLException e) {
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
