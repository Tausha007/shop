package com.example.shop;

import com.example.shop.models.ShopDto;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class ShopApplicationTests {

		RequestSpecification request;
		Faker faker = new Faker();
	static List<ShopDto> testShops = new ArrayList<>();

		@BeforeAll
		public static void setUp() {
			RestAssured.baseURI = "http://localhost:4000";
		}

		@BeforeEach
		public void setRequest() {
			request = RestAssured.given();
		}

		@Test
		@DisplayName("Получение списка всех магазинов")
		public void shouldGetShops() {
			when()
					.get("/shops/all")
					.then()
					.statusCode(200);
		}

		@Test
		@DisplayName("Добавление магазина")
		public void shouldAddShop() {
			Response response = request
					.header("Content-type","application/json")
					.and()
					.param("shopName", faker.name().title()+"_Shop")
//					.body("shopName", faker.name().title()+"_Shop")
					.post("/shops/add");

			assertThat(response)
					.extracting(
					Response::getContentType,
					Response::getStatusCode
                ).containsExactly(
					"application/json",
					200
			);

		}

		@Test
	@DisplayName("Удаление магазина")
		public void shouldDeleteShop() {
			final ShopDto ShopToDelete = testShops.get(faker.random().nextInt(0, testShops.size() - 1));

			Response response = request.param("id", 2).delete("/delete/{shopId}");

			assertThat(response.statusCode()).isEqualTo(204);
		}
	}


