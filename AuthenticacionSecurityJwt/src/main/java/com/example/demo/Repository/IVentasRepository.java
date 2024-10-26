package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CVentas;

public interface IVentasRepository extends JpaRepository<CVentas, Long>{

}
