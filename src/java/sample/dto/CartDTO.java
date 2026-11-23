/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dto;

/**
 *
 * @author Owner
 */
public class CartDTO {

    private String idMobile;
    private String title;
    private int unit;
    private double price;

    public CartDTO() {
        this.idMobile = "";
        this.title = "";
        this.unit = 0;
        this.price = 0;
    }

    public CartDTO(String idMobile, String title, int unit, double price) {
        this.idMobile = idMobile;
        this.title = title;
        this.unit = unit;
        this.price = price;
    }

    public String getIdMobile() {
        return idMobile;
    }

    public void setIdMobile(String idMobile) {
        this.idMobile = idMobile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double totalPrice() {
        return unit * price;
    }

    @Override
    public String toString() {
        return idMobile + "," + title + "," + unit + "," + price;
    }
    
    
}
