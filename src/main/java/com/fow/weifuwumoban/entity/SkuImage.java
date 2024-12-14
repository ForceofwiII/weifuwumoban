package com.fow.weifuwumoban.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@TableName("sku_images") // 映射数据库表 sku_images
public class SkuImage {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; // 图片ID

    private String type; // 图片类型

    private String url; // 图片路径或URL

    private String name; // 图片名称

    private String description; // 图片描述

    private String tags; // 图片标签 (JSON数组)

    private String format; // 图片格式


    private String userName; // 上传用户名


    private Integer sortOrder; // 图片顺序

    @TableField(fill = FieldFill.INSERT) // 自动填充插入时间
    private LocalDateTime createTime; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE) // 自动填充更新时间
    private LocalDateTime updateTime; // 更新时间

    @TableLogic // 逻辑删除字段
    private Integer isDelete; // 是否删除
}
