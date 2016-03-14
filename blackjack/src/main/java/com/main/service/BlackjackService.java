package com.main.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.common.net.MediaType;
import com.main.impl.BlackJackMain;

@Path("/blackjackservice")
public class BlackjackService {

	@GET
	@Path("/gamestart")
	@Produces("application/json")	
	public String blackjackservice()
	{
		BlackJackMain gamestart=new BlackJackMain();
		return "AAA";
		

		
		
	}
	
}
