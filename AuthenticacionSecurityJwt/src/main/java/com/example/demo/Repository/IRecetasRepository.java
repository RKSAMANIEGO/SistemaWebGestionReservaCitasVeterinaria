package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CRecetas;

public interface IRecetasRepository extends JpaRepository<CRecetas, Long> {

}
