package com.teoria.productosfeo.model;

import com.teoria.productosfeo.config.Conexion;
import com.teoria.productosfeo.config.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDao implements Dao {

    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion = new Conexion();


    @Override
    public void save(Producto producto) throws SQLException, ClassNotFoundException {
        Connection conn = conexion.getConextion();
        ps = conn.prepareStatement("insert into productos values(null,?,?,?,current_timestamp,?)");
        ps.setString(1,producto.getNombre());
        ps.setInt(2,producto.getUnidades());
        ps.setDouble(3,producto.getPrecio());
        ps.setBoolean(4, producto.isDisponible());
        ps.executeUpdate();
    }

    @Override
    public ObservableList<Producto> getAll() throws SQLException, ClassNotFoundException {
        Connection conn = conexion.getConextion();
        ObservableList<Producto> lista = FXCollections.observableArrayList();
        ps = conn.prepareStatement("select * from productos");
        rs = ps.executeQuery();
        while (rs.next()){
            Producto producto = new Producto();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setUnidades(rs.getInt("unidades"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setFecha(String.valueOf(rs.getDate("fecha_envasado")));
            producto.setDisponible(rs.getBoolean("disponible"));
            lista.add(producto);
        }
        return lista;
    }


}
