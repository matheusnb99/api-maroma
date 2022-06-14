package com.example.apimaroma.colors;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "api/v1/color")
@Api(value = "Color Resource", description = "Endpoint of Color route")
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/{id}")
    public ColorBean getColor(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return colorService.getColor(id);
    }

    @GetMapping("/")
    public List<ColorBean> getAllColors() throws ExecutionException, InterruptedException {
        System.out.println("+++++---------------------------------");
        return colorService.getAllColors();
    }
}
