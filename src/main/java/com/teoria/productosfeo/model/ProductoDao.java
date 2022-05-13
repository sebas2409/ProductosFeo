package com.teoria.productosfeo.model;

import com.teoria.productosfeo.config.Conexion;
import com.teoria.productosfeo.config.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class ProductoDao implements Dao {

    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion = new Conexion();


    @Override
    public void save(Producto producto) throws SQLException, ClassNotFoundException {
        Connection conn = conexion.getConextion();
        ps = conn.prepareStatement("insert into productos values(null,?,?,?,?,?)");
        ps.setString(1,producto.getNombre());
        ps.setInt(2,producto.getUnidades());
        ps.setDouble(3,producto.getPrecio());
        ps.setString(4, String.valueOf(new Date()));
        ps.setBoolean(5, producto.isDisponible());
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
            producto.setFecha(rs.getString("fecha"));
            producto.setDisponible(rs.getBoolean("disponible"));
            lista.add(producto);
        }
        return lista;
    }

    @Override
    public ObservableList<Integer> getIds() throws SQLException, ClassNotFoundException {
        Connection conn = conexion.getConextion();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        ps = conn.prepareStatement("select id from productos");
        rs = ps.executeQuery();
        while(rs.next()){
            ids.add(rs.getInt("id"));
        }
        return ids;
    }

    @Override
    public Producto getById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = conexion.getConextion();
        ps = conn.prepareStatement("select * from productos where id = ?");
        ps.setInt(1,id);
        rs = ps.executeQuery();
        Producto producto = new Producto();
        while (rs.next()){
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setUnidades(rs.getInt("unidades"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setFecha(rs.getString("fecha"));
            producto.setDisponible(rs.getBoolean("disponible"));
        }
        return producto;
    }

    @Override
    public void actualizar(Producto producto,int id) throws SQLException, ClassNotFoundException {
        Connection conn = conexion.getConextion();
        ps = conn.prepareStatement("update productos set nombre=?,unidades=?,precio=?,disponible=? where id=? ");
        ps.setString(1,producto.getNombre());
        ps.setInt(2,producto.getUnidades());
        ps.setDouble(3,producto.getPrecio());
        ps.setBoolean(4, producto.isDisponible());
        ps.setInt(5,id);
        ps.executeUpdate();
    }

    @Override
    public void eliminar(int id) throws SQLException, ClassNotFoundException {
        Connection conn = conexion.getConextion();
        ps = conn.prepareStatement("delete from productos where id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }


}
