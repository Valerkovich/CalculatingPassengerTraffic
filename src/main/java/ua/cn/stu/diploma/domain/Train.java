package ua.cn.stu.diploma.domain;

import javax.persistence.*;

@Entity
@Table
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "train_table")
    private int train_table;

    @Column(name = "shift_begin")
    private Long shift_begin;

    @Column(name = "shift_end")
    private Long shift_end;

    @Column(name = "initial_stop")
    private int initial_stop;

    public Train(int train_table, Long shift_begin, Long shift_end, int initial_stop) {
        this.train_table = train_table;
        this.shift_begin = shift_begin;
        this.shift_end = shift_end;
        this.initial_stop = initial_stop;
    }

    public Train() {
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setInitial_stop(int initial_stop) {
        this.initial_stop = initial_stop;
    }

    public Long getId() {
        return id;
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

    public int getInitial_stop() {
        return initial_stop;
    }
}
