package com.example.clothes_2nd.controller.rest.admin.revenue;

import com.example.clothes_2nd.service.admin.revenue.RevenueService;
import com.example.clothes_2nd.service.admin.revenue.response.RevenueResponse;
import com.example.clothes_2nd.service.home.cartHome.CartHomeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/")
public class RevenueRestController {
    private final RevenueService revenueService;
    @GetMapping("/revenue")
    public List<RevenueResponse> revenue(@RequestParam LocalDate start, @RequestParam LocalDate end){
        return revenueService.calculateProductRevenue(start, end);
    }
}
