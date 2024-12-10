package com.fow.weifuwumoban.utils;


import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class PageRequest {

    // 当前页码，默认值为1
    private int page = 1;

    // 每页大小，默认值为10
    private int size = 10;





    
    private  String sortField;  //排序字段

    private  String sortOrder; // 排序条件


}
