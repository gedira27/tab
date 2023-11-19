package com.example.menu.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Calculadora extends Stage {

    private Scene escena;
    private VBox vBox;
    private HBox hBox;
    private GridPane grdTeclado;
    private TextField txtResultado;
    private Button[][] arTeclas = new Button[4][4];
    private Button btnLimpiar,btnReinicio;
    private char arDigitos[] = {'7', '8', '9', '/', '4', '5', '6', '*', '1', '2', '3', '-', '0', '.', '=', '+'};
    private int contDecimal = 0, flgTermino = 1;
    private double numPrimero, numSegundo, Resultado;
    private String Operacion;


    public Calculadora() {
        CreateUI();
        escena = new Scene(vBox, 200, 240);
        escena.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toExternalForm());
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void CreateUI() {
        int pos = 0;
        grdTeclado = new GridPane();
        txtResultado = new TextField("");
        txtResultado.setAlignment(Pos.BASELINE_RIGHT);
        txtResultado.setEditable(false);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arTeclas[j][i] = new Button(arDigitos[pos] + "");
                arTeclas[j][i].setPrefSize(50, 50);
                int finalPos = pos;
                arTeclas[j][i].setOnAction(
                        (event) -> Generarexpresion(arDigitos[finalPos] + ""));
                grdTeclado.add(arTeclas[j][i], j, i);
                pos++;
            }
        }
        btnLimpiar = new Button("Limpiar");
        btnLimpiar.setPrefSize(100,40);
        btnLimpiar.setOnAction(actionEvent -> setBtnLimpiar());

        btnReinicio = new Button("Reinicio");
        btnReinicio.setPrefSize(100,40);
        btnReinicio.setOnAction(actionEvent -> setBtnReinicio());
        hBox = new HBox(btnLimpiar,btnReinicio);
        vBox = new VBox(txtResultado, grdTeclado,hBox);
    }

    private void Generarexpresion(String simbolo) {

        if (simbolo.equals("1") || simbolo.equals("2") || simbolo.equals("3") || simbolo.equals("4") || simbolo.equals("5") ||
                simbolo.equals("6") || simbolo.equals("7") || simbolo.equals("8") || simbolo.equals("9") || simbolo.equals("0")) {
            if (Operacion=="Nuevo"){
                txtResultado.clear();
                Operacion="En uso";
            }
            if (flgTermino == 2) {
                txtResultado.clear();
                flgTermino++;
            }
            txtResultado.appendText(simbolo);
        }
        if (simbolo.equals(".") && contDecimal == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("No puede agregar otro punto decimal");
            alert.showAndWait();
        }
        if (simbolo.equals(".") && contDecimal == 0) {
            txtResultado.appendText(simbolo);
            contDecimal++;
        }
        if (simbolo.equals("+") || simbolo.equals("-") || simbolo.equals("*") || simbolo.equals("/")) {
            if (txtResultado.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Aún no ingresa valores");
                alert.showAndWait();
            }
            else {
            Operacion = simbolo;
            if (flgTermino == 1) {
                numPrimero = Double.parseDouble(txtResultado.getText());
                flgTermino = 2;
                contDecimal = 0;
            } else {
                numSegundo = Double.parseDouble(txtResultado.getText());
                switch (Operacion) {
                    case "+":
                        Resultado = numPrimero + numSegundo;
                        txtResultado.setText(String.valueOf(Resultado));
                        break;
                    case "-":
                        Resultado = numPrimero - numSegundo;
                        txtResultado.setText(String.valueOf(Resultado));
                        break;
                    case "*":
                        Resultado = numPrimero * numSegundo;
                        txtResultado.setText(String.valueOf(Resultado));
                        break;
                    case "/":
                        if (numSegundo != 0) {
                            Resultado = numPrimero / numSegundo;
                            txtResultado.setText(String.valueOf(Resultado));
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("No se puede dividir entre CERO");
                            alert.showAndWait();
                            txtResultado.setText("ERROR");
                        }
                        break;
                }

                flgTermino = 1;
                contDecimal = 0;
                Operacion="Nuevo";
            }

            }
        }
        if (simbolo.equals("=")) {
            numSegundo = Double.parseDouble(txtResultado.getText());
            switch (Operacion) {
                case "+":
                    Resultado = numPrimero + numSegundo;
                    txtResultado.setText(String.valueOf(Resultado));
                    break;
                case "-":
                    Resultado = numPrimero - numSegundo;
                    txtResultado.setText(String.valueOf(Resultado));
                    break;
                case "*":
                    Resultado = numPrimero * numSegundo;
                    txtResultado.setText(String.valueOf(Resultado));
                    break;
                case "/":
                    if (numSegundo != 0) {
                        Resultado = numPrimero / numSegundo;
                        txtResultado.setText(String.valueOf(Resultado));
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No se puede dividir entre CERO");
                        alert.showAndWait();
                        txtResultado.setText("ERROR");
                    }
                    break;
            }
            flgTermino = 1;
            contDecimal = 0;
            Operacion="Nuevo";
        }


    }
    public void setBtnLimpiar(){
        txtResultado.clear();
        contDecimal=0;
    }
    public void setBtnReinicio(){
        txtResultado.clear();
        contDecimal=0;
        flgTermino=1;
    }
}
