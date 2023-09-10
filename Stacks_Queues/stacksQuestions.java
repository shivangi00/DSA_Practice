package Stacks_Queues;
import java.util.Arrays;
import java.util.Stack;

public class stacksQuestions{

    // Next Greatest on Right
    public static void NGOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
    }

    // Next Greatest on Left
    public static void NGOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
    }

    public static void NSOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
    }

    public static void NSOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
    }

    // LC: 503
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
    
        Stack<Integer> st = new Stack<>();
            
        for(int i = 0; i < 2 * n; i++){    //we will traverse through nums twice
            while(st.size() != 0 && nums[st.peek()] < nums[i % n]){
                ans[st.pop()] = nums[i % n];
            }
            if(i < n)
                st.push(i);
        }
        return ans;
    }

    // LC: 2454
    public int[] secondGreaterElement(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
    
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        Stack<Integer> temp = new Stack<>();
    
        for(int i = 0; i < n; i++){
    
            while(!st2.empty() && arr[st2.peek()] < arr[i]){
                ans[st2.pop()] = arr[i];
            }
            while(!st1.empty() && arr[st1.peek()] < arr[i]){
                temp.push(st1.pop());
            }
            while(!temp.empty()){
                st2.push(temp.pop());
            }
            st1.push(i);
        }
    
        return ans;
    }

    // GFG: Stock Span Problem
    public static int[] calculateSpan(int price[], int n)
    {
        //using NGOL approach
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(!st.empty() && price[st.peek()] < price[i]){
                ans[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
        
        for(int i = 0; i < n; i++){
            ans[i] = i - ans[i];
        }
        return ans;
    }

    // LC: 903
    // Approach 1: suitable when performance can be compormised as this is easier approach
    class StockSpanner_1 {
        // create a stack of int array type
        Stack<int[]> st;
        int day;

        public StockSpanner_1() {
            this.st = new Stack<>();
            this.day = 0;
            st.push(new int[]{-1, -1}); // {day, data}
        }
            
        public int next(int price) {
            while(st.peek()[0] != -1 && st.peek()[1] <= price) st.pop();
            int span = day - st.peek()[0];
            st.push(new int[]{day++, price});
            return span;
        }
    }
    
    // Approach 2: suitable for high performance
    class Pair{
        int price;
        int span;

        public Pair(int p, int sp){
            price = p;
            span = sp;
        }
    }
    class StockSpanner_2 {
        // create a stack of int array type
        Stack<Pair> st;

        public StockSpanner_2() {
            this.st = new Stack<>();
        }
            
        public int next(int price) {
            int span = 1;

            while(!st.isEmpty() && st.peek().price <= price){
                span += st.peek().span;
                st.pop();
            }

            st.push(new Pair(price, span));

            return span;
        }
    }

    // LC: 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int idx = 0;
        Stack<Integer> st = new Stack<>();
        
        for(int ele : pushed){
            st.push(ele);
            while(st.size() != 0 && popped[idx] == st.peek()){
                st.pop();
                idx++;
            }
        }
        
        return st.size() == 0;
    }

    // LC: 20
    public boolean isValid(String str) {
        int n = str.length();

        Stack<Character> st = new Stack<>();

        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);

            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            } else{
                if(st.size() == 0){
                    return false;
                } else if(ch == ')' && st.peek() != '('){
                    return false;
                } else if(ch == '}' && st.peek() != '{'){
                    return false;
                } else if(ch == ']' && st.peek() != '['){
                    return false;
                } else{
                    st.pop();
                }
            }
        }

        return st.size() == 0;
    }

    // LC: 1249
    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            // push '('
            if(ch == '('){
                st.push(i);
            } else if(ch == ')'){
                // pop if balanced parentheses
                if (!st.isEmpty() && s.charAt(st.peek()) == '(') st.pop();
                // push otherwise
                else st.push(i);
            }
        }
        // now stack contains indices that need to be removed for a valid string
        // initialise a string builder
        StringBuilder ans = new StringBuilder();
        // since using stack, the element at top is the first to be removed from end of string
        // thus iterating right to left
        for(int i = n - 1; i >= 0; i--){
            // pop from stack and ignore the s[i] character as we need to remove it
            if(!st.isEmpty() && st.peek() == i){
                st.pop();
                continue;
            }
            ans.append(s.charAt(i));
        }
        // since traversal was from right to left... reverse the array and convert into string
        return (ans.reverse()).toString();
    }

    // LC: 32
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int len = 0;
        // to handle runtime error for case '()'
        st.push(-1);
        for(int i = 0; i < s.length(); i++){
            if(st.peek() != -1 && s.charAt(st.peek()) == '(' && s.charAt(i) == ')'){
                st.pop();
                len = Math.max(len, i - st.peek());
            } else{
                st.push(i);   
            }
        }
        return len;
    }

    // LC: 735
    public int[] asteroidCollision(int[] asteroids) {
        /*
        Scenarios:
        (+, +) -> no collision
        (+, -) -> collision
        (-, +) -> no collision
        (-, -) -> no collision 
        */

        Stack<Integer> st = new Stack<>();
        for(int asteroid : asteroids){
            // no collision no matter what is on top
            if(asteroid > 0){
                st.push(asteroid);
                continue;
            }
            // +ve on top/ incoming -ve
            // keep popping till st.peek() < -(asteroid)
            while(!st.isEmpty() && st.peek() > 0 && st.peek() < -asteroid) st.pop();
            // if peek is equal to incoming: destroy both
            if(!st.isEmpty() && st.peek() == -asteroid){
                st.pop();
            } else if(st.isEmpty() || st.peek() < 0){
                // if stack becomes empty or top has a -ve value: push incoming
                st.push(asteroid);
            } else{
                // if incoming < peek : destroy it i.e. continue
            }
        }

        int[] ans = new int[st.size()];
        for(int i = ans.length - 1; i >= 0; --i){
            ans[i] = st.pop();
        }

        return ans;
    }

    // LC: 84 Largest Rectangle in Histogram (Approach #1)
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];

        NSOL(heights, nsol);
        NSOR(heights, nsor);

        int maxArea = 0;
        for(int i = 0; i < n; i++){
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }

    // LC: 84 Largest Rectangle in Histogram (Approach #2)
    public int largestRectangleArea_02(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxArea = 0;

        for(int i = 0; i < n; i++){
            while(st.peek() != -1 && heights[st.peek()] > heights[i]){
                int h = heights[st.pop()];
                int w = i - st.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            st.push(i);
        }

        while(st.peek() != -1){
            int h = heights[st.pop()];
            int w = n - st.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }
    
    // LC: 85 Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        if(n == 0 || m == 0) return 0;
        
        int[] heights = new int[m];
        int maxRec = 0;

        for(int i = 0; i < n; i++){
            for(int j =0; j < m; j++){
                char ch = matrix[i][j];
                heights[j] = ch == '1' ? heights[j] + 1 : 0;
            }
            maxRec = Math.max(maxRec, largestRectangleArea(heights));
        }
        return maxRec;
    }

    // LC: 221 Maximal Square (exactly same as Maximal Rectangle with one line change)
    public int largestSquareArea(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];
        
        NSOL(heights, nsol);
        NSOR(heights, nsor);
        
        int maxArea = 0;
        for(int i = 0; i < n; i++){
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;
            // square of the min of h, w will always form a sq
            maxArea = Math.max(maxArea, (h < w) ? h * h : w * w);
        } 
        return maxArea;
    }

    public int maximalSquare(char[][] matrix) {
        return maximalRectangle(matrix);
    }

    public static void main(String[] args) {
        stacksQuestions nextGreatest = new stacksQuestions();
        System.out.println(nextGreatest);
    }
}