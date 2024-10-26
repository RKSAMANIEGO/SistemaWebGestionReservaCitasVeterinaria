package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CPermission;


public interface IPermissionRepository extends JpaRepository<CPermission, Long>{

}
