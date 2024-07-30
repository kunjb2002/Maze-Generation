//Pun Bahadur Chhetri
//Kunj Bhavsar
//CS351-Project4
//Mazes-
package Visualization;

import MazeGeneration.*;
import Pathfinding.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class MainWindow extends Application{

    public static int width,height;
    public static int N;
    public static int generatorAlgorithm;
    public static int solverAlgorithm;
    public GenerateThread generateThread;
    public static GraphicsContext context;

    private int startXold = 0;
    private int startYold = 0;
    private int destXold = 0;
    private int destYold = 0;

    public static List<Integer> path = new ArrayList<Integer>();
    public static List<Integer> searchArea = new ArrayList<Integer>();

    public static final int GENERATOR_DEPTH_FIRST_SEARCH = 0;
    public static final int GENERATOR_KRUSKAL = 1;
    public static final int GENERATOR_PRIM = 2;
    public static final int GENERATOR_ALDOUS = 3;
    public static final int GENERATOR_RECURSIVE_DIVISION = 4;

    public static final int FIND_PATH_MOUSE = 5;
    public static final int FIND_PATH_MOUSE_THREAD = 6;
    public static final int FIND_PATH_WALL = 7;
    public static final int FIND_PATH_WALL_THREAD = 8;
    public static final int FIND_PATH_PLEDGE = 9;
    public static final int FIND_PATH_TREMAUX = 10;
    public static final int FIND_PATH_ROUTING = 11;
    public static final int FIND_PATH_ASTAR = 12;

    public static final Color PATH_COLOR = Color.rgb(255, 254, 106);
    public static final Color SEARCH_AREA_COLOR = Color.rgb(145, 166, 255);
    public static final Color WALL_COLOR = Color.rgb(49, 47, 47);
    public static final Color START_COLOR = Color.rgb(54, 206, 54);
    public static final Color DESTINATION_COLOR = Color.rgb(231, 29, 54);

    public static  void paint(){
        int w = (int)(width - 10) / Maze.WIDTH;
        int h = (int)(height - 10) / Maze.HEIGHT;
        // Draw Maze
        for (int row = 0; row < Maze.WIDTH; row++) {
            for (int col = 0; col < Maze.HEIGHT; col++) {

                Color squareColour;
                // Wall
                if (Maze.maze[row][col] == 1)
                    squareColour = WALL_COLOR;
                else if (Maze.maze[row][col] == 2)      // Start Node
                    squareColour = START_COLOR;
                else if (Maze.maze[row][col] == 9)      // Destination Node
                    squareColour = DESTINATION_COLOR;
                else                                    // Empty Square
                    squareColour = Color.WHITE;

                MainWindow.context.setFill(squareColour);
                MainWindow.context.setStroke(Color.BLACK);
                MainWindow.context.fillRect(w * col, w * row, w, h);
                MainWindow.context.strokeRect(w * col, w * row, w, h);
            }
        }

    }
    public void generateStartEndPoint(){
        Maze.clearStartAndEndPoint();

        int x,y;
        Random random = new Random();
        // generate start point
        while(true){
            x = random.nextInt(N);
            y = random.nextInt(N);
            if (Maze.maze[y][x] == 0){
                startXold = x;
                startYold = y;
                Maze.maze[y][x] = 2;
                break;
            }
        }
        // generate end point
        while(true){
            x = random.nextInt(N);
            y = random.nextInt(N);
            if (Maze.maze[y][x] == 0){
                destXold = x;
                destYold = y;
                Maze.maze[y][x] = 9;
                break;
            }
        }
        paint();

    }
    public void findPath(){
        switch (solverAlgorithm){
            case FIND_PATH_MOUSE:
                findRandomMouse();
                break;
            case FIND_PATH_MOUSE_THREAD:

                break;
            case FIND_PATH_WALL:
                findWallFollower();
                break;
            case FIND_PATH_WALL_THREAD:

                break;
            case FIND_PATH_PLEDGE:

                break;
            case FIND_PATH_TREMAUX:

                break;
            case FIND_PATH_ROUTING:

                break;
            case FIND_PATH_ASTAR:
                findAStar();
                break;
        }
    }
    public void findWallFollower(){
        // Clear Path Squares
        Maze.clearPath();

        clearSearchLists();

        WallFollower.pathfind(Maze.maze, startXold, startYold, destXold, destYold, path, searchArea);

        Maze.maze[startYold][startXold] = 2;
        generateThread.state = FIND_PATH_WALL;
    }

    public void findRandomMouse(){
        // Clear Path Squares
        Maze.clearPath();

        clearSearchLists();

        RandomMouse.pathfind(Maze.maze, startXold, startYold,destXold,destXold,path, searchArea);

        Maze.maze[startYold][startXold] = 2;
        generateThread.state = FIND_PATH_MOUSE;
    }
    public void findAStar(){
        // Clear Path Squares
        Maze.clearPath();

        clearSearchLists();

        AStar.pathfind(Maze.maze, startXold, startYold, destXold, destYold, path, searchArea);

        Maze.maze[startYold][startXold] = 2;
        generateThread.state = FIND_PATH_ASTAR;
    }
    public void clearMaxe(){
        // Initialize Maze
        Maze.clearMaze();

        // Clear Path Squares
        Maze.clearPath();

        Maze.WIDTH = N;
        Maze.HEIGHT = N;

        startYold = 0;
        startXold = 0;
        destYold = 0;
        destXold = 0;
    }
    public void generateInit(){

        clearMaxe();
        paint();
        switch (generatorAlgorithm){
            case GENERATOR_KRUSKAL:
                RandomKruskal.generate(Maze.maze, Maze.getWidth(), Maze.getHeight());
                generateThread.state = GENERATOR_KRUSKAL;
                break;
            case GENERATOR_RECURSIVE_DIVISION:
                RecursiveDivision.generate(Maze.maze, 1, 1, Maze.getWidth() - 2, Maze.getHeight() - 2);
                generateThread.state = GENERATOR_RECURSIVE_DIVISION;
                break;
            case GENERATOR_ALDOUS:
                AldousBorder.generate(Maze.maze,Maze.getWidth(),Maze.getHeight());
                generateThread.state = GENERATOR_ALDOUS;
                break;
            case GENERATOR_PRIM:
                RandomPrim.generate(Maze.maze, Maze.getWidth(), Maze.getHeight());
                generateThread.state = GENERATOR_PRIM;
                break;
            case GENERATOR_DEPTH_FIRST_SEARCH:
                RandomizedDFS.generate(Maze.maze,Maze.getWidth(),Maze.getHeight());
                generateThread.state = GENERATOR_DEPTH_FIRST_SEARCH;
                break;
        }
    }
    private void clearSearchLists() {
        // Clear Path List
        path.clear();

        // Clear Search Area List
        searchArea.clear();
    }
    public static void readFile(String filename){
        try{
            filename = "input.txt";

            File file= new File(filename);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            width = Integer.parseInt(line);
            height = width;

            line = scanner.nextLine();
            N = Integer.parseInt(line);

            line = scanner.nextLine();
            switch (line){
                case "dfs":
                    generatorAlgorithm = GENERATOR_DEPTH_FIRST_SEARCH;
                    break;
                case "kruskal":
                    generatorAlgorithm = GENERATOR_KRUSKAL;
                    break;
                case "prim":
                    generatorAlgorithm = GENERATOR_PRIM;
                    break;
                case "aldous":
                    generatorAlgorithm = GENERATOR_ALDOUS;
                    break;
                case "rec":
                    generatorAlgorithm = GENERATOR_RECURSIVE_DIVISION;
                    break;
            }
            line = scanner.nextLine();
            switch (line){
                case "mouse":
                    solverAlgorithm = FIND_PATH_MOUSE;
                    break;
                case "mouse_thread":
                    solverAlgorithm = FIND_PATH_MOUSE_THREAD;
                    break;
                case "wall":
                    solverAlgorithm = FIND_PATH_WALL;
                    break;
                case "wall_thread":
                    solverAlgorithm = FIND_PATH_WALL_THREAD;
                    break;
                case "pledge":
                    solverAlgorithm = FIND_PATH_PLEDGE;
                    break;
                case "tremaux":
                    solverAlgorithm = FIND_PATH_TREMAUX;
                    break;
                case "routing":
                    solverAlgorithm = FIND_PATH_ROUTING;
                    break;
                case "astar":
                    solverAlgorithm = FIND_PATH_ASTAR;
                    break;

            }
            scanner.close();
        }catch (Exception e){
            System.out.println("Please check input file");
        }
    }
    @Override
    public void init() throws Exception {
        super.init();
        clearMaxe();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        init();

        GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(100, 65, 35), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, width + 200, height);

        Button generateButton = new Button("Generate Maze");
        generateButton.setPrefSize(100,50);
        generateButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                generateInit();
            }
        });

        Button setStartEndButton = new Button("Set End Button");
        setStartEndButton.setPrefSize(100,50);
        setStartEndButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                generateStartEndPoint();
            }
        });

        Button findPathButton = new Button("Find Path");
        findPathButton.setPrefSize(100,50);
        findPathButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                findPath();
            }
        });

        GridPane gridPane = new GridPane();
        Canvas canvas  = new Canvas(width,height);
        gridPane.getChildren().add(canvas);
        context = canvas.getGraphicsContext2D();
        paint();
        generateThread = new GenerateThread();
        generateThread.context = context;

        generateThread.start();

        GridPane buttonPanel = new GridPane();
        buttonPanel.addRow(0,generateButton);
        buttonPanel.addRow(1,setStartEndButton);
        buttonPanel.addRow(2,findPathButton);

        root.addColumn(0,gridPane);
        root.addColumn(1,buttonPanel);

        primaryStage.setTitle("Surgery Control Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    @Override
    public void stop(){
        generateThread.stop();
    }
    public static void main(String[] args) {
        System.out.println(args[0]);
        readFile(args[0]);
        //readFile("input.txt");

        launch(args);
    }


}
