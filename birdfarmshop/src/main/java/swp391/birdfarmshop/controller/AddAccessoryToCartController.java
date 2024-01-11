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
 * @author tlminh
 */
@WebServlet(name = "AddAccessoryToCartController", urlPatterns = {"/AddAccessoryToCartController"})
public class AddAccessoryToCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            String accessory_id = request.getParameter("accessory_id");
            int order_quantity = Integer.parseInt(request.getParameter("order_quantity"));
            AccessoryDAO adao = new AccessoryDAO();
            Accessory accessory = adao.getAccessoryByID(accessory_id);
            HttpSession session = request.getSession();
            if (accessory_id != null) {
                if (session != null) {
                    AddStatusDTO status = new AddStatusDTO();
                    Gson gson = new Gson();
                    status.setStatus("Thất bại");
                    status.setType("error");
                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    if (cart == null) {
                        cart = new CartDTO();
                    }
                    int currentStock = accessory.getStock_quantity();
                    int quantityBuy = 0;
                    if (cart.getAccessoryList().get(accessory.getAccessory_id()) != null) {
                        quantityBuy += cart.getAccessoryList().get(accessory.getAccessory_id()).getOrder_quantity() + cart.getAccessoryList().get(accessory.getAccessory_id()).getFree_order();
                    }
                    if (order_quantity + quantityBuy <= currentStock) {
                        boolean checkAdd = cart.addAccessoryToCart(accessory, order_quantity);
                        if (checkAdd) {
                            status.setStatus("Thành công");
                            status.setContent("Thêm sản phẩm vào giỏ hàng thành công");
                            status.setQuantity(cart.getTotalItem());
                            status.setType("success");
                            session.setAttribute("CART", cart);
                        } else {
                            status.setQuantity(cart.getTotalItem());
                            status.setContent("Sản phẩm đã hết hàng");
                        }
                    }else{
                        status.setQuantity(cart.getTotalItem());
                        status.setContent(accessory.getAccessory_id() + " chỉ có số lượng là " + currentStock + ", bạn đã có " + quantityBuy + " sản phẩm trong giỏ hàng");
                    }
                    out.println(gson.toJson(status));
                }
            }
        } catch (Exception e) {
            log("Error at AddAccessoryToCartController: " + e.toString());
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
