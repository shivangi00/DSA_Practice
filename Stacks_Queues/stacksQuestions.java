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
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
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

    public static void main(String[] args) {
        stacksQuestions nextGreatest = new stacksQuestions();
        System.out.println(nextGreatest);
    }
}

