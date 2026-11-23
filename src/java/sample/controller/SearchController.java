/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.MobileDAO;
import sample.dto.MobileDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Owner
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            HttpSession session = request.getSession();
            UserDTO userLogin = (UserDTO) session.getAttribute("USER_LOGIN");
            if (userLogin == null) {
                url = "login.jsp";
                return;
            }

            String a = request.getParameter("txtSearch");
            if (a == null) {
                a = "";
            } 
            String minPrice = request.getParameter("minPriceText"); 
            String maxPrice = request.getParameter("maxPriceText");

            MobileDAO dao = new MobileDAO();
            ArrayList<MobileDTO> listMobile = new ArrayList();
            listMobile = dao.searchMobile(a, minPrice, maxPrice);
            if (!listMobile.isEmpty()) {
                request.setAttribute("LIST", listMobile);
                if (userLogin.getRole() == 1) {
                    url = "staff.jsp";
                } else {
                    url = "user.jsp";
                }
            } else {
                if (userLogin.getRole() == 1) {
                    url = "staff.jsp";
                } else {
                    url = "user.jsp";
                }
                request.setAttribute("LIST_EMPTY", "Tìm thấy 0 sản phẩm cho từ khóa " + a);
            }
            request.setAttribute("KEYWORD", a);

        } catch (Exception e) {
            log("Error at Searchcontroller: " + e.toString());
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
