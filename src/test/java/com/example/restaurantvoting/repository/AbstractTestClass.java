package com.example.restaurantvoting.repository;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@TestPropertySource(locations="classpath:test.properties")
@Sql(scripts = {"classpath:schema.sql","classpath:data.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class AbstractTestClass {

}
