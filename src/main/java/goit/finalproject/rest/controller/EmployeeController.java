package goit.finalproject.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Api(value = "employee", description = "Operations with Employee")
public class EmployeeController {
}
