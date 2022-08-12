package com.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.admin.pojo.Admin;

@SpringBootTest
public class AdminRepositoryTest {
	
	@Mock
	private AdminRepository adminRepository;
	
	@Test
	public void testSaveAdmin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("srinivas");
		admin.setLastName("v");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("srinivas");
		admin.setEmail("srinivas@mail.com");
		admin.setPassword("12345");
		
		when(adminRepository.save(admin)).thenReturn(admin);
		assertEquals("srinivas", admin.getUserName());
	}
	
	@Test
	public void testFindAllAdmin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("srinivas");
		admin.setLastName("v");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("srinivas");
		admin.setEmail("srinivas@mail.com");
		admin.setPassword("12345");
		
		Admin admin1 = new Admin();
		admin1.setAdminId(11);
		admin1.setFirstName("praveen");
		admin1.setLastName("j");
		admin1.setPhone(454545);
		admin1.setLocation("chennai");
		admin1.setUserName("praveen");
		admin1.setEmail("praveen@mail.com");
		admin1.setPassword("454545");
		
		Admin admin2 = new Admin();
		admin2.setAdminId(12);
		admin2.setFirstName("pradeep");
		admin2.setLastName("j");
		admin2.setPhone(191919);
		admin2.setLocation("hyderabad");
		admin2.setUserName("pradeep");
		admin2.setEmail("pradeep@mail.com");
		admin2.setPassword("191919");
		
		List<Admin> allAdmins = new ArrayList<>();
		allAdmins.add(admin);
		allAdmins.add(admin1);
		allAdmins.add(admin2);
		
		when(adminRepository.findAll()).thenReturn(allAdmins);
		assertEquals(3, allAdmins.size());
	}
	
	@Test
	public void testFindAdminById() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("srinivas");
		admin.setLastName("v");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("srinivas");
		admin.setEmail("srinivas@mail.com");
		admin.setPassword("12345");
		
		Optional<Admin> optionalAdmin = Optional.of(admin);
		when(adminRepository.findById(10)).thenReturn(optionalAdmin);
		assertEquals("srinivas", admin.getUserName());
	}
	
	@Test
	public void testLogin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("srinivas");
		admin.setLastName("v");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("srinivas");
		admin.setEmail("srinivas@mail.com");
		admin.setPassword("12345");
		
		when(adminRepository.login("srinivas", "12345")).thenReturn(admin);
		assertEquals("srinivas", admin.getUserName());
	}

}
