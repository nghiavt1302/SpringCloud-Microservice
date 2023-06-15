package com.ttvnpay.service1.controller;


import com.ttvnpay.service1.feignclients.Service2FeignClient;
import com.ttvnpay.service1.model.ObjectList;
import com.ttvnpay.service1.model.request.InsertBOUserRq;
import com.ttvnpay.service1.model.request.UpdateBOUserRq;
import jdk.jfr.internal.tool.Main;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/service1")
public class TestController {
    @Autowired
    private RestTemplate template;

    @Autowired
    private Environment env;

    @Autowired
    Service2FeignClient service2FeignClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @RequestMapping("/")
    public String home() {
        LOGGER.info("Hello from MS1 Service running at port: " + env.getProperty("local.server.port"));
        return "Hello from MS1 Service running at port: " + env.getProperty("local.server.port");
    }

    @RequestMapping("/{id}")
    public ObjectList getObj(@PathVariable final int id) {
        // create gallery object
        ObjectList objectList = new ObjectList();
        objectList.setId(id);

        // get list of available objects
        List<Object> objects = template.getForObject("http://service-2/api/service2/obj/", List.class);
        objectList.setObjectList(objects);
        LOGGER.info("Get object from Service2: " + objects);
        return objectList;
    }

    @GetMapping("/feign/{id}")
    public String getObjects(@PathVariable final int id){
        ObjectList objectList = new ObjectList();
        objectList.setId(id);

        List<Object> objects = service2FeignClient.getProducts();
        objectList.setObjectList(objects);
        return objectList + " via feign client";

    }

    @GetMapping("/feign/getUserBranches")
    public List<Object> getgetUserBranches(){
        return service2FeignClient.getBOUserBranches();
    }

    @PostMapping("/feign/insertBOUser")
    public String insertBOUser(@RequestBody InsertBOUserRq insertBOUserRq){
        return service2FeignClient.insertBOUser(insertBOUserRq);
    }

    @PutMapping ("/feign/updateBOUser")
    public String updateBOUser(@RequestBody UpdateBOUserRq updateBOUserRq){
        return service2FeignClient.updateBOUser(updateBOUserRq);
    }
}
