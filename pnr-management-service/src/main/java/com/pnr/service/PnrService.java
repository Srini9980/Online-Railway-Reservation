package com.pnr.service;

import java.util.List;

import com.pnr.dto.PnrResponse;
import com.pnr.pojo.Pnr;

public interface PnrService {
	
	public Pnr savePnr(Pnr pnr);
	
	public PnrResponse getPnrByBookingId(int pnrId);
	
	public List<Pnr> getAllPnr();
	
	public void deletePnr(int pnrId);
	
//	public int getSequenceNumber(String sequenceName);

}
