package goit.finalproject.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@Api(value = "event", description = "Operations with Event")
public class EventController {
}
