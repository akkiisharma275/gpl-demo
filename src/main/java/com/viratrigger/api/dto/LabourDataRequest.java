package com.viratrigger.api.dto;

import java.util.List;

public class LabourDataRequest {
	private int timeInterval;
    private List<LabourData> data;

    public LabourDataRequest() {}

    public LabourDataRequest(int timeInterval, List<LabourData> data) {
        this.timeInterval = timeInterval;
        this.data = data;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public List<LabourData> getData() {
        return data;
    }

    public void setData(List<LabourData> data) {
        this.data = data;
    }

	//public Object getLabourInfoList() {
		// TODO Auto-generated method stub
		//return null;
	//}
	
}
