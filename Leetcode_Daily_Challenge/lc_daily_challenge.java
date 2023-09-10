package Leetcode_Daily_Challenge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    // 05-09-2023 : Linked List
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

    // 07-09-2023 : Linked List
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

    // 07-09-2023 : Linked List
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

    // 08-09-2023 : Dynamic Programming
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

    // 09-09-2023 : Dynamic Programming
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

    // 10-09-2023 : Dynamic Programming | Combinators
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
}
