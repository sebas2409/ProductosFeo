package com.teoria.productosfeo.config;

import com.teoria.productosfeo.model.Producto;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Dao {
    void save(Producto producto) throws SQLException, ClassNotFoundException;

    ObservableList<Producto> getAll() throws SQLException, ClassNotFoundException;

    ObservableList<Integer> getIds()throws SQLException, ClassNotFoundException;

    Producto getById(int id) throws  SQLException,ClassNotFoundException;
    void actualizar(Producto producto,int id) throws SQLException,ClassNotFoundException;
    void eliminar(int id) throws SQLException,ClassNotFoundException;
}
