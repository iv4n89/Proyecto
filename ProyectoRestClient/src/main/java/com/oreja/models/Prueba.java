package com.oreja.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Prueba implements Serializable{
    private static final long serialVersionUID = 1L;
    @Expose
    int id;
    @Expose
    @DateTimeFormat(pattern =  "yyyy-MM-dd")
    private Date fecha;
    @Expose
    private String comentario;
    @Expose
    private Material material;   
}
