package com.kuuhaku.heartbeat.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description 心跳
 * @Author Kuuhaku
 * @Date 2019/12/12 15:53
 **/
public interface HeartBeatDao {
    Integer countAll();
}
