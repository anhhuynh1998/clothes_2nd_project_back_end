package com.example.clothes_2nd.controller.rest.admin.product;
import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.service.admin.product.request.ProductSaveRequest;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.service.admin.product.ProductService;
import com.example.clothes_2nd.service.home.productHome.ProductHomeService;
import com.example.clothes_2nd.service.home.productHome.response.ProductOfHomeListResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/products")
public class ProductRestController {
    private ProductHomeService productHomeService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "") String search,Pageable pageable
                                            ) {
        Page<ProductListResponse>  productListResponses =  productService.findAllWithSearchEveryThingAndPaging(search,pageable);
        return new ResponseEntity<>(productListResponses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ProductListResponse findAllProductById(@PathVariable Long id) {
        return  productService.findProductById(id);

    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductSaveRequest request) {
        ProductListResponse productListResponse = productService.createProducts(request);

        return new ResponseEntity<>(productListResponse, HttpStatus.CREATED);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductSaveRequest request, @PathVariable Long id) {
        ProductListResponse productListResponse =   productService.updateProduct(request,id);
        return new ResponseEntity<>(productListResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<?> count(){
        return new ResponseEntity<>(productHomeService.countProduct(), HttpStatus.OK);
    }
    @GetMapping("/add")
    public ResponseEntity<?> findProductByCode(@RequestParam(defaultValue = "") String search){
        return productService.findProductByCodeProduct(search);
    }
    @GetMapping("/checkOut")
    public BigDecimal checkOutProduct(){
        return productService.checkOut();
    }

}
