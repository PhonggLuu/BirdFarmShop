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
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.model.Order;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.services.EmailService;
import swp391.birdfarmshop.util.EmailUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddOrderController", urlPatterns = {"/AddOrderController"})
public class AddOrderController extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            String url = DEST_NAV_CHECKOUT;
            if (u != null) {
                if (cart != null) {
                    if (cart.getBirdPairList().size() == 0) {
                        String name_receiver = request.getParameter("name");
                        String phone_receiver = request.getParameter("mobile");
                        String address_receiver = request.getParameter("address");
                        LocalDate currentDate = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                        String formattedDate = currentDate.format(formatter);
                        OrderDAO od = new OrderDAO();
                        Order o = od.getOrderLatest();
                        if (o != null) {
                            String numberSTT = o.getOrder_id().substring(8);
                            int numberLast = Integer.parseInt(numberSTT);
                            numberLast += 1;
                            String number = String.format("%04d", numberLast);
                            String order_id = formattedDate.substring(2) + 'O' + number;
                            int result = od.createNewOrder(order_id, u.getUsername(), "Chờ xử lý", name_receiver,
                                    phone_receiver, address_receiver, "Chưa thanh toán", cart, null, "Tiền mặt",null,null,null);
                            if (result != 0) {
                                EmailService.sendEmail(u.getEmail(), "Đơn đặt hàng của bạn",
                                        EmailUtils.sendOrderToCustomer(u.getFullName(), cart, order_id, name_receiver, phone_receiver, address_receiver, "Thanh toán khi nhận hàng"));
                                cart = null;
                                url = DEST_NAV_HOME;
                                session.setAttribute("CART", null);
                                session.setAttribute("SUCCESS", "Đặt hàng thành công");
                            } else {
                                session.setAttribute("ERROR", od.error);
                            }

                        }
                    }else{
                       session.setAttribute("ERROR", "Vui lòng thanh toán bằng VNPAY");
                       url = DEST_NAV_CHECKOUT;
                    }
                } else {
                    session.setAttribute("ERROR", "Không có sản phẩm nào trong giỏ hàng của bạn");
                    url = DEST_NAV_HOME;
                }
            } else {
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
                url = DEST_NAV_HOME;
            }
            request.getRequestDispatcher(url).forward(request, response);
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
