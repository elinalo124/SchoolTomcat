package com.elina.SchoolTomcat.dao;

import java.util.List;
import java.util.Optional;

public interface CRUD <T>{

    /*CREATE*/
    void saveElement(T t);

    /*RETRIEVE*/
    List<T> retrieveAllElements();
    Optional<T> retrieveElementByID (int id);

    /*UPDATE*/
    void updateElement(T t);

    /*DELETE*/
    void deleteElement(T t);


}
