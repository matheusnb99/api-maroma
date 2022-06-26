package com.example.apimaroma.categories;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    CategoryModel categoryModel = new CategoryModel();

    public List<CategoryBean> getAllCategories() throws ExecutionException, InterruptedException {
        return (List<CategoryBean>) categoryModel.findAll();
    }

    public CategoryBean getCategory(String id) throws ExecutionException, InterruptedException {
        return categoryModel.findById(id).get();
    }

    public List<CategoryBean> searchCategoryByTitle(String query)
            throws ExecutionException, InterruptedException, TimeoutException {
        String title = query.toLowerCase(Locale.ROOT)
                .replace("é", "e")
                .replace("ç", "c")
                .replace("à", "a")
                .trim();

        return (List<CategoryBean>) categoryModel.findByTitle(title);
    }

    public Timestamp removeCategory(CategoryBean category) throws ExecutionException, InterruptedException {
        return categoryModel.delete(category);
    }

    public CategoryBean addCategory(CategoryBean category) throws ExecutionException, InterruptedException {
        return categoryModel.save(category);
    }
}
