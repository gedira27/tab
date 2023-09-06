package com.example.menu.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Loteria extends Stage {
    private Scene escena;
    private HBox hPrincipal,hBtnSeleccion;
    private VBox vTablilla,vMazo;
    private Button btnAnterior,btnSiguiente,btnIniciar;
    private ImageView imvCarta;
    private Button[][] arBtnTablilla = new Button[4][4];
    private GridPane grdTablilla;
    public Loteria(){
        CearMazo();

        CrearUI();
        escena = new Scene(hPrincipal,800,600);
        this.setTitle("Lotería");
        this.setScene(escena);
        this.show();
    }

    private void CearMazo() {
        Image imgDorso = new Image(new File("src\\main\\java\\com\\example\\menu\\imagenes\\dorso.jpg").toURI().toString());
        imvCarta = new ImageView(imgDorso);
        imvCarta.setFitHeight(480);
        imvCarta.setFitWidth(320);
        btnIniciar = new Button("Iniciar");
        btnIniciar.setPrefSize(400,100);
        vMazo = new VBox(imvCarta,btnIniciar);
    }

    private void CrearUI(){
        CrearTablilla();

        btnAnterior = new Button("<");
        btnSiguiente = new Button(">");
        btnAnterior.setPrefSize(180,40);
        btnSiguiente.setPrefSize(180,40);
        hBtnSeleccion = new HBox(btnAnterior,btnSiguiente);
        hBtnSeleccion.setSpacing(40);
        vTablilla = new VBox(grdTablilla,hBtnSeleccion);
        vTablilla.setSpacing(20);

        hPrincipal = new HBox(vTablilla,vMazo);
        hPrincipal.setPadding(new Insets(20));
    }

    private void CrearTablilla() {
        String[] arImagenes = {"gallo.jpg","diablo.jpg","dama.jpg","catrin.jpg","paraguas.jpg","sirena.jpg","escalera.jpg","botella.jpg","barril.jpg","arbol.jpg","melon.jpg","valiente.jpg","gorrito.jpg","muerte.jpg","pera.jpg","bandera.jpg"};
        int pos=0;
        grdTablilla = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {


                Image imgCartaP = new Image(new File("src\\main\\java\\com\\example\\menu\\imagenes\\"+arImagenes[pos]).toURI().toString());
                ImageView imv = new ImageView(imgCartaP);
                pos++;
                imv.setFitHeight(120);
                imv.setFitWidth(80);
                arBtnTablilla[j][i] = new Button();
                arBtnTablilla[j][i].setGraphic(imv);

                arBtnTablilla[j][i].setPrefSize(100,140);
                grdTablilla.add(arBtnTablilla[j][i],j,i);
            }
        }
    }
}
