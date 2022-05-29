package com.example.dataflowerthree.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Fxxk.Khy
 * @since 2022-05-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Dm_v_as_djk_info extends Model<Dm_v_as_djk_info> {

    private static final long serialVersionUID=1L;

    private Integer acct_no;

    private Double card_no;

    private String cust_name;

    private Integer prod_code;

    private String prod_name;

    private String uid;

    private String entp_name;

    private Integer open_date;

    private String card_sts;

    private String card_sts_name;

    private Integer card_sts_date;

    private String is_withdrw;

    private String is_transfer;

    private String is_deposit;

    private String is_purchse;

    private Double cred_limit;

    private Double mob_phone;

    private Double deposit;

    private Double over_draft;

    private Double dlay_amt;

    private Integer five_class;

    private String bankacct;

    private String bankacct_date;

    private Double bankacct_bal;

    private String is_mob_bank;

    private String mob_bank_date;

    private String is_etc;

    private String etc_date;

    private String issue_mode;

    private String issue_mode_name;

    private Double bal;

    private String active_date;

    private String clsd_date;

    private Integer dlay_mths;

    private String mgr_no;

    private String mgr_name;

    private String recom_name;

    private String mge_org;

    private String mge_org_name;

    private String etl_dt;

  public Dm_v_as_djk_info(){
    this.setDeposit(0.0);
    this.setFive_class(0);
    this.setBal(0.0);
    this.setCred_limit(0.0);
    this.setOver_draft(0.0);
  }
    @Override
    protected Serializable pkVal() {
          return null;
      }

}
