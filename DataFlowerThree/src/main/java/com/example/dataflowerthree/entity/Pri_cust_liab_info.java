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
    public class Pri_cust_liab_info extends Model<Pri_cust_liab_info> {

    private static final long serialVersionUID=1L;

    private String uid;

    private Double all_bal;

    private Double bad_bal;

    private Double due_intr;

    private Double norm_bal;

    private Double delay_bal;

    private String etl_dt;

  public Pri_cust_liab_info(){
    this.setAll_bal(0.0);
    this.setBad_bal(0.0);
    this.setDue_intr(0.0);
    this.setDelay_bal(0.0);
    this.setNorm_bal(0.0);
  }
    @Override
    protected Serializable pkVal() {
          return null;
      }

}
