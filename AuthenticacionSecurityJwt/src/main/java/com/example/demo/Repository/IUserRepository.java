package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.CUser;

@Repository
public interface IUserRepository extends JpaRepository<CUser, Long> {

	Optional<CUser> findByEmail(String email);

	Optional<CUser> findByUsername(String username);

}
