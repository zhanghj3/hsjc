package com.yantai.gov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yantai.util.ExcelUtil;

public class Test {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, JSONObject param,String cookie,String authorization) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 自定义
            conn.setRequestProperty("cookie",cookie);
            conn.setRequestProperty("content-type","application/json;charset=UTF-8");
            conn.setRequestProperty("authorization",authorization);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String loginUrl = "";
        String queryUrl = "";
//        String[] args1 = {"HY062u01,HY063u01","2021-08-03,00:00:00","2021-08-05,23:00:00"};
        if(args.length !=3){
            System.out.println("**********************参数不对！");
        }
        for(int i=0;i<args.length;i++){
            System.out.println("**********************参数" + (i+1) + "：" + args[i]);
        }
        String[] accounts = args[0].split(",");
        String[] starts = args[1].split(",");
        String[] ends = args[2].split(",");
        String start = starts[0]+" "+starts[1];
        String end = ends[0]+" "+ends[1];
        for(String account : accounts){
            System.out.println("**********************账号：" + account);
            System.out.println("**********************登录中...");
            String jsessionid = "JSESSIONID=30F3E0BECAF05B090B23B8640BAC3837";
            JSONObject param = new JSONObject();
            param.put("username",account);
            param.put("password","mrGOTtFXyAd9fUuI1wX9tQ==");
            param.put("vertifyCode","");
            param.put("uuid","1c4e045cc4004b38bdda5e54cde0fb39");
            param.put("ysetduiustted",0);
            String result = sendPost(loginUrl,param,jsessionid,"");
            System.out.println("**********************登录返回结果：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject.getIntValue("code") == 1000){
                System.out.println("**********************登录成功！");
                String token = jsonObject.getJSONObject("data").getString("token");
                String cookie = jsessionid + "; harbour_process_monitor_token=" + token;
                // 第一次请求，获取总条数
                JSONObject param1 = new JSONObject();
                param1.put("paperNum","");
                param1.put("startTime",start);
                param1.put("endTime",end);
                param1.put("page",1);
                param1.put("size",10);
                param1.put("samplingPointCode",account.substring(0,5));
                String result1 = sendPost(queryUrl,param1,cookie,token);
                JSONObject jsonObject1 = JSONObject.parseObject(result1);
                if(jsonObject1.getIntValue("code") == 1000){
                    String count = jsonObject1.getJSONObject("data").getString("total");
                    System.out.println("**********************查询到该时间段总条数："+count);
                    // 第二次请求，查询
                    param1.put("size",count);
                    String result2 = sendPost(queryUrl,param1,cookie,token);
                    JSONObject jsonObject2 = JSONObject.parseObject(result2);
                    JSONArray jsonArray = jsonObject2.getJSONObject("data").getJSONArray("list");
                    ExcelUtil.writeExcel(jsonArray,account);
                    System.out.println("**********************写入Excel成功！");
                }else{
                    System.out.println(jsonObject1.getString("msg"));
                }
            }else{
                System.out.println(jsonObject.getString("msg"));
            }
        }
    }
}
