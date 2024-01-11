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
import swp391.birdfarmshop.dao.BirdCustomerDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.dto.AddStatusDTO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdCustomer;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddBirdPairToCartController", urlPatterns = {"/AddBirdPairToCartController"})
public class AddBirdPairToCartController extends HttpServlet {

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
            String bird_customer_id = request.getParameter("bird_customer_id");
            String bird_shop_id = request.getParameter("bird_shop_id");
            String bird_male_id = request.getParameter("bird_male_id");
            String bird_female_id = request.getParameter("bird_female_id");
            HttpSession session = request.getSession();
            BirdDAO birdDao = new BirdDAO();
            BirdCustomerDAO bsd = new BirdCustomerDAO();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartDTO();
            }

            AddStatusDTO status = new AddStatusDTO();
            Gson gson = new Gson();
            status.setStatus("Thất bại");
            status.setContent("Thêm sản phẩm vào giỏ hàng thất bại");
            status.setType("error");
            if (bird_customer_id != null && bird_shop_id != null) {
                Bird bird = birdDao.getBirdById(bird_shop_id);
                BirdCustomer birdCustomer = bsd.findBirdCustomer(bird_customer_id);
                boolean checkAdd = cart.addBirdPairCustomerToCart(bird, birdCustomer);
                if (checkAdd) {
                    status.setStatus("Thành công");
                    status.setContent("Thêm sản phẩm vào giỏ hàng thành công");
                    status.setType("success");
                    status.setQuantity(cart.getTotalItem());
                    session.setAttribute("CART", cart);
                } else {
                    status.setQuantity(cart.getTotalItem());
                    status.setContent("Sản phẩm này đã có trong giỏ hàng");
                }
            }
            if (bird_male_id != null && bird_female_id != null) {
                Bird birdMale = birdDao.getBirdById(bird_male_id);
                Bird birdFemale = birdDao.getBirdById(bird_female_id);
                boolean checkAdd = cart.addBirdPairShopToCart(birdMale, birdFemale);
                if (checkAdd) {
                    status.setStatus("Thành công");
                    status.setContent("Thêm sản phẩm vào giỏ hàng thành công");
                    status.setType("success");
                    status.setQuantity(cart.getTotalItem());
                    session.setAttribute("CART", cart);
                } else {
                    status.setQuantity(cart.getTotalItem());
                    status.setContent("Sản phẩm này đã có trong giỏ hàng");
                }
            }
            out.println(gson.toJson(status));

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
