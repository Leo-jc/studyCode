package com.serain.study.dataStructure;

import lombok.Getter;

public class Heap {
    private int[] heap;
    @Getter
    private int size;
    private int maxSize;
    private boolean isMaxHeap; // 标识是最大堆还是最小堆

    // 构造函数：默认创建最大堆
    public Heap(int maxSize) {
        this(maxSize, true);
    }

    // 构造函数：可指定是否为最大堆
    public Heap(int maxSize, boolean isMaxHeap) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[this.maxSize];
        this.isMaxHeap = isMaxHeap;
    }

    // 构造函数：从数组创建堆，默认最大堆
    public Heap(int[] array) {
        this(array, true);
    }

    // 构造函数：从数组创建堆，可指定是否为最大堆
    public Heap(int[] array, boolean isMaxHeap) {
        this.maxSize = array.length * 2;
        this.size = array.length;
        this.heap = new int[this.maxSize];
        this.isMaxHeap = isMaxHeap;
        System.arraycopy(array, 0, this.heap, 0, array.length);
        buildHeap();
    }

    // 构建堆
    private void buildHeap() {
        for (int i = this.size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // 查看堆顶元素
    public int peek() {
        if (this.size <= 0) {
            throw new IllegalArgumentException("Heap is empty");
        }
        return this.heap[0];
    }

    // 移除堆顶元素
    public int remove() {
        if (this.size <= 0) {
            throw new IllegalArgumentException("Heap is empty");
        }
        int root = this.heap[0];
        this.heap[0] = this.heap[this.size - 1];
        this.size--;
        heapifyDown(0);
        return root;
    }

    // 向下调整堆（堆化）
    private void heapifyDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int target = i;

        target = getLarget(left, target);

        target = getLarget(right, target);

        if (target != i) {
            int temp = this.heap[i];
            this.heap[i] = this.heap[target];
            this.heap[target] = temp;
            heapifyDown(target);
        }
    }

    private int getLarget(int left, int target) {
        if (left < this.size) {
            if (isMaxHeap) {
                if (this.heap[left] > this.heap[target]) {
                    target = left;
                }
            } else {
                if (this.heap[left] < this.heap[target]) {
                    target = left;
                }
            }
        }
        return target;
    }

    // 尾插法：将元素插入到堆尾，然后向上调整
    public void insert(int value) {
        if (this.size >= this.maxSize) {
            resize();
        }
        this.heap[this.size] = value;
        this.size++;
        heapifyUp(this.size - 1);
    }

    // 头插法：将元素插入到堆顶，然后向下调整
    public void insertAtHead(int value) {
        if (this.size >= this.maxSize) {
            resize();
        }
        
        // 将所有元素后移一位
        System.arraycopy(this.heap, 0, this.heap, 1, this.size);
        
        // 将新元素插入到堆顶
        this.heap[0] = value;
        this.size++;
        
        // 向下调整堆
        heapifyDown(0);
    }

    // 扩展堆的大小
    private void resize() {
        int newMaxSize = this.maxSize * 2;
        int[] newHeap = new int[newMaxSize];
        System.arraycopy(this.heap, 0, newHeap, 0, this.size);
        this.heap = newHeap;
        this.maxSize = newMaxSize;
    }

    // 向上调整堆（堆化）
    private void heapifyUp(int i) {
        int parent = (i - 1) / 2;
        
        while (i > 0) {
            boolean shouldSwap = false;
            
            if (isMaxHeap) {
                if (this.heap[i] > this.heap[parent]) {
                    shouldSwap = true;
                }
            } else {
                if (this.heap[i] < this.heap[parent]) {
                    shouldSwap = true;
                }
            }
            
            if (shouldSwap) {
                int temp = this.heap[i];
                this.heap[i] = this.heap[parent];
                this.heap[parent] = temp;
                i = parent;
                parent = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    // 检查堆是否为空
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试最大堆和尾插法
        System.out.println("=== 测试最大堆和尾插法 ===");
        int[] array = {10, 20, 30, 5, 7, 9, 11, 13, 15, 17};
        Heap maxHeap = new Heap(array);
        maxHeap.insert(18); // 尾插法
        maxHeap.insert(25); // 尾插法
        
        System.out.println("堆大小：" + maxHeap.getSize());
        System.out.println("堆顶元素：" + maxHeap.peek());
        
        System.out.println("堆元素（从大到小）：");
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.remove());
        }
        
        // 测试最小堆和头插法
        System.out.println("\n=== 测试最小堆和头插法 ===");
        Heap minHeap = new Heap(10, false); // 创建最小堆
        minHeap.insert(5); // 尾插法
        minHeap.insert(10); // 尾插法
        minHeap.insert(3); // 尾插法
        
        System.out.println("头插法前堆顶元素：" + minHeap.peek());
        
        minHeap.insertAtHead(1); // 头插法
        minHeap.insertAtHead(8); // 头插法
        
        System.out.println("堆大小：" + minHeap.getSize());
        System.out.println("堆顶元素：" + minHeap.peek());
        
        System.out.println("堆元素（从小到大）：");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.remove());
        }
    }
}