package ua.cn.stu.diploma.domain;

import javax.persistence.*;

@Entity
@Table
public class PassengerRatio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ratio")
    private double ratio;

    @Column(name = "day_of_week")
    private int day_of_week;

    @Column(name = "train_table")
    private int train_table;

    public PassengerRatio() {
    }

    public PassengerRatio(double ratio, int day_of_week, int train_table) {
        this.ratio = ratio;
        this.day_of_week = day_of_week;
        this.train_table = train_table;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public void setTrain_table(int train_table) {
        this.train_table = train_table;
    }

    public Long getId() {
        return id;
    }

    public double getRatio() {
        return ratio;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    public int getTrain_table() {
        return train_table;
    }
}
