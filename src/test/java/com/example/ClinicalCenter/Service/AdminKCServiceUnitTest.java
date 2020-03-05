package com.example.ClinicalCenter.Service;

import com.example.ClinicalCenter.model.AdminKC;
import com.example.ClinicalCenter.repository.AdminKcRepository;
import com.example.ClinicalCenter.service.AdminKcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminKCServiceUnitTest {

    @Autowired
    private AdminKcService adminKcService;

    @MockBean
    private AdminKcRepository adminKcRepository;

    @BeforeEach
    public void setup() {

        AdminKC akc = new AdminKC();
        akc.setIme("Borislav");
        akc.setPrezime("Gajic");
        akc.setLozinka("123456");
        akc.setPrvoPrijavljivanje(false);
        akc.setUsername("BoGa");
        akc.setEmail("bory@gmail.com");
        akc.setId(1L);
        when(
                adminKcRepository.save(any(AdminKC.class))
        ).thenReturn(
                akc
        );

    }

    @Test
    public void getAKCTest() {
        AdminKC akc = new AdminKC();
        akc.setIme("Borislav");
        akc.setPrezime("Gajic");
        akc.setLozinka("123456");
        akc.setPrvoPrijavljivanje(false);
        akc.setUsername("BoGa");
        akc.setEmail("bory@gmail.com");
        akc.setId(1L);

        Mockito.when(adminKcRepository.getAdminKCByUsername("BoGa")).thenReturn(akc);

        AdminKC result = adminKcRepository.getAdminKCByUsername("BoGa");

        assertNotNull(result);
        assertEquals(result.getId(), akc.getId());
        assertEquals(result.getEmail(), akc.getEmail());
    }


    @Test
    public void AddAKCTest() throws Exception {

        AdminKC akc = new AdminKC();
        akc.setIme("Borislav");
        akc.setPrezime("Gajic");
        akc.setLozinka("123456");
        akc.setPrvoPrijavljivanje(false);
        akc.setUsername("BoGa");
        akc.setEmail("bory@gmail.com");
        //akc.setId(1L);

        AdminKC result = adminKcRepository.save(akc);

        assertEquals(1L, result.getId());

    }
}
