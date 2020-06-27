package com.zzn.apprecieve.service;

import com.zzn.apprecieve.mapper.CommonMapper;
import com.zzn.apprecieve.pojo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerImpl {
    @Autowired
    private CommonMapper mapper;

    public void saveData(Data data)
    {
        mapper.insert(data);
    }
}
