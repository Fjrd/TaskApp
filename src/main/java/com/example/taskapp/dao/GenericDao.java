package com.example.taskapp.dao;

import java.util.List;

public interface GenericDao<T>{
  List<T> findAll();
  T save(T t);

}
