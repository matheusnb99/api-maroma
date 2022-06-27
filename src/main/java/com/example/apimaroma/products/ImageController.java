package com.example.apimaroma.products;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/image")
public class ImageController {
    ProductModel productModel = new ProductModel();

    @GetMapping(path="{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String id, @RequestParam("imageNumber") Optional<Integer> imageNumber) throws Exception {
        ProductBean product = productModel.findById(id).orElseThrow(() -> new Exception("Produit pas trouvé"));
        String image = product.getImages().get(imageNumber.get());

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(image);
        HttpResponse response = httpClient.execute(request);

        if(response.getStatusLine().getStatusCode() == 200) {
            System.out.println("response:: " + response.getEntity());
            InputStream content = response.getEntity().getContent();
            return content.readAllBytes();
        }

    return null;
    }
}
