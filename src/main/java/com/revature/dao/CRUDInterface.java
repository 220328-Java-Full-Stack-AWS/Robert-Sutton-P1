package com.revature.dao;

import com.revature.models.Model;

import java.sql.Connection;
import java.util.List;

public interface CRUDInterface<T extends Model> {
    //CRUD - create, read, update, delete
    public T create(T model);
    public T read(int id);
    public void update(T model);
    public void delete(int id);
    public void delete(T model);
    public List<T> getAll();

}