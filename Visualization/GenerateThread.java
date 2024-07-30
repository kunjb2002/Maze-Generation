//Pun Bahadur Chhetri
//Kunj Bhavsar
//CS351-Project4
//Mazes-
package Visualization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GenerateThread extends  Thread{

    public GraphicsContext context;
    public int state = -1;
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            int w = (int)(MainWindow.width - 10) / Maze.WIDTH;
            int h = (int)(MainWindow.height - 10) / Maze.HEIGHT;
            if (state != -1 && state <= MainWindow.GENERATOR_RECURSIVE_DIVISION ){
                // Draw Maze
                for (int row = 0; row < Maze.WIDTH; row++) {
                    for (int col = 0; col < Maze.HEIGHT; col++) {

                        Color squareColour;
                        // Wall
                        if (Maze.maze[row][col] == 1)
                            squareColour = MainWindow.WALL_COLOR;
                        else if (Maze.maze[row][col] == 2)      // Start Node
                            squareColour = MainWindow.START_COLOR;
                        else if (Maze.maze[row][col] == 9)      // Destination Node
                            squareColour = MainWindow.DESTINATION_COLOR;
                        else                                    // Empty Square
                            squareColour = Color.WHITE;

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        context.setFill(squareColour);
                        context.setStroke(Color.BLACK);
                        context.fillRect(w * col, w * row, w, h);
                        context.strokeRect(w * col, w * row, w, h);
                    }
                }
            }
            if (state != -1 && state >= MainWindow.FIND_PATH_MOUSE && state <= MainWindow.FIND_PATH_ASTAR){
                // Draw Search Area
                for (int p = 0; p < MainWindow.searchArea.size(); p += 2) {
                    int searchX = MainWindow.searchArea.get(p);
                    int searchY = MainWindow.searchArea.get(p + 1);

                    // Uncover Start and Destination Node
                    if (Maze.maze[searchY][searchX] != 2 && Maze.maze[searchY][searchX] != 9) {

                        // Add Delay In Between Search Area Drawing
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        context.setFill(MainWindow.SEARCH_AREA_COLOR);
                        context.setStroke(Color.BLACK);
                        context.fillRect(w * searchX, w * searchY, w, w);
                        context.strokeRect(w * searchX, w * searchY, w, w);
                    }
                }

                // Draw Path From Start to Destination
                for (int p = MainWindow.path.size() - 1; p >= 0; p -= 2) {
                    int pathX = MainWindow.path.get(p - 1);
                    int pathY = MainWindow.path.get(p);

                    // Uncover Start and Destination Node
                    if (Maze.maze[pathY][pathX] != 2 && Maze.maze[pathY][pathX] != 9) {

                        // Add Delay In Between Path Drawing
                        try {
                            Thread.sleep(90);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }

                        context.setFill(MainWindow.PATH_COLOR);
                        context.setStroke(Color.BLACK);
                        context.fillRect(w * pathX, w * pathY, w, w);
                        context.strokeRect(w * pathX, w * pathY, w, w);
                    }
                }
            }
        }
    }
}
