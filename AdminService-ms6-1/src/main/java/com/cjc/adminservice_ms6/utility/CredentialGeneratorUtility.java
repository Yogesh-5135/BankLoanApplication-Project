package com.cjc.adminservice_ms6.utility;

import java.util.Random;

public class CredentialGeneratorUtility 
{
   private static final Random USER_NAME_RANDOM = new Random(1000);
   
   public static String generateUsername(String firstName)
   {
	   return firstName+USER_NAME_RANDOM.nextInt(9999);
   }
   
   public static String generatePassword(String firstName)
   {
	   return firstName+'@'+USER_NAME_RANDOM.nextInt(9999);
   }
}
