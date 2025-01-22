package com.viratrigger.api.dto;

public class LabourData {
	 private long mobileNumber;
	    private int labourId;

	    public LabourData() {}

	    public LabourData(long mobileNumber, int labourId) {
	        this.mobileNumber = mobileNumber;
	        this.labourId = labourId;
	    }

	    public long getMobileNumber() {
	        return mobileNumber;
	    }

	    public void setMobileNumber(long mobileNumber) {
	        this.mobileNumber = mobileNumber;
	    }

	    public int getLabourId() {
	        return labourId;
	    }

	    public void setLabourId(int labourId) {
	        this.labourId = labourId;
	    }
	}


