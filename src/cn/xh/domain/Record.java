package cn.xh.domain;

import java.io.Serializable;

/*
 * 选课实体类
 */
public class Record implements Serializable {
    private String record_id;// 选课id
    private String record_word;

    private String record_time;

    public Record() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Record(String record_id, String record_word, String record_time) {
        super();
        this.record_id = record_id;
        this.record_word = record_word;
        this.record_time = record_time;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getRecord_word() {
        return record_word;
    }

    public void setRecord_word(String record_word) {
        this.record_word = record_word;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }


    @Override
    public String toString() {
        return "Record [record_id=" + record_id + ", record_word=" + record_word + ", record_time=" + record_time+ "]";
    }
}

