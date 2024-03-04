package com.example.clothes_2nd.service.admin.revenue;

import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.service.admin.revenue.response.RevenueResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
@Transactional
public class RevenueService {
    private final  CartRepository cartRepository;
    public List<RevenueResponse> calculateProductRevenue(LocalDate start, LocalDate end){
        return cartRepository.calculateRevenue(start, end);
    }
}
