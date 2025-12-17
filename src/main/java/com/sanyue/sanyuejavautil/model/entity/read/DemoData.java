package com.sanyue.sanyuejavautil.model.entity.read;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DemoData {

    @ExcelProperty("日期")
    private Date date;

    @ExcelProperty("金额")
    private Double amount;

    @ExcelProperty("百分比")
    private Double rate;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("id")
    private Long userId;

}
