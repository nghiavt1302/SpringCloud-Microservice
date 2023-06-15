package com.ttvnpay.service1.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertBOUserRq {
    private String username;
    private String fullname;
    private String password;
    private String branchCode;
    private String posCode;
    private boolean status;
    private String createdUser;
    private boolean passChange;
    private Integer roleId;
    private Integer invalidNumber;
    private Integer userId;
    private String email;
    private String mobileNo;
    private String modifiedUser;
}
