package ua.cn.stu.diploma.domain;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Column;

public class PaymentCSV {

    @CsvBindByName(column = "DATE_OF", required = true)
    private String date_of;

    @CsvBindByName(column = "TRAIN_TABLE", required = true)
    private int train_table;

    @CsvBindByName(column = "SHIFT_BEGIN", required = true)
    private String shift_begin;

    @CsvBindByName(column = "SHIFT_END", required = true)
    private String shift_end;

    public PaymentCSV() {
    }

    public PaymentCSV(String date_of, int train_table, String shift_begin, String shift_end) {
        this.date_of = date_of;
        this.train_table = train_table;
        this.shift_begin = shift_begin;
        this.shift_end = shift_end;
    }

    public void setDate_of(String date_of) {
        this.date_of = date_of;
    }

    public void setTrain_table(int train_table) {
        this.train_table = train_table;
    }

    public void setShift_begin(String shift_begin) {
        this.shift_begin = shift_begin;
    }

    public void setShift_end(String shift_end) {
        this.shift_end = shift_end;
    }

    public String getDate_of() {
        return date_of;
    }

    public int getTrain_table() {
        return train_table;
    }

    public String getShift_begin() {
        return shift_begin;
    }

    public String getShift_end() {
        return shift_end;
    }
}
