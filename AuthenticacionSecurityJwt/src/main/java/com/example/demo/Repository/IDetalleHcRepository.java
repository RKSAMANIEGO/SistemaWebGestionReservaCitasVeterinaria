package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CDetalleHistoriaClinica;

public interface IDetalleHcRepository extends JpaRepository<CDetalleHistoriaClinica,Long>{

}
