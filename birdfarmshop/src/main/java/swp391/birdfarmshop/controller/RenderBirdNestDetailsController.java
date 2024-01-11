/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import swp391.birdfarmshop.dao.BirdNestDAO;
import swp391.birdfarmshop.dao.ImageDAO;
import swp391.birdfarmshop.model.BirdNest;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RenderBirdNestDetailsController", urlPatterns = {"/RenderBirdNestDetailsController"})
public class RenderBirdNestDetailsController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "shop/birdnest-details.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url = ERROR;
        try {
            String nest_id = request.getParameter("id");
            BirdNestDAO birdnest = new BirdNestDAO();
            BirdNest nest = birdnest.getBirdsNestById(nest_id);
            ImageDAO i = new ImageDAO();
            String thumbnail = i.getThumbnailUrlByBirdNestId(nest_id);
            ArrayList<String> img_url = i.getImagesByBirdNestId(nest_id);
            if (nest == null) {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR", "không tìm thấy tổ chim");
            } else {
                request.setAttribute("IMG", thumbnail);
                request.setAttribute("IMG_URL", img_url);
                request.setAttribute("NEST", nest);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at RenderBirdNestDetailsController: " + e.toString());
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
