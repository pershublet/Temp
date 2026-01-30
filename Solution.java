import java.util.AbstractMap.*;


class Solution {
    private static int[][] dir = {{0, 1}, {1, 0}};


    public int minCost(int[][] grid, int k) {
        final int m = grid.length;
        final int n = grid[0].length;
        final int M = m - 1;
        final int N = n - 1;
        final int[][][] table = new int[m][n][k + 1];
        final PriorityQueue<State> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new State(0, 0, k, 0));
        final TreeMap<Integer, HashSet<SimpleImmutableEntry<Integer, Integer>>> treeMap = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!treeMap.containsKey(grid[i][j])) {
                    treeMap.put(grid[i][j], new HashSet<>());
                }

                treeMap.get(grid[i][j]).add(new SimpleImmutableEntry<>(i, j));
                Arrays.fill(table[i][j], Integer.MAX_VALUE);
            }
        }

        Arrays.fill(table[0][0], 0);

        while (!priorityQueue.isEmpty()) {
            State currState = priorityQueue.poll();
            final int i = currState.getI();
            final int j = currState.getJ();
            final int t = currState.getTeleportations();
            final int s = t - 1;
            final int cost = currState.getCost();
            // System.out.println(i + " " + j + " " + t + " " + cost);
            if (i == M && j == N) {
                return cost;
            }

            for (int[] d : dir) {
                final int x = i + d[0];
                final int y = j + d[1];

                if (x < m && y < n) {
                    final int newCost = grid[x][y] + cost;

                    if (newCost < table[x][y][t]) {
                        priorityQueue.offer(new State(x, y, t, newCost));
                        
                        for (int i = t; i >= 0 && newCost < table[x][y][i];i--) {
                            table[x][y][i] = newCost;
                        }
                    }
                }
            }

            if(s >= 0) {
                treeMap.headMap(grid[i][j], true).values().forEach(hs -> hs.forEach(e -> {
                    final int x = e.getKey();
                    final int y = e.getValue();

                    if (cost < table[x][y][s]) {
                        priorityQueue.offer(new State(x, y, s, cost));
                        
                        for (int i = s; i >= 0 && newCost < table[x][y][i];i--) {
                            table[x][y][i] = newCost;
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
