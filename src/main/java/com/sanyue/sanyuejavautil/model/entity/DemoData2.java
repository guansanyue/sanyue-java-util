package com.sanyue.sanyuejavautil.model.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DemoData2 {

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("日期")
    private String date;

    @ExcelProperty("金额")
    private Double amount;

    @ExcelProperty("用户名")
    private String username;

    @NumberFormat("#.##%")
    @ExcelProperty("百分比")
    private String rate;

    @ExcelProperty("id")
    private Long userId;


}