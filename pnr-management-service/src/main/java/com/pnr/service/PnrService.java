package com.pnr.service;

import com.pnr.dto.PnrResponse;
import com.pnr.pojo.Pnr;

public interface PnrService {
	
	public Pnr savePnr(Pnr pnr);
	
	public PnrResponse getPnrByBookingId(int pnrId);

}
