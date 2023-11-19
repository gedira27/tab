package com.example.menu.vistas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.*;

public class Loteria extends Stage {
    private Scene escena;
    private HBox hPrincipal,hBtnSeleccion;
    private VBox vTablilla,vMazo;
    private Button btnAnterior,btnSiguiente,btnIniciar;
    private ImageView imvCarta;
    private Button[][] arBtnTablilla = new Button[4][4];
    private GridPane grdTablilla;
    private int numTablilla=0;
    private String[] todasCartas={"gallo.jpg", "diablo.jpg", "dama.jpg", "catrin.jpg", "paraguas.jpg", "sirena.jpg", "escalera.jpg", "botella.jpg", "barril.jpg", "arbol.jpg",
            "melon.jpg", "valiente.jpg", "gorrito.jpg", "muerte.jpg", "pera.jpg", "bandera.jpg","bandolon.jpg", "violoncello.jpg", "garza.jpg", "pajaro.jpg",
            "mano.jpg", "bota.jpg", "luna.jpg", "cotorro.jpg", "borracho.jpg", "negrito.jpg", "corazon.jpg", "sandia.jpg", "tambor.jpg", "camaron.jpg", "jaras.jpg",
            "musico.jpg","araña.jpg", "soldado.jpg", "estrella.jpg", "cazo.jpg", "mundo.jpg", "apache.jpg", "nopal.jpg", "alacran.jpg", "rosa.jpg", "calavera.jpg",
            "campana.jpg", "cantarito.jpg", "venado.jpg", "sol.jpg", "corona.jpg", "chalupa.jpg","pino.jpg", "pescado.jpg", "palma.jpg", "maceta.jpg", "arpa.jpg",
            "rana.jpg"};
    private List<String> CartasRandom;
    private int indiceImagenActual;
    private Set<String> CartasPasadas = new HashSet<>();
    private int cartasVolteadas = 0;

