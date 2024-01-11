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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import swp391.birdfarmshop.dao.BirdBreedDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdBreed;

/**
 *
 * @author phong pc
 */
public class RenderBirdController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "shop/birds.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
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
            String search = request.getParameter("txtBirdName");
            String breedId = request.getParameter("txtBreedId");
            String price = request.getParameter("txtPrice");
            String gender = request.getParameter("txtGender");
            String age = request.getParameter("txtAge");
            String clear = request.getParameter("clear");
            if(clear != null){
                search = null;
                breedId= null;
                price = null;
                age = null;
                clear = null;
                gender = null;
                page = "1";
            }
            if (breedId != null && breedId.equals("All")) {
                breedId = null;
            }
            if (price != null && price.equals("All")) {
                price = null;
            }
            if (gender != null && gender.equals("All")) {
                gender = null;
            }
            if (age != null && age.equals("All")) {
                age = null;
            }
            BirdDAO birdDao = new BirdDAO();
            List<Bird> birds = birdDao.getBirdsCustom(search, breedId, price, gender, age, page, recordsPerPage);
            int noOfRecords = birdDao.totalBirds(search, breedId, price, gender, age);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("BREED_ID", breedId);
            request.setAttribute("PRICE", price);
            request.setAttribute("GENDER", gender);
            request.setAttribute("AGE", age);
            request.setAttribute("SEARCH", search);
            request.setAttribute("BREEDLIST", listBreeds);
            request.setAttribute("BIRDLIST", birds);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
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
     *
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RenderBirdController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RenderBirdController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
