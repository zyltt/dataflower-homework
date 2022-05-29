package com.example.dataflowerthree.entity;

import lombok.Data;

@Data
public class Credit {
    private String uid;

    //如果credit_Level是-1，那么该数据应该放入test集
    private String credit_level;

    private String all_bal;

    private String bad_bal;

    private String due_intr;

    private String norm_bal;

    private String delay_bal;

    private String is_employee;

    private String is_shareholder;

    private String is_contact;

    private String is_mgr_dep;

    private String cred_limit;

    private String over_draft;

    private String five_class;

    public Credit(){
        this.setAll_bal("0.0");
        this.setBad_bal("0.0");
        this.setDue_intr("0.0");
        this.setDelay_bal("0.0");
        this.setNorm_bal("0.0");

        this.setIs_employee("N");
        this.setIs_shareholder("N");
        this.setIs_contact("N");
        this.setIs_mgr_dep("N");

        this.setCred_limit("0.0");
        this.setOver_draft("0.0");
        this.setFive_class("0");
    }
}
