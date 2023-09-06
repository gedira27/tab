package com.example.menu.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Scene escena;
    private VBox vBox;
    private GridPane grdTeclado;
    private TextField txtResultado;
    private Button[][] arTeclas = new Button[4][4];
    private char arDigitos[] = {'7','8','9','/','4','5','6','x','1','2','3','-','0','.','=','+'};


    public Calculadora(){
        CreateUI();
        escena = new Scene(vBox,200,200);
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void CreateUI(){
        int pos=0;
        grdTeclado = new GridPane();
        txtResultado = new TextField("0");
        txtResultado.setAlignment(Pos.BASELINE_RIGHT);
        txtResultado.setEditable(false);
        for (int i = 0; i < 4; i++){
            for (int j=0; j<4; j++){
                arTeclas[j][i] = new Button(arDigitos[pos]+"");
                arTeclas[j][i].setPrefSize(50,50);
                int finalPos = pos;
                arTeclas[j][i].setOnAction(
                        (event)->Generarexpresion(arDigitos[finalPos]+""));
                grdTeclado.add(arTeclas[j][i],j,i);
                pos++;
            }
        }
        vBox = new VBox(txtResultado,grdTeclado);
    }
    private void Generarexpresion(String simbolo){
        txtResultado.appendText(simbolo);
    }
}
