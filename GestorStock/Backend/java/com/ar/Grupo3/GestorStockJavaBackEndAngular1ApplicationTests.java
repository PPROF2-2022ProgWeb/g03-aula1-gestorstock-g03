package com.ar.Grupo3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ar.Grupo3.data.objects.repositorio.UsuarioRepositorio;
import com.ar.Grupo3.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
class GestorStockJavaBackEndAngular1ApplicationTests {

	@Autowired
	private UsuarioRepositorio usuTest;
	
//	@Test
//	void contextLoads() {
//	}
	
	@Test
	public void crearUsuarioTest() {
		Usuario usuarioPrueba = new Usuario();
		usuarioPrueba.setApellido("Norberto");
		usuarioPrueba.setNombre("Nolan");
		usuarioPrueba.setCedula(1);
		usuarioPrueba.setContrasenia("1234");
		usuarioPrueba.setCorreo("nolan22@mail.com");
		
		Usuario prueba = usuTest.save(usuarioPrueba);
		assertTrue(prueba.getContrasenia().equalsIgnoreCase(usuarioPrueba.getContrasenia()));
		
	}

}
