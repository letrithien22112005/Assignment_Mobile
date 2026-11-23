/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.MobileDAO;
import sample.dto.MobileDTO;
import sample.dto.MobileError;
import sample.dto.UserDTO;

/**
 *
 * @author Owner
 */
@WebServlet(name = "AddController", urlPatterns = {"/AddController"})
public class AddController extends HttpServlet {

    private static final String ERROR = "addMobile.jsp";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO userLogin = (UserDTO) session.getAttribute("USER_LOGIN");
            if (userLogin == null) {
                url = "login.jsp";
                return;
            }

            String id = request.getParameter("txtID");
            String description = request.getParameter("txtDescription");
            String price = request.getParameter("txtPrice");
            float priceFloat = Float.parseFloat(price);
            String name = request.getParameter("txtName");
            String yearOfProduction = request.getParameter("txtYear");
            int yearOfProductionInt = Integer.parseInt(yearOfProduction);
            String quantity = request.getParameter("txtQuantity");
            int quantityInt = Integer.parseInt(quantity);
            String notSale = request.getParameter("chkNotSale");
            boolean notSaleBoolean = Boolean.parseBoolean(notSale);
            // name: 2 ky tu 20 ky tun
            // mau: ko bo trong

            //p VOI 2 SO
            MobileError mobileError = new MobileError();
            MobileDAO dao = new MobileDAO();
            boolean checkValid = true;
            if (!id.matches("^[M|m][B|b]\\d{3}$") || dao.getMobileByID(id) != null) {
                mobileError.setMobileIdError("ID format la MB voi 3 số và không trùng");
                checkValid = false;
            }

            if (name.length() < 2 || name.length() > 20) {
                checkValid = false;
                mobileError.setNameError("Name phai tu 2 den 20 ky tu");
            }

            if (priceFloat < 0) {
                checkValid = false;
                mobileError.setPriceError("Gia tri so duong");
            }

            if (quantityInt < 0) {
                checkValid = false;
                mobileError.setPriceError("Gia tri so duong");
            }

            if (checkValid) {
                MobileDTO mobileAdd = new MobileDTO(id, description, priceFloat, name, yearOfProductionInt, quantityInt, notSaleBoolean);
                boolean check = dao.addMobile(mobileAdd);
                if (check) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            } else {
                request.setAttribute("MOBILE_ERROR", mobileError);
            }
        } catch (Exception e) {
            log("Error at AddController: " + e.toString());
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
