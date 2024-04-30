package com.example.productservices.Services;

import com.example.productservices.DTO.FakeStoreDto;
import com.example.productservices.Models.Category;
import com.example.productservices.Models.Product;
import com.example.productservices.Repositories.CategoryRepository;
import com.example.productservices.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<FakeStoreDto> getAllProducts(String limit, String sort) {
        return null;
    }

    @Override
    public FakeStoreDto getSingleProduct(Long id) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new Exception("Product not found");
        }
        return ProductToFakeStoreDto(optionalProduct.get());
    }

    @Override
    public FakeStoreDto createProduct(FakeStoreDto product) {
        return null;
    }

    @Override
    public void updateProduct(Long id, FakeStoreDto product) {

    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public String[] getAllCategory() {
        return new String[0];
    }

    @Override
    public List<FakeStoreDto> getProductOfCategory(String productCategory) {
        return null;
    }

    @Override
    public Product upsertProduct(Product product) {
        Category category = product.getCategory();
         if(category.getId() == null) {
             category = categoryRepository.save(category);
         }
         else {
             category = categoryRepository.findById(category.getId()).get();
         }
        product.setCategory(category);
        return productRepository.save(product);
    }

    public FakeStoreDto ProductToFakeStoreDto(Product product) {
        FakeStoreDto fakeStoreDto = new FakeStoreDto();

        fakeStoreDto.setId(product.getId());
        fakeStoreDto.setCategory(product.getCategory().getName());
        fakeStoreDto.setDescription(product.getDescription());
        fakeStoreDto.setTitle(product.getTitle());
        fakeStoreDto.setPrice(product.getPrice());

        return fakeStoreDto;
    }
}
