package com.kuuhaku.heartbeat.service;

import com.kuuhaku.heartbeat.nettyServer.annotation.ServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/12/13 13:09
 **/
@Service
@ServiceBean
public class MongoDBService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void test(){
        Query query = new Query();

        Long count = mongoTemplate.count(query,"viewing_2019_12_08");
        System.out.println("------------->:"+count);
    }
}
