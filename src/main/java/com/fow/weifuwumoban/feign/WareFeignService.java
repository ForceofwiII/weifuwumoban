package com.fow.weifuwumoban.feign;

import com.fow.weifuwumoban.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-ware")
public interface WareFeignService {


    @PostMapping("/ware/waresku/hasstock")
    public R hasstock(@RequestBody List<Long> ids);
}
