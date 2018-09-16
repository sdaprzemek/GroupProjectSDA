package pl.sda.serwer.services;

import java.util.List;

public interface ApiService<T> {

    void delete(Integer id);

    void add(T elem);

    void update(T elem);

    List<T> findAll();

    Long count();

    T findById(Integer id);
}
