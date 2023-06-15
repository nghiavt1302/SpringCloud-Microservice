package com.ttvnpay.service2.model.response;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class BOUserRp {
    private String username;

    private String fullname;

    private String password;

    private String branchCode;
    private String posCode;
    private boolean status;
    private Timestamp createdDate;
    private String createdUser;
    private boolean passChange;
    private Integer roleId;
    private Integer invalidNumber;
    private Integer userId;
    private String email;
    private String mobileNo;
    private Timestamp modifiedDate;
    private String modifiedUser;
}
