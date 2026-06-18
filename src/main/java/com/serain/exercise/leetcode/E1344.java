package com.serain.exercise.leetcode;

/**
 * 1344. 时钟指针的夹角
 * 给定 hour（1~12）和 minutes（0~59），计算时针和分针之间的较小夹角（度数）。
 * <p>
 * 分针：每分钟走 6°（360°/60）
 * 时针：每小时走 30°（360°/12），每分钟额外走 0.5°（30°/60）
 */
public class E1344 {

    public double angleClock(int hour, int minutes) {
        // 每小时时针走 30°，每分钟时针额外走 0.5°
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5;

        // 每分钟分针走 6°
        double minuteAngle = minutes * 6.0;

        // 两针夹角
        double angle = Math.abs(hourAngle - minuteAngle);

        // 取较小夹角
        return Math.min(angle, 360.0 - angle);
    }
}
