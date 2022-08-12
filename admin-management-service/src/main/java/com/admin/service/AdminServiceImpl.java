package com.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.exception.AuthenticationFailedException;
import com.admin.exception.PhoneNumberAlreadyExistingException;
import com.admin.exception.UserNameAlreadyExistingException;
import com.admin.exception.UserNotFoundException;
import com.admin.pojo.Admin;
import com.admin.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin doLogin(String userName, String password) {

		Admin admin = adminRepository.login(userName, password);
		if (admin == null) {
			throw new AuthenticationFailedException("Username or password are invalid");
		}
		return admin;
	}

	@Override
	public Admin saveAdmin(Admin admin) {

//		To make the userName unique, we declared the exception on line 37 to 40

		Optional<Admin> optionalAdmin = adminRepository.findByUserName(admin.getUserName());
		if (optionalAdmin.isPresent()) {
			throw new UserNameAlreadyExistingException("Username already exists");
		}

		Optional<Admin> adminByPhone = adminRepository.findByPhone(admin.getPhone());
		if (adminByPhone.isPresent()) {
			throw new PhoneNumberAlreadyExistingException("Phone number already exists " + admin.getPhone());
		}
		Admin newAdmin = adminRepository.save(admin);
		return newAdmin;

	}

	@Override
	public List<Admin> getAllAdmin() {

		List<Admin> allAdmin = adminRepository.findAll();
		return allAdmin;
	}

	@Override
	public Admin getAdminById(int adminId) {

		Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
		if (optionalAdmin.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + adminId);
		}
		Admin adminById = optionalAdmin.get();
		return adminById;

	}

	@Override
	public Admin updateAdmin(Admin admin) {

		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getAdminId());
		if (optionalAdmin.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + admin.getAdminId());
		}
		Admin updatedAdmin = adminRepository.save(admin);
		return updatedAdmin;

	}

	@Override
	public void deleteAdminById(int adminId) {

		Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
		if (optionalAdmin.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + adminId);
		}

		adminRepository.deleteById(adminId);

	}

	@Override
	public Optional<Admin> getAllAdminByUserName(String userName) {

		Optional<Admin> adminByUsername = adminRepository.findByUserName(userName);
		if (adminByUsername.isEmpty()) {
			throw new UserNotFoundException("No user found with this username : " + userName);
		}
		return adminByUsername;
	}

	@Override
	public Optional<Admin> getAllAdminByPhone(long phone) {

		Optional<Admin> adminByPhone = adminRepository.findByPhone(phone);
		if (adminByPhone.isEmpty()) {
			throw new UserNotFoundException("No user found with this phone number : " + phone);
		}
		return adminByPhone;
	}

	@Override
	public List<Admin> getAllAdminByLocation(String location) {

		List<Admin> adminByLocation = adminRepository.findByLocation(location);
		if (adminByLocation.isEmpty()) {
			throw new UserNotFoundException("No user found with this location : " + location);
		}
		return adminByLocation;

	}

}
