package com.kuuhaku.heartbeat.service;

import com.kuuhaku.heartbeat.dao.HeartBeatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/12/12 15:56
 **/
@Service("heartBeatService")
public class HeartBeatServiceImpl  implements HeartBeatService{
    @Autowired
    private HeartBeatDao heartBeatDao;

    public HeartBeatServiceImpl(){
        if(heartBeatDao == null){
            System.out.println("||||||||||||||||||||||||||||||||||||||||||");
        }
        System.out.println("----\\\\\\\\\\\\\\\\---------");
    }
    public int count(){
        int count = heartBeatDao.countAll();
        System.out.println("count:");
        return count;
    }
}
