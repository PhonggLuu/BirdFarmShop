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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import swp391.birdfarmshop.dao.UserDAO;
import swp391.birdfarmshop.dto.AccountDTO;
import swp391.birdfarmshop.model.User;

/**
 *
 * @author tlminh
 */
@WebServlet(name = "RenderAccountsController", urlPatterns = {"/RenderAccountsController"})
public class RenderAccountsController extends HttpServlet {

    private static final String ERROR = "errorpages/error.jsp";
    private static final String SUCCESS = "management/accounts.jsp";
    private static final String DEST_NAV_LOGIN = "/authentication/login.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            String role = request.getParameter("roles");
            if (u != null) {
                if (u.getRole().equals("admin") || u.getRole().equals("manager")) {
                    url = SUCCESS;
                    String page = "1";
                    int numberOfRecords = 10;
                    if (request.getParameter("page") != null) {
                        page = request.getParameter("page");
                    }
                    String search = request.getParameter("search");
                    ArrayList<AccountDTO> accountList = new ArrayList<>();
                    UserDAO userDao = new UserDAO();
                    if(role == null){
                        accountList = userDao.getAccountList(search, page, numberOfRecords);
                    }else{
                        accountList = userDao.getAccountList(search, page, numberOfRecords, role);
                    }
                    int numberOfAccount = userDao.totalAccount(search,role);
                    int numberOfPage = (int) Math.ceil(numberOfAccount * 1.0 / numberOfRecords);
                    request.setAttribute("noOfPages", numberOfPage);
                    request.setAttribute("currentPage", page);
                    request.setAttribute("ACCOUNT_LIST", accountList);
                }else{
                    session.setAttribute("ERROR", "Bạn không phải là quản lí hoặc quản trị viên");
                    url = DEST_NAV_LOGIN;
                }
            } else {
                session.setAttribute("ERROR", "Bạn chưa đăng nhập");
                url = DEST_NAV_LOGIN;
            }
        } catch (Exception e) {
            log("Error at RenderAccountsController: " + e.toString());
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
