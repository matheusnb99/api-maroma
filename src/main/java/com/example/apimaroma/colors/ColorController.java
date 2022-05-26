package com.example.apimaroma.colors;

import com.example.apimaroma.products.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path="api/v1/color")
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService){this.colorService = colorService;}

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
