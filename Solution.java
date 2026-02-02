class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        final TreeMap<Integer, Integer> treeMsp = new TreeMap<>();

        for(int i = dist + 1; I >= 1; i++) {
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0));
        }

        int answer = treeMap.firstKey();

        if (treeMap.get(answer) >= 2) {
            answer += answer;
        } else {
            answer += higherKey(answer, false);
        }
        
        return answer + nums[0];
    }
}
