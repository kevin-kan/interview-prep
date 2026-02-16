# Complete DSA Cheat Sheet (Java)

## 🎯 Problem-Solving Framework

1. **Understand the problem**: Read carefully, ask clarifying questions
2. **Explore examples**: Walk through 2-3 examples (including edge cases)
3. **Choose approach**: Brute force → Optimize (time/space trade-offs)
4. **Code**: Write clean, readable code
5. **Test**: Walk through your code with examples
6. **Analyze**: State time/space complexity

---

## 📊 Arrays & Strings

### Common Operations
```java
// Array initialization
int[] arr = new int[5];                    // All zeros
int[] arr = {1, 2, 3, 4, 5};
int[] arr = new int[]{1, 2, 3};

// Array sorting
Arrays.sort(arr);                          // O(n log n)
Arrays.sort(arr, 0, 3);                    // Sort first 3 elements

// Array searching
int index = Arrays.binarySearch(arr, 3);   // O(log n) - array must be sorted
Arrays.fill(arr, -1);                      // Fill with value

// Array copying
int[] copy = Arrays.copyOf(arr, arr.length);
int[] copy = arr.clone();

// String operations
String s = "hello";
char[] chars = s.toCharArray();            // To char array
String sub = s.substring(1, 4);            // "ell" - [start, end)
String[] parts = s.split(",");             // Split by delimiter
String joined = String.join(",", parts);   // Join with delimiter
s = s.trim();                              // Remove whitespace
s = s.toLowerCase();                       // To lowercase
s = s.toUpperCase();                       // To uppercase

// StringBuilder (for string building)
StringBuilder sb = new StringBuilder();
sb.append("hello");                        // O(1) amortized
sb.append(" world");
sb.insert(5, "!");                         // Insert at index
sb.delete(5, 6);                           // Delete range
sb.reverse();                              // Reverse
String result = sb.toString();
```

### Pattern: Kadane's Algorithm (Max Subarray Sum)
```java
public int maxSubArray(int[] nums) {
    int maxSum = nums[0];
    int currentSum = nums[0];
    
    for (int i = 1; i < nums.length; i++) {
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        maxSum = Math.max(maxSum, currentSum);
    }
    
    return maxSum;
}
```

### Pattern: Dutch National Flag (3-way partition)
**Use for**: Sort colors, partition array
```java
public void sortColors(int[] nums) {
    int low = 0, mid = 0, high = nums.length - 1;
    
    while (mid <= high) {
        if (nums[mid] == 0) {
            swap(nums, low++, mid++);
        } else if (nums[mid] == 1) {
            mid++;
        } else {
            swap(nums, mid, high--);
        }
    }
}
```

---

## 🔄 Two Pointers

### When to Use
- Sorted array or can be sorted
- Finding pairs/triplets with certain sum
- Removing duplicates
- Reversing

### Pattern: Two Sum (Sorted Array)
```java
public int[] twoSum(int[] numbers, int target) {
    int left = 0, right = numbers.length - 1;
    
    while (left < right) {
        int sum = numbers[left] + numbers[right];
        if (sum == target) {
            return new int[]{left + 1, right + 1};  // 1-indexed
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    
    return new int[]{-1, -1};
}
```

### Pattern: Remove Duplicates from Sorted Array
```java
public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    
    int slow = 0;  // Points to last unique element
    
    for (int fast = 1; fast < nums.length; fast++) {
        if (nums[fast] != nums[slow]) {
            slow++;
            nums[slow] = nums[fast];
        }
    }
    
    return slow + 1;  // Length of unique elements
}
```

### Pattern: 3Sum
```java
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    
    for (int i = 0; i < nums.length - 2; i++) {
        // Skip duplicates
        if (i > 0 && nums[i] == nums[i-1]) continue;
        
        int left = i + 1, right = nums.length - 1;
        int target = -nums[i];
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                
                // Skip duplicates
                while (left < right && nums[left] == nums[left+1]) left++;
                while (left < right && nums[right] == nums[right-1]) right--;
                
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    return result;
}
```

### Pattern: Container With Most Water
```java
public int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int maxArea = 0;
    
    while (left < right) {
        int area = Math.min(height[left], height[right]) * (right - left);
        maxArea = Math.max(maxArea, area);
        
        // Move pointer with smaller height
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    
    return maxArea;
}
```

---

## 🪟 Sliding Window

### When to Use
- Contiguous subarray/substring problems
- Keywords: "longest", "shortest", "maximum", "minimum"

