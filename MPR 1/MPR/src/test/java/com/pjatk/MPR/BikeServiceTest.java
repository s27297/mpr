package com.pjatk.MPR;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeServiceTest {
	@Mock
	private BikeRepository repository;
	private AutoCloseable openMocks;
	private BikeService bikeService;

	@BeforeEach
	public void init(){
		openMocks = MockitoAnnotations.openMocks(this);
		bikeService = new BikeService(repository);
	}

	@AfterEach
	public void tearDown() throws Exception {
		openMocks.close();
	}

	@Test
	public void serviceGeterColorTest() {
		String name = "bc";
		Bike testBike = new Bike(name,  "Green");
		when(repository.findByColor("Green")).thenReturn(List.of(testBike));

		List<Bike> result = bikeService.findByColor("Green");
		assertEquals(List.of(testBike), result);
	}

	@Test
	public void serviceGeterNameTest() {
		String name = "bc";
		Bike testBike = new Bike(name,  "Green");
		when(repository.findByColor(name)).thenReturn(List.of(testBike));

		List<Bike> result = bikeService.findByColor(name);
		assertEquals(List.of(testBike), result);
	}

	@Test
	public void ServiceAddFunctionTest() {
		Bike bike = new Bike("Tomasz", "Blue");
		String name = "Tomasz";
		String color = "Blue";
		when(repository.save(any())).thenReturn(bike);

		Bike createdBike = bikeService.addBike(new Bike(name, color));

		Mockito.verify(repository).save(any());

		assertEquals(name, createdBike.getName());
		assertEquals(color, createdBike.getColor());
	}

	@Test
	public void ServiceAddNoColorFunctionTest() {
		String name = "Jan";
		String color = "Green";
		when(repository.findByName("Jan")).thenReturn(new ArrayList<>());
		when(repository.findByColor("Green")).thenReturn(List.of(new Bike()));
		when(repository.save(any())).thenReturn(new Bike(name, color));

		Bike createdBike = bikeService.addBike(new Bike(name, color));

		Mockito.verify(repository).save(any());

		assertEquals(name, createdBike.getName());
		assertEquals(color, createdBike.getColor());
	}

	@Test
	public void ServiceAddNoNameFunctionTest() {
		String name = "Jan";
		String color = "Green";
		when(repository.findByName("Jan")).thenReturn(List.of(new Bike()));
		when(repository.findByColor("Green")).thenReturn(new ArrayList<>());
		when(repository.save(any())).thenReturn(new Bike(name, color));

		Bike createdBike = bikeService.addBike(new Bike(name, color));

		Mockito.verify(repository).save(any());

		assertEquals(name, createdBike.getName());
		assertEquals(color, createdBike.getColor());
	}

	@Test
	public void ServiceAddNoParametersFunctionTest() {
		String name = "Jan";
		String color = "Green";
		when(repository.findByName("Jan")).thenReturn(List.of(new Bike()));
		when(repository.findByColor("Green")).thenReturn(List.of(new Bike()));

		//Bike createdBike = bikeService.addBike(name, color);

		assertThrows(BikeAlreadyExistException.class, () -> {
			Bike createdBike = bikeService.addBike(new Bike(name, color));
		});

	}

//	@Test
//	public void serviceTest() {
//		String bikeName = "ab";
//		String bikeColor = "green";
//
//		bikeService.addBike(bikeName, bikeColor);
//
//		Mockito.verify(repository).save(new Bike(bikeName, bikeColor));
//	}

	@Test
	public void serviceGetByColorName() {

		String searchedPhrase = "ab";

		Bike test1 = new Bike("abc", "Blue");
		Bike test2 = new Bike("efg", "");

		when(repository.findAll()).thenReturn(List.of(test1, test2));

		List<Bike> resultList = bikeService.findByName(searchedPhrase);

		assertEquals(List.of(test1), resultList);
	}

}
