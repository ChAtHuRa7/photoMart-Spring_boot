package com.photoMart.userservice.dto.responseDTO;

public class UserBookingResponseDTO {

    private String userName;

    private String userMail;

    private String mobileNumber;

    public UserBookingResponseDTO() {
    }

    public UserBookingResponseDTO(String userName, String userMail, String mobileNumber) {
        this.userName = userName;
        this.userMail = userMail;
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
