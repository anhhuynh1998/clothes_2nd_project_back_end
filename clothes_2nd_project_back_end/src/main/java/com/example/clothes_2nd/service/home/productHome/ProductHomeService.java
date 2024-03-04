package com.example.clothes_2nd.service.home.productHome;
import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.service.home.productHome.request.ProductFilterRequest;
import com.example.clothes_2nd.service.home.productHome.response.ProductDetailHomeResponse;
import com.example.clothes_2nd.service.home.productHome.response.ProductOfHomeListResponse;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class ProductHomeService {
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    public Page<ProductOfHomeListResponse> findAll(Pageable pageable) {
        return productRepository.findAllProduct(pageable)
                .map(product -> {
                        var result = AppUtil.mapper.map(product, ProductOfHomeListResponse.class);
                        result.setImageUrl(product.getFiles().size() > 0 ?
                        product.getFiles().get(0).getUrl() : "");
                    result.setStatus(product.getStatus().name);
                    return result;
               });
    }

    public Optional<ProductDetailHomeResponse> productDetail(Long id) {
        return productRepository.findById(id)
                .map(e -> {
                    var result = AppUtil.mapper.map(e, ProductDetailHomeResponse.class);
                    result.setListFile(e.getFiles().stream().map(File::getUrl).toList());
                    result.setStatus(e.getStatus().name);
                    return result;
                });
    }

    public Page<ProductOfHomeListResponse> filter(Pageable pageable, ProductFilterRequest request) {
        if (Strings.isNotBlank(request.getSize())) {
            request.setSizes(Arrays.stream(request.getSize().split(","))
                    .map(Size::valueOf).collect(Collectors.toList()));
        }
        return productRepository.filterProduct(request, pageable)
                .map(e -> {
                    var result = AppUtil.mapper.map(e, ProductOfHomeListResponse.class);
                    result.setImageUrl(e.getFiles().size() > 0 ?
                            e.getFiles().get(0).getUrl() : "");
                    result.setStatus(e.getStatus().name);
                    return result;
                });
    }

    public Long countProduct(){
        return productRepository.countProduct();
    }
}