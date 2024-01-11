/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dto.AddStatusDTO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.OrderedAccessoryItem;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateAccessoryCartController", urlPatterns = {"/UpdateAccessoryCartController"})
public class UpdateAccessoryCartController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            String accessory_id = request.getParameter("accessory_id");
            String type = request.getParameter("type");
            AccessoryDAO adao = new AccessoryDAO();
            Accessory accessory = adao.getAccessoryByID(accessory_id);
            HttpSession session = request.getSession();
            if (type != null && type.equals("up")) {
                if (session != null) {
                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    if (cart == null) {
                        cart = new CartDTO();
                    }
                    int stockQuantity = accessory.getStock_quantity();
                    //lấy ra số lượng trong cart 
                    int orderQuantity = 0, freeQuantity = 0;
                    if(cart.getAccessoryList().get(accessory.getAccessory_id()) != null) {
                        orderQuantity = cart.getAccessoryList().get(accessory.getAccessory_id()).getOrder_quantity();
                        freeQuantity = cart.getAccessoryList().get(accessory.getAccessory_id()).getFree_order();
                    }
                    boolean checkAdd = false;
                    if(orderQuantity + freeQuantity + 1 <= stockQuantity) {
                        checkAdd = cart.addAccessoryToCart(accessory, 1);
                    }
                    if (checkAdd) {
                        OrderedAccessoryItem item = cart.getAccessoryList().get(accessory_id);
                        int price = accessory.getUnit_price() * item.getOrder_quantity();
                        if (accessory.getDiscount() > 0) {
                            price = (accessory.getUnit_price() - (accessory.getUnit_price() * accessory.getDiscount() / 100)) * item.getOrder_quantity();
                        }
                        AddStatusDTO status = new AddStatusDTO();
                        Gson gson = new Gson();
                        status.setTotalAccessory(item.getOrder_quantity());
                        status.setQuantity(cart.getTotalItem());
                        status.setTotalPrice(cart.getCartTotalPrice());
                        status.setTotalPriceAccessory(price);
                        out.println(gson.toJson(status));
                        session.setAttribute("CART", cart);
                    }
                }
            } else if(type != null && type.equals("down")) {
                if (session != null) {
                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    if (cart == null) {
                        cart = new CartDTO();
                    }
                    boolean checkAdd = cart.removeAccessoryItem(accessory);
                    if (checkAdd) {
                        OrderedAccessoryItem item = cart.getAccessoryList().get(accessory_id);
                        int price = accessory.getUnit_price() * item.getOrder_quantity();
                        if (accessory.getDiscount() > 0) {
                            price = (accessory.getUnit_price() - (accessory.getUnit_price() * accessory.getDiscount() / 100)) * item.getOrder_quantity();
                        }
                        AddStatusDTO status = new AddStatusDTO();
                        Gson gson = new Gson();
                        status.setTotalAccessory(item.getOrder_quantity());
                        status.setQuantity(cart.getTotalItem());
                        status.setTotalPrice(cart.getCartTotalPrice());
                        status.setTotalPriceAccessory(price);
                        out.println(gson.toJson(status));
                        session.setAttribute("CART", cart);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
