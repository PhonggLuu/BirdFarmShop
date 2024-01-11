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
import swp391.birdfarmshop.dao.BirdNestDAO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.BirdNest;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddBirdNestToCartController", urlPatterns = {"/AddBirdNestToCartController"})
public class AddBirdNestToCartController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "RenderCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR; 
        try {
            String nest_id = request.getParameter("nest_id");
            BirdNestDAO bndao = new BirdNestDAO();
            BirdNest bn = bndao.getBirdsNestById(nest_id);
            HttpSession session = request.getSession();
            if (nest_id != null) {
                if (session != null) {
                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    if (cart == null) {
                        cart = new CartDTO();
                    }
                    boolean checkAdd = cart.addBirdNestToCart(bn);
                    if (checkAdd) {
                        out.println(1);
                        session.setAttribute("CART", cart);
                        url = SUCCESS;
                    } else {
                        out.println(0);
                    }
                    return;
                }
            } else {
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (cart != null) {
                    int amountItems = cart.getTotalItem();
                    out.println(amountItems);
                    return;
                }
            }

        } catch (Exception e) {
            log("Error at AddBirdNestToCartController: " + e.toString());
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
