    package com.example.menu;

    import com.example.menu.vistas.Calculadora;
    import com.example.menu.vistas.Loteria;
    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.scene.control.Menu;
    import javafx.scene.control.MenuBar;
    import javafx.scene.control.MenuItem;
    import javafx.scene.layout.BorderPane;
    import javafx.stage.Stage;

    import java.io.IOException;

    public class HelloApplication extends Application {



        private Scene escena;
        private BorderPane borderPane;
        private MenuBar menuBar;
        private Menu menuParcial1, menuParcial2;
        private MenuItem mitCalculadora,mitLoteria;


        private  void CrearUI(){
            mitCalculadora = new MenuItem("Calculadora");
            mitCalculadora.setOnAction((event)->new Calculadora());
            mitLoteria = new MenuItem("Lotería");
            mitLoteria.setOnAction((event)->new Loteria());
            menuParcial1 = new Menu("Parcial 1");
            menuParcial1.getItems().add(mitCalculadora);
            menuParcial1.getItems().add(mitLoteria);
            menuParcial2 = new Menu("Parcial 2");
            menuBar = new MenuBar(menuParcial1,menuParcial2);


        }

        @Override
        public void start(Stage stage) throws IOException {
            CrearUI();
            borderPane = new BorderPane();
            borderPane.setLeft(menuBar);

            escena = new Scene(borderPane,200,300);
            stage  .setScene(escena);
            stage.show();


        }



        public static void main(String[] args) {
            launch();
        }
    }