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
import javax.mail.Session;
import swp391.birdfarmshop.dao.BirdNestDAO;
import swp391.birdfarmshop.dao.ImageDAO;
import swp391.birdfarmshop.dto.BirdNestDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddNewBirdNestController", urlPatterns = {"/AddNewBirdNestController"})
public class AddNewBirdNestController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "MainController?action=NavToNewBirdNest";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession(true);
        try {
            String nest_id = request.getParameter("id");
            String nest_name = request.getParameter("name");
            String breed_id = request.getParameter("breed-id");
            String dad_bird_id = request.getParameter("dad_bird_id");
            String mom_bird_id = request.getParameter("mom_bird_id");
            String txtbaby_quantity = request.getParameter("baby_quantity");
            int baby_quantity = Integer.parseInt(txtbaby_quantity);
            String status = request.getParameter("status");
            String txtprice = request.getParameter("price");
            int price = Integer.parseInt(txtprice);
            String txtdiscount = request.getParameter("discount");
            int discount = Integer.parseInt(txtdiscount);
            String description = request.getParameter("description");
            String txtImage_1 = request.getParameter("txtImage_1");
            String txtImage_2 = request.getParameter("txtImage_2");
            String txtImage_3 = request.getParameter("txtImage_3");
            BirdNestDTO bn = new BirdNestDTO(nest_id, nest_name, dad_bird_id, mom_bird_id, baby_quantity, status, price, description, breed_id, discount);
            BirdNestDAO birdNestDAO = new BirdNestDAO();
            boolean check = birdNestDAO.addNewBirdNest(bn);
            ImageDAO imageDao = new ImageDAO();
            boolean check_image1 = imageDao.addNewImageBirdNest(txtImage_1, "1", nest_id);
            boolean check_image2 = imageDao.addNewImageBirdNest(txtImage_2, "0", nest_id);
            boolean check_image3 = imageDao.addNewImageBirdNest(txtImage_3, "0", nest_id);
            if (check) {
                session.setAttribute("MESSAGE", "Đã thêm " + nest_id + " thành công");
                url = SUCCESS;
            }

        } catch (Exception e) {
            if (e.getMessage().contains("duplicate key")) {
                session.setAttribute("ERROR",  "Id của tổ đã được sử dụng" );
                url = SUCCESS;
            } else {
                e.printStackTrace();
            }

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
