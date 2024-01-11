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
import java.time.DayOfWeek;
import java.time.LocalDate;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.dao.BirdPairDAO;
import swp391.birdfarmshop.dao.OrderDAO;
import swp391.birdfarmshop.dao.UserDAO;
import swp391.birdfarmshop.dto.ReportDTO;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author tlminh
 */
@WebServlet(name = "RenderReportsController", urlPatterns = {"/RenderReportsController"})
public class RenderReportsController extends HttpServlet {

    private static final String ERROR = "RenderHomeController";
    private static final String SUCCESS = "management/reports.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SUCCESS;
        String filter = request.getParameter("filter");
        if (filter == null) {
            filter = "All";
        }
        try {
            HttpSession session = request.getSession();
            OrderDAO order = new OrderDAO();
            UserDAO user = new UserDAO();
            BirdDAO bird = new BirdDAO();
            AccessoryDAO accessory = new AccessoryDAO();
            BirdPairDAO birdPair = new BirdPairDAO();
            ReportDTO r = new ReportDTO();
            User u = (User) session.getAttribute("LOGIN_USER");
            LocalDate dateNow = LocalDate.now();
            Gson gson = new Gson();
            if (u != null) {
                if (u.getRole().equals("admin") || u.getRole().equals("manager")) {
                    switch (filter) {
                        case "week":
                            LocalDate startDate = LocalDate.of(dateNow.getYear(), dateNow.getMonth(), dateNow.getDayOfMonth());
                            LocalDate startDateMonday = startDate;
                            while (startDateMonday.getDayOfWeek() != DayOfWeek.MONDAY) {
                                startDateMonday = startDateMonday.minusDays(1);
                            }
                            r.setRevenue(order.getRevenue(startDateMonday, null));
                            r.getRevenue().add(user.numberCustomer(startDateMonday, null));
                            r.setProduct_sale(order.getProductSale(startDateMonday, null));
                            String typeMoney = request.getParameter("type_money");
                            r.setTotalMoney(order.getMoneyByWeek(startDateMonday, typeMoney));
                            r.setTotalMoneyBird(order.getMoneyByWeek(startDateMonday, typeMoney, true, false, false));
                            r.setTotalMoneyAccessory(order.getMoneyByWeek(startDateMonday, typeMoney, false, true, false));
                            r.setTotalMoneyBirdPair(order.getMoneyByWeek(startDateMonday, typeMoney, false, false, true));
                            String json = gson.toJson(r);
                            out.println(json);
                            break;
                        case "month":
                            dateNow = LocalDate.now();
                            startDate = LocalDate.of(dateNow.getYear(), dateNow.getMonth(), 1);
                            r.setRevenue(order.getRevenue(startDate, null));
                            r.getRevenue().add(user.numberCustomer(startDate, null));
                            r.setProduct_sale(order.getProductSale(startDate, null));
                            typeMoney = request.getParameter("type_money");
                            r.setTotalMoney(order.getMoneyByMonth(startDate, typeMoney));
                            r.setTotalMoneyBird(order.getMoneyByMonth(startDate, typeMoney, true, false, false));
                            r.setTotalMoneyAccessory(order.getMoneyByMonth(startDate, typeMoney, false, true, false));
                            r.setTotalMoneyBirdPair(order.getMoneyByMonth(startDate, typeMoney, false, false, true));
                            json = gson.toJson(r);
                            out.println(json);
                            break;
                        case "year":
                            dateNow = LocalDate.now();
                            startDate = LocalDate.of(dateNow.getYear(), 1, 1);
                            r.setRevenue(order.getRevenue(startDate, null));
                            r.getRevenue().add(user.numberCustomer(startDate, null));
                            r.setProduct_sale(order.getProductSale(startDate, null));
                            typeMoney = request.getParameter("type_money");
                            r.setTotalMoney(order.getMoneyByYear(startDate, typeMoney));
                            r.setTotalMoneyBird(order.getMoneyByYear(startDate, typeMoney, true, false, false));
                            r.setTotalMoneyAccessory(order.getMoneyByYear(startDate, typeMoney, false, true, false));
                            r.setTotalMoneyBirdPair(order.getMoneyByYear(startDate, typeMoney, false, false, true));
                            json = gson.toJson(r);
                            out.println(json);
                            break;
                        case "from_date":
                            String from_date = request.getParameter("data");
                            LocalDate fromDate = LocalDate.parse(from_date);
                            startDate = LocalDate.of(fromDate.getYear(), fromDate.getMonth(), fromDate.getDayOfMonth());
                            r.setRevenue(order.getRevenue(startDate, null));
                            r.getRevenue().add(user.numberCustomer(startDate, null));
                            r.setProduct_sale(order.getProductSale(startDate, null));
                            json = gson.toJson(r);
                            out.println(json);
                            break;
                        case "to_date":
                            String to_date = request.getParameter("data");
                            LocalDate toDate = LocalDate.parse(to_date);
                            LocalDate endDate = LocalDate.of(toDate.getYear(), toDate.getMonth(), toDate.getDayOfMonth());
                            r.setRevenue(order.getRevenue(null, endDate));
                            r.getRevenue().add(user.numberCustomer(null, endDate));
                            r.setProduct_sale(order.getProductSale(null, endDate));
                            json = gson.toJson(r);
                            out.println(json);
                            break;
                        case "both":
                            to_date = request.getParameter("to_date");
                            toDate = LocalDate.parse(to_date);
                            endDate = LocalDate.of(toDate.getYear(), toDate.getMonth(), toDate.getDayOfMonth());
                            from_date = request.getParameter("from_date");
                            fromDate = LocalDate.parse(from_date);
                            startDate = LocalDate.of(fromDate.getYear(), fromDate.getMonth(), fromDate.getDayOfMonth());
                            r.setRevenue(order.getRevenue(startDate, endDate));
                            r.getRevenue().add(user.numberCustomer(startDate, endDate));
                            r.setProduct_sale(order.getProductSale(startDate, endDate));
                            json = gson.toJson(r);
                            out.println(json);
                            break;
                        default:
                            r.setRevenue(order.getRevenue(null, null));
                            r.getRevenue().add(user.numberCustomer(null, null));
                            r.setProduct_sale(order.getProductSale(null, null));
                            r.setBird(bird.getBirdAmount());
                            r.setAccessory(accessory.getQuantityByStatus());
                            r.setBirdPair(birdPair.getBirdPairByStatus());
                            typeMoney = request.getParameter("type_money");
                            r.setTotalMoney(order.getMoneyByAll(typeMoney));
                            r.setTotalMoneyBird(order.getMoneyByAll(typeMoney, true, false, false));
                            r.setTotalMoneyAccessory(order.getMoneyByAll(typeMoney, false, true, false));
                            r.setTotalMoneyBirdPair(order.getMoneyByAll(typeMoney, false, false, true));
                            json = gson.toJson(r);
                            out.println(json);
                            request.setAttribute("REPORT", r);
                    }
                } else {
                    url = ERROR;
                    session.setAttribute("ERROR", "Bạn không có quyền truy cập");
                }
            } else {
                url = ERROR;
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
            }
        } catch (Exception e) {
            log("Error at RenderReportsController: " + e.toString());
        } finally {
            if (filter.equals("All")) {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
