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
import swp391.birdfarmshop.dao.BirdPairDAO;
import swp391.birdfarmshop.dto.BirdPairDTO;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RenderBirdPairShopController", urlPatterns = {"/RenderBirdPairShopController"})
public class RenderBirdPairShopController extends HttpServlet {

    private static final String DEST_NAV_BIRD_PAIR_SHOP = "management/view-birdPair.jsp";

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
        String url = DEST_NAV_BIRD_PAIR_SHOP;
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            if (u != null) {
                if (!u.getRole().equals("customer")) {
                    BirdPairDAO pbDao = new BirdPairDAO();
                    int recordsPerPage = 10;
                    String page = request.getParameter("page");
                    if (page == null) {
                        page = "1";
                    }
                    String search = request.getParameter("search");
                    ArrayList<BirdPairDTO> pList = pbDao.getBirdPair(search, page, recordsPerPage);
                    int noOfRecords = pbDao.totalBirdPair(search);
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                    request.setAttribute("BIRDPAIRLIST", pList);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", page);
                } else {
                    session.setAttribute("ERROR", "Bạn không có quyền truy cập");
                }
            } else {
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
