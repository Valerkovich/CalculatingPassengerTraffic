package ua.cn.stu.diploma.domain;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of")
    private Long date_of;

    @Column(name = "train_table")
    private int train_table;

    @Column(name = "shift_begin")
    private Long shift_begin;

    @Column(name = "shift_end")
    private Long shift_end;

    @Column(name = "type_ticket")
    private int type_ticket;// 0 - обычный билет, 1 - ученический

    @Column(name = "day_of_week")
    private int day_of_week;

    public Payment() {
    }

    public Payment(Long date_of, int train_table, Long shift_begin, Long shift_end, int type_ticket, int day_of_week) {
        this.date_of = date_of;
        this.train_table = train_table;
        this.shift_begin = shift_begin;
        this.shift_end = shift_end;
        this.type_ticket = type_ticket;
        this.day_of_week = day_of_week;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate_of(Long date_of) {
        this.date_of = date_of;
    }

    public void setTrain_table(int train_table) {
        this.train_table = train_table;
    }

    public void setShift_begin(Long shift_begin) {
        this.shift_begin = shift_begin;
    }

    public void setShift_end(Long shift_end) {
        this.shift_end = shift_end;
    }

    public void setType_ticket(int type_ticket) {
        this.type_ticket = type_ticket;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public Long getId() {
        return id;
    }

    public Long getDate_of() {
        return date_of;
    }

    public int getTrain_table() {
        return train_table;
    }

    public Long getShift_begin() {
        return shift_begin;
    }

    public Long getShift_end() {
        return shift_end;
    }

    public int getType_ticket() {
        return type_ticket;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "date_of='" + date_of + '\'' +
                ", train_table='" + train_table + '\'' +
                ", shift_begin='" + shift_begin + '\'' +
                ", shift_end='" + shift_end + '\'' +
                '}';
    }
}

