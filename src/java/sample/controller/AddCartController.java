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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.MobileDAO;
import sample.dto.CartDTO;
import sample.dto.MobileDTO;

/**
 *
 * @author Owner
 */
@WebServlet(name = "AddCartController", urlPatterns = {"/AddCartController"})
public class AddCartController extends HttpServlet {

 private static final String ERROR = "MainController?action=Search";
    private static final String SUCCESS = "MainController?action=Search";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("idCart");
            HashMap<String, CartDTO> gioHang;
            HttpSession sessionGioHang = request.getSession();
            gioHang = (HashMap<String, CartDTO>) sessionGioHang.getAttribute("CART");
            if(gioHang == null){
                gioHang = new HashMap<>();
                sessionGioHang.setAttribute("CART", gioHang);
            }

            MobileDAO phoneDAO = new MobileDAO();
            MobileDTO phoneDatabase = phoneDAO.getMobileByID(id); 
            CartDTO cartPhone = gioHang.get(id);
            String thongbao;
            
            if(cartPhone == null) {
                CartDTO newPhone = new CartDTO(id, phoneDatabase.getFullName(), 1, phoneDatabase.getPrice());
                gioHang.put(id, newPhone);
                thongbao = "San pham" +id+ " vua duoc them vao gio hang";
            } else {
                cartPhone.setUnit(cartPhone.getUnit()+1);
                thongbao = "Phone" + id + "vua tang len 1";
            }
            url = SUCCESS;
            request.setAttribute("THONGBAO", thongbao);
        } catch (Exception e) {
            log("Error at AddCartController: " + e.toString());
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
