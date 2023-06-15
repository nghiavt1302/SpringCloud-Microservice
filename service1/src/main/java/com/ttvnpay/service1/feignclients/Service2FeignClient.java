package com.ttvnpay.service1.feignclients;

import com.ttvnpay.service1.model.request.InsertBOUserRq;
import com.ttvnpay.service1.model.request.UpdateBOUserRq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "service-2", path = "api/service2")
public interface Service2FeignClient {

    @GetMapping("/obj")
    public List<Object> getProducts();

    @GetMapping("/getUserBranches")
    public List<Object> getBOUserBranches();

    @PostMapping("/insertBOUser")
    public String insertBOUser(@RequestBody InsertBOUserRq insertBOUserRq);

    @PutMapping("/updateBOUser")
    public String updateBOUser(@RequestBody UpdateBOUserRq updateBOUserRq);
}
