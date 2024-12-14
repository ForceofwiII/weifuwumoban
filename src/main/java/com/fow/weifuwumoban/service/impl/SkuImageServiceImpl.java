package com.fow.weifuwumoban.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fow.weifuwumoban.entity.SkuImage;
import com.fow.weifuwumoban.mapper.SkuImageMapper;
import com.fow.weifuwumoban.service.SkuImageService;
import org.springframework.stereotype.Service;

@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage> implements SkuImageService {
    // 使用 ServiceImpl 自动继承 CRUD 功能
    // 可以在这里实现额外的业务逻辑
}
