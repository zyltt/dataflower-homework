package com.example.dataflowerthree.entity;

import lombok.Data;

@Data
public class Star {
    private String uid;

    //如果credit_Level是-1，那么该数据应该放入test集
    private String star_level;

    private String is_employee;

    private String is_shareholder;

    private String is_contact;

    private String is_mgr_dep;

    private String all_bal;

    private String avg_mth;

    private String avg_qur;

    private String avg_year;

    private String deposit;

    private String five_class;

    private String bal;

    public Star(){
        this.setIs_employee("N");
        this.setIs_shareholder("N");
        this.setIs_contact("N");
        this.setIs_mgr_dep("N");

        this.setAll_bal("0.0");
        this.setAvg_qur("0.0");
        this.setAvg_mth("0.0");
        this.setAvg_year("0.0");
    }
}
