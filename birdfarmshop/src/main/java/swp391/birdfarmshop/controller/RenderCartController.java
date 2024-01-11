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
import java.util.ArrayList;
import java.util.List;
import swp391.birdfarmshop.dao.AccessoryCategoryDAO;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dao.BirdBreedDAO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.AccessoryCategory;
import swp391.birdfarmshop.model.BirdBreed;

/**
 *
 * @author tlminh
 */
@WebServlet(name = "RenderCartController", urlPatterns = {"/RenderCartController"})
public class RenderCartController extends HttpServlet {

    private static final String DEST_NAV_HOME = "RenderHomeController";
    private static final String SUCCESS = "shop/cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = DEST_NAV_HOME;
        try {
            HttpSession session = request.getSession();
            CartDTO c = (CartDTO) session.getAttribute("CART");
            if (c != null && c.getTotalItem() > 0) {
                List<BirdBreed> breeds = new ArrayList<>();
                BirdBreedDAO breedDao = new BirdBreedDAO();
                breeds = breedDao.getBirdBreeds();
                request.setAttribute("BREED_LIST", breeds);
                List<AccessoryCategory> categories = new ArrayList<>();
                AccessoryCategoryDAO categoryDao = new AccessoryCategoryDAO();
                List<Accessory> accessories = new ArrayList<>();
                AccessoryDAO accessoryDao = new AccessoryDAO();
                accessories = accessoryDao.getAccessories();
                categories = categoryDao.getAccessoryCategories();
                request.setAttribute("ACCESSORY_LIST", accessories);
                request.setAttribute("CATEGORY_LIST", categories);
                url = SUCCESS;
            } else {
                session.setAttribute("ERROR", "Giỏ hàng của bạn không có sản phẩm");
            }

        } catch (Exception e) {
            log("Error at RenderCartController: " + e.toString());
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
