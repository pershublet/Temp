class Solution {
    public boolean isWordExist(char[][] mat, String word) {
        final int n = mat.length;
        final int m = mat[0].length;
        final int w = word.length();
        final HashMap<Character, Integer> targetMap = new HashMap<>();
        Arrays.stream(word.toCharArray())
            .forEach(c -> targetMap.put(c, targeting.getOrDefault(, 0) + 1);
        final HashSet<Integer> hashSet = new HashSet<>();
        
        for (int x = 1; x <= w; x ++) {
            if (w % x != 0) {
                continue;
            }

            final int y = w / x;
        }
        
    }
}
