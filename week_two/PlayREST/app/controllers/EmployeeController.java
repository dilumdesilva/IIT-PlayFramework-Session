package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.EmployeeService;
import utils.ApplicationUtil;

import java.util.Set;

public class EmployeeController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In EmployeeController.create(), input is: {}", json.toString());
        Employee employee = EmployeeService.getInstance().addEmployee(Json.fromJson(json, Employee.class));
        JsonNode jsonObject = Json.toJson(employee);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update() {
        logger.debug("In EmployeeController.update()");
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Employee employee = EmployeeService.getInstance().updateEmployee(Json.fromJson(json, Employee.class));
        logger.debug("In EmployeeController.update(), employee is: {}",employee);
        if (employee == null) {
            return notFound(ApplicationUtil.createResponse("Employee not found", false));
        }

        JsonNode jsonObject = Json.toJson(employee);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result retrieve(int id) {
        logger.debug("In EmployeeController.retrieve(), retrieve employee with id: {}",id);
        if (EmployeeService.getInstance().getEmployee(id) == null) {
            return notFound(ApplicationUtil.createResponse("Employee with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(EmployeeService.getInstance().getEmployee(id));
        logger.debug("In EmployeeController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result listEmployees() {
        Set<Employee> result = EmployeeService.getInstance().getAllEmployees();
        logger.debug("In EmployeeController.listEmployees(), result is: {}",result.toString());
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));

    }

    public Result delete(int id) {
        logger.debug("In EmployeeController.retrieve(), delete employee with id: {}",id);
        if (!EmployeeService.getInstance().deleteEmployee(id)) {
            return notFound(ApplicationUtil.createResponse("Employee with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Employee with id:" + id + " deleted", true));
    }
}
