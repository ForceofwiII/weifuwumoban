package com.fow.weifuwumoban.vo;

import java.io.Serial;
import java.io.Serializable;

public class FileVo  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id; // 图片ID

    private String type; // 图片类型

    private String url; // 图片路径或URL

    private String name; // 图片名称

    private String description; // 图片描述

    private String tags; // 图片标签 (JSON数组)

    private String format; // 图片格式


}
