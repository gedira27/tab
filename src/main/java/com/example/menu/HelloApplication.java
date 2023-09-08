    package com.example.menu;

    import com.example.menu.vistas.Calculadora;
    import com.example.menu.vistas.Loteria;
    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.layout.BorderPane;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.util.Optional;

    public class HelloApplication extends Application {



        private Scene escena;
        private BorderPane borderPane;
        private MenuBar menuBar;
        private Menu menuParcial1, menuParcial2, menuSalir;
        private MenuItem mitCalculadora,mitLoteria, mitSalir;


        private  void CrearUI(){
            mitCalculadora = new MenuItem("Calculadora");
            mitCalculadora.setOnAction((event)->new Calculadora());
            mitLoteria = new MenuItem("Lotería");
            mitLoteria.setOnAction((event)->new Loteria());

            menuParcial1 = new Menu("Parcial 1");
            menuParcial1.getItems().add(mitCalculadora);
            menuParcial1.getItems().add(mitLoteria);

            menuParcial2 = new Menu("Parcial 2");


            menuSalir = new Menu("Mas opciones");
            mitSalir = new MenuItem("Salir");
            mitSalir.setOnAction((event)->Salir());
            menuSalir.getItems().add(mitSalir);

            menuBar = new MenuBar(menuParcial1,menuParcial2,menuSalir);
        }

        private void Salir() {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("¿Confirmar cerrar sistema?");
            Optional< ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK){
                System.exit(0);

            }
        }

        @Override
        public void start(Stage stage) throws IOException {
            CrearUI();
            borderPane = new BorderPane();
            borderPane.setLeft(menuBar);

            escena = new Scene(borderPane,400,300);
            stage  .setScene(escena);
            stage.show();


        }



        public static void main(String[] args) {
            launch();
        }
    }