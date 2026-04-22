# Study Code

算法学习与练习项目 - 专注于数据结构、算法和编程能力提升

## 📋 项目简介

本项目是一个综合性的算法学习仓库，包含了 LeetCode 练习题、双周赛/单周赛题目、各大厂面试笔试题以及基础数据结构和操作系统的学习代码。

## 🏗️ 项目结构

```
studyCode/
├── src/main/java/com/serain/
│   ├── exercise/              # 日常练习
│   │   ├── leetcode/         # LeetCode 练习题 (E1-E3779+)
│   │   └── niuke/            # 牛客网练习题
│   ├── doubleweekgame/       # LeetCode 双周赛题目
│   │   ├── game174/         # 第 174 场双周赛
│   │   ├── game175/         # 第 175 场双周赛
│   │   └── game178/         # 第 178 场双周赛
│   ├── singleweekgame/       # LeetCode 单周赛题目
│   │   ├── game485/         # 第 485 场单周赛
│   │   ├── game486/         # 第 486 场单周赛
│   │   └── game492/         # 第 492 场单周赛
│   ├── exam/                 # 大厂面试笔试题
│   │   ├── alibaba/         # 阿里巴巴
│   │   ├── bytedance/       # 字节跳动
│   │   ├── jingdong/        # 京东
│   │   ├── meituan/         # 美团
│   │   ├── tencent/         # 腾讯
│   │   └── xiechen/         # 协辰
│   ├── study/                # 学习代码
│   │   ├── dataStructure/   # 数据结构实现
│   │   └── os/              # 操作系统相关 (生产者消费者、单例模式等)
│   └── parameter/            # 公共参数类
│       ├── ListNode.java    # 链表节点定义
│       ├── Node.java        # 通用节点定义
│       └── TreeNode.java    # 树节点定义
└── src/test/                 # 测试代码
```

## 📊 题目统计

### LeetCode 练习
- **题目数量**: 100+ 道
- **难度分布**: 简单、中等、困难
- **涵盖主题**:
  - 数组与字符串
  - 链表
  - 树与二叉树
  - 动态规划
  - 回溯算法
  - 贪心算法
  - 滑动窗口
  - 二分查找
  - 图论
  - 并查集
  - 堆与优先队列

### 竞赛题目
- **双周赛**: 3 场 (game174, game175, game178)
- **单周赛**: 3 场 (game485, game486, game492)
- **每题包含**: 题目描述、解题思路、完整代码实现

### 大厂面试题
- **阿里巴巴**: 3 道
- **字节跳动**: 测试题
- **京东**: 测试题
- **美团**: 3 道
- **腾讯**: 测试题
- **协辰**: 4 道

## 🛠️ 技术栈

- **语言**: Java 21
- **构建工具**: Maven
- **依赖库**:
  - Lombok 1.18.42 (简化代码)
  - JUnit 3.8.1 (单元测试)
- **IDE**: IntelliJ IDEA

## 🚀 快速开始

### 环境要求
- JDK 21+
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

4. **运行特定题目**
```bash
# 修改 E102.java 中的 main 方法后运行
mvn exec:java -Dexec.mainClass="com.serain.exercise.leetcode.E102"
```

## 📝 代码规范

### 命名规范
- 类名：大驼峰命名 (PascalCase) - 如 `E102`, `Q1`
- 方法名：小驼峰命名 (camelCase) - 如 `addTwoNumbers`, `bestTower`
- 变量名：小驼峰命名 - 如 `dummyHead`, `maxQuality`

### 代码结构
每个题目文件包含：
- 作者信息注释
- 题目描述
- 解题方法实现
- 必要的输入输出示例

### 包结构
- `com.serain.exercise` - 日常练习
- `com.serain.doubleweekgame` - 双周赛
- `com.serain.singleweekgame` - 单周赛
- `com.serain.exam` - 大厂面试题
- `com.serain.study` - 学习代码
- `com.serain.parameter` - 公共参数类

## 📚 学习路线

### 第一阶段：基础数据结构
- [x] 链表 (ListNode)
- [x] 树 (TreeNode)
- [ ] 堆 (Heap)
- [ ] 图 (Graph)

### 第二阶段：基础算法
- [x] 排序算法
- [x] 查找算法
- [ ] 递归与分治
- [ ] 回溯算法

### 第三阶段：高级算法
- [ ] 动态规划
- [ ] 贪心算法
- [ ] 图论算法
- [ ] 字符串算法

### 第四阶段：实战演练
- [x] LeetCode 热题 HOT 100
- [x] 周赛/双周赛
- [ ] 大厂面试真题
- [ ] 专项突破

## 🔥 热门题目

### 高频面试题
- [E2](src/main/java/com/serain/exercise/leetcode/E2.java) - 两数相加 (链表操作)
- [E15](src/main/java/com/serain/exercise/leetcode/E15.java) - 三数之和 (双指针)
- [E20](src/main/java/com/serain/exercise/leetcode/E20.java) - 有效的括号 (栈)
- [E23](src/main/java/com/serain/exercise/leetcode/E23.java) - 合并 K 个升序链表 (堆)
- [E139](src/main/java/com/serain/exercise/leetcode/E139.java) - 单词拆分 (动态规划)

### 经典算法
- [E53](src/main/java/com/serain/exercise/leetcode/E53.java) - 最大子数组和 (动态规划)
- [E76](src/main/java/com/serain/exercise/leetcode/E76.java) - 最小覆盖子串 (滑动窗口)
- [E79](src/main/java/com/serain/exercise/leetcode/E79.java) - 单词搜索 (回溯)
- [E128](src/main/java/com/serain/exercise/leetcode/E128.java) - 最长连续序列 (并查集)

## 🎯 学习目标

- ✅ 掌握常见数据结构的实现和应用
- ✅ 熟练运用各类经典算法
- ✅ 提升代码编写能力和调试技巧
- ✅ 培养算法思维和问题分析能力
- ✅ 为技术面试做好充分准备

## 📖 推荐资源

### 在线平台
- [LeetCode](https://leetcode.com/) - 全球领先的算法刷题平台
- [牛客网](https://www.nowcoder.com/) - 国内知名的 IT 求职平台
- [力扣中国](https://leetcode-cn.com/) - LeetCode 中文版

### 学习资料
- 《算法导论》- 经典算法教材
- 《剑指 Offer》- 面试必备算法题
- 《LeetCode 101》- 算法入门指南

## 🤝 贡献指南

欢迎贡献代码和题解！

1. Fork 本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request

## 📄 许可证

本项目仅供学习交流使用

## 👤 作者

- **Serain** - 初始创建者

## 📧 联系方式

如有问题或建议，欢迎通过 Issue 反馈。

## 🌟 致谢

感谢所有为开源社区做出贡献的开发者！

---

**Happy Coding! 🎉**
