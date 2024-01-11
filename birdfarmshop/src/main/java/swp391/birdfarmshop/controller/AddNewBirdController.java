/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import swp391.birdfarmshop.dao.BirdBreedDAO;
import swp391.birdfarmshop.dao.BirdDAO;
import swp391.birdfarmshop.dao.ImageDAO;
import swp391.birdfarmshop.dto.BirdDTO;
import swp391.birdfarmshop.model.BirdBreed;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.util.Constants;
import swp391.birdfarmshop.util.ImageUtils;
import swp391.birdfarmshop.util.S3Utils;

/**
 *
 * @author phong pc
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,//1mb
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
public class AddNewBirdController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String HOME = "shop/home.jsp";
    private static final String SUCCESS = "management/add-bird.jsp";
    private static final String DETAIL = "MainController?action=NavToBirdDetails&bird_id=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean success = true;
        String  txtBirdId = null, txtBirdName = null, txtBirdColor = null, txtBirdDate = null, txtBirdGrownAge = null, 
                txtBirdGender = null, txtBirdBreed = null, txtBirdAchievement = null, txtBirdReproduction_history = null, 
                txtBirdPrice = null, txtBirdDescription = null, txtDadId = null, txtMomId = null, txtBirdDiscount = null, txtBirdStatus = null;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            if (user != null && !user.getRole().equals("customer")) {
                BirdDAO birdDao = new BirdDAO();
                BirdBreedDAO breedDao = new BirdBreedDAO();
                //breed list
                List<BirdBreed> breedList = breedDao.getBirdBreeds();
                //mom dad list
                List<BirdDTO> listBirdDad = birdDao.getBirdMomDadByGender(true);
                List<BirdDTO> listBirdMom = birdDao.getBirdMomDadByGender(false);
                request.setAttribute("DADLIST", listBirdDad);
                request.setAttribute("MOMLIST", listBirdMom);
                request.setAttribute("BREED", breedList);
                String btAction = request.getParameter("btAction");
                if (btAction != null) {
                    txtBirdId = request.getParameter("txtBirdId");
                    boolean isExisted = birdDao.isBirdIdExisted(txtBirdId);
                    if(isExisted) {
                        session.setAttribute("ERROR", "ID ĐÃ TỒN TẠI. NHẬP ID MỚI");
                        success = false;
                        url = SUCCESS;
                        return;
                    }
                    txtBirdName = request.getParameter("txtBirdName");
                    txtBirdColor = request.getParameter("txtBirdColor");
                    txtBirdDate = request.getParameter("txtBirdDate");
                    txtBirdGrownAge = request.getParameter("txtBirdGrownAge");
                    txtBirdGender = request.getParameter("txtBirdGender");
                    txtBirdBreed = request.getParameter("txtBirdBreed");
                    txtBirdAchievement = request.getParameter("txtBirdAchievement");
                    txtBirdReproduction_history = request.getParameter("txtBirdReproduction_history");
                    txtBirdPrice = request.getParameter("txtBirdPrice");
                    txtBirdDescription = request.getParameter("txtBirdDescription");
                    txtDadId = request.getParameter("txtDadId");
                    txtMomId = request.getParameter("txtMomId");
                    txtBirdDiscount = request.getParameter("txtBirdDiscount");
                    txtBirdStatus = "Còn hàng";
                    boolean check = birdDao.addNewBird(txtBirdId, txtBirdName, txtBirdColor,
                            txtBirdDate, txtBirdGrownAge, txtBirdGender, txtBirdBreed,
                            txtBirdAchievement, txtBirdReproduction_history, txtBirdPrice,
                            txtBirdDescription, txtDadId, txtMomId, txtBirdDiscount, txtBirdStatus);
                    Part image_1 = request.getPart("txtImage_1");
                    Part image_2 = request.getPart("txtImage_2");
                    Part image_3 = request.getPart("txtImage_3");
                    addNewImage(image_1, "1", txtBirdId);
                    addNewImage(image_2, "0", txtBirdId);
                    addNewImage(image_3, "0", txtBirdId);
                    if (check) {
                        session.setAttribute("SUCCESS", "Tạo mới thành công");
                    } else {
                        session.setAttribute("ERROR", "Tạo mới thất bại");
                        success = false;
                    }
                    if (btAction.equals("Add&Return")) {
                        url = DETAIL + txtBirdId;
                    } else {
                        url = SUCCESS;
                    }
                } else {
                    url = SUCCESS;
                }
            } else {
                url = HOME;
            }
        } catch (SQLException e) {
        } finally {
            if (!success) {
                request.setAttribute("txtBirdId", txtBirdId);
                request.setAttribute("txtBirdName", txtBirdName);
                request.setAttribute("txtBirdColor", txtBirdColor);
                request.setAttribute("txtBirdDate", txtBirdDate);
                request.setAttribute("txtBirdGrownAge", txtBirdGrownAge);
                request.setAttribute("txtBirdGender", txtBirdGender);
                request.setAttribute("txtBirdBreed", txtBirdBreed);
                request.setAttribute("txtBirdAchievement", txtBirdAchievement);
                request.setAttribute("txtBirdReproduction_history", txtBirdReproduction_history);
                request.setAttribute("txtBirdPrice", txtBirdPrice);
                request.setAttribute("txtBirdDescription", txtBirdDescription);
                request.setAttribute("txtDadId", txtDadId);
                request.setAttribute("txtMomId", txtMomId);
                request.setAttribute("txtBirdDiscount", txtBirdDiscount);
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public void addNewImage(Part part, String isThumbnail, String bird_id) throws SQLException, IOException {
        ImageDAO imgDao = new ImageDAO();
        if (part.getSize() < 1048576) {
            String file = ImageUtils.getFileName(part);
            LocalTime currentTime = LocalTime.now();
            String nameImage = currentTime.getNano() + file;
            String img_url = Constants.C3_HOST + nameImage;
            S3Utils.uploadFile(nameImage, part.getInputStream());
            imgDao.addNewImageBird(img_url, isThumbnail, bird_id);
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
        } catch (SQLException ex) {
            Logger.getLogger(AddNewBirdController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddNewBirdController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddNewBirdController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(AddNewBirdController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddNewBirdController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddNewBirdController.class.getName()).log(Level.SEVERE, null, ex);
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
