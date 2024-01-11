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
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.dao.BirdPairDAO;
import swp391.birdfarmshop.dto.BirdPairDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.Bird;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RenderCheckoutBirdPairController", urlPatterns = {"/RenderCheckoutBirdPairController"})
public class RenderCheckoutBirdPairController extends HttpServlet {

    private static final String DEST_NAV_CHECKOUT_BIRD_PAIR = "profile/checkout-bird-pair.jsp";

    /*
             * Processes requests for both HTTP <code>GET</code> and
             * <code>POST</code> methods.
             *
             * @param request servlet request
             * @param response servlet response
             * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = DEST_NAV_CHECKOUT_BIRD_PAIR;
        try {
            String male_bird_id = request.getParameter("male_bird");
            String female_bird_id = request.getParameter("female_bird");
            String order_id = request.getParameter("order_id");
            
            HttpSession session = request.getSession();
            ArrayList<String> orderItem = (ArrayList<String>) session.getAttribute("LISTBIRDPAIR");
            if(orderItem == null){
                orderItem = new ArrayList<>();
                orderItem.add(order_id);
                orderItem.add(male_bird_id);
                orderItem.add(female_bird_id);
            }else if(male_bird_id != null){
                orderItem = new ArrayList<>();
                orderItem.add(order_id);
                orderItem.add(male_bird_id);
                orderItem.add(female_bird_id);
            }else if (female_bird_id != null){
                orderItem = new ArrayList<>();
                orderItem.add(order_id);
                orderItem.add(male_bird_id);
                orderItem.add(female_bird_id);
            }else if(female_bird_id == null&& male_bird_id == null&& order_id != null){
                 orderItem = new ArrayList<>();
                 orderItem.add(order_id);
                 orderItem.add(male_bird_id);
                 orderItem.add(female_bird_id);
            }
            if(orderItem != null){
                order_id = orderItem.get(0);
                male_bird_id = orderItem.get(1);
                female_bird_id = orderItem.get(2);
            }
            BirdDAO bDao = new BirdDAO();
            AccessoryDAO adao = new AccessoryDAO();
            BirdPairDAO bpDao = new BirdPairDAO();
            BirdPairDTO bp = bpDao.getBirdPairByOrderId(order_id);
            List<Accessory> cageList = adao.getCageList();
            Accessory cheapestCage = cageList.get(0);
            int cheapestCagePrice = cheapestCage.getUnit_price() - cheapestCage.getUnit_price() * cheapestCage.getDiscount() / 100;
            for (Accessory cage : cageList) {
                if ((cage.getUnit_price() - cage.getUnit_price() * cage.getDiscount() / 100) < cheapestCagePrice) {
                    cheapestCage = cage;
                }
            }
            cheapestCage.setUnit_price(0);
            cheapestCage.setDiscount(0);
            Bird male_bird = bDao.getBirdById(male_bird_id);
            Bird female_bird = bDao.getBirdById(female_bird_id);
            int totalPrice = bp.getYoung_bird_price() * bp.getNumber_young_bird();
            if(male_bird != null && male_bird.getDiscount() > 0){
                totalPrice += male_bird.getPrice() - male_bird.getPrice() * male_bird.getDiscount() /100;
            }else if (male_bird != null){
                totalPrice += male_bird.getPrice();
            }
            if(female_bird != null && female_bird.getDiscount() > 0){
                totalPrice += female_bird.getPrice() - female_bird.getPrice() * female_bird.getDiscount() /100;
            }else if (female_bird != null){
                totalPrice += female_bird.getPrice();
            }
            orderItem.add(totalPrice+"");
            orderItem.add(bp.getPair_id()+"");
            if(male_bird_id != null || female_bird_id != null){
                orderItem.add(cheapestCage.getAccessory_id());
                request.setAttribute("CHEAPESTCAGE", cheapestCage);
            }
            session.setAttribute("LISTBIRDPAIR", orderItem);
            request.setAttribute("BIRDPAIR", bp);
            request.setAttribute("MALEBIRD", male_bird);
            request.setAttribute("FEMALEBIRD", female_bird);
            request.setAttribute("ORDERID", order_id);
        } catch (Exception e) {
            e.printStackTrace();
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
