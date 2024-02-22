package com.e_commerce.controller;

import com.e_commerce.dto.ProductDto;
import com.e_commerce.dto.ResponseDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("get-product-by-id")
    public ResponseEntity<?> getProductDetailsById (@RequestParam Integer productId) {

        try {
            ProductDto productDto = productService.getProductDetailsByProductId(productId);
            return  new ResponseEntity<>(new ResponseDto<>(productDto, true), HttpStatus.OK);
        } catch (ResultQueryException ex) {
            return  new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-product")
    public ResponseEntity<?> getAllProducts () {
        List<ProductDto> productDtoList = productService.findAllProduct();
        return  new ResponseEntity<>(new ResponseDto<>(productDtoList, true), HttpStatus.OK);
    }
}
