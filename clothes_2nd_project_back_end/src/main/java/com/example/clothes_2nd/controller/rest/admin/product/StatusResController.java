package com.example.clothes_2nd.controller.rest.admin.product;


import com.example.clothes_2nd.model.emun.Status;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/status")
@AllArgsConstructor
public class StatusResController {
    @GetMapping
    public Status[] getEnums() {
        Status[] statuses = Status.values();
        return statuses;
    }

}
