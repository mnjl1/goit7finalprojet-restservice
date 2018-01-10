package goit.finalproject.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/position")
@Api(value = "position", description = "Operations with Position")
public class PositionController {
}
