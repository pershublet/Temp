import java.util.AbstractMap.*;


class Solution {
    private static int[][] dir = {{0, 1}, {1, 0}};


    public int minCost(int[][] grid, int k) {
        final int m = grid.length;
        final int n = grid[0].length;
        final int M = m - 1;
        final int N = n - 1;
        final int[][][] table = new int[m][n][k + 1];
        final PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((x, y) -> x[3] == y[3] ? y[2] - x[2] : x[3] - y[3]);
        //priorityQueue.offer(new State(0, 0, k, 0));
        priorityQueue.offer(new int[] {0, 0, k, 0});
        final TreeMap<Integer, HashSet<int[]>> treeMap = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!treeMap.containsKey(grid[i][j])) {
                    treeMap.put(grid[i][j], new HashSet<>());
                }

                treeMap.get(grid[i][j]).add(new int[] {i, j});
                Arrays.fill(table[i][j], Integer.MAX_VALUE);
            }
        }

        Arrays.fill(table[0][0], 0);

        while (!priorityQueue.isEmpty()) {
            final int[] curr = priorityQueue.poll();
            final int i = curr[0];
            final int j = curr[1];
            final int t = curr[2];
            final int s = t - 1;
            final int cost = curr[3];
            // System.out.println(i + " " + j + " " + t + " " + cost);
            if (i == M && j == N) {
                return c;
            }

            for (int[] d : dir) {
                final int x = i + d[0];
                final int y = j + d[1];

                if (x < m && y < n) {
                    final int newC = grid[x][y] + c;

                    if (newC < table[x][y][t]) {
                        //priorityQueue.offer(new State(x, y, t, newC));
                        
                        for (int h = t; h >= 0 && newC < table[x][y][h]; h--) {
                            table[x][y][h] = newC;
                        }
                    }
                }
            }

            if(s >= 0) {
                treeMap.headMap(grid[i][j], true).values().forEach(hs -> hs.forEach(a -> {
                    final int x = a[0];
                    final int y = a[1];

                    if (c < table[x][y][s]) {
                        //priorityQueue.offer(new State(x, y, s, c));
                        
                        for (int h = s; h >= 0 && c < table[x][y][h]; h--) {
                            table[x][y][h] = c;
                        }
                    }
                }));
            }
        }

        return 0;
    }
}


class State implements Comparable<State> {
    private int i;
    private int j;
    private int teleportations;
    private int cost;


    State(final int i, final int j, final int teleportations, final int cost) {
        this.setI(i);
        this.setJ(j);
        this.setTeleportations(teleportations);
        this.setCost(cost);
    }


    int getI() {
        return this.i;
    }


    private void setI(final int i) {
        this.i = i;
    }


    int getJ() {
        return this.j;
    }


    private void setJ(final int j) {
        this.j = j;
    }


    int getTeleportations() {
        return this.teleportations;
    }


    private void setTeleportations(final int teleportations) {
        this.teleportations = teleportations;
    }


    int getCost() {
        return this.cost;
    }


    private void setCost(final int cost) {
        this.cost = cost;
    }


    @Override
    public int compareTo(State other) {
        if (this.getCost() == other.getCost()) {
            return Integer.compare(other.getTeleportations(), this.getTeleportations());
        } else {
            return Integer.compare(this.getCost(), other.getCost());
        }
    }
}
