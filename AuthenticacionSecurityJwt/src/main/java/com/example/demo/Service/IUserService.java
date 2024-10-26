package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CUser;

public interface IUserService {

	List<CUser> listAllUsers();

	CUser createUser(CUser u);
	
	CUser updateUser(CUser u,Long id);

	boolean deleteUser(Long id);

	CUser listFindByUsername(String username);
}
