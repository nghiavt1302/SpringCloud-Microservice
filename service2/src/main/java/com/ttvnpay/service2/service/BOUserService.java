package com.ttvnpay.service2.service;


import com.ttvnpay.service2.model.entity.BOUserEntity;
import com.ttvnpay.service2.model.request.InsertBOUserRq;
import com.ttvnpay.service2.model.request.UpdateBOUserRq;
import com.ttvnpay.service2.model.response.BOUserBranchNameRp;
import com.ttvnpay.service2.repository.BOUserRepository;
import jdk.jfr.internal.tool.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class BOUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    @Autowired
    BOUserRepository boUserRepository;

    public String insertBOUser(InsertBOUserRq insertBOUserRq){
        List<BOUserEntity> exits = boUserRepository.findByUserId(insertBOUserRq.getUserId());
        if (exits.isEmpty()){
            BOUserEntity user = BOUserEntity.builder()
                    .userId(insertBOUserRq.getUserId())
                    .fullname(insertBOUserRq.getFullname())
                    .username(insertBOUserRq.getUsername())
                    .password(insertBOUserRq.getPassword())
                    .branchCode(insertBOUserRq.getBranchCode())
                    .posCode(insertBOUserRq.getPosCode())
                    .status(insertBOUserRq.isStatus())
                    .createdDate(new Timestamp(System.currentTimeMillis()))
                    .createdUser(insertBOUserRq.getCreatedUser())
                    .passChange(insertBOUserRq.isPassChange())
                    .roleId(insertBOUserRq.getRoleId())
                    .invalidNumber(insertBOUserRq.getInvalidNumber())
                    .email(insertBOUserRq.getEmail())
                    .mobileNo(insertBOUserRq.getMobileNo())
                    .modifiedDate(new Timestamp(System.currentTimeMillis()))
                    .modifiedUser(insertBOUserRq.getModifiedUser())
                    .build();
            boUserRepository.save(user);
            MDC.put("requestId", UUID.randomUUID().toString());
            MDC.put("username", insertBOUserRq.getCreatedUser());
            LOGGER.info("Insert sucess: " + user);
            return "Insert sucess: " + user;
        }
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("username", insertBOUserRq.getCreatedUser());
        LOGGER.info("User is already exists: {}", insertBOUserRq.getUserId());
        return "User is already exists: " + insertBOUserRq.getUserId();
    }

    public String updateBOUser(UpdateBOUserRq updateBOUserRq){
        List<BOUserEntity> exists = boUserRepository.findByUserId(updateBOUserRq.getUserId());
        if (!exists.isEmpty()){
            BOUserEntity existEntity = exists.get(0);
            if (existEntity != null){
                BeanUtils.copyProperties(updateBOUserRq, existEntity);
                existEntity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            }
            boUserRepository.save(existEntity);
            MDC.put("requestId", UUID.randomUUID().toString());
            MDC.put("username", updateBOUserRq.getModifiedUser());
            LOGGER.info("Update sucess: " + existEntity);
            return "Update sucess: " + existEntity;
        }
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("username", updateBOUserRq.getModifiedUser());
        LOGGER.info("User id not found: {}", updateBOUserRq.getUserId());
        return "User id not found: " + updateBOUserRq.getUserId();
    }

    public List<BOUserBranchNameRp> getBOUserBranches(){
        List<BOUserBranchNameRp> list = boUserRepository.getBOUserBranches();
        return list;
    }
    @Cacheable(cacheNames = "testCache", key = "'testKey'")
    public List<BOUserEntity> getAllUser(){
        System.out.println("Get data from db");
        return boUserRepository.findAll();
    }
}
