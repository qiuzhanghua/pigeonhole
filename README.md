# 抽屉原理+回溯算法

有49个小孩，每人胸前有一个号码，号码从1到49各不相同。请你挑选若干个小孩，排成一个圆圈，使任何相邻两个小孩的号码数的乘积小于100。请问，最多能挑选多少个小孩？保证挑选最多小孩的情况下总共有多少种挑选方法？

## 解题思路
第一问：最多能挑选多少个小孩？
这是小学奥数，使用抽屉原理，可以得到结论，最多能挑选18个小孩。

第二问：总共有多少种挑选方法？
可使用回溯算法。

## 什么叫不同的方法
1. 如挑选3个小孩，【1，2，3】和【1，3，2】是不同的方法，因为他们的圆圈排列不同，代表顺时针和逆时针两种写法。

2. 但是【1，2，3】、【2，3，1】和【3， 1， 2】是相同的方法，因为他们的旋转后相同。

3. 当然【1, 2, 3】和【4, 5, 6】也不是相同的方法.

## 记录下运行所需的时间

如 30秒， 1分钟， 1小时等


# **使用Java 21的虚拟线程，进一步缩短运行时间。**

## 环境要求
1. Java 21(https://www.oracle.com/java/technologies/downloads/#java21)
2. Maven 3.9.1 or above

## 运行
```bash
mvn exec:java -Dexec.mainClass="dev.taiji.App"
```
for my m1x,
```text
Time used: 601 ms
Result = 171595008
```
## 编译可执行文件
```bash
mvn package -Pnative
```
## 运行可执行文件
```bash
target/pigeonhole
```
for my m1x,
```text
Time used: 755 ms
Result = 171595008
```