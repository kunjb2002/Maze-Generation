//Pun Bahadur Chhetri
//Kunj Bhavsar
//CS351-Project4
//Mazes-
package Pathfinding;

import java.util.List;

public class RandomMouse {
    public static void visit(int[][] maze,int cx,int cy){
        if (maze[cy][cx] < 3)
            maze[cy][cx] = 3;
        else if (maze[cy][cx] < 8)
            maze[cy][cx]++;
    }
    public static void pathfind(int[][] maze, int xStart, int yStart, int xDest, int yDest, List<Integer> path, List<Integer> searchArea) {
        int cx,cy;
        int dir = 0;
        cx = xStart;
        cy = yStart;
        int dirF = 0;
        int count = 0;
        while(true){

            if (dir == 0){
                if (maze[cy][cx] >= 5 && maze[cy][cx] < 9){
                    if (maze[cy - 1][cx] != 1){
                        dirF = 1;
                        cy--;
                    }
                    else if (maze[cy][cx - 1] != 1){
                        cx--;
                        dirF = 0;
                    }
                    else if (maze[cy + 1][cx] != 1){
                        cy++;
                        dirF = 3;
                    }else{
                        cx++;
                        dirF = 2;
                    }
                }
                else if (maze[cy][cx - 1] != 1){
                    cx--;
                    dirF = 0;
                }
                else{
                    if (maze[cy - 1][cx] != 1){
                        cy--;
                        dirF = 1;
                    }
                    else if (maze[cy + 1][cx] != 1){
                        cy++;
                        dirF = 3;
                    }
                    else{
                        cx++;
                        dirF = 2;
                    }
                }
            }
            else if (dir == 1){
                if (maze[cy][cx] >= 5 && maze[cy][cx] < 9){
                    if (maze[cy][cx + 1] != 1){
                        dirF = 2;
                        cx++;
                    }else if (maze[cy - 1][cx] != 1){
                        cy--;
                        dirF = 1;
                    }
                    else if (maze[cy][cx - 1] != 1){
                        dirF = 0;
                        cx--;
                    }else{
                        cy++;
                        dirF = 3;
                    }
                }else if (maze[cy - 1][cx] != 1){
                    cy--;
                    dirF = 1;
                }
                else{
                    if (maze[cy][cx + 1] != 1){
                        cx++;
                        dirF = 2;
                    }
                    else if (maze[cy][cx - 1] != 1){
                        cx--;
                        dirF = 0;
                    }
                    else{
                        cy++;
                        dirF = 3;
                    }
                }
            }else if (dir == 2){

                if (maze[cy][cx] >= 5 && maze[cy][cx] < 9){
                    if (maze[cy + 1][cx] != 1){
                        dirF = 3;
                        cy++;
                    }else if (maze[cy][cx + 1] != 1){
                        cx++;
                        dirF = 2;
                    }
                    else if (maze[cy - 1][cx] != 1){
                        dirF = 1;
                        cy--;
                    }else{
                        cx--;
                        dirF = 0;
                    }
                }else if (maze[cy][cx + 1] != 1){
                    cx++;
                    dirF = 2;
                }
                else{
                    if (maze[cy + 1][cx] != 1){
                        cy++;
                        dirF = 3;
                    }
                    else if (maze[cy - 1][cx] != 1){
                        cy--;
                        dirF = 1;
                    }
                    else{
                        cx--;
                        dirF = 0;
                    }
                }
            }else if (dir == 3){
                if (maze[cy][cx] >= 5 && maze[cy][cx] < 9){
                    if (maze[cy][cx - 1] != 1){
                        dirF = 0;
                        cx--;
                    }
                    else if (maze[cy + 1][cx] != 1){
                        cy++;
                        dirF = 3;
                    }
                    else if (maze[cy][cx + 1] != 1){
                        dirF = 2;
                        cx++;
                    }else{
                        cy--;
                        dirF = 1;

                    }
                }
                else if (maze[cy + 1][cx] != 1){
                    cy++;
                    dirF = 3;
                }
                else{
                    if (maze[cy][cx - 1] != 1){
                        cx--;
                        dirF = 0;
                    }
                    else if (maze[cy][cx + 1] != 1){
                        cx++;
                        dirF = 2;
                    }
                    else{
                        cy--;
                        dirF = 1;

                    }
                }
            }
            if (maze[cy][cx] == 9)
                break;
            visit(maze,cx,cy);
            dir = dirF;
            if (maze[cy][cx] <= 7){
                searchArea.add(cx);
                searchArea.add(cy);
            }
            count++;

        }
        for (int p = 0; p < searchArea.size(); p += 2) {
            int searchX = searchArea.get(searchArea.size() - p - 2);
            int searchY = searchArea.get(searchArea.size() - p - 1);
            path.add(searchX);
            path.add(searchY);
        }
    }
}
