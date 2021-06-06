package com.oreja.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
@Entity
@Table(name ="material")
public class Material implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private Integer codigo;
    private String nombre;
    private Double resistencia;
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Material other = (Material) obj;
	if (codigo != other.codigo)
	    return false;
	return true;
    }
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + codigo;
	return result;
    }
    
    
}
