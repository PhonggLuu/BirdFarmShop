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
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Accessory;

/**
 *
 * @author tlminh
 */
@WebServlet(name="RemoveAccessoryFromCartController", urlPatterns={"/RemoveAccessoryFromCartController"})
public class RemoveAccessoryFromCartController extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String accessory_id = request.getParameter("accessory_id");
            AccessoryDAO aDao = new AccessoryDAO();
            Accessory accessory = aDao.getAccessoryByID(accessory_id);
            HttpSession session = request.getSession();
            if (session != null) {
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                int order_quantity = cart.getAccessoryList().get(accessory_id).getOrder_quantity();
                cart.removeAccessoryFromCart(accessory, order_quantity);
                session.setAttribute("CART", cart);
                session.setAttribute("SUCCESS", "Xóa sản phẩm thành công");
            }else{
                session.setAttribute("ERROR", "Xóa sản phẩm thất bại");
            }
        } catch (Exception e) {
            log("Error at RemoveBirdFromCartController: " + e.toString());
        } finally {
            response.sendRedirect("MainController?action=NavToCart");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
