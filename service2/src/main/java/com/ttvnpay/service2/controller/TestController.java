package com.ttvnpay.service2.controller;

import com.ttvnpay.service2.model.entity.BOUserEntity;
import com.ttvnpay.service2.model.request.InsertBOUserRq;
import com.ttvnpay.service2.model.request.UpdateBOUserRq;
import com.ttvnpay.service2.model.response.BOUserBranchNameRp;
import com.ttvnpay.service2.model.response.BOUserRp;
import com.ttvnpay.service2.service.BOUserService;
import jdk.jfr.internal.tool.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service2")
public class TestController {
    @Autowired
    private Environment env;
    @Autowired
    BOUserService boUserService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    @RequestMapping("/")
    public String home() {
        MDC.put("requestId", UUID.randomUUID().toString());
        LOGGER.info("Hello from MS2 Service running at port: " + env.getProperty("local.server.port"));
        return "Hello from MS2 Service running at port: " + env.getProperty("local.server.port");
    }

    @PostMapping("/insertBOUser")
    public String insertBOUser(@RequestBody InsertBOUserRq insertBOUserRq){
        return boUserService.insertBOUser(insertBOUserRq);
    }

    @PutMapping("/updateBOUser")
    public String updateBOUser(@RequestBody UpdateBOUserRq updateBOUserRq){
        return boUserService.updateBOUser(updateBOUserRq);
    }

    @GetMapping("/getUserBranches")
    public List<BOUserBranchNameRp> getBOUserBranches(){
        MDC.put("requestId", UUID.randomUUID().toString());
        LOGGER.info("Get all users with branches");
        return boUserService.getBOUserBranches();
    }

    @GetMapping("/getAllUsers")
    public List<BOUserEntity> getAllUsers(){
        return boUserService.getAllUser();
    }

}
