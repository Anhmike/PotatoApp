package com.PotatoServer.lib;


import java.net.URLDecoder;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

public final class Convertors {
	public void Convertors(){
		
	}


public static String[] SplitTags(String Tags){
	String args[] = null;
	
	StringTokenizer st = Convertors.SplitTagString(Tags);
	args = new String[st.countTokens()+1];  //+1 for _No_Tag_
	//Lets assume the number is the last argument
	
	int argv=0;
	while (st.hasMoreTokens ()) {;
		args[argv]=new String();
		args[argv]=st.nextToken();
		argv++;
		} 
	args[argv]= "_No-Tag_";
	return args;
	}
	
  private static StringTokenizer SplitTagString(String str){
  		return new StringTokenizer (str,",");

  }
  
  public static String[] SplitRequestPath(HttpServletRequest request){
		String args[] = null;
		 
			
		StringTokenizer st = SplitString(request.getRequestURI());
		args = new String[st.countTokens()];
		//Lets assume the number is the last argument
		
		int argv=0;
		while (st.hasMoreTokens ()) {;
			args[argv]=new String();
						
			args[argv]=st.nextToken();
			try{
				//System.out.println("String was "+URLDecoder.decode(args[argv],"UTF-8"));
				args[argv]=URLDecoder.decode(args[argv],"UTF-8");
				
			}catch(Exception et){
				System.out.println("Bad URL Encoding"+args[argv]);
			}
			argv++;
			} 

	//so now they'll be in the args array.  
	// argv[0] should be the user directory
	
		return args;
		}
		
	  private static StringTokenizer SplitString(String str){
	  		return new StringTokenizer (str,"/");

	  }


}