package ru.vigovskiy.strike_team.service;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = {/*"classpath:db/initDB.sql",*/ "classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("postgres")
abstract class AbstractServiceTest {

    @BeforeAll
    static void init() {

    }

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }
}