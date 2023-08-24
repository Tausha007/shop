package com.example.shop;

import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class WebTest {
    MainPage mainPage = new MainPage();
    Faker faker = new Faker();


    @BeforeEach
    public void setSelenide(){
        baseUrl="http://localhost:4000";
        open("/");
    }

    @Test
    @DisplayName("Добавление нового магазина")
    @Feature("Добавление")
    public void shouldAddShop(){
        mainPage.createShop.setValue(faker.company().name()+"_shop").pressEnter();

    }
}
