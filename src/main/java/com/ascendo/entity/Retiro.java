package com.ascendo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/*
 * Clase de tipo entidad Retiro
 */
@Entity
@Table(name = "retiro")
public class Retiro {

	//Valor autoincrementable identificador de la entidad
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer idRetiro;

	//Atributo donde se guarda el valor que se desea retirar
	@Column(name = "valor", nullable = false)
	@Min(value = 10000, message = "El valor debe ser mayor o igual a $10000")
	@Max(value = 600000, message = "El valor debe ser menor o igual a $600000")
	private Long valor;

	//Getters & setters de los diferentes atributos
	public Integer getIdRetiro() {
		return idRetiro;
	}

	public void setIdRetiro(Integer idRetiro) {
		this.idRetiro = idRetiro;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}	
	
}
