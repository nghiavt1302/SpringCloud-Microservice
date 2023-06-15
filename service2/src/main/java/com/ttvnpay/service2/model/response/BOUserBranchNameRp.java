package com.ttvnpay.service2.model.response;

import java.io.Serializable;

public interface BOUserBranchNameRp extends Serializable {
    String getUsername();
    String getFullname();
    String getBranch_code();
    String getBranch_name();
    String getBranch_address();
}
