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

    public static void main(String[] args) {
        stacksQuestions nextGreatest = new stacksQuestions();
        System.out.println(nextGreatest);
    }
}