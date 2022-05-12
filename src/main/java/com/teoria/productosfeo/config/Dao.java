package com.teoria.productosfeo.config;

import com.teoria.productosfeo.model.Producto;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Dao {
    void save(Producto producto) throws SQLException, ClassNotFoundException;

    ObservableList<Producto> getAll() throws SQLException, ClassNotFoundException;
}
