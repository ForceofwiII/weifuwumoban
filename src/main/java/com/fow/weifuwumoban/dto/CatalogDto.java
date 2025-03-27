package com.fow.weifuwumoban.dto;


import lombok.Data;

import java.util.List;

@Data
public class CatalogDto {


    private Long id;

    private Long catalogId;


    private Long parentId;

    private String catalogName;


    private List<CatalogDto> children;



}
