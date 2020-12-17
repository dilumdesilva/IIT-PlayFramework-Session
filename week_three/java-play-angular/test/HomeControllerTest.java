import controllers.HomeController;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.WithApplication;

import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void test_app_summary_from_a_new_instance() {
        Result result = new HomeController().appSummary();
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }

    @Test
    public void test_app_summary_from_route() {
        RequestBuilder request = new RequestBuilder()
                .method(GET)
                .uri("/api/summary");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play Angular Seed\"}");
    }
}
