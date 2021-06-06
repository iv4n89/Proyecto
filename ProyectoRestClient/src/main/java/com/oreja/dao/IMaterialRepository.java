package com.oreja.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.oreja.models.Material;
import com.oreja.models.Prueba;

public interface IMaterialRepository {

    void saveMaterial(Material material);

    Material getMaterial(int codigo);

    Page<Material> getPageOfMaterial(int page, String sort);
    
    void updateMaterial(Material material);

    void deleteMaterial(int codigo);

    void addPrueba(Prueba prueba, int codigo);

    void deletePrueba(Prueba prueba);

    Prueba getPruebaById(int id);

    Page<Material> getPageOfMaterialByResistenciaLessThan(int page, double resistencia);

}