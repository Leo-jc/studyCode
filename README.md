# Study Code

算法学习与练习项目 - 专注于数据结构、算法和编程能力提升

## 项目简介

本项目是一个综合性的算法学习仓库，包含了 LeetCode 练习题、双周赛/单周赛题目、各大厂面试笔试题以及基础数据结构和操作系统的学习代码。LeetCode 题解按算法类型分类组织，便于系统化学习和复习。

## 项目结构

```
studyCode/                          # Maven 多模块项目 (父 POM)
├── algorithms/                     # 算法练习模块
│   └── src/main/java/com/serain/
│       ├── exercise/leetcode/      # LeetCode 题解（按算法分类）
│       │   ├── array/             # 数组 (32 题)
│       │   ├── backtracking/      # 回溯 (5 题)
│       │   ├── binarysearch/      # 二分查找 (10 题)
│       │   ├── bitmanipulation/   # 位运算 (12 题)
│       │   ├── design/            # 设计题 (1 题)
│       │   ├── dp/                # 动态规划 (32 题)
│       │   ├── graph/             # 图论 (13 题)
│       │   ├── greedy/            # 贪心 (13 题)
│       │   ├── heap/              # 堆/优先队列 (5 题)
│       │   ├── linkedlist/        # 链表 (10 题)
│       │   ├── math/              # 数学 (15 题)
│       │   ├── matrix/            # 矩阵 (16 题)
│       │   ├── monotonic/         # 单调栈/单调队列 (2 题)
│       │   ├── simulation/        # 模拟 (6 题)
│       │   ├── slidingwindow/     # 滑动窗口 (11 题)
│       │   ├── special/           # LCR/面试题 (4 题)
│       │   ├── stack/             # 栈与队列 (4 题)
│       │   ├── string/            # 字符串 (15 题)
│       │   ├── tree/              # 树 (8 题)
│       │   └── trie/              # 字典树 (2 题)
│       ├── exercise/niuke/        # 牛客网练习题 (10 题)
│       ├── doubleweekgame/        # LeetCode 双周赛
│       │   ├── game174/
│       │   ├── game175/
│       │   ├── game178/
│       │   └── game186/
│       ├── singleweekgame/        # LeetCode 单周赛
│       │   ├── game485/
│       │   ├── game486/
│       │   ├── game492/
│       │   └── game508/
│       ├── exam/                  # 大厂面试笔试题
│       │   ├── alibaba/          # 阿里巴巴 (6 题)
│       │   ├── bilibili/         # B站 (2 题)
│       │   ├── bytedance/        # 字节跳动
│       │   ├── dewu/             # 得物 (4 题)
│       │   ├── huawei/           # 华为 (4 题)
│       │   ├── jingdong/         # 京东 (2 题)
│       │   ├── meituan/          # 美团 (3 题)
│       │   ├── tencent/          # 腾讯
│       │   ├── wangyi/           # 网易 (2 题)
│       │   └── xiechen/          # 携程 (4 题)
│       ├── study/                 # 学习代码
│       │   ├── dataStructure/    # 数据结构实现
│       │   └── os/               # 操作系统相关
│       └── parameter/             # 公共数据结构类
│           ├── ListNode.java
│           ├── Node.java
│           └── TreeNode.java
├── tools/
│   └── todo-scanner/              # TODO 扫描工具（独立模块）
│       └── src/main/java/com/serain/tools/scanner/
│           ├── TodoScanner.java
│           ├── BatchFixer.java
│           ├── ScanReport.java
│           └── TodoItem.java
└── pom.xml                        # 父 POM
```

## 题目统计

### LeetCode 练习 (216 题)

