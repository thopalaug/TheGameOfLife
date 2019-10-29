package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameGrid {

    public static void drawGrid(Canvas canvas, int height, int width){

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0,0,canvas.getHeight(),canvas.getWidth());
        gc.setFill(Color.BLACK);

        for(int i = 1; i < height; i++){
            int y = (int) (i * canvas.getHeight() / height);
            gc.strokeLine(0, y, canvas.getWidth(), y);
        }
        for(int j = 1; j < width; j++){
            int x = (int) (j * canvas.getWidth() / width);
            gc.strokeLine(x,0,x,canvas.getHeight());
        }
    }

    public static void Cells(Canvas canvas, int height, int width, boolean[][] currentMove){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(currentMove[i][j]){
                    gc.setFill(Color.YELLOW);
                    int y = (int) (i * canvas.getHeight() / height);
                    int x = (int) (j * canvas.getWidth() / width);

                    gc.fillRect(x,y,canvas.getWidth() / width, canvas.getHeight() / height);
                }
            }
        }
    }

    public static boolean deadOrAlive(int y, int x, boolean[][] currentMove, int height, int width){
        int neighbours = 0;

        if(x > 0){
            if(currentMove[y][x-1])
                neighbours++;
            if(y > 0)
                if(currentMove[y-1][x-1])
                    neighbours++;
            if(y < height-1)
                if(currentMove[y+1][x-1])
                    neighbours++;
        }

        if(x < width-1){
            if(currentMove[y][x+1])
                neighbours++;
            if(y > 0)
                if (currentMove[y-1][x+1])
                    neighbours++;
            if(y < height-1)
                if (currentMove[y+1][x+1])
                    neighbours++;
        }

        if(y > 0)
            if (currentMove[y-1][x])
                neighbours++;
        if(y < height-1)
            if (currentMove[y+1][x])
                neighbours++;
        if(neighbours == 3)
            return true;
        return currentMove[y][x] && neighbours == 2;
    }
}
