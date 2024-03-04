package com.example.clothes_2nd.service.admin.product.size;

import com.example.clothes_2nd.model.emun.Size;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class SizeService {
    public List<Size> getAllSizeEnum() {
        return Arrays.asList(Size.values());
    }
}
