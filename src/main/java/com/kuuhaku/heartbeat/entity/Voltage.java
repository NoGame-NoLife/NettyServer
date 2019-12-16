package com.kuuhaku.heartbeat.entity;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@MessageUsage(usage="Voltage")
public class Voltage{
    private String groupName = "";
    
    private double voltage = 0D;//电压
    
    private double current = 0D;//电流
    
    private double osc_width = 0D;//摆动宽度
    
    private double osc_frq = 0D;//摆动频率
    
    private double osc_Ldelay = 0D;//左停留
    
    private double osc_Rdelay = 0D;//右停留
    
    private double wire = 0D;//送丝速度
    
    private double angle = 0D;//角度
    
    private double travel = 0D;//焊接行走速度
    
    private Date time = null;
    
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public double getVoltage() {
		return voltage;
	}
	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}
	public double getCurrent() {
		return current;
	}
	public void setCurrent(double current) {
		this.current = current;
	}
	public double getOsc_width() {
		return osc_width;
	}
	public void setOsc_width(double osc_width) {
		this.osc_width = osc_width;
	}
	public double getOsc_frq() {
		return osc_frq;
	}
	public void setOsc_frq(double osc_frq) {
		this.osc_frq = osc_frq;
	}
	public double getOsc_Ldelay() {
		return osc_Ldelay;
	}
	public void setOsc_Ldelay(double osc_Ldelay) {
		this.osc_Ldelay = osc_Ldelay;
	}
	public double getOsc_Rdelay() {
		return osc_Rdelay;
	}
	public void setOsc_Rdelay(double osc_Rdelay) {
		this.osc_Rdelay = osc_Rdelay;
	}
	public double getWire() {
		return wire;
	}
	public void setWire(double wire) {
		this.wire = wire;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getTravel() {
		return travel;
	}
	public void setTravel(double travel) {
		this.travel = travel;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

   
}
