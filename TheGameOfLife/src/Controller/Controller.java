package Controller;

import Model.GameGrid;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Button start;
    @FXML
    public Button reset;
    @FXML
    public Button slowSpeed;
    @FXML
    public Button normalSpeed;
    @FXML
    public Button fastSpeed;

    @FXML
    private Canvas canvas;
    private int height = 50;
    private int width = 50;

    private int move = 500;

    private boolean [][]currentMove = new boolean[move][move], nextMove=new boolean[move][move];
    private boolean play;

    private double time;
    private double startSpeed = 6;

    @FXML
    private void background(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        GameGrid.Cells(canvas, height, width, currentMove);
        GameGrid.drawGrid(canvas, height, width);
    }

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(play){
                time += 1;
                if(time >= startSpeed){
                    for(int i = 0; i < height; i++){
                        for(int j = 0; j < width; j++){
                            nextMove[i][j] = GameGrid.deadOrAlive(i,j,currentMove, height,width);
                        }
                    }
                    for(int i = 0; i < height; i++){
                        if (width >= 0) System.arraycopy(nextMove[i], 0, currentMove[i], 0, width);
                    }
                    background();
                    GameGrid.Cells(canvas,height,width,currentMove);
                    GameGrid.drawGrid(canvas,height,width);
                    time = 0;
                }
            }
        }
    };

    @FXML
    public void canvasMouseEvent(MouseEvent e){
        int j = (int) (width * e.getX() / canvas.getWidth());
        int i = (int) (height * e.getY() / canvas.getHeight());
        currentMove[i][j] = !currentMove[i][j];
        background();
        GameGrid.Cells(canvas, height, width, currentMove);
        GameGrid.drawGrid(canvas, height, width);
    }

    @FXML
    public void startButton(){
        play = !play;
        if(play){
            start.setText("Pause");
        }else{
            start.setText("Start");
        }
        background();
        GameGrid.Cells(canvas, height, width, currentMove);
        GameGrid.drawGrid(canvas, height, width);
    }

    @FXML
    public void resetButton(){
        currentMove = new boolean[move][move];
        background();
        GameGrid.drawGrid(canvas,height,width);
    }

    @FXML
    public void slowSpeed() {
        startSpeed = 12;
    }

    @FXML
    public void normalSpeed() {
        startSpeed = 8;
    }

    @FXML
    public void fastSpeed() {
        startSpeed = 4;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timer.start();
        background();
        GameGrid.Cells(canvas,height,width,currentMove);
        GameGrid.drawGrid(canvas, height, width);
    }

}
