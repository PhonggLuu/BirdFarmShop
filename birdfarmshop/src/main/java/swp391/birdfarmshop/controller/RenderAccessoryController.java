/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import swp391.birdfarmshop.dao.AccessoryDAO;
//<<<<<<< .mine
//import swp391.birdfarmshop.dao.BirdDAO;
//import swp391.birdfarmshop.dao.ImageDAO;
//=======

//>>>>>>> .theirs
import swp391.birdfarmshop.model.Accessory;
//import swp391.birdfarmshop.model.User;
//import swp391.birdfarmshop.model.Bird;
//import swp391.birdfarmshop.model.AccessoryBreed;
//import swp391.birdfarmshop.model.Image;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RenderAccessoryController", urlPatterns = {"/RenderAccessoryController"})
public class RenderAccessoryController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "shop/accessories.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String page = "1";
            int numberOfRecord = 9;
            List<Accessory> accessoryList = new ArrayList<Accessory>();
            AccessoryDAO dao = new AccessoryDAO();
            if (request.getParameter("page") != null) {
                page = request.getParameter("page");
            }
            String search = request.getParameter("txtAccessory");
            String categoryID = request.getParameter("txtType");
            String price = request.getParameter("txtPrice");
            String clear = request.getParameter("clear");
            if(clear != null){
                search = null;
                categoryID = null;
                price = null;
            }
            if (price != null && price.equals("All")) {
                price = null;
            }
            if (categoryID != null && categoryID.equals("All")) {
                categoryID = null;
            }
            accessoryList = dao.getAccessoriesCustom(search, categoryID, price, page, numberOfRecord);
            int noOfRecords = dao.totalAccessories(search, categoryID, price);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / numberOfRecord);
            for(Accessory a : accessoryList){
                if(a.getStatus().equals("hết hàng")){
                    String message = "hết hàng";
                    request.setAttribute("message", message);
                }
            }
            request.setAttribute("accessoryList", accessoryList);
            request.setAttribute("SEARCH", search);
            request.setAttribute("PRICE", price);
            request.setAttribute("CATEGORY_ID", categoryID);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at RenderAccessoryController: " + e.toString());
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
