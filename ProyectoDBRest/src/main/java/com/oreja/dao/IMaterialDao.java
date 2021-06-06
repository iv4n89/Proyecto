package com.oreja.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.oreja.entities.Material;

@RepositoryRestResource(path = "api", collectionResourceRel = "materiales")
@Repository
public interface IMaterialDao extends JpaRepository<Material, Integer>{
    Page<Material> findAllByResistenciaGreaterThan (double resistencia, Pageable pageable);
    Page<Material> findAllByResistenciaGreaterThanEqual (double resistencia, Pageable pageable);
    Page<Material> findAllByResistenciaLessThanEqual (double resistencia, Pageable pageable);
    Page<Material> findAllByResistenciaLessThan (double resistencia, Pageable pageable);
}