### Pattern: Fixed-Size Window
```java
// Maximum sum of subarray of size k
public int maxSumSubarray(int[] nums, int k) {
    int windowSum = 0;
    
    // Calculate sum of first window
    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }
    
    int maxSum = windowSum;
    
    // Slide window
    for (int i = k; i < nums.length; i++) {
        windowSum = windowSum - nums[i - k] + nums[i];  // Remove left, add right
        maxSum = Math.max(maxSum, windowSum);
    }
    
    return maxSum;
}
```

### Pattern: Variable-Size Window (Longest Substring Without Repeating Characters)
```java
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int left = 0, maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        
        // If char seen before, move left pointer
        if (map.containsKey(c)) {
            left = Math.max(left, map.get(c) + 1);
        }
        
        map.put(c, right);
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}
```

### Pattern: Minimum Window Substring
```java
public String minWindow(String s, String t) {
    if (s.length() == 0 || t.length() == 0) return "";
    
    Map<Character, Integer> tCount = new HashMap<>();
    for (char c : t.toCharArray()) {
        tCount.put(c, tCount.getOrDefault(c, 0) + 1);
    }
    
    int required = tCount.size();  // Unique chars in t
    int formed = 0;  // Chars with desired frequency in current window
    
    Map<Character, Integer> windowCounts = new HashMap<>();
    int left = 0, right = 0;
    int[] result = {-1, 0, 0};  // {window length, left, right}
    
    while (right < s.length()) {
        char c = s.charAt(right);
        windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
        
        if (tCount.containsKey(c) && windowCounts.get(c).intValue() == tCount.get(c).intValue()) {
            formed++;
        }
        
        // Contract window
        while (left <= right && formed == required) {
            c = s.charAt(left);
            
            // Update result if this window is smaller
            if (result[0] == -1 || right - left + 1 < result[0]) {
                result[0] = right - left + 1;
                result[1] = left;
                result[2] = right;
            }
            
            windowCounts.put(c, windowCounts.get(c) - 1);
            if (tCount.containsKey(c) && windowCounts.get(c) < tCount.get(c)) {
                formed--;
            }
            
            left++;
        }
        
        right++;
    }
    
    return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
}
```

---

## 🔍 Binary Search

### When to Use
- Sorted array
- Finding target or insertion position
- Minimizing/maximizing a value

### Template
```java
public int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;  // Avoid overflow
        
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return -1;  // Not found
}
```

### Pattern: First/Last Occurrence
```java
// Find first occurrence
public int findFirst(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            result = mid;
            right = mid - 1;  // Keep searching left
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return result;
}

// Find last occurrence: change to left = mid + 1 when found
```

### Pattern: Search in Rotated Sorted Array
```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) return mid;
        
        // Determine which half is sorted
        if (nums[left] <= nums[mid]) {  // Left half is sorted
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {  // Right half is sorted
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    
    return -1;
}
```

### Pattern: Find Peak Element
```java
public int findPeakElement(int[] nums) {
    int left = 0, right = nums.length - 1;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1;  // Peak is on the right
        } else {
            right = mid;  // Peak is on the left or at mid
        }
    }
    
    return left;
}
```

---

## 🔗 Linked Lists

### Definition
```java
public class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
}
```

### Pattern: Reverse Linked List
```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    
    return prev;
}
```

### Pattern: Fast & Slow Pointers (Cycle Detection)
```java
public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow == fast) return true;
    }
    
    return false;
}

// Find cycle start
public ListNode detectCycle(ListNode head) {
    if (head == null) return null;
    
    ListNode slow = head, fast = head;
    
    // Find meeting point
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) break;
    }
    
    if (fast == null || fast.next == null) return null;
    
    // Reset slow to head, move both at same pace
    slow = head;
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }
    
    return slow;
}
```

### Pattern: Merge Two Sorted Lists
```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            curr.next = l1;
            l1 = l1.next;
        } else {
            curr.next = l2;
            l2 = l2.next;
        }
        curr = curr.next;
    }
    
    curr.next = (l1 != null) ? l1 : l2;
    
    return dummy.next;
}
```

### Pattern: Remove Nth Node From End
```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    ListNode fast = dummy;
    ListNode slow = dummy;
    
    // Move fast n+1 steps ahead
    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }
    
    // Move both until fast reaches end
    while (fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    
    // Remove node
    slow.next = slow.next.next;
    
    return dummy.next;
}
```

