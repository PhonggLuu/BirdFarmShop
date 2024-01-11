/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package swp391.birdfarmshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import swp391.birdfarmshop.dao.BirdCustomerDAO;
import swp391.birdfarmshop.model.BirdCustomer;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name="RenderBirdCustomerController", urlPatterns={"/RenderBirdCustomerController"})
public class RenderBirdCustomerController extends HttpServlet {
     private static final String DEST_NAV_PROFILE = "RenderProfileController";
    private static final String SUCCESS = "profile/bird-customer.jsp";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = DEST_NAV_PROFILE;
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");  
            ArrayList<BirdCustomer> birdCustomerList = new ArrayList<>();
            if(u != null){
                BirdCustomerDAO bsd = new BirdCustomerDAO();
                birdCustomerList = bsd.getBirdCustomerByCustomer(u.getUsername(), null, null, 0);
                url = SUCCESS;
            }else{
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
            }
        request.setAttribute("BIRDCUMTOMER", birdCustomerList);
        request.getRequestDispatcher(url).forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
