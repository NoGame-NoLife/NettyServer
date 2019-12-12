package com.kuuhaku.heartbeat.entity;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import org.springframework.stereotype.Component;

@Component
@MessageUsage(usage="Heart")
public class HeartBeat{
	
    private String deviceSerial = ""; //设备编号
    private String time         = ""; //时间
    private String mobileCSQ    = "";//设备信号强度
    private String mobileGPS    = "";//GPS定位信息
    private String mobileICCID	= "";//卡号信息
    private String mobileCPSI	= "";//cpsi
    private String mobileLBSX   = "";//设备低精度的经度
    private String mobileLBSY   = "";//设备低精度的纬度
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMobileCSQ() {
		return mobileCSQ;
	}
	public void setMobileCSQ(String mobileCSQ) {
		this.mobileCSQ = mobileCSQ;
	}
	public String getMobileGPS() {
		return mobileGPS;
	}
	public void setMobileGPS(String mobileGPS) {
		this.mobileGPS = mobileGPS;
	}
	public String getMobileICCID() {
		return mobileICCID;
	}
	public void setMobileICCID(String mobileICCID) {
		this.mobileICCID = mobileICCID;
	}
	public String getMobileCPSI() {
		return mobileCPSI;
	}
	public void setMobileCPSI(String mobileCPSI) {
		this.mobileCPSI = mobileCPSI;
	}
	public String getMobileLBSX() {
		return mobileLBSX;
	}
	public void setMobileLBSX(String mobileLBSX) {
		this.mobileLBSX = mobileLBSX;
	}
	public String getMobileLBSY() {
		return mobileLBSY;
	}
	public void setMobileLBSY(String mobileLBSY) {
		this.mobileLBSY = mobileLBSY;
	}
	@Override
	public String toString() {
		return "HeartBeat [deviceSerial=" + deviceSerial + ", time=" + time + ", mobileCSQ=" + mobileCSQ
				+ ", mobileGPS=" + mobileGPS + ", mobileICCID=" + mobileICCID + ", mobileCPSI=" + mobileCPSI
				+ ", mobileLBSX=" + mobileLBSX + ", mobileLBSY=" + mobileLBSY + "]";
	}
	
   
}
