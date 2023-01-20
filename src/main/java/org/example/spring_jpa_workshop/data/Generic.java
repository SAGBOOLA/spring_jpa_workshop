package org.example.spring_jpa_workshop.data;

import java.util.Collection;

public interface Generic <T, ID>{

    T findById(ID id);
    Collection<T> findAll();
    T create(T t);
    T update(T t);
    void delete(ID id);
}
