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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Order;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.services.EmailService;
import swp391.birdfarmshop.util.EmailUtils;
import swp391.birdfarmshop.util.VNPAYUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddOrderByOnlineController", urlPatterns = {"/AddOrderByOnlineController"})
public class AddOrderByOnlineController extends HttpServlet {

    private static final String DEST_NAV_HOME = "MainController?action=NavToHome";
    private static final String DEST_NAV_CHECKOUT = "RenderCheckOutController";

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
        String url = DEST_NAV_CHECKOUT;
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            ArrayList<String> infor = (ArrayList<String>) session.getAttribute("INFOORRDER");
            CartDTO cart = (CartDTO) session.getAttribute("CART"); 
            OrderDAO od = new OrderDAO();
            DateTimeFormatter formatterOrder = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = LocalDate.now().format(formatterOrder);
            Order o = od.getOrderLatest();
            // Generate a unique orderId
            String numberSTT = o.getOrder_id().substring(8);
            int numberLast = Integer.parseInt(numberSTT);
            numberLast += 1;
            String number = String.format("%04d", numberLast);
            String order_id = formattedDate.substring(2) + 'O' + number;
            if (order_id != null && cart != null) {
                int result = od.createNewOrder(order_id, u.getUsername(), "Đang xử lý", infor.get(0),
                        infor.get(1), infor.get(2), "Đã thanh toán", cart, null, "Chuyển khoản",infor.get(3),infor.get(5),infor.get(6));
                if (result != 0) {
                    EmailService.sendEmail(u.getEmail(), "Đơn đặt hàng của bạn",
                            EmailUtils.sendOrderToCustomer(u.getFullName(), cart, order_id, infor.get(0), infor.get(1), infor.get(2),"Đã thanh toán"));
                    cart = null;
                    url = DEST_NAV_HOME;
                    session.setAttribute("CART", null);
                    session.setAttribute("SUCCESS", "Đặt hàng thành công");
                } else {
                    VNPAYUtils.refundMoney(infor.get(3),cart.getCartTotalPrice()+"",infor.get(5),infor.get(6), request);
                    session.setAttribute("ERROR", od.error);
                }
            }else{
                session.setAttribute("ERROR", "Thanh toán thất bại");     
            }
            session.removeAttribute("INFOORRDER");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            response.sendRedirect(url);
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
