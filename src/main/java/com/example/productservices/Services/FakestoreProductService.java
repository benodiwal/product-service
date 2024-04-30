package com.example.productservices.Services;

import com.example.productservices.DTO.FakeStoreDto;
import com.example.productservices.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakestoreProductService implements ProductService{

    RestTemplate restTemplate = new RestTemplate();
    private final String url = "https://fakestoreapi.com/products";
    @Override
    public List<FakeStoreDto> getAllProducts(String limit, String sort) throws Exception {
        String urlCopy = url;
        System.out.println(limit);

        urlCopy += "?sort="+sort;
        if(!limit.equals("-1"))
            urlCopy += "&limit="+limit;

        FakeStoreDto[] response = restTemplate.getForObject(
                urlCopy,
                FakeStoreDto[].class
        );
        if(response != null){
            return Arrays.asList(response);
        }
        else throw new Exception("No products received");
    }

    @Override
    public FakeStoreDto getSingleProduct(Long id) {
        return restTemplate.getForObject(
                url+"/"+id,
                FakeStoreDto.class
        );
    }

    @Override
    public FakeStoreDto createProduct(FakeStoreDto product) {

        return restTemplate.postForObject(
                url,
                product,
                FakeStoreDto.class
        );
    }

    @Override
    public void updateProduct(Long id, FakeStoreDto product) {
        restTemplate.put(
                url+"/"+id,
                product
        );
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete(
                url+"/"+id
        );
    }

    @Override
    public String[] getAllCategory(){
        return restTemplate.getForObject(
                url+"/categories",
                String[].class
        );
    }

    @Override
    public List<FakeStoreDto> getProductOfCategory(String productCategory) {
        FakeStoreDto[] response = restTemplate.getForObject(
                url+"/category/"+productCategory,
                FakeStoreDto[].class
        );
        return Arrays.asList(response);
    }

    @Override
    public Product upsertProduct(Product product) {
        return null;
    }
}
