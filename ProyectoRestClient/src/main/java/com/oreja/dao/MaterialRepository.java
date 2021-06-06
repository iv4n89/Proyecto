package com.oreja.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.oreja.models.Material;
import com.oreja.models.Prueba;

@Repository
public class MaterialRepository implements IMaterialRepository {

    @Autowired
    private IPruebaDao pruebaDao;

    @Autowired
    private GenericClientDao<Material> materialDao; //

    @Override
    public void saveMaterial(Material material) {
	materialDao.addObject(material);
    }

    @Override
    public Material getMaterial(int codigo) {
	Material material = materialDao.getObjectById(codigo);
	List<Prueba> pruebas = pruebaDao.getPruebasByMaterial(codigo);
	material.setPruebas(pruebas);
	return material;
    }

    @Override
    public Page<Material> getPageOfMaterial(int page, String sort) {
	return materialDao.getPage(page, sort);
    }

    @Override
    public Page<Material> getPageOfMaterialByResistenciaLessThan(int page, double resistencia) {
	return ((MaterialDaoImpl) materialDao).getPageByResistenciaLessThan(page, resistencia);
    }

    @Override
    public void deleteMaterial(int codigo) {
	pruebaDao.deleteAllPruebaForMaterial(codigo);
	((PruebaDaoImpl) pruebaDao).saveChanges();
	materialDao.deleteObject(codigo);
    }

    @Override
    public void addPrueba(Prueba prueba, int codigo) {
	Material material = getMaterial(codigo);
	prueba.setMaterial(material);
	pruebaDao.addPrueba(prueba);
	((PruebaDaoImpl) pruebaDao).saveChanges();
    }

    @Override
    public void updateMaterial(Material material) {
	materialDao.updateObject(material);
    }

    @Override
    public Prueba getPruebaById(int id) {
	return pruebaDao.getPrueba(id);
    }

    @Override
    public void deletePrueba(Prueba prueba) {
	pruebaDao.deletePrueba(prueba);
	((PruebaDaoImpl) pruebaDao).saveChanges();
    }

}
