package ch.lucbu.m151.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.lucbu.m151.webshop.model.dto.ProductDto;
import ch.lucbu.m151.webshop.service.ProductService;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
  @Autowired
  ProductService productService;

  @PostMapping("/create")
  @ResponseStatus(code = HttpStatus.OK)
  public Long create(@RequestBody ProductDto productDto) throws Exception {
    return productService.create(productDto);
  }

  @GetMapping("/list")
  public List<ProductDto> list() throws Exception {
    return productService.getAll();
  }

  @GetMapping("/get/{number}")
  public ProductDto get(@PathVariable Long number) throws Exception {
    return productService.getByNumber(number);
  }

  @PatchMapping("/update/{number}")
  @ResponseStatus(code = HttpStatus.OK)
  public void create(@PathVariable Long number, @RequestBody ProductDto productDto) throws Exception {
    productService.update(number, productDto);
  }

  @DeleteMapping("/delete/{number}")
  @ResponseStatus(code = HttpStatus.OK)
  public void delete(@PathVariable Long number) throws Exception {
    productService.delete(number);
  }
}
