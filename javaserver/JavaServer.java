/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaserver;

import network.Server;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author Thomas
 */
public class JavaServer extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        //Creating a Polygon 
        Polygon polygon = new Polygon();  

        //Adding coordinates to the polygon 
        polygon.getPoints().addAll(new Double[]{ 
           300.0, 50.0, 
           300.0, 250.0,
           450.0, 150.0,
           150.0, 150.0, 
        }); 

        //Creating a Group object  
        Group root2 = new Group(polygon); 
        
        Scene scene = new Scene(root2, 600, 300);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.start();
        
        launch(args);
    }
    
}
