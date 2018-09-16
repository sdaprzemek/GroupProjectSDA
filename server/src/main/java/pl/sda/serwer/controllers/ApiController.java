package pl.sda.serwer.controllers;

import java.util.List;

public interface ApiController<T> {

    void delete(Integer id);

    void add(T elem);

    void update(T elem);

    List<T> findAll();

    Long count();

    T findById(Integer id);

    String elementToJson(T elem);

    T jsonToElement(String jsonString);
}
