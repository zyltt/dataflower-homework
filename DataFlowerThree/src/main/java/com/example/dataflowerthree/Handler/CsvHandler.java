package com.example.dataflowerthree.Handler;

import com.example.dataflowerthree.entity.Credit;
import com.example.dataflowerthree.entity.Star;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvHandler {
    public static void Execute(List<Credit> creditTrain, List<Credit> creditTest, List<Star> starTrain,
                               List<Star> starTest) throws Exception {
        String dir_path = "C:\\Users\\zou\\Desktop\\DataFlowerThree\\";
        csv_maker(dir_path+ "credit_train.csv");
        csv_maker(dir_path+ "credit_test.csv");
        csv_maker(dir_path+ "star_train.csv");
        csv_maker(dir_path+ "star_test.csv");

        List<String> dataList = new ArrayList<String>();
        String file_name = null;

        file_name = dir_path + "credit_test.csv";
        csv_writer(file_name, credit_dataList_maker(creditTest,"test"));

        file_name = dir_path + "credit_train.csv";
        csv_writer(file_name, credit_dataList_maker(creditTrain,"train"));

        file_name = dir_path + "star_test.csv";
        csv_writer(file_name, star_dataList_maker(starTest,"test"));

        file_name = dir_path + "star_train.csv";
        csv_writer(file_name, star_dataList_maker(starTrain,"train"));
    }

    public static void csv_maker(String file_name) throws IOException {
        File this_file = new File(file_name);
        if (!this_file.exists()){
            this_file.createNewFile();
        }
    }

    // mode possible values: test / train
    public static List<String> credit_dataList_maker(List<Credit> credits, String mode){
        List<String> result = new ArrayList<String>();
        if (mode.equals("test")){
            result.add("uid,all_bal,bad_bal,due_intr,norm_bal," +
                    "delay_bal,is_employee,is_shareholder,is_contact," +
                    "is_mgr_dep,cred_limit,over_draft,five_class");
        }
        else{
            result.add("uid,credit_level,all_bal,bad_bal,due_intr,norm_bal," +
                    "delay_bal,is_employee,is_shareholder,is_contact," +
                    "is_mgr_dep,cred_limit,over_draft,five_class");
        }
        for (Credit thisCredit: credits){
            String thisLine = thisCredit.getUid() + ",";
            if (mode.equals("train")){
                thisLine = thisLine + thisCredit.getCredit_level() + ",";
            }

            thisLine = thisLine + thisCredit.getAll_bal() + ",";
            thisLine = thisLine + thisCredit.getBad_bal() + ",";
            thisLine = thisLine + thisCredit.getDue_intr() + ",";
            thisLine = thisLine + thisCredit.getNorm_bal() + ",";
            thisLine = thisLine + thisCredit.getDelay_bal() + ",";
            thisLine = thisLine + thisCredit.getIs_employee()+ ",";
            thisLine = thisLine + thisCredit.getIs_shareholder() + ",";
            thisLine = thisLine + thisCredit.getIs_contact()+ ",";
            thisLine = thisLine + thisCredit.getIs_mgr_dep()+ ",";
            thisLine = thisLine + thisCredit.getCred_limit()+ ",";
            thisLine = thisLine + thisCredit.getOver_draft()+ ",";
            thisLine = thisLine + thisCredit.getFive_class();

            result.add(thisLine);
        }
        return result;
    }

    // mode possible values: test / train
    public static List<String> star_dataList_maker(List<Star> stars, String mode){
        List<String> result = new ArrayList<String>();
        if (mode.equals("test")){
            result.add("uid,is_employee,is_shareholder,is_contact,is_mgr_dep," +
                    "all_bal,avg_mth,avg_qur,avg_year,deposit," +
                    "five_class,bal");
        }
        else{
            result.add("uid,star_level,is_employee,is_shareholder,is_contact,is_mgr_dep," +
                    "all_bal,avg_mth,avg_qur,avg_year,deposit," +
                    "five_class,bal");
        }
        for (Star thisStar: stars){
            String thisLine = thisStar.getUid() + ",";
            if (mode.equals("train")){
                thisLine = thisLine + thisStar.getStar_level() + ",";
            }

            thisLine = thisLine + thisStar.getIs_employee() + ",";
            thisLine = thisLine + thisStar.getIs_shareholder() + ",";
            thisLine = thisLine + thisStar.getIs_contact() + ",";
            thisLine = thisLine + thisStar.getIs_mgr_dep() + ",";
            thisLine = thisLine + thisStar.getAll_bal() + ",";
            thisLine = thisLine + thisStar.getAvg_mth() + ",";
            thisLine = thisLine + thisStar.getAvg_qur() + ",";
            thisLine = thisLine + thisStar.getAvg_year() + ",";
            thisLine = thisLine + thisStar.getDeposit() + ",";
            thisLine = thisLine + thisStar.getFive_class() + ",";
            thisLine = thisLine + thisStar.getBal();

            result.add(thisLine);
        }
        return result;
    }

    public static void csv_writer(String file_name, List<String> datalist) throws IOException {
        File file = new File(file_name);
        FileOutputStream out = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(osw);

        for (String data: datalist){
            bw.append(data).append("\r");
        }

        bw.flush();
        bw.close();
    }

}
