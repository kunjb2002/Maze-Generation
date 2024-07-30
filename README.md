Authors: Kunj Bhavsar &
Pun Bahadur Chhetri

CS351-Project4


Mazes:

1) We implemented the generating algorithm as follows:
- Randomized Depth First Search.
- Randomized Kruskal’s algorithm.
- Randomized Prim’s algorithm.
- Aldous-Border algorithm.
- Recursive Division algorithm.
2) We implemented the solving algorithm as follows:
- Random Mouse algorithm.
- Wall Follower algorithm.
- AStar algorithm.
3) How code works
- set program property in input.txt file like this
900
20
dfs
astar
- after you give this run the code, after running it.
- you should click the "Generate Maze" button.
- after that click the "set end" button it will set the starting and end point.
- then click the "Path" button. It will find the path according to implemented algorithm in the input.txt file.
- for changing property of input.txt file, for changing algorithm we should write dfs or kruskal, prim, Aldous, rec on line 3.
- and for solving algorithm, we should write mouse or wall, astar on line 4.
- and for interface size you can give accordingly, we gave 900 and 20.
4) Program works : 
- it will generate maze according to input in txt file (we gave name as MazeGeneration in code).
- after than it will set the starting and end point, to find the path (green starting point and red ending point).
- at last it will find the path according to input in txt file.
- We made the code simple by printing all the blocks when we press generate maze.
5) Reference : 
- we took help of internet like stackoverflow, and some other websites to solve our small errors like how to link the text file.
- took help of java books by clearing some of our concepts.
6) Errors :
- the whole code works good, but sometimes its giving errors while running mouse solving algorithm. But most of the times its working good.
7) Notes :
- as link of wikipedia in the instruction pdf, it will start solving the maze directly. But, for our better understanding we created the maze so we can see it and then we solved the path of it.


