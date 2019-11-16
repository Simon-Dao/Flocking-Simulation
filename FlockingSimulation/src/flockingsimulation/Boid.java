/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flockingsimulation;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Simon Dao
 */
public class Boid {
    
    FlockingSimulation main = new FlockingSimulation();
    
    private Node view;
    private Point2D velocity = new Point2D(0, 0);

    private boolean alive = true;

    public Boid(Node view) {
        this.view = view;
    }

    public void update() {
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public Node getView() {
        return view;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getRotate() {
        return view.getRotate();
    }

    public Node setView(Node view) {
        this.view = view;
        return view;
    }
    
    public void rotateRight() {
        view.setRotate(view.getRotate() + 5);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    public void rotateLeft() {
        view.setRotate(view.getRotate() - 5);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }
    
    public boolean isColliding(Boid other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
    
    public void randomMovement() {
        Double r = randomIntRange(-10,9);
        double x = Math.cos(Math.toRadians(getRotate()));
        double y = Math.sin(Math.toRadians(getRotate()));
        
        view.setRotate(getRotate() + r);
        
        /*
        if(x > main.getStageWidth()) {
            x = 0;
        }
        if(x < 0) {
            x = main.getStageWidth();
        }
        if(y > main.getStageWidth()) {
            y = 0;
        }
        if(y > main.getStageWidth()) {
            y = main.getStageHeight();
        }
        */
        
        setVelocity(new Point2D(x, y));
        
        System.out.println();
    }
    
    public double randomIntRange(int min, int max) {
        
        if( min >= max) {
            System.err.println("minimum has to be larger than the maximum");
        }
        
        Random rand = new Random();
        int random = rand.nextInt((max - min) +1) +min;
        
        Double parsedDouble = new Double(random);
        double randomNumber = parsedDouble;
        
        return randomNumber;
    }
}