---

## 📚 Stacks & Queues

### Stack Applications
```java
Stack<Integer> stack = new Stack<>();
stack.push(1);              // O(1)
int top = stack.pop();      // O(1)
int peek = stack.peek();    // O(1)
boolean empty = stack.isEmpty();
```

### Pattern: Valid Parentheses
```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put(']', '[');
    map.put('}', '{');
    
    for (char c : s.toCharArray()) {
        if (map.containsKey(c)) {  // Closing bracket
            if (stack.isEmpty() || stack.pop() != map.get(c)) {
                return false;
            }
        } else {  // Opening bracket
            stack.push(c);
        }
    }
    
    return stack.isEmpty();
}
```

### Pattern: Daily Temperatures (Monotonic Stack)
```java
public int[] dailyTemperatures(int[] temperatures) {
    int[] result = new int[temperatures.length];
    Stack<Integer> stack = new Stack<>();  // Store indices
    
    for (int i = 0; i < temperatures.length; i++) {
        while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            int idx = stack.pop();
            result[idx] = i - idx;
        }
        stack.push(i);
    }
    
    return result;
}
```

### Queue Applications
```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(1);             // O(1)
int front = queue.poll();   // O(1)
int peek = queue.peek();    // O(1)
boolean empty = queue.isEmpty();
```

---

## 🔄 Dynamic Programming

### When to Use DP
- Problem has **overlapping subproblems**
- Problem has **optimal substructure**
- Keywords: "maximize", "minimize", "count ways", "longest", "shortest"

### Pattern: Fibonacci-Style (1D DP)
```java
// Climbing Stairs
public int climbStairs(int n) {
    if (n <= 2) return n;
    
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    
    return dp[n];
}

// Space optimized O(1)
public int climbStairsOptimized(int n) {
    if (n <= 2) return n;
    
    int prev2 = 1, prev1 = 2;
    
    for (int i = 3; i <= n; i++) {
        int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}
```

### Pattern: House Robber
```java
public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    
    int prev2 = nums[0];
    int prev1 = Math.max(nums[0], nums[1]);
    
    for (int i = 2; i < nums.length; i++) {
        int curr = Math.max(prev1, prev2 + nums[i]);
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}
```

### Pattern: Coin Change
```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    
    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (i >= coin) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    
    return dp[amount] > amount ? -1 : dp[amount];
}
```

### Pattern: Longest Increasing Subsequence
```java
public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxLen = 1;
    
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    
    return maxLen;
}
```

### Pattern: Knapsack (0/1)
```java
public int knapsack(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    int[][] dp = new int[n + 1][capacity + 1];
    
    for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= capacity; w++) {
            if (weights[i-1] <= w) {
                dp[i][w] = Math.max(
                    dp[i-1][w],
                    dp[i-1][w - weights[i-1]] + values[i-1]
                );
            } else {
                dp[i][w] = dp[i-1][w];
            }
        }
    }
    
    return dp[n][capacity];
}
```

### Pattern: Longest Common Subsequence
```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i-1) == text2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    
    return dp[m][n];
}
```

### Pattern: Edit Distance
```java
public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    // Base cases
    for (int i = 0; i <= m; i++) dp[i][0] = i;
    for (int j = 0; j <= n; j++) dp[0][j] = j;
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i-1) == word2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1];
            } else {
                dp[i][j] = 1 + Math.min(
                    dp[i-1][j-1],  // Replace
                    Math.min(dp[i-1][j], dp[i][j-1])  // Delete, Insert
                );
            }
        }
    }
    
    return dp[m][n];
}
```

---

## 🌳 Trees

### Tree Node Definition
```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
```

### DFS Traversals
```java
// Inorder (Left → Root → Right)
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    inorderHelper(root, result);
    return result;
}

private void inorderHelper(TreeNode node, List<Integer> result) {
    if (node == null) return;
    inorderHelper(node.left, result);
    result.add(node.val);
    inorderHelper(node.right, result);
}

// Preorder (Root → Left → Right)
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    result.add(root.val);
    result.addAll(preorderTraversal(root.left));
    result.addAll(preorderTraversal(root.right));
    return result;
}

// Postorder (Left → Right → Root)
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    result.addAll(postorderTraversal(root.left));
    result.addAll(postorderTraversal(root.right));
    result.add(root.val);
    return result;
}
```

### BFS (Level-Order)
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        result.add(currentLevel);
    }
    
    return result;
}
```

### Common Tree Patterns

**Max Path Sum:**
```java
private int maxSum;

