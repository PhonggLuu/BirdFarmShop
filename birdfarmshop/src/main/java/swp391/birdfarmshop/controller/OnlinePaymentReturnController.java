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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import swp391.birdfarmshop.util.VNPAYUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OnlinePaymentReturnController", urlPatterns = {"/OnlinePaymentReturnController"})
public class OnlinePaymentReturnController extends HttpServlet {

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
        String url = "RenderHomeController";
        try {
            HttpSession session = request.getSession();
            ArrayList<String> orderitem = (ArrayList<String>) session.getAttribute("LISTBIRDPAIR");
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            ArrayList<String> infor = (ArrayList<String>) session.getAttribute("INFOORRDER");
            if(fields.containsKey("vnp_TxnRef")){
                infor.add((String) fields.get("vnp_TxnRef"));
            }
            if(fields.containsKey("vnp_Amount")){
                infor.add((String) fields.get("vnp_Amount"));
            }
            if(fields.containsKey("vnp_PayDate")){
                infor.add((String) fields.get("vnp_PayDate"));
            }
            if(fields.containsKey("vnp_TransactionNo")){
                infor.add((String) fields.get("vnp_TransactionNo"));
            }
            session.setAttribute("INFOORRDER", infor);
            if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
                if (orderitem == null) {
                    url = "AddOrderByOnlineController";
                } else {
                    url = "AddNewOrderItemBirdPairController";
                }
            } else {
                if (orderitem == null) {
                    url = "RenderCheckOutController";
                } else {
                    url = "RenderCheckoutBirdPairController";
                }
                session.setAttribute("ERROR", "Thanh toán thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
