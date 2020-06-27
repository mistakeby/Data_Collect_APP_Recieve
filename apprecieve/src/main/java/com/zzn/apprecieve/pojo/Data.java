package com.zzn.apprecieve.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

@lombok.Data
@Accessors(chain = true)
@TableName("upload_data")
public class Data {
    private String userid;
    private String type;
    private String light;
    private String topic;
    private String latitude;
    private String city;
    private String district;
    private String savepath;
    private String originaltime;
    private String descprition;
}
