package ua.cn.stu.diploma.domain;

import javax.persistence.*;

@Entity
@Table
public class AverageCountPassenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stop_number")
    private int stop_number;

    @Column(name = "train_table")
    private int train_table;

    @Column(name = "average_count_passenger")
    private int average_count_passenger;

    public AverageCountPassenger() {
    }

    public AverageCountPassenger(int stop_number, int train_table, int average_count_passenger) {
        this.stop_number = stop_number;
        this.train_table = train_table;
        this.average_count_passenger = average_count_passenger;
    }

    public void setStop_number(int stop_number) {
        this.stop_number = stop_number;
    }

    public void setTrain_table(int train_table) {
        this.train_table = train_table;
    }

    public void setAverage_count_passenger(int average_count_passenger) {
        this.average_count_passenger = average_count_passenger;
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

    public int getAverage_count_passenger() {
        return average_count_passenger;
    }
}