public int maxPathSum(TreeNode root) {
    maxSum = Integer.MIN_VALUE;
    dfs(root);
    return maxSum;
}

private int dfs(TreeNode node) {
    if (node == null) return 0;
    
    int left = Math.max(0, dfs(node.left));
    int right = Math.max(0, dfs(node.right));
    
    maxSum = Math.max(maxSum, node.val + left + right);
    return node.val + Math.max(left, right);
}
```

**Lowest Common Ancestor:**
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    
    if (left != null && right != null) return root;
    return left != null ? left : right;
}
```

**Validate BST:**
```java
public boolean isValidBST(TreeNode root) {
    return validate(root, null, null);
}

private boolean validate(TreeNode node, Integer min, Integer max) {
    if (node == null) return true;
    
    if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
        return false;
    }
    
    return validate(node.left, min, node.val) && 
           validate(node.right, node.val, max);
}
```

---

## 🏔️ Heaps (Priority Queues)

### When to Use Heaps
- Need **Kth largest/smallest** element
- **Merge K sorted** lists/arrays
- **Top K frequent** elements
- **Running median**

### Java PriorityQueue
```java
// Min-heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(5);           // O(log n)
int min = minHeap.poll();   // O(log n)
int peek = minHeap.peek();  // O(1)

// Max-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Custom comparator
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
```

### Pattern: Kth Largest Element
```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    
    for (int num : nums) {
        heap.offer(num);
        if (heap.size() > k) {
            heap.poll();
        }
    }
    
    return heap.peek();
}
```

### Pattern: Top K Frequent Elements
```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> count = new HashMap<>();
    for (int num : nums) {
        count.put(num, count.getOrDefault(num, 0) + 1);
    }
    
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    
    for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
        heap.offer(new int[]{entry.getValue(), entry.getKey()});
        if (heap.size() > k) heap.poll();
    }
    
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
        result[i] = heap.poll()[1];
    }
    
    return result;
}
```

---

## 🕸️ Graphs

### Graph Representations
```java
// Adjacency List
Map<Integer, List<Integer>> graph = new HashMap<>();
// Or
List<List<Integer>> graph = new ArrayList<>();

// Adjacency Matrix
int[][] graph = new int[n][n];

// Edge List
int[][] edges = {{0, 1}, {0, 2}, {1, 3}};
```

### DFS (Depth-First Search)
```java
// Recursive
public void dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
    visited.add(node);
    
    for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
            dfs(graph, neighbor, visited);
        }
    }
}

// Iterative
public void dfsIterative(Map<Integer, List<Integer>> graph, int start) {
    Set<Integer> visited = new HashSet<>();
    Stack<Integer> stack = new Stack<>();
    stack.push(start);
    
    while (!stack.isEmpty()) {
        int node = stack.pop();
        if (!visited.contains(node)) {
            visited.add(node);
            
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }
}
```

### BFS (Breadth-First Search)
```java
public void bfs(Map<Integer, List<Integer>> graph, int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    
    visited.add(start);
    queue.offer(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
    }
}
```

### Pattern: Number of Islands
```java
public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    
    int count = 0;
    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {
            if (grid[r][c] == '1') {
                count++;
                dfs(grid, r, c);
            }
        }
    }
    
    return count;
}

private void dfs(char[][] grid, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') {
        return;
    }
    
    grid[r][c] = '0';
    dfs(grid, r + 1, c);
    dfs(grid, r - 1, c);
    dfs(grid, r, c + 1);
    dfs(grid, r, c - 1);
}
```

### Pattern: Topological Sort
```java
public List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
    Set<Integer> visited = new HashSet<>();
    Stack<Integer> stack = new Stack<>();
    
    for (int node : graph.keySet()) {
        if (!visited.contains(node)) {
            dfs(graph, node, visited, stack);
        }
    }
    
    List<Integer> result = new ArrayList<>();
    while (!stack.isEmpty()) {
        result.add(stack.pop());
    }
    
    return result;
}

private void dfs(Map<Integer, List<Integer>> graph, int node, 
                 Set<Integer> visited, Stack<Integer> stack) {
    visited.add(node);
    
    for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
            dfs(graph, neighbor, visited, stack);
        }
    }
    
    stack.push(node);
}
```

