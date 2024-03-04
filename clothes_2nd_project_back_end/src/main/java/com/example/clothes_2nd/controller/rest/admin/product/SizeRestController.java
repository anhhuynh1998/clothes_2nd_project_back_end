package com.example.clothes_2nd.controller.rest.admin.product;

import com.example.clothes_2nd.model.emun.Size;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/sizes")
@AllArgsConstructor
public class SizeRestController {
    @GetMapping
    public Size[] getEnums() {
        Size[] sizes = Size.values();
        return sizes;
    }

}
