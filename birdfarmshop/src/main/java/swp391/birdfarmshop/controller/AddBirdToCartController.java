/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.dto.AddStatusDTO;
import swp391.birdfarmshop.dto.BirdDTO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.OrderedAccessoryItem;
import swp391.birdfarmshop.model.OrderedBirdItem;

/**
 *
 * @author tlminh
 */
@WebServlet(name = "AddBirdToCartController", urlPatterns = {"/AddBirdToCartController"})
public class AddBirdToCartController extends HttpServlet {

    private static final String DEST_NAV_CART = "RenderCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String bird_id = request.getParameter("bird_id");
            String cage_id = request.getParameter("cage_id");
            BirdDAO birdDao = new BirdDAO();
            AccessoryDAO adao = new AccessoryDAO();
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            AddStatusDTO status = new AddStatusDTO();
            Gson gson = new Gson();
            status.setStatus("Thất bại");
            status.setType("error");
            if (cart == null) {
                cart = new CartDTO();
            }
            if (bird_id != null && cage_id != null) {

                List<Accessory> cageList = adao.getCageList();
                Accessory cheapestCage = cageList.get(0);
                int cheapestCagePrice = cheapestCage.getUnit_price() - cheapestCage.getUnit_price() * cheapestCage.getDiscount() / 100;
                for (Accessory cage : cageList) {
                    if ((cage.getUnit_price() - cage.getUnit_price() * cage.getDiscount() / 100) < cheapestCagePrice) {
                        cheapestCage = cage;
                    }
                }
                Accessory a = adao.getAccessoryByID(cage_id);
                a.setDiscount(30);
                if (cheapestCage.getAccessory_id().equals(cage_id)) {
                    cheapestCage.setUnit_price(0);
                    cheapestCage.setDiscount(0);
                    a = cheapestCage;
                }
                HashMap<String, OrderedBirdItem> birdList = (HashMap<String, OrderedBirdItem>) cart.getBirdList();
                for (OrderedBirdItem ob : birdList.values()) {
                    if (ob.getBird().getBird_id().equals(bird_id)) {
                        Accessory old = ob.getCage();
                        cart.setCartTotalPrice(cart.getCartTotalPrice() - (old.getUnit_price() - (old.getUnit_price() * old.getDiscount() / 100)));
                    }
                    if (ob.getBird().getBird_id().equals(bird_id)) {
                        ob.setCage(a);
                        break;
                    }
                }
                cart.setBirdList(birdList);
                cart.setCartTotalPrice(cart.getCartTotalPrice() + (a.getUnit_price() - (a.getUnit_price() * a.getDiscount() / 100)));
                session.setAttribute("CART", cart);
                session.setAttribute("SUCCESS", "Đổi lồng thành công");
                response.sendRedirect(DEST_NAV_CART);
            }

            if (bird_id != null && cage_id == null) {
                Bird bird = birdDao.getBirdById(bird_id);
                List<Accessory> cageList = adao.getCageList();
                Accessory cheapestCage = cageList.get(0);
                //lấy ra số lượng trong kho
                int stockQuantity = cheapestCage.getStock_quantity();
                //lấy ra số lượng trong cart 
                OrderedAccessoryItem accessoryInCart = null;
                int orderQuantity = 0, freeQuantity = 0;
                if(cart.getAccessoryList().get(cheapestCage.getAccessory_id()) != null) {
                    accessoryInCart = cart.getAccessoryList().get(cheapestCage.getAccessory_id());
                    orderQuantity = cart.getAccessoryList().get(cheapestCage.getAccessory_id()).getOrder_quantity();
                    freeQuantity = cart.getAccessoryList().get(cheapestCage.getAccessory_id()).getFree_order();
                }
                if (bird.getStatus().equals("Còn hàng")) {
                    if(orderQuantity + freeQuantity + 1 <= stockQuantity) {
                        boolean checkAdd = cart.addBirdToCart(bird, cheapestCage);
                        if (checkAdd) {
                            cart.addAccessoryToCart(cheapestCage, 1, 1);
                            status.setStatus("Thành công");
                            status.setContent("Thêm sản phẩm vào giỏ hàng thành công");
                            status.setType("success");
                            status.setQuantity(cart.getTotalItem());
                            session.setAttribute("CART", cart);
                        } else {
                            status.setQuantity(cart.getTotalItem());
                            status.setContent("Sản phẩm này đã có trong giỏ hàng");
                        }
                    } else {
                        boolean checkAdd = cart.addBirdToCart(bird, null);
                        if(orderQuantity > 1) {
                            accessoryInCart.setOrder_quantity(orderQuantity - 1);
                            accessoryInCart.setFree_order(freeQuantity + 1);
                        }
                        if (checkAdd) {
                            status.setStatus("Thành công");
                            if(orderQuantity > 1) {
                                status.setContent("Vì số lượng sản phẩm không đủ. Cửa hàng sẽ tặng bạn 1 chiếc lồng trong giỏ hàng của bạn.");
                            }else {
                                status.setContent("Thêm sản phẩm vào giỏ hàng thành công");
                            }
                            status.setType("success");
                            status.setQuantity(cart.getTotalItem());
                            session.setAttribute("CART", cart);
                        } else {
                            status.setQuantity(cart.getTotalItem());
                            status.setContent("Sản phẩm này đã có trong giỏ hàng");
                        }
                    }
                } else {
                    status.setQuantity(cart.getTotalItem());
                    status.setContent("Sản phẩm này đã bán");
                }
                out.println(gson.toJson(status));
            }
        } catch (Exception e) {
            log("Error at AddBirdToCartController: " + e.toString());
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
