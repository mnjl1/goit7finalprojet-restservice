package goit.finalproject.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@Api(value = "department", description = "Operations with Department")
public class DepartmentController {
}
