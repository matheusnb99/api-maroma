package com.example.apimaroma.products;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/v1/image")
public class ImageController {
    ProductModel productModel = new ProductModel();

    @GetMapping(path="{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String id) throws Exception {
        ProductBean product = productModel.findById(id).orElseThrow(() -> new Exception("Produit pas trouvé"));
        String image = product.getImages().get(0);

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
