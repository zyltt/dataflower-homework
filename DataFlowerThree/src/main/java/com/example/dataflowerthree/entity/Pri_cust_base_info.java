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
    public class Pri_cust_base_info extends Model<Pri_cust_base_info> {

    private static final long serialVersionUID=1L;

    private String uid;

    private String cert_type;

    private Double cust_no;

    private String cust_name;

    private String sex;

    private Integer birthday;

    private String cer_expd_date;

    private String marrige;

    private String education;

    private String home_phone;

    private String mob_phone;

    private String home_add;

    private String reg_add;

    private String career;

    private String prof_titl;

    private String country;

    private String is_employee;

    private String is_shareholder;

    private String is_black;

    private String is_contact;

    private String mgr_name;

    private String mgr_no;

    private String mge_org_name;

    private String mge_org;

    private Integer create_date;

    private String open_org;

    private String open_teller_no;

    private Integer update_date;

    private String update_org;

    private String update_teller_no;

    private String etl_dt;

    private String is_mgr_dep;

  public Pri_cust_base_info(){
    this.setIs_employee("N");
    this.setIs_shareholder("N");
    this.setIs_contact("N");
    this.setIs_mgr_dep("N");
  }
    @Override
    protected Serializable pkVal() {
          return null;
      }

}
