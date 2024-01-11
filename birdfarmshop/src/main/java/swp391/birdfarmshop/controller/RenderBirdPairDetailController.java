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
import swp391.birdfarmshop.dao.BirdPairDAO;
import swp391.birdfarmshop.dao.TrackingBirdPairDAO;
import swp391.birdfarmshop.dto.BirdPairDTO;
import swp391.birdfarmshop.dto.TrackingDTO;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name="RenderBirdPairDetailController", urlPatterns={"/RenderBirdPairDetailController"})
public class RenderBirdPairDetailController extends HttpServlet {
     private static final String ERROR = "RenderHomeController";
     private static final String SUCCESS = "profile/bird-pair-detail.jsp";
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
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            String order_id = request.getParameter("order_id");
            if(u != null && u.getRole().equals("customer")){
               BirdPairDAO bpd = new BirdPairDAO();
               TrackingBirdPairDAO trackingDao =  new TrackingBirdPairDAO();
               BirdPairDTO birdPair = bpd.getBirdPairByOrderId(order_id);
               ArrayList<TrackingDTO> trackingList = trackingDao.getTrackingBirdPair(birdPair.getPair_id());
               trackingList.size();
               request.setAttribute("BIRDPAIR", birdPair);
               request.setAttribute("TRACKINGLIST", trackingList);
               request.setAttribute("SIZE", trackingList.size());
            }else{
                url=ERROR;
                session.setAttribute("ERROR", "Bạn chưa đăng nhập"); 
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
