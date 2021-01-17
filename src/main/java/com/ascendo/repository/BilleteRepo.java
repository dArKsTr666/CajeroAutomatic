package com.ascendo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ascendo.entity.Billete;

/*
 * Clase que permite la comunicación con la tabla billete en base de datos
 */
@Repository
public interface BilleteRepo extends JpaRepository<Billete, Integer>{

}
