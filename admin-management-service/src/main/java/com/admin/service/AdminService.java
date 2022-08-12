package com.admin.service;

import java.util.List;
import java.util.Optional;

import com.admin.pojo.Admin;

public interface AdminService {
	
	public Admin doLogin(String userName, String password);

	public Admin saveAdmin(Admin admin);

	public List<Admin> getAllAdmin();

	public Admin getAdminById(int adminId);

	public Admin updateAdmin(Admin admin);

	public void deleteAdminById(int adminId);
	
	public Optional<Admin> getAllAdminByUserName(String userName);
	
	public Optional<Admin> getAllAdminByPhone(long phone);
	
	public List<Admin> getAllAdminByLocation(String location);

}
