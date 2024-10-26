package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CCitas;

public interface ICitasRepository extends JpaRepository<CCitas, Long> {

}