    private String[][] arTablilla = {
            {"gallo.jpg", "diablo.jpg", "dama.jpg", "catrin.jpg", "paraguas.jpg", "sirena.jpg", "escalera.jpg", "botella.jpg", "barril.jpg", "arbol.jpg", "melon.jpg", "valiente.jpg", "gorrito.jpg", "muerte.jpg", "pera.jpg", "bandera.jpg"},
            {"bandolon.jpg", "violoncello.jpg", "garza.jpg", "pajaro.jpg", "mano.jpg", "bota.jpg", "luna.jpg", "cotorro.jpg", "borracho.jpg", "negrito.jpg", "corazon.jpg", "sandia.jpg", "tambor.jpg", "camaron.jpg", "jaras.jpg", "musico.jpg"},
            {"araña.jpg", "soldado.jpg", "estrella.jpg", "cazo.jpg", "mundo.jpg", "apache.jpg", "nopal.jpg", "alacran.jpg", "rosa.jpg", "calavera.jpg", "campana.jpg", "cantarito.jpg", "venado.jpg", "sol.jpg", "corona.jpg", "chalupa.jpg"},
            {"pino.jpg", "pescado.jpg", "palma.jpg", "maceta.jpg", "arpa.jpg", "rana.jpg", "gallo.jpg", "diablo.jpg", "dama.jpg", "catrin.jpg", "paraguas.jpg", "sirena.jpg", "escalera.jpg", "botella.jpg", "barril.jpg", "arbol.jpg"},
            {"melon.jpg", "valiente.jpg", "gorrito.jpg", "muerte.jpg", "pera.jpg", "bandera.jpg", "bandolon.jpg", "violoncello.jpg", "garza.jpg", "pajaro.jpg", "mano.jpg", "bota.jpg", "luna.jpg", "cotorro.jpg", "borracho.jpg", "negrito.jpg"},
            {"corazon.jpg", "sandia.jpg", "tambor.jpg", "camaron.jpg", "jaras.jpg", "musico.jpg", "araña.jpg", "soldado.jpg", "estrella.jpg", "cazo.jpg", "mundo.jpg", "apache.jpg", "nopal.jpg", "alacran.jpg", "rosa.jpg", "calavera.jpg"},
            {"campana.jpg", "cantarito.jpg", "venado.jpg", "sol.jpg", "corona.jpg", "chalupa.jpg", "pino.jpg", "pescado.jpg", "palma.jpg", "maceta.jpg", "arpa.jpg", "rana.jpg", "gallo.jpg", "diablo.jpg", "dama.jpg", "catrin.jpg"},
            {"paraguas.jpg", "sirena.jpg", "escalera.jpg", "botella.jpg", "barril.jpg", "arbol.jpg", "melon.jpg", "valiente.jpg", "gorrito.jpg", "muerte.jpg", "pera.jpg", "bandera.jpg", "bandolon.jpg", "violoncello.jpg", "garza.jpg", "pajaro.jpg"}
    };


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
        btnIniciar.setOnAction(event -> {iniciarLoteria();});
        vMazo = new VBox(imvCarta,btnIniciar);
    }

    private void CrearUI(){
        CrearTablilla(numTablilla);

        btnAnterior = new Button("<");
        btnAnterior.setPrefSize(180,40);
        btnAnterior.setOnAction(actionEvent -> setBtnAnterior());

        btnSiguiente = new Button(">");
        btnSiguiente.setPrefSize(180,40);
        btnSiguiente.setOnAction(actionEvent ->setBtnSiguiente());

        hBtnSeleccion = new HBox(btnAnterior,btnSiguiente);
        hBtnSeleccion.setSpacing(40);
        vTablilla = new VBox(grdTablilla,hBtnSeleccion);
        vTablilla.setSpacing(20);

        hPrincipal = new HBox(vTablilla,vMazo);
        hPrincipal.setPadding(new Insets(20));
    }



    private void CrearTablilla(int numTabilla) {

        int pos=0;
        grdTablilla = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                Image imgCartaP = new Image(new File("src\\main\\java\\com\\example\\menu\\imagenes\\"+arTablilla[numTabilla][pos]).toURI().toString());
                ImageView imv = new ImageView(imgCartaP);
                pos++;
                imv.setFitHeight(120);
                imv.setFitWidth(80);
                arBtnTablilla[j][i] = new Button();
                arBtnTablilla[j][i].setGraphic(imv);
                arBtnTablilla[j][i].setPrefSize(100,140);

                int finalPos = pos - 1;
                arBtnTablilla[j][i].setOnAction(event -> VoltearCarta(finalPos));

                grdTablilla.add(arBtnTablilla[j][i],j,i);
            }
        }
    }
    public void setBtnAnterior(){
        if (numTablilla > 1) {
            numTablilla--;
        }else{
            numTablilla=7;
        }
        actualizarGradilla();
    }
    public void setBtnSiguiente(){
        if (numTablilla < 7) {
            numTablilla++;
        }else{
            numTablilla=0;
        }
        actualizarGradilla();
    }
    private void actualizarGradilla() {
        CrearTablilla(numTablilla);
        vTablilla.getChildren().remove(0);
        vTablilla.getChildren().add(0, grdTablilla);
    }

    private void VoltearCarta(int pos) {
        String imagenActual = arTablilla[numTablilla][pos];
        if (!CartasPasadas.contains(imagenActual)) {
            return;
        }

        Image imgVolteada = new Image(new File("src\\main\\java\\com\\example\\menu\\imagenes\\dorso.jpg").toURI().toString());
        ImageView imvVolteada = new ImageView(imgVolteada);
        imvVolteada.setFitHeight(120);
        imvVolteada.setFitWidth(80);
        int columna = pos % 4;
        int fila = pos / 4;
        arBtnTablilla[columna][fila].setGraphic(imvVolteada);
        arBtnTablilla[columna][fila].setDisable(true);

        cartasVolteadas++;
        if (cartasVolteadas == 16) {
            Ganaste("¡Ganaste!");
        }
    }
    private void iniciarLoteria() {
        CartasRandom = new ArrayList<>(List.of(todasCartas));
        Collections.shuffle(CartasRandom);
        CartasLoteria();
        btnIniciar.setDisable(true);
        btnSiguiente.setDisable(true);
        btnAnterior.setDisable(true);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (indiceImagenActual < CartasRandom.size()) {
                            CartasLoteria();
                        }
                    }
                }));
        timeline.setCycleCount(CartasRandom.size());
        timeline.play();
    }

    private void CartasLoteria() {
        String imagenActual = CartasRandom.get(indiceImagenActual);
        CartasPasadas.add(imagenActual);
        Image imgActual = new Image(new File("src\\main\\java\\com\\example\\menu\\imagenes\\"+imagenActual).toURI().toString());
        imvCarta.setImage(imgActual);
        indiceImagenActual++;
    }
    private void Ganaste(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Felicidades!");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
