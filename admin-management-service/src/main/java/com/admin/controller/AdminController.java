package com.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.LoginRequest;
import com.admin.dto.LoginResponse;
import com.admin.pojo.Admin;
import com.admin.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin/add")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {

		Admin newAdmin = adminService.savePassenger(admin);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<>(newAdmin, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/admin/all")
	public List<Admin> fetchAllAdmin() {

		List<Admin> allAdmin = adminService.getAllAdmin();
		return allAdmin;
	}
	
	@GetMapping("/admin/find/{adminId}")
	public ResponseEntity<Admin> fetchById(@PathVariable("adminId") int adminId) {

		ResponseEntity<Admin> reponseEntity = null;
		Admin admin = adminService.getAdminById(adminId);
		reponseEntity = new ResponseEntity<>(admin, HttpStatus.OK);
		return reponseEntity;
	}
	
	@PostMapping("/admin/login")
	public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest) {

		Admin admin = adminService.doLogin(loginRequest.getUserName(), loginRequest.getPassword());

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setAdminId(admin.getAdminId());
		loginResponse.setFirstName(admin.getFirstName());
		loginResponse.setLastName(admin.getLastName());
		loginResponse.setPhone(admin.getPhone());
		loginResponse.setUserName(admin.getUserName());
		loginResponse.setEmail(admin.getEmail());
		ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/admin/update")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {

		Admin updatedAdmin = adminService.updateAdmin(admin);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/admin/delete/{adminId}")
	public ResponseEntity<String> deletePassengerById(@PathVariable("adminId") int adminId) {

		adminService.deleteAdminById(adminId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/admin/bylocation/{location}")
	public List<Admin> fetchAdminbyLocation(@PathVariable("location") String location) {

		List<Admin> adminByLocation = adminService.getAllAdminByLocation(location);
		return adminByLocation;
	}
	
	@GetMapping("/admin/byusername/{username}")
	public Optional<Admin> fetchAdminbyUserName(@PathVariable("username") String userName) {

		Optional<Admin> adminByUserName = adminService.getAllAdminByUserName(userName);
		return adminByUserName;
	}

	@GetMapping("/admin/byphone/{phone}")
	public Optional<Admin> fetchAdminbyPhone(@PathVariable("phone") long phone) {

		Optional<Admin> adminByPhone = adminService.getAllAdminByPhone(phone);
		return adminByPhone;
	}

}
