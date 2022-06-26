package com.example.apimaroma.colors;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.utils.wrappers.AddressWrapper;
import com.google.cloud.Timestamp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("Adds color")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @PostMapping
    public ColorBean addColor(@RequestBody ColorBean color) throws ExecutionException, InterruptedException {
        return colorService.addColor(color);
    }

    @ApiOperation("Delete a specific color")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @DeleteMapping
    public Timestamp removeColor(@RequestBody String colorId) throws ExecutionException, InterruptedException {
        return colorService.removeColor(colorId);
    }
}
