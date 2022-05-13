package com.teoria.productosfeo.controller;

import com.teoria.productosfeo.model.Producto;
import com.teoria.productosfeo.model.ProductoDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {
    public TextField tfUnidades;
    public Button btnGuardar;
    public TextField tfNombre;
    public TextField tfPrecio;
    public Button btnEliminar;
    public Button btnActualizar;
    public TableView tvTabla;
    public ComboBox cbID;
    public TableColumn ColID;
    public TableColumn colNombre;
    public TableColumn colUnidades;
    public TableColumn colPrecio;
    public TableColumn colFecha;
    public TableColumn colDisponible;
    public CheckBox cbDisponible;
    Producto producto;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


    public void Guardar() throws SQLException, ClassNotFoundException {
        String nombre = tfNombre.getText();
        int unidades = Integer.parseInt(tfUnidades.getText());
        double precio = Double.parseDouble(tfPrecio.getText());
        boolean disponible = cbDisponible.isSelected();

        producto = new Producto(nombre,precio,unidades,disponible);
        ProductoDao productoDao = new ProductoDao();
        productoDao.save(producto);
        alert.setTitle("Exito!");
        alert.setHeaderText(null);
        alert.setContentText("Producto añadido con exito!");
        alert.showAndWait();
        clearFields();
        mostrarDatos();
        mostrarIds();

    }
    public void mostrarDatos() throws SQLException, ClassNotFoundException {
        ProductoDao productoDao = new ProductoDao();
        ObservableList<Producto> lista = productoDao.getAll();
        ColID.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("unidades"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Producto, String>("fecha"));
        colDisponible.setCellValueFactory(new PropertyValueFactory<Producto,Boolean>("disponible"));
        tvTabla.setItems(lista);
    }
    public void clearFields(){
        tfNombre.setText("");
        tfPrecio.setText("");
        tfUnidades.setText("");
    }

    public void eliminar() throws SQLException, ClassNotFoundException {
        int id = (int) cbID.getValue();
        ProductoDao productoDao = new ProductoDao();
        productoDao.eliminar(id);
        alert.setTitle("Exito!");
        alert.setHeaderText(null);
        alert.setContentText("Producto eliminado con exito!");
        alert.showAndWait();
        clearFields();
        mostrarDatos();
        mostrarIds();
        btnGuardar.setDisable(false);
    }

    public void actualizar() throws SQLException, ClassNotFoundException {
        int id = (int) cbID.getValue();
        String nombre = tfNombre.getText();
        int unidades = Integer.parseInt(tfUnidades.getText());
        double precio = Double.parseDouble(tfPrecio.getText());
        boolean disponible = cbDisponible.isSelected();
        producto = new Producto(nombre,precio,unidades,disponible);
        ProductoDao productoDao = new ProductoDao();
        productoDao.actualizar(producto,id);
        alert.setTitle("Exito!");
        alert.setHeaderText(null);
        alert.setContentText("Producto actualizado con exito!");
        alert.showAndWait();
        btnGuardar.setDisable(false);
        clearFields();
        mostrarDatos();
    }

    public void mostrarIds() throws SQLException, ClassNotFoundException {
        ProductoDao productoDao = new ProductoDao();
        ObservableList<Integer> ids = productoDao.getIds();
        cbID.setItems(ids);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mostrarDatos();
            mostrarIds();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getData() throws SQLException, ClassNotFoundException {
        int id = (int) cbID.getValue();
        ProductoDao productoDao = new ProductoDao();
        Producto producto = productoDao.getById(id);
        tfNombre.setText(producto.getNombre());
        tfUnidades.setText(producto.getUnidades()+"");
        tfPrecio.setText(producto.getPrecio()+"");
        cbDisponible.setSelected(producto.isDisponible());
        btnGuardar.setDisable(true);
    }
}
