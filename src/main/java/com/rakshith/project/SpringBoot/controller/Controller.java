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


StringBuilder baseUrl = new StringBuilder("http://localhost:8000");
RestTemplate restTemplate = new RestTemplate();

	
	

@GetMapping("/check")
public String test(){
	
return "it works";
}



	@GetMapping("/product/list")
	public Product[] index() {
		
		StringBuilder url = new StringBuilder(baseUrl + "/product/list");
		
		return restTemplate.getForObject(url.toString(), Product[].class);
	}
	
	
	
	@PostMapping("/product/create")
	  public String postProduct(@RequestBody Product product) {
		
		
		StringBuilder url = new StringBuilder(baseUrl + "/product/create");
		HttpEntity<Product> request = new HttpEntity<>(product);
		return restTemplate.postForObject(url.toString(), request, String.class);
		
	    
	  }
	
	
	@DeleteMapping("/product/delete/{productName}")
	public String deleteProduct(@PathVariable String productName){
		
		StringBuilder url = new StringBuilder(baseUrl + "/product/delete/" + productName);
		
		ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.DELETE, null, String.class);
		return response.getBody();
	};
	
}
