# Game of Life

## Design Decisions

1. The grid is infinite, but since the computer memory is limited, the left and right edges of the field are considered 
to be stitched together, and the top and bottom edges also, yielding a toroidal array.
[[↗]](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Algorithms)
2. The API works with the grid as a dense matrix, a sparse one is possible to implement. This should be based on a
discussion with the API consumers rather than an arbitrary decision.

## Possible Improvements

1. The algorithm processes each cell in the grid, we can optimize this by tracking live cells and updating their
surroundings only.
2. Generating GIF output in the API response.

## The Problem

Do an implementation of Conway's Game of Life.

### Rules

The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells,
each of which is in one of two possible states, live or dead (or populated and unpopulated,
respectively). Every cell interacts with its eight neighbors, which are the cells that are
horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions
occur:
1. Any live cell with fewer than two live neighbors dies, as if by underpopulation.
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbors dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
1. Any live cell with two or three live neighbors survives.
2. Any dead cell with three live neighbors becomes a live cell.
3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.

The initial pattern constitutes the seed of the system. The first generation is created by applying
the above rules simultaneously to every cell in the seed, live or dead; births and deaths occur
simultaneously, and the discrete moment at which this happens is sometimes called a tick. Each
generation is a pure function of the preceding one. The rules continue to be applied repeatedly
to create further generations.

## Expected Outcome

You should create a REST API that simulates the Game of Life. The API should be able to
receive the initial pattern (seed) and the number of iterations and return the status of the grid
after these iterations.

**Note:** If an object is partially outside the grid, it should be treated as if the grid was infinite.
If you find it useful you can add any other functionality on top of the required one.

## Resources

- Wikipedia page: [https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
- YouTube video with many different shapes: [https://www.youtube.com/watch?v=C2vgICfQawE](https://www.youtube.com/watch?v=C2vgICfQawE)