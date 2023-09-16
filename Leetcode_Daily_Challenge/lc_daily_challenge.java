package Leetcode_Daily_Challenge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class lc_daily_challenge {
    // 01-09-2023 : Bit Manipulation
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n &= n - 1;
        }
        return count;
    }

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for(int i = 0; i <= n; i++){
            ans[i] = hammingWeight(i);
        }
        return ans;
    }

    // 05-09-2023 : LC 138
    // Definition for a Node
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        HashMap<Node, Node> visited = new HashMap<>();
        
        Node curr = head;
        while (curr != null) {
            visited.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        
        curr = head;
        while (curr != null) {
            visited.get(curr).next = visited.get(curr.next);
            visited.get(curr).random = visited.get(curr.random);
            curr = curr.next;
        }
        
        return visited.get(head);
    }

    // 07-09-2023 : LC 725
    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode curr = head;
        int n = 0;
        while(curr != null){
            curr = curr.next;
            n++;
        }

        int width = n / k, rem = n % k;

        ListNode[] ans = new ListNode[k];
        curr = head;
        for(int i = 0; i < k; i++){
            ListNode start = curr;
            for(int j = 0; j < width + (i < rem ? 1 : 0) - 1; j++){
                if(curr != null) curr = curr.next;
            }
            if(curr != null){
                ListNode prev = curr;
                curr = curr.next;
                prev.next = null;
            }
            ans[i] = start;
        }
        return ans;
    }

    // 07-09-2023 : LC 92
    ListNode h = null, t = null;

    void addFirst(ListNode node){
        if(h == null){
            h = t = node;
        } else{
            node.next = h;
            h = node;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m==n){
            return head;
        }
        
        int idx = 1;
        ListNode curr = head, prev = null;
        
        while(curr != null){
            ListNode fwd = curr.next;
            while(idx >= m && idx <= n){
                fwd = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = fwd;
                idx++;
            }

            if(idx > n){
                if(prev != null){
                    prev.next = h;
                    t.next = curr;
                    return head;
                } else{
                    head = h;
                    t.next = curr;
                    return head;   
                }
            }
            prev = curr;
            curr = curr.next;
            idx++;

        }
        return head;
    }

    // 08-09-2023 : LC 118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        // add first row to the triangle
        triangle.add(new ArrayList<>());
        // Base case : first row contains only 1
        triangle.get(0).add(1);

        for(int row = 1; row < numRows; row++){
            List<Integer> currRow = new ArrayList<>();
            List<Integer> prevRow = triangle.get(row - 1);
            currRow.add(1);

            for(int col = 1; col < row; col++){
                currRow.add(prevRow.get(col - 1) + prevRow.get(col));
            }
            currRow.add(1);
            triangle.add(currRow);
        }
        return triangle;
    }

    // 09-09-2023 : LC 377
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // base case: there exists at least one combinationv
        dp[0] = 1;

        for(int i = 1; i <= target; i++){
            for(int num : nums){
                if(i -  num >= 0){
                    // recurrence relation: target = target - num
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    // 10-09-2023 : Dynamic Programming | Combinators LC 1359
    private static final int MOD = 1000000007;
    
    public int countOrders(int n) {
        // base case
        long count = 1;

        for(int i = 2; i <= n; i++){
            /* recurrence relation : P(i) has 2i-1 choices and D(i) has i choices thus, P(i)D(i) give the total no. of possibilities for ith order all these possibilities are valid for all (i - 1) orders too thus, we multiply P(i)D(i) * count to get total possibilities till ith order */
            count = (count * (2 * i - 1) * i) % MOD;
        }
        return (int) count;
    }

    // 11-09-2023 : Hashmap :C 1282

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int n = groupSizes.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < n; i++){
            // add value in arraylist of map corresponding to key groupSize[i]
            if(!map.containsKey(groupSizes[i])){
                map.put(groupSizes[i], new ArrayList<>());
            }
            // add the index i to the list map[groupSizes[i]] i.e. add it to the list of values corresponding to key groupSizes[i] in map 
            List<Integer> group = map.get(groupSizes[i]);
            group.add(i);
            // if length of list of values (or people) corresponding to key (or group size) equals the key : add the list of values to the result and remove it from the map
            if(group.size() == groupSizes[i]){
                result.add(group);
                map.remove(groupSizes[i]);
            }
        }
        return result;
    }

    // 12-09-2023 : LC 1647
    public int minDeletions(String s) {
        int delCount = 0;
        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i++){
            freq[s.charAt(i) - 'a']++;
        }

        HashSet<Integer> vis = new HashSet<>();
        for(int i = 0; i < 26; i++){
            while(freq[i] > 0 && vis.contains(freq[i])){
                freq[i]--;
                delCount++;
            }
            vis.add(freq[i]);
        }
        return delCount;
    }

    // 13-09-2023 LC 135
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] ans = new int[n];
        int totalCandies = 0;
        // each child to have at least one candy
        Arrays.fill(ans, 1);

        for(int i = 1; i < n; i++){
            if(ratings[i] > ratings[i - 1]){
                ans[i] = ans[i - 1] + 1;
            }
        }

        for(int i = n - 2; i >= 0; i--){
            if(ratings[i] > ratings[i + 1]){
                ans[i] = Math.max(ans[i], ans[i + 1] + 1);
            }
        }

        for(int ele : ans){
            totalCandies += ele;
        }
        return totalCandies;
    }

    // 14-09-2023 : LC 332
    HashMap<String, PriorityQueue<String>>graph;
    LinkedList<String> ans;

    public List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<>();
        ans = new LinkedList<>();
        for(List<String> ticket : tickets){
            PriorityQueue<String> temp = graph.getOrDefault(ticket.get(0), new PriorityQueue<>());
            temp.add(ticket.get(1));
            graph.put(ticket.get(0), temp);
        }

        dfs("JFK");
        return ans;
    }

    public void dfs(String src){
        PriorityQueue<String> nbrs = graph.get(src);
        while(nbrs != null && nbrs.size() > 0){
            String nbr = nbrs.remove();
            dfs(nbr);
        }

        ans.addFirst(src);
        
    }

    // 15-09-2023 : LC 1584
    public static int manhattanDist(int[] p1, int[] p2){
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.add(new int[]{0, 0});

        int mstWeight = 0;

        while(!minHeap.isEmpty()){
            int[] top = minHeap.poll();
            int w = top[0], u = top[1];

            if(visited[u] || map.getOrDefault(u, Integer.MAX_VALUE) < w)continue;
            visited[u] = true;
            mstWeight += w;

            for(int v = 0; v < n; v++){
                if(!visited[v]){
                    int newDist = manhattanDist(points[u], points[v]);
                    if(newDist < map.getOrDefault(v, Integer.MAX_VALUE)){
                        map.put(v, newDist);
                        minHeap.add(new int[]{newDist, v});
                    }
                }
            }
        }
        return mstWeight;
    }

    // 16-09-2023 : LC 1631
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        int[][] distance = new int[rows][cols];
        // pq -> {dist, src, dest}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.add(new int[]{0,0,0});

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        distance[0][0] = 0;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!minHeap.isEmpty()){
            int[] top = minHeap.poll();
            int effort = top[0], row = top[1], col = top[2];

            if(effort > distance[row][col]) continue;

            if(row == rows - 1 && col == cols - 1) return effort;

            for(int[] dir : directions){
                int currRow = row + dir[0], currCol = col + dir[1];
                if(currRow >= 0 && currRow < rows && currCol >= 0 && currCol < cols){
                    int currEffort = Math.max(effort, Math.abs(heights[row][col] - heights[currRow][currCol]));
                    if(currEffort < distance[currRow][currCol]){
                        distance[currRow][currCol] = currEffort;
                        minHeap.add(new int[]{currEffort, currRow, currCol});
                    }
                }
            }
        }
        return -1;
    }
}
