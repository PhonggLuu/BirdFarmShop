/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import swp391.birdfarmshop.dao.FeedbackDAO;
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.dao.OrderItemDAO;
import swp391.birdfarmshop.dto.OrderItemDTO;
import swp391.birdfarmshop.model.Feedback;
import swp391.birdfarmshop.model.Order;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author phong pc
 */
public class RenderCustomerOrderController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "profile/order-customer.jsp";
    private static final String HOME = "MainController?action=NavToHome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            if (user != null && user.getRole().equals("customer")) {
                url = SUCCESS;
                OrderDAO orderDao = new OrderDAO();
                ArrayList<Order> orderList = orderDao.getOrderByCustomer(user.getUsername());
                OrderItemDAO orderItemDao = new OrderItemDAO();
                ArrayList<OrderItemDTO> orderItemList;
                LinkedHashMap<Order, ArrayList> orderItemMap = new LinkedHashMap<>();
                String status = request.getParameter("status");
                ArrayList<OrderItemDTO> freeItemList = new ArrayList<>();
                FeedbackDAO feedbackDao = new FeedbackDAO();
                ArrayList<Feedback> feedbacks = feedbackDao.getFeedbackByCustomer(user.getUsername());
                if (status != null) {
                    if (status.equals("wait")) {
                        for (Order order : orderList) {
                            int count = 0;
                            OrderItemDTO item = null;
                            if (!orderItemMap.containsKey(order) && order.getOrder_status().equals("Chờ xử lý")) {
                                orderItemList = orderItemDao.getItemByOrderId(order.getOrder_id());
                                for (OrderItemDTO orderItemDTO : orderItemList) {
                                    count++;
                                    if (orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                                        item = orderItemList.get(count - 1);
                                        freeItemList.add(item);
                                        orderItemList.remove(orderItemList.get(count - 1));
                                        break;
                                    }
                                }
                                orderItemMap.put(order, orderItemList);
                            }
                        }
                        request.setAttribute("status", "wait");
                    }
                    if (status.equals("inProgress")) {
                        for (Order order : orderList) {
                            int count = 0;
                            OrderItemDTO item = null;
                            if (!orderItemMap.containsKey(order) && order.getOrder_status().equals("Đang xử lý")) {
                                orderItemList = orderItemDao.getItemByOrderId(order.getOrder_id());
                                for (OrderItemDTO orderItemDTO : orderItemList) {
                                    count++;
                                    if (orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                                        item = orderItemList.get(count - 1);
                                        freeItemList.add(item);
                                        orderItemList.remove(orderItemList.get(count - 1));
                                        break;
                                    }
                                }
                                orderItemMap.put(order, orderItemList);
                            }
                        }
                        request.setAttribute("status", "inProgress");
                    }
                    if (status.equals("delivering")) {
                        for (Order order : orderList) {
                            int count = 0;
                            OrderItemDTO item = null;
                            if (!orderItemMap.containsKey(order) && order.getOrder_status().equals("Đang giao hàng")) {
                                orderItemList = orderItemDao.getItemByOrderId(order.getOrder_id());
                                for (OrderItemDTO orderItemDTO : orderItemList) {
                                    count++;
                                    if (orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                                        item = orderItemList.get(count - 1);
                                        freeItemList.add(item);
                                        orderItemList.remove(orderItemList.get(count - 1));
                                        break;
                                    }
                                }
                                orderItemMap.put(order, orderItemList);
                            }
                        }
                        request.setAttribute("status", "delivering");
                    }
                    if (status.equals("delivered")) {
                        for (Order order : orderList) {
                            int count = 0;
                            OrderItemDTO item = null;
                            if (!orderItemMap.containsKey(order) && order.getOrder_status().equals("Đã giao hàng")) {
                                orderItemList = orderItemDao.getItemByOrderId(order.getOrder_id());
                                for (OrderItemDTO orderItemDTO : orderItemList) {
                                    count++;
                                    if (orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                                        item = orderItemList.get(count - 1);
                                        freeItemList.add(item);
                                        orderItemList.remove(orderItemList.get(count - 1));
                                        break;
                                    }
                                }
                                orderItemMap.put(order, orderItemList);
                            }
                        }
                        request.setAttribute("status", "delivered");
                    }
                    if (status.equals("rated")) {
                        for (Order order : orderList) {
                            int count = 0;
                            OrderItemDTO item = null;
                            if (!orderItemMap.containsKey(order) && order.getOrder_status().equals("Đã đánh giá")) {
                                orderItemList = orderItemDao.getItemByOrderId(order.getOrder_id());
                                for (OrderItemDTO orderItemDTO : orderItemList) {
                                    count++;
                                    if (orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                                        item = orderItemList.get(count - 1);
                                        freeItemList.add(item);
                                        orderItemList.remove(orderItemList.get(count - 1));
                                        break;
                                    }
                                }
                                orderItemMap.put(order, orderItemList);
                            }
                        }
                        request.setAttribute("status", "rated");
                    }
                    if (status.equals("cancel")) {
                        for (Order order : orderList) {
                            int count = 0;
                            OrderItemDTO item = null;
                            if (!orderItemMap.containsKey(order) && order.getOrder_status().equals("Đã hủy")) {
                                orderItemList = orderItemDao.getItemByOrderId(order.getOrder_id());
                                for (OrderItemDTO orderItemDTO : orderItemList) {
                                    count++;
                                    if (orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                                        item = orderItemList.get(count - 1);
                                        freeItemList.add(item);
                                        orderItemList.remove(orderItemList.get(count - 1));
                                        break;
                                    }
                                }
                                orderItemMap.put(order, orderItemList);
                            }
                        }
                        request.setAttribute("status", "cancel");
                    }
                } else {
                    for (Order order : orderList) {
                        int count = 0;
                        OrderItemDTO item = null;
                        if (!orderItemMap.containsKey(order)) {
                            orderItemList = orderItemDao.getItemByOrderId(order.getOrder_id());
                            for (OrderItemDTO orderItemDTO : orderItemList) {
                                count++;
                                if (orderItemDTO.getUnit_price() == 0 && orderItemDTO.getAccessory() != null) {
                                    item = orderItemList.get(count - 1);
                                    freeItemList.add(item);
                                    System.out.println(item.getOrder_item_id());
                                    orderItemList.remove(orderItemList.get(count - 1));
                                    break;
                                }
                            }
                            orderItemMap.put(order, orderItemList);
                        }
                    }
                }
                request.setAttribute("FEEDBACKLIST", feedbacks);
                request.setAttribute("ITEMMAP", orderItemMap);
                request.setAttribute("FREEITEMLIST", freeItemList);
            } else {
                response.sendRedirect(HOME);
            }
        } catch (IOException | SQLException e) {
            log("Error at RenderShopOrdersController: " + e.toString());
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
