import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class MobileBankApiTestV1 {

    @Test

    void shouldReturnAccounts() {

        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1/")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200)
                .header("content-type", "application/json; charset=UTF-8")
                .body("", hasSize(3))
                .body("[0].id", equalTo(0))
                .body("[0].currency", equalTo("RUB"))
                .body("[1].balance", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }

}
