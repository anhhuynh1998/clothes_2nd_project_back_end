package com.example.clothes_2nd.service.admin.product;

import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.repository.CategoryRepository;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.repository.UserInfoRepository;
import com.example.clothes_2nd.service.admin.cart.response.CartAdminResponse;
import com.example.clothes_2nd.service.admin.product.request.ProductSaveRequest;
import com.example.clothes_2nd.service.admin.product.request.SelectOptionRequest;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.service.admin.user.UserInfoService;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FileRepository fileRepository;
    private final CategoryRepository categoryRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserInfoService userInfoService;


    public Page<ProductListResponse> findAllWithSearchEveryThingAndPaging(String search, Pageable pageable) {
        search = "%" + search + "%";
        return productRepository
                .searchEverything(search, pageable)
                .map(product -> {
                    var response = AppUtil.mapper.map(product, ProductListResponse.class);
                    response.setCategory(product.getCategory().getName());
                    if (product.getUserInfo() != null){

                        response.setFullName(product.getUserInfo().getFullName());
                    }
                    return response;
                });
    }

    /// chỉ render ra những product có paid == false còn lại thì không
    public ProductListResponse findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductListResponse productListResponse = AppUtil.mapper.map(product, ProductListResponse.class);
            productListResponse.setSize(product.getSize());
            productListResponse.setPhone(product.getUserInfo() != null ? product.getUserInfo().getPhone() : null);
            productListResponse.setFullName(product.getUserInfo() != null ? product.getUserInfo().getFullName() : null);



            Category productCategory = product.getCategory();

            if (productCategory != null) {
                productListResponse.setCategory(String.valueOf(productCategory.getId()));

                Category parentCategory = productCategory.getCategoryParent();
                if (parentCategory != null) {
                    productListResponse.setCategoryParentId(parentCategory.getId());

                    Category granParentCategory = parentCategory.getCategoryParent();
                    if (granParentCategory != null) {
                        productListResponse.setCategoryGranParentId(granParentCategory.getId());
                    }
                }
            }

            List<SelectOptionRequest> fileSelectOptions = product.getFiles().stream()
                    .map(file -> {
                        SelectOptionRequest selectOption = new SelectOptionRequest();
                        selectOption.setId(Long.valueOf(file.getId()));
                        selectOption.setUrl(file.getUrl());
                        return selectOption;
                    })
                    .collect(Collectors.toList());

            productListResponse.setFiles(fileSelectOptions);

            return productListResponse;
        } else {
            return null;
        }
    }

    public ProductListResponse createProducts(ProductSaveRequest request) {
        var newProduct = AppUtil.mapper.map(request, Product.class);
        if (!request.getPhone().isBlank()) {
            Optional<UserInfo> userInfoOptional = userInfoRepository.findByPhone(request.getPhone());

            if (userInfoOptional.isEmpty()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setPhone(request.getPhone());
                userInfo.setFullName(request.getFullName());
                userInfoRepository.save(userInfo);
                newProduct.setUserInfo(userInfo);
            } else {
                UserInfo userInfo = userInfoOptional.get();
                newProduct.setUserInfo(userInfo);
            }
        }
        Long categoryId = request.getCategory().getId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        newProduct.setFiles(null);
        newProduct.setCategory(category);
        newProduct.setPaid(false);
        newProduct.setActive(false);
        newProduct.setDepositDate(LocalDateTime.now());
        // Tạo mã sản phẩm từ chữ cái đầu của category và ngày tháng năm
        String productCode = generateProductCode(category, newProduct.getDepositDate());
        newProduct.setCodeProduct(productCode); // Gán mã sản phẩm vào trường codeProduct

        productRepository.save(newProduct);
        var images = fileRepository.findAllById(request.getFiles().stream().map(e -> Long.valueOf(e.getId())).collect(Collectors.toList()));

        for (var image : images) {
            image.setProduct(newProduct);
        }

        fileRepository.saveAll(images);
        ProductListResponse productListResponse = AppUtil.mapper.map(newProduct, ProductListResponse.class);
        productListResponse.setCategory(newProduct.getCategory().getName());
        if (!request.getPhone().isBlank()) {
            productListResponse.setFullName(request.getFullName());
        }

        return productListResponse;
    }

    // Phương thức tạo mã sản phẩm từ chữ cái đầu của category và ngày tháng năm
    private String generateProductCode(Category category, LocalDateTime depositDate) {
        String categoryInitial = getCategoryInitial(category.getName());
        String datePart = formatDateTime(depositDate);
        return categoryInitial + datePart;
    }


    // Phương thức định dạng ngày tháng năm giờ phút giây

    // Phương thức định dạng ngày tháng năm giờ phút giây (và mili giây)

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return Normalizer.normalize(dateTime.format(formatter), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    // Phương thức lấy chữ cái đầu của category (viết thường) mà không lấy dấu
    private String getCategoryInitial(String category) {
        if (category != null && !category.isEmpty()) {
            String normalizedCategory = Normalizer.normalize(category, Normalizer.Form.NFD);
            // Loại bỏ dấu diacritic và chuyển về chữ hoa
            return normalizedCategory.replaceAll("[^\\p{ASCII}]", "").substring(0, 1).toUpperCase();
        } else {
            throw new IllegalArgumentException("Category must not be null or empty");
        }
    }

    public ProductListResponse updateProduct(ProductSaveRequest request, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product ID not valid"));

        List<File> existingImages = fileRepository.findByProductId(id);
        for (File existingImage : existingImages) {
            existingImage.setProduct(null);
        }
        fileRepository.saveAll(existingImages);

        var updatedProduct = AppUtil.mapper.map(request, Product.class);
        Long categoryId = request.getCategory().getId();
        Category category = categoryRepository.findById(categoryId).get();
        updatedProduct.setId(id);
        updatedProduct.setFiles(null);
        updatedProduct.setUserInfo(product.getUserInfo());
        updatedProduct.setCategory(category);
        updatedProduct.setDepositDate(request.getDepositDate());
        updatedProduct.setCodeProduct(request.getCodeProduct());
        productRepository.save(updatedProduct);

        List<File> images = fileRepository.findAllById(request.getFiles().stream().map(e -> Long.valueOf(e.getId())).collect(Collectors.toList()));
        for (File image : images) {
            image.setProduct(updatedProduct);
        }
        fileRepository.saveAll(images);

        ProductListResponse productListResponse = AppUtil.mapper.map(updatedProduct, ProductListResponse.class);

        productListResponse.setCategory(updatedProduct.getCategory().getName());
//        if (!productListResponse.getPhone().isBlank()) {
            productListResponse.setFullName(updatedProduct.getUserInfo().getFullName());
//        }


        return productListResponse;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ResponseEntity<?>  findProductByCodeProduct(String search){
        Product product = productRepository.getProductsByCodeProduct(search);
        if (product.getPaid() == false){
            ProductListResponse response = AppUtil.mapper.map(product, ProductListResponse.class);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sản phẩm đã được bán");
    }

    public BigDecimal checkOut(){
        BigDecimal checkOut = productRepository.checkOutProduct();
       return checkOut;
    }
//moisua vai 3 cai 
}
