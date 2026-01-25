class Solution {
    int findWays(int n) {
        final int o = n / 2;
        final HashSet<String>[] hashSets = new HashSet[o + 1];
        hashSets[0] = new HashSet<>();
        hashSets[1] = new HashSet<>();
        hashSets[0].add("");
        hashSets[1].add("()");
        
        for (int i = 2; i <= o; i++) {
            final HashSet<String> hs = new HashSet<>();
            hashSets[i] = hs;
            
            for (int j = (i + 1) / 2; j <= i; j++) {
                for (String s1 : hashSets[j]) {
                    for (String s2 : hashSets[o - j]) {
                        hs.add(s1 + s2);
                    }
                }
            }
            
            for (int j = (i + 1) / 2; j < i; j++) {
                for (String s1 : hashSets[j]) {
                    for (String s2 : hashSets[o - j - 1]) {
                        hs.add("(" + s1 + s2 + ")");
                        hs.add("(" + s2 + s1 + ")");
                    }
                }
            }
        }
        for(String s: hashSets[o]){System.out.print(s + " ");}
        return 1;//hashSets[o].size();
    }
}
