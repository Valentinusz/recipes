package com.valentinusz.recipes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class RecipesApplicationTests {
	@Autowired
    private ApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(context);
	}
}
