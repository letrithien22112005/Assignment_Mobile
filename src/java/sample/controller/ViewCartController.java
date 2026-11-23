/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dto.CartDTO;
import sample.utils.MobileCookie;

/**
 *
 * @author Owner
 */
@WebServlet(name = "ViewCartController", urlPatterns = {"/ViewCartController"})
public class ViewCartController extends HttpServlet {

    private static final String ERROR = "cart.jsp";
    private static final String SUCCESS = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        Cookie cartCookie;
        MobileCookie mc = null;
        HashMap<String, CartDTO> gioHang;
        try {
            HttpSession cartSession  = request.getSession();
            gioHang = (HashMap<String, CartDTO>) cartSession.getAttribute("CART");
            if(gioHang == null){
                // Tìm tơi cookie của người dùng
                cartCookie = mc.layCookieNguoiDung(request, "CART");
                if(cartCookie != null){
                    gioHang = mc.giaMaGioHang(cartCookie);
                    if(gioHang != null){
                        request.setAttribute("CART", gioHang);
                    }
                }
            }else{
                request.setAttribute("CART", gioHang);
            }
            // lay trong session va cookie
            // CART
            /// DAT GIONG TEN HAY KHAC TEN?
            
            
        } catch (Exception e) {
            log("Error at ViewCartController: " + e.toString());
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
