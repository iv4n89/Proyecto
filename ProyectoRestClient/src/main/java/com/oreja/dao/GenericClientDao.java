package com.oreja.dao;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.oreja.util.RestPageImpl;

import reactor.core.publisher.Mono;

@Component
public interface  GenericClientDao<T> {
    
    String getSingleObjectPath();
    String getPageObjectPath(int page, String sort);
    String getDeleteObjectPath();
    
    Class<T> getGenericClass();
    
    WebClient webClientBuilder();

    default T getObjectById(int id) {
	Mono<T> response = webClientBuilder().get()
		.uri(getSingleObjectPath() + id)
		.retrieve()
		.bodyToMono(getGenericClass());
	return response.block();
    }
    
    default Page<T> getPage(int page, String sort) {
	Mono<RestPageImpl<T>> response = webClientBuilder().get()
		.uri(getPageObjectPath(page, sort)) //
		.retrieve()
		.bodyToMono(new ParameterizedTypeReference<RestPageImpl<T>>() {});
	return  response.block();
    }
    
    default T addObject(T object) {
	Mono<T> created =  webClientBuilder().post()
		.body(Mono.just(object), getGenericClass())
		.retrieve()
		.bodyToMono(getGenericClass());
	return created.block();
    }
    
    default T updateObject(T object) {
	Mono<T> updated = webClientBuilder().put()
		.body(Mono.just(object), getGenericClass())
		.retrieve()
		.bodyToMono(getGenericClass());
	return updated.block();
    }
    
    default Void deleteObject(int id) {
	Mono<Void> response = webClientBuilder().delete()
		.uri(getDeleteObjectPath() + id)
		.retrieve()
		.bodyToMono(Void.class);
	return response.block();
    }
    
}

