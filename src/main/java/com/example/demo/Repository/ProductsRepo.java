package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

@Repository
public class ProductsRepo {
    private List<Product> productsList = new ArrayList<>();
    public ProductsRepo(){
        productsList.add(new Product("1", "Apple", "Apple Inc.", "Fruit", 1.0, 100));
        productsList.add(new Product("2", "Banana", "Banana Inc.", "Fruit", 2.0, 100));
        productsList.add(new Product("3", "Orange", "Orange Inc.", "Fruit", 3.0, 100));
        productsList.add(new Product("4", "Mango", "Mango Inc.", "Fruit", 4.0, 100));
        productsList.add(new Product("5", "Pineapple", "Pineapple Inc.", "Fruit", 5.0, 100));
        productsList.add(new Product("6", "Strawberry", "Strawberry Inc.", "Fruit", 6.0, 100));
        productsList.add(new Product("7", "Blueberry", "Blueberry Inc.", "Fruit", 7.0, 100));
        productsList.add(new Product("8", "Raspberry", "Raspberry Inc.", "Fruit", 8.0, 100));
        productsList.add(new Product("9", "Blackberry", "Blackberry Inc.", "Fruit", 9.0, 100));
        productsList.add(new Product("10", "Watermelon", "Watermelon Inc.", "Fruit", 10.0, 100));
        productsList.add(new Product("11", "Grapes", "Grapes Inc.", "Fruit", 11.0, 100));
        productsList.add(new Product("12", "Cherry", "Cherry Inc.", "Fruit", 12.0, 100));
        productsList.add(new Product("13", "Kiwi", "Kiwi Inc.", "Fruit", 13.0, 100));
        productsList.add(new Product("14", "Peach", "Peach Inc.", "Fruit", 14.0, 100));
        productsList.add(new Product("15", "Pear", "Pear Inc.", "Fruit", 15.0, 100));
        productsList.add(new Product("16", "Plum", "Plum Inc.", "Fruit", 16.0, 100));
        productsList.add(new Product("17", "Avocado", "Avocado Inc.", "Fruit", 17.0, 100));
    }
    public List<Product> getProductsList(){
        return productsList;
    }
    public int getProductsQuantity(String serialnumber){
        for(Product product : productsList){
            if(product.getSerialNumber().equals(serialnumber)){
              return product.getRemainingQuantity();
            }
          }
          return -1;
      }
}
