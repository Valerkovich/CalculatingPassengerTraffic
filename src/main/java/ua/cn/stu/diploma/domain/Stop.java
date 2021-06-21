package ua.cn.stu.diploma.domain;

import javax.persistence.*;

@Entity
@Table
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stop_number")
    private int stop_number;

    @Column(name = "train_table")
    private int train_table;

    @Column(name = "train_shift_begin")
    private Long train_shift_begin;

    @Column(name = "arrival")
    private Long arrival;

    @Column(name = "amount_passengers")
    private int amount_passengers;

    @Column(name = "hour")
    private int hour;

    public Stop() {
    }

    public Stop(int stop_number, int train_table, Long train_shift_begin, Long arrival, int amount_passengers, int hour) {
        this.stop_number = stop_number;
        this.train_table = train_table;
        this.train_shift_begin = train_shift_begin;
        this.arrival = arrival;
        this.amount_passengers = amount_passengers;
        this.hour = hour;
    }

    public Long getId() {
        return id;
    }

    public int getStop_number() {
        return stop_number;
    }

    public int getTrain_table() {
        return train_table;
    }

    public Long getTrain_shift_begin() {
        return train_shift_begin;
    }

    public Long getArrival() {
        return arrival;
    }

    public int getAmount_passengers() {
        return amount_passengers;
    }

    public int getHour() {
        return hour;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStop_number(int stop_number) {
        this.stop_number = stop_number;
    }

    public void setTrain_table(int train_table) {
        this.train_table = train_table;
    }

    public void setTrain_shift_begin(Long train_shift_begin) {
        this.train_shift_begin = train_shift_begin;
    }

    public void setArrival(Long arrival) {
        this.arrival = arrival;
    }

    public void setAmount_passengers(int amount_passengers) {
        this.amount_passengers = amount_passengers;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
