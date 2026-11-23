/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.utils;

import java.util.Base64;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dto.CartDTO;

/**
 *
 * @author Owner
 */
public class MobileCookie {

    public HashMap<String, CartDTO> giaMaGioHang(Cookie c) {
        HashMap<String, CartDTO> gioHang = new HashMap<>();
        Base64.Decoder maHoa = Base64.getDecoder();
        String giaiMaHoa = new String(maHoa.decode(c.getValue().getBytes()));
        //P1, ip17, 10| P2,ip16,..| P10, ip12,...
        String[] mangPhone = giaiMaHoa.split("\\|");
        for (String m : mangPhone) {
            String[] cart = m.split(",");
            String cartID = cart[0].trim();
            String cartName = cart[1].trim();
            int cartUnit = Integer.parseInt(cart[2].trim());
            double cartPrice = Double.parseDouble(cart[3].trim());
            gioHang.put(cartID, new CartDTO(cartID, cartName, cartUnit, cartPrice));
        }
        return gioHang;
    }

    public String chuyenGioHangVeChuoi(HashMap<String, CartDTO> gioHang) {
        // p1    // P1, ip17, 10 P2,ip16,.. P10, ip12,...  = ABCyg==?a
        //P2     // P2, ip16....
        // p10    // P10, ip12......
        StringBuilder sb = new StringBuilder();
        for (CartDTO c : gioHang.values()) {
            sb.append(c + "|");
        }
        //sb.toSTring():STring
        // Mahoa: Byte
        Base64.Encoder maHoa = Base64.getEncoder();
        String gioHangDaMaHoa = maHoa.encodeToString(sb.toString().getBytes());
        return gioHangDaMaHoa;
    }

    public void luuCookie(HttpServletRequest request, HttpServletResponse response, String tenGioHang) {// CART
        // LAY TRONG COOKIE NGTA CO SẴN CHƯA
        // 1 thoi
        // 4 them 5-> 5.
        Cookie cookieLayThuCoHayKo = layCookieNguoiDung(request, "CART");
        if (cookieLayThuCoHayKo != null) {
            cookieLayThuCoHayKo.setValue(tenGioHang);
        } else {
            cookieLayThuCoHayKo = new Cookie("CART", tenGioHang);
        }
        cookieLayThuCoHayKo.setMaxAge(60);
        response.addCookie(cookieLayThuCoHayKo);
    }

    // LAY THU CO COOKIE KO
    public Cookie layCookieNguoiDung(HttpServletRequest request, String tenCookie) { //CART
        Cookie mangCookie[] = request.getCookies();//  ,CART, HISTORY(weB), ACCOUNT(GOOGLE)
        if (mangCookie != null) {
            for (Cookie c : mangCookie) {
                if (c.getName().equals(tenCookie)) {
                    return c;
                }
            }
        }
        return null;
    }
}
