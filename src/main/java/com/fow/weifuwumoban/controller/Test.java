package com.fow.weifuwumoban.controller;


import com.fow.weifuwumoban.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {



    @GetMapping("/test")
    public R  test(){





        return R.ok("hello");


    }

}
