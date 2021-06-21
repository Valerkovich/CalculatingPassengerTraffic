package ua.cn.stu.diploma.domain;

import javax.persistence.*;

@Entity
@Table
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "train_table")
    private int train_table;

    @Column(name = "time")
    private int[] time;

    @Column(name = "stops")
    private int[] stops;

    @Column(name = "initial_stop")
    private int[] initial_stops;

    @Column(name = "initial_time")
    private int[] initial_time;

    public Route() {
    }

    public Route(int train_table,  int[] stops, int[] time, int[] initial_stops, int[] initial_time) {
        this.train_table = train_table;
        this.time = time;
        this.stops = stops;
        this.initial_stops = initial_stops;
        this.initial_time = initial_time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrain_table(int train_table) {
        this.train_table = train_table;
    }

    public void setTime(int[] time) {
        this.time = time;
    }

    public void setStops(int[] stops) {
        this.stops = stops;
    }

    public void setInitial_stops(int[] initial_stops) {
        this.initial_stops = initial_stops;
    }

    public void setInitial_time(int[] initial_time) {
        this.initial_time = initial_time;
    }

    public Long getId() {
        return id;
    }

    public int getTrain_table() {
        return train_table;
    }

    public int[] getTime() {
        return time;
    }

    public int[] getStops() {
        return stops;
    }

    public int[] getInitial_stops() {
        return initial_stops;
    }

    public int[] getInitial_time() {
        return initial_time;
    }
}
