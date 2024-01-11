/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swp391.birdfarmshop.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.dao.FeedbackDAO;
import swp391.birdfarmshop.dao.ImageDAO;
import swp391.birdfarmshop.dto.AccessoryDTO;
import swp391.birdfarmshop.dto.FeedbackDTO;
import swp391.birdfarmshop.dto.StarDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RenderAccessoryDetailsController", urlPatterns = {"/RenderAccessoryDetailsController"})
public class RenderAccessoryDetailsController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "shop/accessory-details.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR;
        try {
            String page = "1";
            int numberOfRecords = 5;
            String tmpPage = request.getParameter("page");
            if (tmpPage != null) {
                page = tmpPage;
            }
            String accessory_id = request.getParameter("id");
            AccessoryDAO a = new AccessoryDAO();
            AccessoryDTO ac = a.getAccessoryDetailsByID(accessory_id);
            FeedbackDAO f = new FeedbackDAO();
            ArrayList<FeedbackDTO> feedbackList = f.getFeedbackByIdProduct(accessory_id, page, numberOfRecords);
            int numberOfFeebacks = f.totalFeedbackByIdProduct(accessory_id);
            int numberOfPage = (int) Math.ceil(numberOfFeebacks * 1.0 / numberOfRecords);
            StarDTO starCustomer = f.getRatingByIdProduct(accessory_id);
            request.setAttribute("noOfPages", numberOfPage);
            request.setAttribute("currentPage", page);
            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("starCustomer", starCustomer);
            ImageDAO image = new ImageDAO();
            String im = image.getThumbnailUrlByAccessoryId(accessory_id);
            request.setAttribute("im", im);
            if (ac != null) {
                request.setAttribute("a", ac);
                if (ac.getStock_quantity() == 0) {
                    String message = "Hết hàng";
                    request.setAttribute("MESSAGE", message);
                }
                url = SUCCESS;
            } else {
                url = ERROR;
            }
            if (accessory_id != null && request.getParameter("page") != null) {
                for (FeedbackDTO feedbackDTO : feedbackList) {
                    out.println(" <div class=\"mx-4 mt-4 user-comment\">\n"
                            + "                                <div class=\"d-flex justify-content-between align-items-center\">\n"
                            + "                                    <div>\n"
                            + "                                        <h6 class=\"mb-2 font-weight-bold\">" + feedbackDTO.getFullName() + "</h6>\n"
                            + "                                        <div class=\"rating\">");
                    for (int i = 1; i <= 5; i++) {
                        if (i <= feedbackDTO.getRating()) {
                            out.println("  <span class=\"fa fa-star star-checked\"></span>");
                        } else {
                            out.println("<span class=\"fa fa-star\"></span>");
                        }
                    }
                    out.println("     </div>\n"
                            + "                                    </div>\n"
                            + "                                    <div>\n"
                            + "                                        <p class=\"text-muted user-select-none\">" + feedbackDTO.getFeedbackDate() + "</p>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                                <p class=\"mt-2\">" + feedbackDTO.getComment() + "</p>\n"
                            + "                            </div>");
                }
                if (numberOfPage != 1) {
                    int nPage = Integer.parseInt(page);
                    String prev_page = null;
                    String activePage = null;
                    String next_page = null;
                    out.println("<div class=\"d-flex float-right align-items-center page-pagination\">\n"
                            + "                                <input type=\"hidden\" name=\"page\" value=" + nPage + " />\n"
                            + " <input type=\"hidden\" name=\"numberOfPage\" value=" + numberOfPage + " />\n");
                    if (numberOfPage > 1 && numberOfPage <= 5) {
                        prev_page = nPage <= 1 ? " page-disable" : "";
                        out.println("  <div onclick=\"PrevPage()\" class=\"page-prev" + prev_page + "\"><i class=\"fa fa-angle-left\" aria-hidden=\"true\"></i></i></div>");
                        for (int i = 1; i <= numberOfPage; i++) {
                            activePage = nPage == i ? "page-active" : "";
                            out.println("<div onclick=\"NavToNewPage(this)\" class=\"page-navTo " + activePage + "\" data-value=\"" + i + "\">" + i + "</div>");
                        }
                        next_page = nPage >= numberOfPage ? " page-disable" : "";
                        out.println("<div onclick=\"NextPage()\" class=\"page-next ml-3" + next_page + "\"><i class=\"fa fa-angle-right\" aria-hidden=\"true\"></i></i></div>");
                    }
                    if (numberOfPage > 5) {
                        if (nPage > 2) {
                            prev_page = nPage <= 1 ? " page-disable" : "";
                            out.println("  <div onclick=\"PrevPage()\" class=\"page-prev" + prev_page + "\"><i class=\"fa fa-angle-left\" aria-hidden=\"true\"></i></i></div>");
                            int begin = 0;
                            int end = 0;
                            if(numberOfPage + 2 < numberOfFeebacks){
                                begin = nPage - 2;
                                end = nPage + 2;
                            }else{
                                 begin = numberOfFeebacks - 4;
                                 end = numberOfFeebacks;
                            }
                            for (int i = begin; i <= end + 2; i++) {
                                activePage = nPage == i ? " page-active" : "";
                                out.println("<div onclick=\"NavToNewPage(this)\" class=\"page-navTo" + activePage + "\" data-value=\"" + i + "\">" + i + "</div>");
                            }
                            next_page = nPage >= numberOfPage ? " page-disable" : "";
                            out.println("<div onclick=\"NextPage()\" class=\"page-next ml-3" + next_page + "\"><i class=\"fa fa-angle-right\" aria-hidden=\"true\"></i></i></div>");
                        } else {
                            prev_page = nPage <= 1 ? " page-disable" : "";
                            out.println("  <div onclick=\"PrevPage()\" class=\"page-prev" + prev_page + "\"><i class=\"fa fa-angle-left\" aria-hidden=\"true\"></i></i></div>");
                            for (int i = 1; i <= nPage; i++) {
                                activePage = nPage == i ? " page-active" : "";
                                out.println("<div onclick=\"NavToNewPage(this)\" class=\"page-navTo" + activePage + "\" data-value=\"" + i + "\">" + i + "</div>");
                            }
                            next_page = nPage >= numberOfPage ? " page-disable" : "";
                            out.println("<div onclick=\"NextPage()\" class=\"page-next ml-3" + next_page + "\"><i class=\"fa fa-angle-right\" aria-hidden=\"true\"></i></i></div>");
                        }
                    }
                }
                return;
            }
        } catch (Exception e) {
            log("Error at RenderAccessoryDetailsController: " + e.toString());
        } finally {
            if(request.getParameter("page") == null){
               request.getRequestDispatcher(url).forward(request, response);
            }
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
