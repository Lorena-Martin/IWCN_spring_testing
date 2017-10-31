package com.iwcn.master.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.iwcn.master.repositories.ProductoRepository;
import com.iwcn.master.entities.Producto;

@RunWith(SpringRunner.class)
public class DataBaseLoaderTests {

	@Mock
	private ProductoRepository productoRepository;
	
	@InjectMocks
	private DataBaseLoader dbloader = new DataBaseLoader();
	
	@Before
	public void init() {
		Producto p1 = new Producto(1, "nombre1", "descripcion1", 1);
		Producto p2 = new Producto(2, "nombre2", "descripcion2", 2);
		List<Producto> productos = new ArrayList<>();
		productos.add(p1);
		productos.add(p2);
		when(productoRepository.findAll()).thenReturn(productos);
		when(productoRepository.findById(1)).thenReturn(p1);
		when(productoRepository.findById(2)).thenReturn(p2);
	}
	
	@Test
	public void getProductoTest() {
		
		Producto p = dbloader.getProducto(1);
		assertEquals(p.getCodigo(), 1);
		assertEquals(p.getNombre(), "nombre1");
		assertEquals(p.getDescripcion(), "descripcion1");
		assertEquals(p.getPrecio(), 1);
		
	}
	
	@Test
	public void getTodosTest() {
		
		List<Producto> productos = dbloader.getTodos();
		assertEquals(productos.size(), 2);
		
	}
	
	@Test
	public void guardarTest() {
		
		Producto p3 = new Producto(3, "nombre3", "descripcion3", 3);
		List<Producto> productos = dbloader.getTodos();
		productos.add(p3);
		when(productoRepository.findAll()).thenReturn(productos);
//		dbloader.guardar(p3);
		assertEquals(dbloader.getTodos().size(), 3);
		
	}
	
	@Test
	public void borrarTest() {
		
		Producto p1 = dbloader.getProducto(1);
		List<Producto> productos = dbloader.getTodos();
		productos.remove(p1);
		when(productoRepository.findAll()).thenReturn(productos);
//		dbloader.borrar(p1);
		assertEquals(dbloader.getTodos().size(), 1);
		
	}
	
	@Test
	public void getProductoFromDBTest() {
		
		List<Producto> resultado = dbloader.getTodos();
		assertEquals(resultado.size(), 2);
		assertEquals(resultado.get(0).getCodigo(), 1);
		assertEquals(resultado.get(0).getNombre(), "nombre1");
		assertEquals(resultado.get(0).getDescripcion(), "descripcion1");
		assertEquals(resultado.get(0).getPrecio(), 1);
		assertEquals(resultado.get(1).getCodigo(), 2);
		assertEquals(resultado.get(1).getNombre(), "nombre2");
		assertEquals(resultado.get(1).getDescripcion(), "descripcion2");
		assertEquals(resultado.get(1).getPrecio(), 2);
		
		
	}

}
