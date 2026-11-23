/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dto;

/**
 *
 * @author Owner
 */
public class MobileError {

    private String mobileIdError;
    private String descriptionError;
    private String priceError;
    private String nameError;
    private String yearError;
    private String quantityError;
    private String notSale;

    public MobileError() {
        this.mobileIdError = "";
        this.descriptionError = "";
        this.priceError = "";
        this.nameError = "";
        this.yearError = "";
        this.quantityError = "";
        this.notSale = "";
    }

    public MobileError(String mobileIdError, String descriptionError, String priceError, String nameError, String yearError, String quantityError, String notSale) {
        this.mobileIdError = mobileIdError;
        this.descriptionError = descriptionError;
        this.priceError = priceError;
        this.nameError = nameError;
        this.yearError = yearError;
        this.quantityError = quantityError;
        this.notSale = notSale;
    }

    public String getMobileIdError() {
        return mobileIdError;
    }

    public void setMobileIdError(String mobileIdError) {
        this.mobileIdError = mobileIdError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getYearError() {
        return yearError;
    }

    public void setYearError(String yearError) {
        this.yearError = yearError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getNotSale() {
        return notSale;
    }

    public void setNotSale(String notSale) {
        this.notSale = notSale;
    }

}
