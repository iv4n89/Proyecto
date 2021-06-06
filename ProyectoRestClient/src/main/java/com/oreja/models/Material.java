package com.oreja.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material implements Serializable{

    private static final long serialVersionUID = 1L;
    @Expose
    private Integer codigo;
    private String nombre;
    private Double resistencia;
    @JsonIgnore
    private transient List<Prueba> pruebas = new ArrayList<>();
}
