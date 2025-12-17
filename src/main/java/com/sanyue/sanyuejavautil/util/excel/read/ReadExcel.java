package com.sanyue.sanyuejavautil.util.excel.read;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.sanyue.sanyuejavautil.model.entity.read.DemoData;
import com.sanyue.sanyuejavautil.model.entity.read.DemoData2;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *  读 excel
 */
@Slf4j
public class ReadExcel {

    public static void main(String[] args) {
//        simpleRead();
        readWithCustomListener();
//        simpleReadMuti();
//        simpleRead2();
//        simpleReadMultiHead();
        synchronousRead();
    }

    /**
     * 最简单的读，读取第一个 sheet 流后会自动关闭
     *
     */
    public static void simpleRead(){
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\test.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSONUtil.toJsonStr(demoData));
            }
        })).sheet().doRead();
    }

    /**
     * 最简单的读，读取第一个 sheet 流后会自动关闭
     *
     */
    public static void simpleRead2(){
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\test.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, DemoData2.class, new PageReadListener<DemoData2>(dataList -> {
            for (DemoData2 demoData : dataList) {
                log.info("读取到一条数据{}", JSONUtil.toJsonStr(demoData));
            }
        })).sheet().doRead();
    }


    /**
     * 自定义监听器的读
     *
     */
    public static void readWithCustomListener(){
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\test.xlsx";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

    /**
     * 读取多个 sheet流
     *
     */
    public static void simpleReadMuti() {
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\test.xlsx";
        // 读取全部sheet
        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        // 读取所有的 sheet，需要确保数据对应的对象相同
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).doReadAll();

        // 读取部分sheet
        try (ExcelReader excelReader = EasyExcel.read(fileName).build()) {
            // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(DemoData.class).registerReadListener(new DemoDataListener()).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(DemoData.class).registerReadListener(new DemoDataListener()).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能

            excelReader.read(readSheet1, readSheet2);
        }
    }


    /**
     * 最简单的读，多行头情况
     *
     */
    public static void simpleReadMultiHead(){
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\test.xlsx";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet()
                // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
                .headRowNumber(2)
                .doRead();
    }


    /**
     * 同步读取，返回一个集合，数据保存在内存中，只能读取第一个 sheet 流，读取多个参考上面的代码
     */
    public static void synchronousRead() {
        String fileName = "F:\\note\\sanyue-java-util\\src\\main\\resources\\tmp\\test.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<DemoData> list = EasyExcel.read(fileName).head(DemoData2.class).sheet().doReadSync();
        System.out.println(list);
    }

}