### Pattern: Dijkstra's Algorithm (Shortest Path)
```java
public Map<Integer, Integer> dijkstra(Map<Integer, List<int[]>> graph, int start) {
    Map<Integer, Integer> distances = new HashMap<>();
    for (int node : graph.keySet()) {
        distances.put(node, Integer.MAX_VALUE);
    }
    distances.put(start, 0);
    
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    heap.offer(new int[]{0, start});
    
    Set<Integer> visited = new HashSet<>();
    
    while (!heap.isEmpty()) {
        int[] current = heap.poll();
        int dist = current[0], node = current[1];
        
        if (visited.contains(node)) continue;
        visited.add(node);
        
        for (int[] edge : graph.getOrDefault(node, new ArrayList<>())) {
            int neighbor = edge[0], weight = edge[1];
            int newDist = dist + weight;
            
            if (newDist < distances.get(neighbor)) {
                distances.put(neighbor, newDist);
                heap.offer(new int[]{newDist, neighbor});
            }
        }
    }
    
    return distances;
}
```

---

## 🔙 Backtracking

### When to Use
- Generate all permutations/combinations
- Solve constraint satisfaction problems
- Explore all possible solutions

### Template
```java
public void backtrack(/* state */) {
    if (/* base case */) {
        // Add solution
        return;
    }
    
    for (/* each choice */) {
        // Make choice
        backtrack(/* new state */);
        // Undo choice
    }
}
```

### Pattern: Permutations
```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current, 
                       int[] nums, boolean[] used) {
    if (current.size() == nums.length) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;
        
        current.add(nums[i]);
        used[i] = true;
        
        backtrack(result, current, nums, used);
        
        current.remove(current.size() - 1);
        used[i] = false;
    }
}
```

### Pattern: Combinations (Subsets)
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, 0);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current, 
                       int[] nums, int start) {
    result.add(new ArrayList<>(current));
    
    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);
        backtrack(result, current, nums, i + 1);
        current.remove(current.size() - 1);
    }
}
```

### Pattern: Combination Sum
```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(result, new ArrayList<>(), candidates, target, 0);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current, 
                       int[] candidates, int remain, int start) {
    if (remain < 0) return;
    if (remain == 0) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    for (int i = start; i < candidates.length; i++) {
        current.add(candidates[i]);
        backtrack(result, current, candidates, remain - candidates[i], i);
        current.remove(current.size() - 1);
    }
}
```

### Pattern: N-Queens
```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++) {
        Arrays.fill(board[i], '.');
    }
    
    backtrack(result, board, 0);
    return result;
}

private void backtrack(List<List<String>> result, char[][] board, int row) {
    if (row == board.length) {
        result.add(construct(board));
        return;
    }
    
    for (int col = 0; col < board.length; col++) {
        if (isValid(board, row, col)) {
            board[row][col] = 'Q';
            backtrack(result, board, row + 1);
            board[row][col] = '.';
        }
    }
}

private boolean isValid(char[][] board, int row, int col) {
    // Check column
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') return false;
    }
    
    // Check diagonal (top-left)
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') return false;
    }
    
    // Check diagonal (top-right)
    for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
        if (board[i][j] == 'Q') return false;
    }
    
    return true;
}

private List<String> construct(char[][] board) {
    List<String> result = new ArrayList<>();
    for (char[] row : board) {
        result.add(new String(row));
    }
    return result;
}
```

---

## 🔢 Bit Manipulation

### Common Operations
```java
// Check if bit is set
boolean isSet = (num & (1 << i)) != 0;

// Set bit
num |= (1 << i);

// Clear bit
num &= ~(1 << i);

// Toggle bit
num ^= (1 << i);

// Get rightmost set bit
int rightmost = num & (-num);

// Clear rightmost set bit
num &= (num - 1);

// Count set bits
int count = Integer.bitCount(num);
```

### Pattern: Single Number
```java
public int singleNumber(int[] nums) {
    int result = 0;
    for (int num : nums) {
        result ^= num;  // XOR: a ^ a = 0, a ^ 0 = a
    }
    return result;
}
```

### Pattern: Power of Two
```java
public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
}
```

### Pattern: Reverse Bits
```java
public int reverseBits(int n) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
        result <<= 1;
        result |= (n & 1);
        n >>= 1;
    }
    return result;
}
```

---

## 📊 Hash Maps & Hash Sets

### Common Operations
```java
// HashMap
Map<String, Integer> map = new HashMap<>();
map.put("key", 1);                      // O(1) avg
int val = map.get("key");               // O(1) avg
map.getOrDefault("key", 0);             // O(1) avg
boolean has = map.containsKey("key");   // O(1) avg
map.remove("key");                      // O(1) avg

