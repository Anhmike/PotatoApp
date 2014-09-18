package com.PotatoServer.Stores;

public class ProblemDes {

	
	private String P_ID;
	private String Description;
	
	public void setP_ID(String id)
	{
		P_ID = id;
	}
	
	public void SetDescription(String DES)
	{
		Description = DES;
	}
	
	public String GetP_ID()
	{
		return P_ID;
	}
	
	public String GetDescription()
	{
		return Description;
	}
}
