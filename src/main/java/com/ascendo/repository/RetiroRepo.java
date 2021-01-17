package com.ascendo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ascendo.entity.Retiro;

/*
 * Clase que permite la comunicación con la tabla retiro en base de datos
 */
@Repository
public interface RetiroRepo extends JpaRepository<Retiro, Integer>{

}
