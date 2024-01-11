/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import swp391.birdfarmshop.dao.BirdBreedDAO;
import swp391.birdfarmshop.dao.BirdCustomerDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.model.BirdCustomer;
import swp391.birdfarmshop.model.BirdBreed;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.util.Constants;
import swp391.birdfarmshop.util.ImageUtils;
import swp391.birdfarmshop.util.S3Utils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateBirdCustomerController", urlPatterns = {"/CreateBirdCustomerController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,//1mb
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
public class CreateBirdCustomerController extends HttpServlet {
    
    private static final String DEST_NAV_CREATE_BIRD_CUSTOMER = "shop/add-customerBird.jsp";
    private static final String DEST_NAV_BIRD_PAIR = "MainController?action=NavToPairBirds";

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
        String url = DEST_NAV_CREATE_BIRD_CUSTOMER;
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            List<BirdBreed> breedList = new ArrayList<>();
            BirdBreedDAO breedDao = new BirdBreedDAO();
            BirdDAO birdDao = new BirdDAO();
            breedList = breedDao.getBirdBreeds();
            String breedId = request.getParameter("txtBreedId");
            String birdId = request.getParameter("txtBirdId");
            String nameBird = request.getParameter("nameBird");
            String gender = request.getParameter("gender");
            Part part = null;
            if (nameBird != null && breedId != null
                    && nameBird != null && gender != null) {
                part = (Part) request.getPart("filePicture");
                if (u != null) {
                    BirdCustomerDAO bcd = new BirdCustomerDAO();
                    BirdCustomer bc = bcd.findBirdCustomer(birdId);
                    if (bc == null) {
                        if (part.getSize() < 1048576) {
                            String file = ImageUtils.getFileName(part);
                            LocalTime currentTime = LocalTime.now();
                            String nameImage = currentTime.getNano() + file;
                            String img_url = Constants.C3_HOST + nameImage;
                            S3Utils.uploadFile(nameImage, part.getInputStream());
                            int result = bcd.createNewBirdCustomer(birdId, nameBird, gender,
                                    breedId, u.getUsername(), img_url, "Chưa ghép cặp");
                            if (result == 0) {
                                session.setAttribute("ERROR", "Thêm vẹt mới thất bại");
                                request.getRequestDispatcher(url).forward(request, response);
                            } else {
                                session.setAttribute("SUCCESS", "Thêm vẹt mới thành công");                                
                                response.sendRedirect(DEST_NAV_BIRD_PAIR);
                            }
                        } else {
                            session.setAttribute("ERROR", "Ảnh có dung lượng quá 1mb");
                            request.getRequestDispatcher(url).forward(request, response);
                        }
                    } else {
                        session.setAttribute("ERROR", "Mã vẹt đã được sử dụng");
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                    
                } else {
                    session.setAttribute("ERROR", "Bạn phải đăng nhập");
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
            request.setAttribute("BIRD_BREEDS", breedList);
            if(nameBird == null){
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
