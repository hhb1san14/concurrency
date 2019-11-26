package com.hhb.concurrency.other.myself;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: huanghongbo
 * @Date: 2019-10-26 11:44
 * @Description:
 */
public class CSVUtil {


    public static void main(String[] args) throws IOException {
        String fileName = "/Users/baiwang/Downloads/Exam/csv.txt";
        CSVUtil csvUtil = new CSVUtil();
        List<CSVBean> csvBeanList = csvUtil.getCSVBeanList(fileName);

        System.err.println(csvBeanList);
    }


    /**
     * 通过文件存放位置获取转化好的CSV对象
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public List<CSVBean> getCSVBeanList(String fileName) throws IOException {
        List<CSVBean> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        String line;
        // 读取到的内容给line变量
        while ((line = br.readLine()) != null) {
            CSVBean csvBean = getCSVBean(line.split(","));
            list.add(csvBean);
        }
        return list;

    }


    /**
     * 将CSV每行数据转换为CSV对象
     *
     * @param strArray
     * @return
     */
    public CSVBean getCSVBean(String[] strArray) {
        CSVBean bean = new CSVBean();
        // todo: 问题：一行数据由多个 ，
        if (strArray.length > 0) {
            for (int i = 0; i < strArray.length; i++) {
                switch (i) {
                    case 0:
                        bean.setId(getIntegerValue(strArray[i]));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }

            }
        }
        return bean;
    }

    /**
     * 获取整型数据
     *
     * @param param
     * @return
     */
    public static Integer getIntegerValue(String param) {
        if (!StringUtils.isEmpty(param) && isNumber(param)) {
            return Integer.valueOf(param);
        }
        //视具体的业务情况，是否返回默认值
        return 0;
    }


    /**
     * 判断一个字符串是否都为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }


    class CSVBean {
        private Integer id;

        private String s1;

        private String s2;

        private Float f1;

        private Date d;

        public Integer getId() {
            return id;
        }

        public CSVBean setId(Integer id) {
            this.id = id;
            return this;
        }

        public String getS1() {
            return s1;
        }

        public CSVBean setS1(String s1) {
            this.s1 = s1;
            return this;
        }

        public String getS2() {
            return s2;
        }

        public CSVBean setS2(String s2) {
            this.s2 = s2;
            return this;
        }

        public Float getF1() {
            return f1;
        }

        public CSVBean setF1(Float f1) {
            this.f1 = f1;
            return this;
        }

        public Date getD() {
            return d;
        }

        public CSVBean setD(Date d) {
            this.d = d;
            return this;
        }


        @Override
        public String toString() {
            return "CSVBean{" +
                    "id=" + id +
                    ", s1='" + s1 + '\'' +
                    ", s2='" + s2 + '\'' +
                    ", f1=" + f1 +
                    ", d=" + d +
                    '}';
        }
    }


}
