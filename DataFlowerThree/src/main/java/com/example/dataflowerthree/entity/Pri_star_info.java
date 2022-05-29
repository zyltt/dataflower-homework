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
    public class Pri_star_info extends Model<Pri_star_info> {

    private static final long serialVersionUID=1L;

    private String uid;

    private Integer star_level;


    @Override
    protected Serializable pkVal() {
          return null;
      }

}
