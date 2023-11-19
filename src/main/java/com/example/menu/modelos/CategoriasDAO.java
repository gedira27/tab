package com.example.menu.modelos;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CategoriasDAO {

    private int idCategoria;
    private String nomCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public void Insertar(){
        try{
            String query = "INSERT INTO tblCategorias(nomCategoria) VALUES('"+this.nomCategoria+"')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public void Actualizar(){
        try{
            String query = "UPDATE tblCategorias SET nomCategoria = '"+this.nomCategoria+"' WHERE idCategoria = "+this.idCategoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void Borrar(){
        try{
            String query = "DELETE FROM tlbCategorias where idCategoria = "+this.idCategoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<CategoriasDAO> ListarCategorias(){
        ObservableList<CategoriasDAO> listCat = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try{
            String query = "SELECT * FROM tblCategorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CategoriasDAO();
                objC.idCategoria = res.getInt("idCategoria");
                objC.nomCategoria = res.getString("nomCategoria");
                listCat.add(objC);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCat;
    }


}
