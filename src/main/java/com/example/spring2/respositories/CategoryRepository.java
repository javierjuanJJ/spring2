package com.example.spring2.respositories;

import com.example.spring2.Exceptions.EtBadRequestException;
import com.example.spring2.Exceptions.EtResourceNotFoundException;
import com.example.spring2.domain.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;
    Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;
    Integer create(Integer userId, String title, String description) throws EtBadRequestException;
    void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;
    void removeById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}
