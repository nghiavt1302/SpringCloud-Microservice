package com.ttvnpay.service2.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "BO_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BOUserEntity implements Serializable {
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "FULLNAME")
    private String fullname;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "BRANCH_CODE")
    private String branchCode;
    @Column(name = "POS_CODE")
    private String posCode;
    @Column(name = "STATUS")
    private boolean status;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Column(name = "CREATED_USER")
    private String createdUser;
    @Column(name = "PASSCHANGE")
    private boolean passChange;
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Column(name = "INVALID_NUMBER")
    private Integer invalidNumber;
    @Id
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MOBILE_NO")
    private String mobileNo;
    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate;
    @Column(name = "MODIFIED_USER")
    private String modifiedUser;

}
