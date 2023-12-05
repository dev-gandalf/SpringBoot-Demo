package com.rakshith.project.SpringBoot.controller;




import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rakshith.project.SpringBoot.model.Product;

@RestController
public class Controller {

	@GetMapping("/product/list")
	public Product[] index() {
		
		RestTemplate restTemplate = new RestTemplate();
		Product[] productList = restTemplate.getForObject("http://localhost:8000/product/list", Product[].class);
		return productList;
	}
	
	
	@PostMapping("/product/create")
	  public String postProduct(@RequestBody Product product) {
		
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Product> request = new HttpEntity<>(product);
		String response = restTemplate.postForObject("http://localhost:8000/product/create", request, String.class);
		
	    return response;
	  }
	
	
	@DeleteMapping("/product/delete/{productName}")
	public String deleteProduct(@PathVariable String productName){
		RestTemplate restTemplate = new RestTemplate();
		
		String deleteURL = "http://localhost:8000/product/delete" + "/" + productName;
		ResponseEntity<String> response = restTemplate.exchange(deleteURL, HttpMethod.DELETE, null, String.class);
		return response.getBody();
	};
	
}
