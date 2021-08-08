package com.yantai.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {

    private static int sheetIndex = 0;
    private static final String filename = "./"+(new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_").format(new Date()))+"身份证信息.xls";
    /**
     * 如果创建多个sheet，只能用相同的workbook
     */
    private static HSSFWorkbook workbook = new HSSFWorkbook();
    /**
     * POI 创建Excel文件
     * @author yangtingting
     * @date 2019/07/29
     */
    public static void writeExcel(JSONArray jsonArray,String sheetName) {
        //创建工作表sheeet
        HSSFSheet sheet=workbook.createSheet();
        workbook.setSheetName(sheetIndex,sheetName);
        //创建第一行标题
        HSSFRow row=sheet.createRow(0);
        String[] title={"姓名","身份证","号码"};
        HSSFCell cell=null;
        for (int i=0;i<title.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据（从第二行开始写）
        for (int i=0;i<jsonArray.size();i++){
            JSONObject tt = (JSONObject) jsonArray.get(i);
            String name = tt.getString("name")+"";
            String paperNum = tt.getString("paperNum")+"";
            String phoneNum = tt.getString("phoneNum")+"";
            HSSFRow nextrow=sheet.createRow(i+1);
            HSSFCell cell2=nextrow.createCell(0);
            cell2.setCellValue(name);
            cell2=nextrow.createCell(1);
            cell2.setCellValue(paperNum);
            cell2=nextrow.createCell(2);
            cell2.setCellValue(phoneNum);
        }
        //创建一个文件
        File file=null;
        FileOutputStream stream = null;
        try{
            file = new File(filename);
            if(file == null){
                // 不存在文件就创建
                file.createNewFile();
            }
            stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sheetIndex++;
    }
}