| 算法分类 | 题目数 | 代表题目 |
|---------|--------|---------|
| 数组 (array) | 32 | E4, E15, E31, E56, E75, E128, E283, E287, E560 |
| 动态规划 (dp) | 32 | E53, E62, E64, E70, E72, E139, E198, E279, E300, E322, E416, E516 |
| 数学 (math) | 15 | E396, E1344, E1545, E1980, E2033, E3020, E3300, E3756 |
| 矩阵 (matrix) | 16 | E48, E54, E74, E1292, E1582, E1727, E1861, E3070 |
| 字符串 (string) | 15 | E5, E67, E696, E796, E1404, E1758, E1967, E3120 |
| 位运算 (bitmanipulation) | 12 | E136, E190, E693, E762, E868, E1356, E1680, E2657 |
| 图论 (graph) | 13 | E417, E1306, E1345, E1559, E1722, E2812, E2976, E3558 |
| 贪心 (greedy) | 13 | E11, E45, E55, E121, E763, E1288, E1536, E1877 |
| 滑动窗口 (slidingwindow) | 11 | E3, E76, E438, E1004, E1358, E1461, E1888, E1984 |
| 二分查找 (binarysearch) | 10 | E33, E34, E35, E153, E744, E1855, E3464, E3532 |
| 链表 (linkedlist) | 10 | E2, E19, E24, E25, E61, E138, E142, E206, E234 |
| 模拟 (simulation) | 6 | E874, E1291, E1914, E2946, E3633, E3653 |
| 回溯 (backtracking) | 5 | E51, E79, E131, E401, E1415 |
| 堆 (heap) | 5 | E23, E148, E347, E3296, E3507 |
| 树 (tree) | 8 | E98, E102, E108, E110, E543, E1832, E2196, E1022 |
| 栈 (stack) | 4 | E20, E32, E2130, E2751 |
| 特殊 (special) | 4 | E2942, E3161, LCR095, M1707 |
| 字典树 (trie) | 2 | E2977, E3093 |
| 单调栈 (monotonic) | 2 | E239, E3660 |
| 设计 (design) | 1 | E2069 |

### 竞赛题目
- **双周赛**: 4 场 (game174, game175, game178, game186)
- **单周赛**: 4 场 (game485, game486, game492, game508)

### 大厂面试题
- **阿里巴巴**: 6 题 (含 AI 方向)
- **B站**: 2 题
- **得物**: 4 题
- **华为**: 4 题
- **京东**: 2 题
- **美团**: 3 题
- **携程**: 4 题
- **网易**: 2 题

### 牛客网练习
- 10 道笔试题 (BISHI 系列)

## 技术栈

- **语言**: Java 22
- **构建工具**: Maven (多模块)
- **依赖库**:
  - Lombok 1.18.42 (简化代码)
  - JUnit 3.8.1 (单元测试)
- **IDE**: IntelliJ IDEA

## 快速开始

### 环境要求
- JDK 22+
- Maven 3.6+
- IntelliJ IDEA (推荐)

### 安装步骤

1. **克隆项目**
```bash
git clone <repository-url>
cd studyCode
```

2. **构建项目**
```bash
mvn clean install
```

3. **运行测试**
```bash
mvn test
```

4. **运行 TODO 扫描工具**
```bash
java -cp tools/todo-scanner/target/classes com.serain.tools.scanner.BatchFixer
```

## 代码规范

### 命名规范
- 类名：大驼峰命名 (PascalCase) - 如 `E102`, `Q1`
- 方法名：小驼峰命名 (camelCase) - 如 `addTwoNumbers`, `bestTower`
- 变量名：小驼峰命名 - 如 `dummyHead`, `maxQuality`

### 代码结构
每个题目文件包含：
- Javadoc 注释 (`@BelongsProject`, `@BelongsPackage`, `@Author`, `@CreateTime`, `@Description`)
- 解题方法实现

### 包结构
- `com.serain.exercise.leetcode.<category>` - LeetCode 题解（按算法分类）
- `com.serain.exercise.niuke` - 牛客网练习
- `com.serain.doubleweekgame` - 双周赛
- `com.serain.singleweekgame` - 单周赛
- `com.serain.exam` - 大厂面试题
- `com.serain.study` - 学习代码
- `com.serain.parameter` - 公共数据结构类
- `com.serain.tools.scanner` - TODO 扫描工具

## 推荐资源

### 在线平台
- [LeetCode](https://leetcode.com/) - 全球领先的算法刷题平台
- [牛客网](https://www.nowcoder.com/) - 国内知名的 IT 求职平台

### 学习资料
- 《算法导论》- 经典算法教材
- 《剑指 Offer》- 面试必备算法题
- 《LeetCode 101》- 算法入门指南

---

**Happy Coding!**
