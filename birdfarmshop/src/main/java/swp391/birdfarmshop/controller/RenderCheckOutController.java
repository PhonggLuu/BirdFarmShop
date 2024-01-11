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
import java.util.HashMap;
import java.util.Map;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.OrderedBirdItem;
import swp391.birdfarmshop.model.User;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "RenderCheckOutController", urlPatterns = {"/RenderCheckOutController"})
public class RenderCheckOutController extends HttpServlet {
    
private static final String DEST_NAV_CART = "RenderCartController";
private static final String DEST_NAV_CHECK_OUT = "shop/checkout.jsp";
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = DEST_NAV_CART;
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            if (u != null) {
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if(cart != null){
                    url = DEST_NAV_CHECK_OUT;
                }else{
                    session.setAttribute("ERROR", "Không có sản phẩm nào trong giỏ hàng của bạn");
                }
            }else{
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
            }
        } catch (Exception e) {
            log("Error at RenderCheckOutController: " + e.toString());
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
