package com.example.apimaroma.colors;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

@Service
public class ColorService {
    ColorModel colorModel = new ColorModel();

    public ColorBean getColor(String id) throws ExecutionException, InterruptedException {
        return colorModel.findById(id).get();
    }

    public List<ColorBean> getAllColors() throws ExecutionException, InterruptedException {
        return (List<ColorBean>) colorModel.findAll();
    }
}
