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
import java.io.IOException;
import java.util.List;
import swp391.birdfarmshop.dao.BirdBreedDAO;
import swp391.birdfarmshop.dao.BirdNestDAO;
import swp391.birdfarmshop.model.BirdBreed;
import swp391.birdfarmshop.model.BirdNest;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RenderBirdNestController", urlPatterns = {"/RenderBirdNestController"})
public class RenderBirdNestController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "shop/birdnests.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String page = "1";
            int recordsPerPage = 9;
            BirdBreedDAO breed = new BirdBreedDAO();
            List<BirdBreed> listBreeds = breed.getBirdBreeds();

            if (request.getParameter("page") != null) {
                page = request.getParameter("page");
            }
            String search = request.getParameter("txtBirdNest");
            String breedId = request.getParameter("txtBreedId");
            String price = request.getParameter("txtPrice");
            if (breedId != null && breedId.equals("All")) {
                breedId = null;
            }
            if (price != null && price.equals("All")) {
                price = null;
            }
            BirdNestDAO nestDao = new BirdNestDAO();
            List<BirdNest> nestList = nestDao.getBirdsNest(search, breedId, price, page, recordsPerPage);
            int noOfRecords = nestDao.totalBirdsNest(search, breedId, price, page, recordsPerPage);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("BREED_ID", breedId);
            request.setAttribute("PRICE", price);
            request.setAttribute("SEARCH", search);
            request.setAttribute("BREEDLIST", listBreeds);
            request.setAttribute("BIRD_NEST_LIST", nestList);
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
