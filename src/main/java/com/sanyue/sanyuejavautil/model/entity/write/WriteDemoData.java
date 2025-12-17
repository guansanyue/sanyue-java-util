package com.sanyue.sanyuejavautil.model.entity.write;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class WriteDemoData {

    @ExcelProperty(value = {"用户答题系统","用户名"},index = 0)
    private String name;
    @ExcelProperty(value = {"用户答题系统","得分"},index = 2)
    private Double score;
    @ExcelProperty(value = {"用户答题系统","答题日期"},index = 4)
    private Date date;

    /**
     * 忽略这个字段，希望忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
