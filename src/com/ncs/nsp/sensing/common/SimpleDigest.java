package com.ncs.nsp.sensing.common;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.*;
import org.jasypt.util.digest.*;
import org.jasypt.util.password.*;
import org.jasypt.encryption.*;

public class SimpleDigest {

	 public static void main(String[] args)
	  {
		 
		 StandardStringDigester ssd = new StandardStringDigester();
		 ssd.setAlgorithm("SHA-256");
		 ssd.setIterations(100000);
		 ssd.setSaltSizeBytes(16);
		 System.out.println(ssd.digest("weetat"));
		 
		 
		 StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
		 System.out.println(spe.encryptPassword("weetat"));
		 System.out.println(spe.checkPassword("weetat", "61hzoRj2NsHnbiiyOsOt4ornMUny+gEkdiBcRthZddO6Dv/TQA00P/tS8VFEcHhw"));
		 
	  }
}
