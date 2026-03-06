package com.kevinkan.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
* You are given an array of CPU tasks tasks, where tasks[i] is an uppercase english character from A to Z. You are also given an integer n.
* Each CPU cycle allows the completion of a single task, and tasks may be completed in any order.
* The only constraint is that identical tasks must be separated by at least n CPU cycles, to cooldown the CPU.
* Return the minimum number of CPU cycles required to complete all tasks.
* 
* Constraints:
* 1 <= tasks.length <= 1000
* 0 <= n <= 100
*/
class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int cpuCycles = 0;

        // Count the frequencies of each Task:
        Map<Character, Integer> taskFreq = new HashMap<>();
        for (char task : tasks) {
            taskFreq.put(task, taskFreq.getOrDefault(task, 0) + 1);
        }
        // Load those characters into a Priority Queue for Processing:
        PriorityQueue<TaskFrequency> maxHeap = new PriorityQueue<>((a,b) -> b.frequency - a.frequency);
        for (Map.Entry<Character, Integer> e : taskFreq.entrySet()) {
            maxHeap.offer(new TaskFrequency(e.getKey(), e.getValue()));
        }

        // Process the Priority Queue:
        Deque<TaskFrequency> q = new ArrayDeque<>();
        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            cpuCycles++;

            if (!maxHeap.isEmpty()) {
                TaskFrequency t = maxHeap.poll();
                // Process the task
                t.frequency--;

                // Put the task into timeout (if there's any left)
                if (t.frequency > 0) {
                    t.nextCycle = cpuCycles + n;
                    q.offer(t);
                }
            } else {
                // If main queue is empty, then fast forward time to the next item in wait queue.
                cpuCycles = q.peek().nextCycle;
            }

            // Move items from timeout back into the priority queue.
            while (!q.isEmpty() && q.peek().nextCycle <= cpuCycles) {
                maxHeap.add(q.poll());
            }
            
        }
        
        return cpuCycles;
    }

}

class TaskFrequency {
    char task;
    int frequency;
    int nextCycle;
    
    public TaskFrequency(char task, int frequency) {
        this.task = task;
        this.frequency = frequency;
        this.nextCycle = -1;
    }
}
