/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flockingsimulation;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author SimonDao
 */
public class FlockingSimulation extends Application {
    
    private Pane root;
    private Boid boid;
    private boolean running = true;
    private final String title = "flocking simulator";
    private final int stageWidth = 600;
    private final int stageHeight = 600;
    
    public int getStageWidth() {return stageWidth;}
    public int getStageHeight() {return stageWidth;}
    
    List<Boid> boids = new ArrayList<>();
    
    public FlockingSimulation() {
        
    }
    
    private void initializeContent(Stage stage, Scene scene, GraphicsContext gc) {
        
        //Make boids
        for(int i = 0; i < 100; i++) {
            boids.add(new Boid(new Circle(10, Color.WHITE)));
            
        boids.get(i).setVelocity(new Point2D(1, 0));
        addGameObject(boids.get(i), 300, 300);
        }
        
        new AnimationTimer(){
                long lastTick = 0;
                
           //TIMER     
                public void handle(long now){
                    
                if(running == true ){ 
                    
                    stage.setScene(scene);
                    
                    if(lastTick == 0 ){
                        lastTick = now;
                        onUpdate(gc);
                        return;
                    }
                    if ( now - lastTick > 1000000000/100){
                        lastTick = now;
                        onUpdate(gc);
                    }
                }
                }
            }.start(); 
    }

    private void addGameObject(Boid object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private void onUpdate(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, stageWidth, stageHeight);
        
        for(int i = 0; i<boids.size(); i++) {
            boids.get(i).randomMovement();
            boids.get(i).update();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        root = new Pane();
        Canvas c = new Canvas(stageWidth, stageHeight);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);
        Scene scene = new Scene(root, stageWidth, stageHeight);
        
        initializeContent(stage, scene, gc);
        
        
        stage.addEventFilter(KeyEvent.KEY_PRESSED, keys ->{
            if(null != keys.getCode())
                
                switch (keys.getCode()) {
                    /*
                case LEFT:
                    player.rotateLeft();
                    break;
                case RIGHT:
                    player.rotateRight();
                    break;
                    */
                case ESCAPE:
                    stage.close();
                    System.exit(0);
                default:
                    break;
            }
        });
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}