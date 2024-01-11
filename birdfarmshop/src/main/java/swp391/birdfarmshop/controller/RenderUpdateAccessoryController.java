/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
//import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.time.LocalTime;
import java.util.List;
import swp391.birdfarmshop.dao.AccessoryCategoryDAO;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dao.ImageDAO;
import swp391.birdfarmshop.dto.AccessoryDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.AccessoryCategory;
import swp391.birdfarmshop.model.Image;
import swp391.birdfarmshop.model.User;
import swp391.birdfarmshop.util.Constants;
import swp391.birdfarmshop.util.ImageUtils;
import swp391.birdfarmshop.util.S3Utils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RenderUpdateAccessoryController", urlPatterns = {"/RenderUpdateAccessoryController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,//1mb
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
public class RenderUpdateAccessoryController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "management/edit-accessory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            String btAction = request.getParameter("btAction");
            String id = request.getParameter("txtAccessoryID");
            AccessoryDAO d = new AccessoryDAO();
            ImageDAO i = new ImageDAO();
            Accessory a = d.getAccessoryByID(id);
            AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
            List<AccessoryCategory> ac = dao.getAccessoryCategories();
            ImageDAO im = new ImageDAO();
            String url_thumnail = im.getThumbnailUrlByAccessoryId(id);
            List<Image> list = im.getImageByAccessoryId(id);
            String category = request.getParameter("category_id");
            String categoryname = dao.getAccessoryCategoryNameById(category);
            if (!list.isEmpty()) {
                request.setAttribute("list", list);
            }
            if (url_thumnail != null) {
                request.setAttribute("url_thumnail", url_thumnail);
            }
            if (ac != null) {
                request.setAttribute("ac", ac);
            }
            if (a != null) {
                request.setAttribute("a", a);
            }
            if (category != null) {
                request.setAttribute("category_id", category);
            }
            if (categoryname != null) {
                request.setAttribute("categoryname", categoryname);
            }

            boolean exit = false;

            if (btAction != null) {
                if (btAction.equalsIgnoreCase("Update")) {
                    if (u.getRole().equals("admin") || u.getRole().equals("manager")) {
                        exit = true;
                        String txtAccessoryID = request.getParameter("txtAccessoryID");
                        String txtAccessoryName = request.getParameter("txtAccessoryName");
                        String txtCategoryID = request.getParameter("txtCategoryID");
                        String txtPrice = request.getParameter("txtPrice");
                        String txtStockQuantity = request.getParameter("txtStockQuantity");
                        String txtDescribe = request.getParameter("txtDescribe");
                        String txtDiscount = request.getParameter("txtDiscount");

                        Part txtImage = request.getPart("txtImage");
                        Part txtImage_1 = request.getPart("txtImage_1");
                        Part txtImage_2 = request.getPart("txtImage_2");

                        if (txtImage.getSize() > 0 && txtImage.getSize() < 1048576) {
                            String imageURL = returnUrl(txtImage);
                            boolean checkImage = i.updateImageAccessory(txtAccessoryID, true, imageURL, null);
                        }

                        if (txtImage_1.getSize() > 0 && txtImage_1.getSize() < 1048576) {
                            String imageURL = returnUrl(txtImage_1);
                            String Image_id = request.getParameter("Image_id_1");
                            boolean checkImage = i.updateImageAccessory(txtAccessoryID, false, imageURL, Image_id);
                        }

                        if (txtImage_2.getSize() > 0 && txtImage_2.getSize() < 1048576) {
                            String imageURL = returnUrl(txtImage_2);
                            String Image_id = request.getParameter("Image_id_2");
                            boolean checkImage = i.updateImageAccessory(txtAccessoryID, false, imageURL, Image_id);
                        }

                        boolean rs = d.updateAccessory(txtAccessoryID, txtAccessoryName, txtCategoryID, txtPrice, txtStockQuantity, txtDescribe, txtDiscount);
                        if (rs) {
                            session.setAttribute("SUCCESS", "Chỉnh sửa phụ kiện thành công");
                            url = "MainController?action=NavToAccessoryDetails&id=" + txtAccessoryID;
                        } else {
                            session.setAttribute("ERROR", "Chỉnh sửa phụ kiện thất bại");
                            url = SUCCESS;
                        }

                        AccessoryDAO aDAO = new AccessoryDAO();
                        AccessoryDTO acDTO = aDAO.getAccessoryDetailsByID(txtAccessoryID);
                        if (acDTO.getStatus().equalsIgnoreCase("hết hàng")) {
                            String message = "Hết hàng";
                            request.setAttribute("MESSAGE", message);
                        }
                        request.setAttribute("a", acDTO);
                       
                    }
                }
                if (btAction.equalsIgnoreCase("UpdateQuantity")) {
                    if (u.getRole().equals("admin") || u.getRole().equals("manager") || u.getRole().equals("staff")) {
                        exit = true;
                        String txtAccessoryID = request.getParameter("txtAccessoryID");
                        String txtNewQuantity = request.getParameter("txtNewQuantity");                      
                        boolean rs = d.updateAccessoryQuantity(txtAccessoryID, txtNewQuantity);
                        if (rs) {
                            session.setAttribute("SUCCESS", "Chỉnh sửa số lượng thành công");
                        } else {
                            session.setAttribute("ERROR", "Chỉnh sửa phụ kiện thất bại");
                        }   
                        String isThumbnail = i.getThumbnailUrlByAccessoryId(txtAccessoryID);
                        request.setAttribute("im", isThumbnail);
                        AccessoryDAO aDAO = new AccessoryDAO();
                        AccessoryDTO acDTO = aDAO.getAccessoryDetailsByID(txtAccessoryID);
                        if (acDTO.getStatus().equalsIgnoreCase("hết hàng")) {
                            String message = "Hết hàng";
                            request.setAttribute("MESSAGE", message);
                        }
                        request.setAttribute("a", acDTO);

                        url = "MainController?action=NavToAccessoryDetails&id=" + txtAccessoryID;
                    }
                }
            }
            if (!exit) {
                url = SUCCESS;
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
