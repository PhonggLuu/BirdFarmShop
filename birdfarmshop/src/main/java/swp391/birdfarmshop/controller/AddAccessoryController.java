/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
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
import swp391.birdfarmshop.dao.AccessoryCategoryDAO;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.AccessoryCategory;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.util.Constants;
import swp391.birdfarmshop.util.ImageUtils;
import swp391.birdfarmshop.util.S3Utils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddAccessoryController", urlPatterns = {"/AddAccessoryController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,//1mb
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
public class AddAccessoryController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "management/add-accessory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            String btAction = request.getParameter("btAction");
            AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
            List<AccessoryCategory> ac = dao.getAccessoryCategories();
            if (ac != null) {
                request.setAttribute("ac", ac);
                url = SUCCESS;
            }
            String txtAccessoryID = request.getParameter("txtAccessoryID");
            String txtAccessoryName = request.getParameter("txtAccessoryName");
            String txtCategoryID = request.getParameter("txtCategoryID");
            String txtPrice = request.getParameter("txtPriceNew");
            String txtStockQuantity = request.getParameter("txtStockQuantity");
            String txtDescribe = request.getParameter("txtDescribe");
            String txtDiscount = request.getParameter("txtDiscount");
            
            request.setAttribute("txtCategoryID", txtCategoryID);
            
            if (btAction != null) {
                if (btAction.equalsIgnoreCase("Add")) {
                    if (u.getRole().equals("admin") || u.getRole().equals("manager")) {
                        String type = request.getParameter("type");
                        AccessoryDAO d = new AccessoryDAO();
                        ArrayList<Accessory> a = d.getAccessories();
                        for (Accessory id : a) {
                            if (id.getAccessory_id().equals(txtAccessoryID)) {
                                session.setAttribute("ERROR", "ID ĐÃ TỒN TẠI. NHẬP ID MỚI");                            
                                url = SUCCESS;
                                return;
                            }
                        }
                        Part txtImage_1 = request.getPart("txtImage_1");
                        Part txtImage_2 = request.getPart("txtImage_2");
                        Part txtImage_3 = request.getPart("txtImage_3");

                        String imageURL_1 = null;
                        String imageURL_2 = null;
                        String imageURL_3 = null;

                        if (txtImage_1.getSize() < 1048576 && txtImage_2.getSize() < 1048576 && txtImage_3.getSize() < 1048576) {
                            imageURL_1 = returnUrl(txtImage_1);
                            imageURL_2 = returnUrl(txtImage_2);
                            imageURL_3 = returnUrl(txtImage_3);
                        }

                        boolean rs = d.insertAccessory(txtAccessoryID, txtAccessoryName, txtCategoryID, txtPrice, txtStockQuantity, txtDescribe, txtDiscount, imageURL_1, imageURL_2, imageURL_3);

                        if (rs) {
                            session.setAttribute("SUCCESS", "Thêm mới sản phẩm thành công");
                            if (type.equals("continue")) {
                                url = SUCCESS;
                            } else if (type.equals("close")) {
                                url = "MainController?action=NavToAccessory";
                            }
                        } else {
                            session.setAttribute("ERROR", "Thêm mới sản phẩm thất bại");
                        }

                    }
                }
            }

        } catch (Exception e) {
            log("Error at RenderAddAccessoryController: " + e.toString());
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

    public String returnUrl(Part txtImage) throws IOException {
        String file = ImageUtils.getFileName(txtImage);
        LocalTime currentTime = LocalTime.now();
        String nameImage = currentTime.getNano() + file;
        String img_url = Constants.C3_HOST + nameImage;
        S3Utils.uploadFile(nameImage, txtImage.getInputStream());
        return img_url;
    }

}