// Iterate
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    int value = entry.getValue();
}

// HashSet
Set<Integer> set = new HashSet<>();
set.add(1);                             // O(1) avg
boolean has = set.contains(1);          // O(1) avg
set.remove(1);                          // O(1) avg
```

### Pattern: Two Sum
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    
    return new int[]{-1, -1};
}
```

### Pattern: Group Anagrams
```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    
    for (String str : strs) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);
        
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(str);
    }
    
    return new ArrayList<>(map.values());
}
```

---

## ⏱️ Time & Space Complexities Summary

| Data Structure | Access | Search | Insert | Delete | Space |
|----------------|--------|--------|--------|--------|-------|
| Array | O(1) | O(n) | O(n) | O(n) | O(n) |
| ArrayList | O(1) | O(n) | O(1)* | O(n) | O(n) |
| LinkedList | O(n) | O(n) | O(1) | O(1) | O(n) |
| Stack | O(n) | O(n) | O(1) | O(1) | O(n) |
| Queue | O(n) | O(n) | O(1) | O(1) | O(n) |
| HashMap | - | O(1)* | O(1)* | O(1)* | O(n) |
| HashSet | - | O(1)* | O(1)* | O(1)* | O(n) |
| TreeMap | - | O(log n) | O(log n) | O(log n) | O(n) |
| PriorityQueue | - | O(n) | O(log n) | O(log n) | O(n) |
| Binary Search | - | O(log n) | - | - | - |

*Amortized

| Algorithm | Time | Space |
|-----------|------|-------|
| DFS/BFS | O(V + E) | O(V) |
| Binary Search | O(log n) | O(1) |
| Quick Sort | O(n log n) avg, O(n²) worst | O(log n) |
| Merge Sort | O(n log n) | O(n) |
| Dijkstra | O((V+E) log V) | O(V) |

---

## 🎯 Pattern Recognition Guide

**See these keywords** → **Think this pattern:**

- "longest/shortest substring" → Sliding Window
- "subarray with sum K" → Prefix Sum or Sliding Window
- "two numbers that sum to target" → Two Pointers or HashMap
- "sorted array" → Binary Search or Two Pointers
- "Kth largest/smallest" → Heap
- "top K elements" → Heap
- "connected components" → DFS/BFS (Union Find)
- "islands" → DFS/BFS
- "cycle detection" → DFS with colors / Fast & Slow Pointers
- "topological sort" → DFS or BFS (Kahn's)
- "shortest path" → BFS (unweighted) or Dijkstra (weighted)
- "maximize/minimize" → DP or Greedy
- "count ways" → DP
- "generate all combinations/permutations" → Backtracking
- "valid parentheses/brackets" → Stack
- "next greater element" → Monotonic Stack
- "level order traversal" → BFS
- "inorder/preorder/postorder" → DFS
- "binary search tree" → Inorder traversal (sorted)

---

## 💡 Interview Tips

1. **Clarify requirements**: Ask about input constraints, edge cases, expected output
2. **Think out loud**: Share your thought process with the interviewer
3. **Start simple**: Brute force → Optimize
4. **Test your code**: Walk through with examples before submitting
5. **Edge cases**: null, empty, single element, duplicates, negative numbers
6. **Time/space analysis**: Always state complexity at the end
7. **Don't panic**: If stuck, explain your thinking and ask for hints

---

## 🔧 Java Quick Reference

```java
// Math utilities
int max = Math.max(a, b);
int min = Math.min(a, b);
int abs = Math.abs(a);
double sqrt = Math.sqrt(a);
double pow = Math.pow(a, b);

// Arrays
Arrays.sort(arr);
Arrays.fill(arr, value);
int[] copy = Arrays.copyOf(arr, length);
boolean equals = Arrays.equals(arr1, arr2);

// Collections
Collections.sort(list);
Collections.reverse(list);
Collections.shuffle(list);
int max = Collections.max(list);
int min = Collections.min(list);

// Character checks
Character.isDigit(c);
Character.isLetter(c);
Character.isLetterOrDigit(c);
Character.toLowerCase(c);
Character.toUpperCase(c);

// String operations
s.charAt(i);
s.length();
s.substring(start, end);  // [start, end)
s.indexOf(substring);
s.split(delimiter);
String.join(delimiter, array);
```

Good luck! 🚀
