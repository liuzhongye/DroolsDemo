package com.lzy.entity;

public class Order {
    private int amout;//订单金额
    private int score;//累计积分

    @Override
    public String toString() {
        return "Order{" +
                "amout=" + amout +
                ", score=" + score +
                '}';
    }

    public int getAmout() {
        return amout;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
