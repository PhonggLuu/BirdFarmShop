/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import swp391.birdfarmshop.dao.BirdPairDAO;
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.dao.TrackingBirdPairDAO;
import swp391.birdfarmshop.dao.UserDAO;
import swp391.birdfarmshop.dto.BirdPairDTO;
import swp391.birdfarmshop.model.Order;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.services.EmailService;
import swp391.birdfarmshop.util.Constants;
import swp391.birdfarmshop.util.EmailUtils;
import swp391.birdfarmshop.util.ImageUtils;
import swp391.birdfarmshop.util.S3Utils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateStatusTrackingController", urlPatterns = {"/UpdateStatusTrackingController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,//1mb
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
public class UpdateStatusTrackingController extends HttpServlet {

    private static final String DEST_NAV_HOME = "RenderHomeController";

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
        String url = DEST_NAV_HOME;
        try {
            HttpSession session = request.getSession();
            String pair_id = request.getParameter("pair_id");
            int pairId = 0;
            if (pair_id != null) {
                pairId = Integer.parseInt(pair_id);
            }
            String content = request.getParameter("content");
            String quantity_egg = request.getParameter("quantity_egg");
            String quantity_young_bird = request.getParameter("quantity_young_bird");
            Part part = request.getPart("file");
            url = "MainController?action=NavToBirdPairDetailShop&pair_id=" + pair_id;
            User u = (User) session.getAttribute("LOGIN_USER");
            TrackingBirdPairDAO trackingDao = new TrackingBirdPairDAO();
            BirdPairDAO bpDao = new BirdPairDAO();
            boolean check = true;
            if (u != null) {
                if (!u.getRole().equals("customer")) {
                    BirdPairDTO pair = bpDao.getBirdPairByBirdPairId(pair_id);
                    String status = pair.getStatus();
                    switch (status) {
                        case "Chờ lấy chim":
                            status = "Đang ghép cặp";
                            break;
                        case "Đang ghép cặp":
                            status = "Đã sinh sản";
                            break;
                        case "Đã sinh sản":
                            quantity_egg = "" + pair.getNumber_egg();
                            status = "Đã ấp nở";
                            if (quantity_young_bird != null && Integer.parseInt(quantity_young_bird) > pair.getNumber_egg()) {
                                session.setAttribute("ERROR", "Số chim non không lớn hơn số trứng");
                                check = false;
                            }
                            UserDAO userDAO = new UserDAO();
                            User customer = userDAO.getUserByUsername(pair.getUsername());
                            EmailService.sendEmail(customer.getEmail(), "Xác nhận đơn hàng ghép cặp", EmailUtils.confirmBirdPair(customer.getFullName(), pair.getOrder_id(), pair.getPair_id()));
                            break;

                        case "Đã ấp nở":
                            OrderDAO orderDao = new OrderDAO();
                            String txtOrderId = request.getParameter("order_id");
                            Order thisOrder = orderDao.getOrderById(txtOrderId);
                            java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now().minusDays(15));
                            if (sqlDate.after(thisOrder.getOrder_date())) {
                                boolean isUpdated = orderDao.updateOrderStatus(txtOrderId, "Khách hàng không xác nhận đơn sau 15 ngày", "Đã hủy", request);
                                if (isUpdated) {
                                    session.setAttribute("SUCCESS", "Bạn đã hủy đơn hàng " + txtOrderId + " thành công");
                                } else {
                                    session.setAttribute("ERROR", "Bạn hủy đơn hàng thất bại. Liên hệ nhân viên để biết thêm thông tin.");
                                }
                            } else {
                                session.setAttribute("ERROR", "Không thể hủy do đơn hàng chưa đủ 15 ngày");
                            }
                            request.getRequestDispatcher(url).forward(request, response);
                    }
                    if (check) {
                        if (part.getSize() < 1048576) {
                            String file = ImageUtils.getFileName(part);
                            LocalTime currentTime = LocalTime.now();
                            String nameImage = currentTime.getNano() + file;
                            String img_url = Constants.C3_HOST + nameImage;
                            if (file.isEmpty()) {
                                img_url = null;
                            }
                            S3Utils.uploadFile(nameImage, part.getInputStream());
                            int result = trackingDao.updateTrackingBirdPair(img_url, pairId, content, u.getUsername(), status, quantity_young_bird, quantity_egg);
                            if (result == 0) {
                                session.setAttribute("ERROR", "Cập nhật trạng thái thất bại");
                                request.getRequestDispatcher(url).forward(request, response);
                            } else {
                                session.setAttribute("SUCCESS", "Cập nhật trạng thái thành công");
                                response.sendRedirect(url);
                            }
                        } else {
                            session.setAttribute("ERROR", "Ảnh có dung lượng quá 1mb");
                            request.getRequestDispatcher(url).forward(request, response);
                        }
                    } else {
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                } else {
                    session.setAttribute("ERROR", "Bạn không có quyền này");
                    request.getRequestDispatcher(url).forward(request, response);
                }

            } else {
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
                request.getRequestDispatcher(url).forward(request, response);
            }
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
