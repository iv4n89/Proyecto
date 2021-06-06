package com.oreja.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oreja.dao.IMaterialRepository;
import com.oreja.models.Material;
import com.oreja.models.Prueba;

@Service
public class MaterialServiceImpl implements IMaterialService{

    @Autowired
    private IMaterialRepository repository;
    
    @Override
    public void saveMaterial(Material material) {
	repository.saveMaterial(material);
    }

    @Override
    public void savePrueba(Prueba prueba, int codigoMaterial) {
	repository.addPrueba(prueba, codigoMaterial);
    }

    @Override
    public Material getMaterial(int codigo) {
	return repository.getMaterial(codigo);
    }

    @Override
    public Prueba getPrueba(int id) {
	return repository.getPruebaById(id);
    }

    @Override
    public Page<Material> getPageOfMaterial(int page, String sort) {
	return repository.getPageOfMaterial(page, sort);
    }

    @Override
    public Page<Material> getPageOfMaterialByResistenciaLessThan(int page, double resistencia) {
	return repository.getPageOfMaterialByResistenciaLessThan(page, resistencia);
    }

    @Override
    public Page<Prueba> getPageOfPruebaForMaterial(int page, int codigoMaterial) {
	List<Prueba> pruebas = repository.getMaterial(codigoMaterial).getPruebas().stream().sorted(Comparator.comparing(Prueba::getFecha)).collect(Collectors.toList());
	Pageable pageable = PageRequest.of(page, 3);
	final int start = (int) pageable.getOffset();
	final int end = Math.min((start + pageable.getPageSize()), pruebas.size());
	return new PageImpl<>(pruebas.subList(start, end), pageable, pruebas.size());
    }
    
   @Override
    public void updateMaterial(Material material) {
        repository.updateMaterial(material);
    }

    @Override
    public void deleteMaterial(int codigo) {
	repository.deleteMaterial(codigo);
    }

    @Override
    public void deletePrueba(Prueba prueba) {
	repository.deletePrueba(prueba);
    }

}
