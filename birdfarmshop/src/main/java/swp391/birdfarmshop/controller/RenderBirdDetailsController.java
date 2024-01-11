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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.dao.FeedbackDAO;
import swp391.birdfarmshop.dao.ImageDAO;
import swp391.birdfarmshop.dto.BirdDTO;
import swp391.birdfarmshop.dto.FeedbackDTO;
import swp391.birdfarmshop.dto.StarDTO;

/**
 *
 * @author phong pc
 */
public class RenderBirdDetailsController extends HttpServlet {
   
    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "shop/bird-details.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String page = "1";
            int numberOfRecords = 5;
            if (request.getParameter("page") != null) {
                page = request.getParameter("page");
            }

            String bird_id = request.getParameter("bird_id");
            BirdDAO birdDao = new BirdDAO();
            FeedbackDAO f = new FeedbackDAO();
            ArrayList<FeedbackDTO> feedbackList = f.getFeedbackByIdProduct(bird_id, page, numberOfRecords);
            int numberOfFeebacks = f.totalFeedbackByIdProduct(bird_id);
            int numberOfPage = (int) Math.ceil(numberOfFeebacks * 1.0 / numberOfRecords);
            ImageDAO image = new ImageDAO();
            String im = image.getThumbnailUrlByBirdId(bird_id);

            StarDTO starCustomer = f.getRatingByIdProduct(bird_id);
            BirdDTO birdDetails = birdDao.getBirdDetailsById(bird_id);
            request.setAttribute("noOfPages", numberOfPage);
            request.setAttribute("currentPage", page);
            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("starCustomer", starCustomer);
            request.setAttribute("birdDetails", birdDetails);
            request.setAttribute("im", im);
            url = SUCCESS;
        } catch (SQLException e) {
            log("Error at RenderHomeController: " + e.toString());
        } finally {
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RenderBirdDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RenderBirdDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
