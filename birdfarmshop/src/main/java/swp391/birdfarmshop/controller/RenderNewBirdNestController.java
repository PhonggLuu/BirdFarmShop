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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import swp391.birdfarmshop.dao.BirdBreedDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.dao.BirdNestDAO;
import swp391.birdfarmshop.dto.BirdDTO;
import swp391.birdfarmshop.model.BirdBreed;
import swp391.birdfarmshop.model.BirdNest;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RenderNewBirdNestController", urlPatterns = {"/RenderNewBirdNestController"})
public class RenderNewBirdNestController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "management/add-birdnest.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            BirdBreedDAO breed = new BirdBreedDAO();
            List<BirdBreed> listBreeds = breed.getBirdBreeds();
            BirdDAO birddao = new BirdDAO();
            ArrayList<BirdDTO> listDadBird = birddao.getBirdsByGender(true);
            ArrayList<BirdDTO> listMomBird = birddao.getBirdsByGender(false);
            request.setAttribute("BREEDLIST", listBreeds);
            request.setAttribute("BIRDDADLIST", listDadBird);
            request.setAttribute("BIRDMOMLIST", listMomBird);

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
