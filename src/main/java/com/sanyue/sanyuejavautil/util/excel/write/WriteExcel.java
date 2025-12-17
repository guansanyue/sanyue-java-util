package com.sanyue.sanyuejavautil.util.excel.write;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sanyue.sanyuejavautil.model.entity.write.WriteDemoData;

import java.util.List;

public class WriteExcel {

    public static void main(String[] args) {
//        writeExcel();
//        writeExcelMulti();
        writeExcelMultiSheetMulti();
    }




    /**
     * 重复多次写入，单个 sheet
     */
    public static void writeExcelMulti(){
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\writeTest.xlsx";
        // 这里 需要指定写用哪个class去写
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, WriteDemoData.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<WriteDemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        }
    }


    /**
     * 重复多次写入 + 多个 sheet
     */
    public static void writeExcelMultiSheetMulti(){
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\writeTest.xlsx";
        // 方法2: 如果写到不同的sheet 同一个对象
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, WriteDemoData.class).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                for(int j = 0;j <5;j++){
                    List<WriteDemoData> data = data();
                    excelWriter.write(data, writeSheet);
                }

            }
        }
    }



    /**
     * 简单写，写到第一个 sheet
     */
    public static void writeExcel(){
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\writeTest.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 写法一：可以对 data 数据进行一些操作
        EasyExcel.write(fileName, WriteDemoData.class)
                .sheet("模板")
                .doWrite(() -> {
                    // 分页查询数据
                    return data();
                });

//        //  写法二
//        EasyExcel.write(fileName, WriteDemoData.class).sheet("模板").doWrite(data());
//
//        // 写法三
//        // 这里 需要指定写用哪个class去写
//        try (ExcelWriter excelWriter = EasyExcel.write(fileName, WriteDemoData.class).build()) {
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(data(), writeSheet);
//        }

    }


    /**
     * 假数据编写接口
     * @return
     */
    private static List<WriteDemoData> data() {
        List<WriteDemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            WriteDemoData data = new WriteDemoData();
            data.setName("sanyue" + i);
            data.setScore(RandomUtil.randomDouble());
            data.setDate(RandomUtil.randomDay(0,20));
            data.setIgnore("0");
            list.add(data);
        }
        return list;
    }
}
