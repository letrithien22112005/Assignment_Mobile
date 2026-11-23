/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Owner
 */
public class MainController extends HttpServlet {

    private static final String WELCOME = "login.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String VIEW = "View";
    private static final String VIEW_CONTROLLER = "ViewController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String ADD_CART = "AddCart";
    private static final String ADD_CART_CONTROLLER = "AddCartController";
    private static final String VIEW_CART = "ViewCart";
    private static final String VIEW_CART_CONTROLLER = "ViewCartController";
    private static final String DELETE_CART = "DeleteCart";
    private static final String DELETE_CART_CONTROLLER = "DeleteCartController";
    private static final String UPDATE_CART = "UpdateCart";
    private static final String UPDATE_CART_CONTROLLER = "UpdateCartController";
    private static final String SAVE_CART = "SaveCart";
    private static final String SAVE_CART_CONTROLLER = "SaveCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String x = request.getParameter("action");
            if (x == null) {
                url = WELCOME;
            } else if (LOGIN.equals(x)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH.equals(x)) {
                url = SEARCH_CONTROLLER;
            } else if (LOGOUT.equals(x)) {
                url = LOGOUT_CONTROLLER;
            }else if ("Detail".equals(x)) {
                url = "DetailController";
            } else if (DELETE.equals(x)) {
                url = DELETE_CONTROLLER;
            } else if (VIEW.equals(x)) {
                url = VIEW_CONTROLLER;
            } else if (UPDATE.equals(x)) {
                url = UPDATE_CONTROLLER;
            } else if (ADD.equals(x)) {
                url = ADD_CONTROLLER;
            } else if (ADD_CART.equals(x)) {
                url = ADD_CART_CONTROLLER;
            } else if (VIEW_CART.equals(x)) {
                url = VIEW_CART_CONTROLLER;
            } else if (DELETE_CART.equals(x)) {
                url = DELETE_CART_CONTROLLER;
            } else if (UPDATE_CART.equals(x)) {
                url = UPDATE_CART_CONTROLLER;
            } else if (SAVE_CART.equals(x)){
                url = SAVE_CART_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
