package com.oreja.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.oreja.models.Material;
import com.oreja.util.RestPageImpl;

import reactor.core.publisher.Mono;

@Component
public class MaterialDaoImpl implements GenericClientDao<Material>{

    @Autowired
    private WebClient webClient;
    
    @Override
    public Class<Material> getGenericClass() {
	return Material.class;
    }

    @Override
    public WebClient webClientBuilder() {
	return webClient;
    }

    @Override
    public String getSingleObjectPath() {
	return "/";
    }
    
    @Override
    public String getPageObjectPath(int page, String sort) {
	return "/list/" + page + "/" + sort;
    }

    @Override
    public String getDeleteObjectPath() {
	return "/";
    }
    
    public Page<Material> getPageByResistenciaLessThan(int page, double resistencia) {
	Mono<RestPageImpl<Material>> response = webClient.get()
		.uri("/sortResistencia/less/" + resistencia + "?page=" + page)
		.retrieve()
		.bodyToMono(new ParameterizedTypeReference<RestPageImpl<Material>>() {});
	return response.block();
    }

}
