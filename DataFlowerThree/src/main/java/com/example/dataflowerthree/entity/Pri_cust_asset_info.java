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
    public class Pri_cust_asset_info extends Model<Pri_cust_asset_info> {

    private static final long serialVersionUID=1L;

    private String cust_no;

    private String cust_name;

    private String uid;

    private String belong_org;

    private String exam_org;

    private Double all_bal;

    private Double avg_mth;

    private Double avg_qur;

    private Double avg_year;

    private Double sa_bal;

    private Double td_bal;

    private Double fin_bal;

    private Double sa_crd_bal;

    private Double td_crd_bal;

    private Double sa_td_bal;

    private Double ntc_bal;

    private Double td_3m_bal;

    private Double td_6m_bal;

    private Double td_1y_bal;

    private Double td_2y_bal;

    private Double td_3y_bal;

    private Double td_5y_bal;

    private Double oth_td_bal;

    private Double cd_bal;

    private String etl_dt;

    public Pri_cust_asset_info(){
      this.setAll_bal(0.0);
      this.setAvg_qur(0.0);
      this.setAvg_mth(0.0);
      this.setAvg_year(0.0);
  }


    @Override
    protected Serializable pkVal() {
          return null;
      }

}
