package com.fow.weifuwumoban.vo;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class GithubUser {

    private String login;


    private Long id;


    private String name;

    private String email;

    private String location;


}
