//Pun Bahadur Chhetri
//Kunj Bhavsar
//CS351-Project4
//Mazes-
package Pathfinding;

import Visualization.MainWindow;

import java.util.List;

public class WallFollower {
    public static void pathfind(int[][] maze, int xStart, int yStart, int xDest, int yDest, List<Integer> path, List<Integer> searchArea) {
            int facing = 0;
            int currentRow = xStart;
            int currentCol = yStart;
            int numMoves = 0;

            while (true) {
                boolean noMoveFound = true;
                switch (facing){
                    case 0:
                        if (maze[currentCol - 1][currentRow] != 1){
                            currentCol--;                          //turns to the left
                            facing = 1;
                            noMoveFound = false;
                        }
                        else if (maze[currentCol][currentRow - 1] != 1){
                            currentRow--;                          //moves forward
                            facing = 0;
                            noMoveFound = false;
                        }
                        else if (maze[currentCol + 1][currentRow] != 1){
                            currentCol++;                          //turns to the right
                            facing = 3;
                            noMoveFound = false;
                        }
                        else if (maze[currentCol][currentRow + 1] != 1){
                            currentRow++;                         //moves backwards
                            facing = 2;
                            noMoveFound = false;
                        }
                        break;
                    case 1:
                        if (maze[currentCol][currentRow + 1] != 1){
                            currentRow++;                        //turns to the left
                            facing = 2;
                            noMoveFound = false;
                        }
                        else if(maze[currentCol -1][currentRow] != 1){
                            currentCol--;                         //keeps moving forward
                            facing = 1;
                            noMoveFound = false;
                        }
                        else if (maze[currentCol][currentRow - 1] != 1){
                            currentRow--;   //turns to the right
                            facing=0;
                            noMoveFound = false;
                        }
                        else if(maze[currentCol + 1][currentRow] != 1){
                            currentCol++;                          //moves backwards
                            facing = 3;
                            noMoveFound = false;
                        }
                        break;
                    case 2:
                        if(maze[currentCol + 1][currentRow] != 1){
                            currentCol++;                          //turns to the left
                            facing = 3;
                            noMoveFound = false;
                        }
                        else if(maze[currentCol][currentRow + 1] != 1){
                            currentRow++;                        //continues forward
                            facing = 2;
                            noMoveFound = false;
                        }
                        else if(maze[currentCol - 1][currentRow] != 1){
                            currentCol--;                         //turns to the right
                            facing = 1;
                            noMoveFound = false;
                        }
                        else if (maze[currentCol][currentRow - 1] != 1){
                            currentRow--;                          //moves backwards
                            facing = 0;
                            noMoveFound = false;
                        }
                        break;
                    case 3:
                        if (maze[currentCol][currentRow -1] != 1){
                            currentRow--;                          //turns to the left
                            facing = 0;
                            noMoveFound = false;
                        }
                        else if(maze[currentCol + 1][currentRow] != 1){
                            currentCol++;                          //moves forward
                            facing = 3;
                            noMoveFound = false;
                        }
                        else if(maze[currentCol][currentRow + 1] != 1){
                            currentRow++;                        //turns to the right
                            facing = 2;
                            noMoveFound = false;
                        }
                        else if(maze[currentCol - 1][currentRow] != 1){
                            currentCol--;                         //moves backwards
                            facing = 1;
                            noMoveFound = false;
                        }
                        break;
                }
                if (currentCol == yDest && currentRow == xDest)
                    break;
                if (!noMoveFound){
                    maze[currentCol][currentRow] = 3;
                    searchArea.add(currentRow);
                    searchArea.add(currentCol);
                }
                else
                    break;

                numMoves++;
            }
            for (int p = 0; p < searchArea.size(); p += 2) {
                int searchX = searchArea.get(searchArea.size() - p - 2);
                int searchY = searchArea.get(searchArea.size() - p - 1);
                path.add(searchX);
                path.add(searchY);
            }
        }
}
