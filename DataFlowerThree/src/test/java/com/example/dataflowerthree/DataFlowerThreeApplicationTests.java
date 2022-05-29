package com.example.dataflowerthree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dataflowerthree.Handler.CsvHandler;
import com.example.dataflowerthree.entity.*;
import com.example.dataflowerthree.mapper.*;
import com.sun.org.apache.xerces.internal.impl.xs.util.ShortListImpl;
import io.swagger.models.auth.In;
import org.apache.velocity.runtime.directive.Parse;
import org.junit.jupiter.api.Test;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DataFlowerThreeApplicationTests {

    @Autowired
    Pri_star_infoMapper pri_star_infoMapper;
    @Autowired
    Pri_credit_infoMapper pri_credit_infoMapper;
    @Autowired
    Dm_v_as_djk_infoMapper dm_v_as_djk_infoMapper;
    @Autowired
    Pri_cust_asset_infoMapper pri_cust_asset_infoMapper;
    @Autowired
    Pri_cust_base_infoMapper pri_cust_base_infoMapper;
    @Autowired
    Pri_cust_liab_infoMapper pri_cust_liab_infoMapper;

    @Test
    void contextLoads() throws Exception {

        List<Credit> creditTrain = new ArrayList<Credit>();
        List<Credit> creditTest = new ArrayList<Credit>();

//        TODO credit样本大小
//        List<Pri_credit_info> credit_infos = pri_credit_infoMapper.
//                selectList(new QueryWrapper<Pri_credit_info>().last("limit 3000"));

        Integer temp = 0;
//        for (Pri_credit_info credit_info: credit_infos){
//            temp += 1;
//
//            System.out.println("Dealing with Creditor " + temp.toString());
//
//            if (credit_info.getCredit_level() == null)
//                continue;
//            String uid = credit_info.getUid();
//
//            Pri_cust_liab_info pri_cust_liab_info = pri_cust_liab_infoMapper.
//                    selectOne(new QueryWrapper<Pri_cust_liab_info>().eq("uid", uid).last("limit 1"));
//            if (pri_cust_liab_info == null)
//                pri_cust_liab_info = new Pri_cust_liab_info();
//
//            Pri_cust_base_info pri_cust_base_info = pri_cust_base_infoMapper.
//                    selectOne(new QueryWrapper<Pri_cust_base_info>().eq("uid", uid).last("limit 1"));
//            if (pri_cust_base_info == null)
//                pri_cust_base_info = new Pri_cust_base_info();
//
//            Dm_v_as_djk_info dm_v_as_djk_info = dm_v_as_djk_infoMapper.
//                    selectOne(new QueryWrapper<Dm_v_as_djk_info>().eq("uid", uid).last("limit 1"));
//            if (dm_v_as_djk_info == null)
//                dm_v_as_djk_info = new Dm_v_as_djk_info();
//
//            Credit credit = Credit_maker(uid, credit_info.getCredit_level().toString(), pri_cust_liab_info,
//                    pri_cust_base_info, dm_v_as_djk_info);
//
//            if (credit.getCredit_level().equals("-1"))
//                creditTest.add(credit);
//            else
//                creditTrain.add(credit);
//        }

        List<Star> starTrain = new ArrayList<Star>();
        List<Star> starTest = new ArrayList<Star>();

//        TODO star样本大小
        List<Pri_star_info> pri_star_infos = pri_star_infoMapper.
                selectList(new QueryWrapper<>());

        temp = 0;
        for (Pri_star_info star_info: pri_star_infos){

            temp += 1;
            System.out.println("Dealing with Star " + temp.toString());

            if (star_info.getStar_level() != -1)
                continue;

            if (star_info.getStar_level() == null)
                continue;

            String uid = star_info.getUid();

            Pri_cust_base_info pri_cust_base_info = pri_cust_base_infoMapper.
                    selectOne(new QueryWrapper<Pri_cust_base_info>().eq("uid", uid).last("limit 1"));
            if (pri_cust_base_info == null)
                pri_cust_base_info = new Pri_cust_base_info();

            Pri_cust_asset_info pri_cust_asset_info = pri_cust_asset_infoMapper.
                    selectOne(new QueryWrapper<Pri_cust_asset_info>().eq("uid", uid).last("limit 1"));
            if (pri_cust_asset_info == null)
                pri_cust_asset_info = new Pri_cust_asset_info();

            Dm_v_as_djk_info dm_v_as_djk_info = dm_v_as_djk_infoMapper.
                    selectOne(new QueryWrapper<Dm_v_as_djk_info>().eq("uid", uid).last("limit 1"));
            if (dm_v_as_djk_info == null)
                dm_v_as_djk_info = new Dm_v_as_djk_info();

            Star star = Star_maker(uid, star_info.getStar_level().toString(), pri_cust_base_info,
                    pri_cust_asset_info, dm_v_as_djk_info);
//
//            if (star.getStar_level().equals("-1"))
//                starTest.add(star);
//            else
//                starTrain.add(star);
            starTest.add(star);
        }

        CsvHandler.Execute(creditTrain,creditTest,starTrain,starTest);
    }

    private Credit Credit_maker(String uid, String Credit_level, Pri_cust_liab_info pri_cust_liab_info, Pri_cust_base_info pri_cust_base_info,
                                Dm_v_as_djk_info dm_v_as_djk_info){
        Credit credit = new Credit();
        credit.setUid(uid);
        credit.setCredit_level(Credit_level);

        if (pri_cust_liab_info.getAll_bal() == null)
            pri_cust_liab_info.setAll_bal(0.0);
        credit.setAll_bal(pri_cust_liab_info.getAll_bal().toString());
        if (pri_cust_liab_info.getBad_bal() == null)
            pri_cust_liab_info.setBad_bal(0.0);
        credit.setBad_bal(pri_cust_liab_info.getBad_bal().toString());
        if (pri_cust_liab_info.getDue_intr() == null)
            pri_cust_liab_info.setDue_intr(0.0);
        credit.setDue_intr(pri_cust_liab_info.getDue_intr().toString());
        if (pri_cust_liab_info.getNorm_bal() == null)
            pri_cust_liab_info.setNorm_bal(0.0);
        credit.setNorm_bal(pri_cust_liab_info.getNorm_bal().toString());
        if (pri_cust_liab_info.getDelay_bal() == null)
            pri_cust_liab_info.setDelay_bal(0.0);
        credit.setDelay_bal(pri_cust_liab_info.getDelay_bal().toString());

        if (pri_cust_base_info.getIs_employee().isEmpty())
            pri_cust_base_info.setIs_employee("N");
        credit.setIs_employee(ParseInf(pri_cust_base_info.getIs_employee()));
        if (pri_cust_base_info.getIs_shareholder().isEmpty())
            pri_cust_base_info.setIs_shareholder("N");
        credit.setIs_shareholder(ParseInf(pri_cust_base_info.getIs_shareholder()));
        if (pri_cust_base_info.getIs_contact().isEmpty())
            pri_cust_base_info.setIs_contact("N");
        credit.setIs_contact(ParseInf(pri_cust_base_info.getIs_contact()));
        if (pri_cust_base_info.getIs_mgr_dep().isEmpty())
            pri_cust_base_info.setIs_mgr_dep("N");
        credit.setIs_mgr_dep(ParseInf(pri_cust_base_info.getIs_mgr_dep()));

        if (dm_v_as_djk_info.getCred_limit() == null)
            dm_v_as_djk_info.setCred_limit(0.0);
        credit.setCred_limit(dm_v_as_djk_info.getCred_limit().toString());
        if (dm_v_as_djk_info.getOver_draft() == null)
            dm_v_as_djk_info.setOver_draft(0.0);
        credit.setOver_draft(dm_v_as_djk_info.getOver_draft().toString());
        if (dm_v_as_djk_info.getFive_class() == null)
            dm_v_as_djk_info.setFive_class(0);
        credit.setFive_class(dm_v_as_djk_info.getFive_class().toString());

        return credit;
    }

    private Star Star_maker(String uid, String Star_level, Pri_cust_base_info pri_cust_base_info,
                                Pri_cust_asset_info pri_cust_asset_info, Dm_v_as_djk_info dm_v_as_djk_info){
        Star star = new Star();
        star.setUid(uid);
        star.setStar_level(Star_level);

        if (pri_cust_base_info.getIs_employee().isEmpty())
            pri_cust_base_info.setIs_employee("N");
        star.setIs_employee(ParseInf(pri_cust_base_info.getIs_employee()));
        if (pri_cust_base_info.getIs_shareholder().isEmpty())
            pri_cust_base_info.setIs_shareholder("N");
        star.setIs_shareholder(ParseInf(pri_cust_base_info.getIs_shareholder()));
        if (pri_cust_base_info.getIs_contact().isEmpty())
            pri_cust_base_info.setIs_contact("N");
        star.setIs_contact(ParseInf(pri_cust_base_info.getIs_contact()));
        if (pri_cust_base_info.getIs_mgr_dep().isEmpty())
            pri_cust_base_info.setIs_mgr_dep("N");
        star.setIs_mgr_dep(ParseInf(pri_cust_base_info.getIs_mgr_dep()));

        if (pri_cust_asset_info.getAll_bal() == null)
            pri_cust_asset_info.setAll_bal(0.0);
        star.setAll_bal(pri_cust_asset_info.getAll_bal().toString());
        if (pri_cust_asset_info.getAvg_mth() == null)
            pri_cust_asset_info.setAvg_mth(0.0);
        star.setAvg_mth(pri_cust_asset_info.getAvg_mth().toString());
        if (pri_cust_asset_info.getAvg_qur() == null)
            pri_cust_asset_info.setAvg_qur(0.0);
        star.setAvg_qur(pri_cust_asset_info.getAvg_qur().toString());
        if (pri_cust_asset_info.getAvg_year() == null)
            pri_cust_asset_info.setAvg_year(0.0);
        star.setAvg_year(pri_cust_asset_info.getAvg_year().toString());

        if (dm_v_as_djk_info.getDeposit() == null)
            dm_v_as_djk_info.setDeposit(0.0);
        star.setDeposit(dm_v_as_djk_info.getDeposit().toString());
        if (dm_v_as_djk_info.getFive_class() == null)
            dm_v_as_djk_info.setFive_class(0);
        star.setFive_class(dm_v_as_djk_info.getFive_class().toString());
        if (dm_v_as_djk_info.getBal() == null)
            dm_v_as_djk_info.setBal(0.0);
        star.setBal(dm_v_as_djk_info.getBal().toString());

        return star;
    }

    private String ParseInf(String str){
        String ans = "";
        if (str.equals("X"))
            ans = "-1";
        else
            if (str.equals("Y"))
                ans = "1";
            else
                if (str.equals("N"))
                    ans = "0";
        return ans;
    }
}
