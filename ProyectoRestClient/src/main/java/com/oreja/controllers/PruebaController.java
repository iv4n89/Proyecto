package com.oreja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.oreja.models.Material;
import com.oreja.models.Prueba;
import com.oreja.service.IMaterialService;
import com.oreja.util.RenderizadorPaginas;

@Controller
public class PruebaController {
    
    @Autowired
    private IMaterialService service;

    @GetMapping("/pruebas/{codigo}")
    public String pruebas(@RequestParam(name = "page", defaultValue = "0")int page,@PathVariable(name = "codigo")int codigo, Material material, Prueba prueba, Model model) {
	material = service.getMaterial(codigo);
	Page<Prueba> pruebas = service.getPageOfPruebaForMaterial(page, codigo);
	RenderizadorPaginas<Prueba> renderizadorPaginas = new RenderizadorPaginas<Prueba>("/pruebas/"+codigo, pruebas);
	model.addAttribute("page", renderizadorPaginas);
	model.addAttribute("material", material);
	return "pruebas";
    }
    
    @PostMapping("/addPrueba/{codigo}")
    public String addPrueba(Material material, Prueba prueba, @RequestParam(name ="page", defaultValue = "0")int page) {
	material = service.getMaterial(material.getCodigo());
	service.savePrueba(prueba, material.getCodigo());
	return "redirect:/pruebas/" + material.getCodigo() + "?page=" + page;
    }
    
    @GetMapping("/deletePrueba/{codigo}/{pagActual}/{id}")
    public String deletePrueba(Material material, Prueba prueba,@PathVariable(name = "pagActual")int page, @PathVariable(name = "codigo")int codigo, @PathVariable("id")int id) {
	prueba = service.getPrueba(id);
	service.deletePrueba(prueba);
	material = service.getMaterial(codigo);
	return "redirect:/pruebas/"+material.getCodigo()+"?page="+page;
    }
    
}
