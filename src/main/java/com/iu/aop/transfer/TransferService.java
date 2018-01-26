package com.iu.aop.transfer;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class TransferService {
	@Inject
	private Transport transport;
	@Inject
	private CardCheck cardCheck;
	@Inject
	private CardCheck2 cardCheck2;
	@Inject
	private Ticket ticket;
	@Inject
	private Trip trip;
	
	public void start(){
		trip.trip();
		transport.bus();
	}

}
