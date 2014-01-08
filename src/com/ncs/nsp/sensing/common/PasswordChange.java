package com.ncs.nsp.sensing.common;


import java.io.PrintStream;
import org.jasypt.util.text.BasicTextEncryptor;


//Password Change for shell script
public class PasswordChange
{
  public static void main(String[] args)
  {
    try
    {
      if ((args.length > 0) && (args.length <= 2)) {
        BasicTextEncryptor encryptor2 = new BasicTextEncryptor();
        encryptor2.setPassword("1qaz2wsX");
        String userNameToEncrypt = args[0];
        String userNameEncrypted = encryptor2.encrypt(userNameToEncrypt);
        String passwordToEncrypt = args[1];
        String passwordEncrypted = encryptor2.encrypt(passwordToEncrypt);
        System.out.println("****************************************");
        System.out.println("userNameEncrypted :" + userNameEncrypted);
        System.out.println("passwordEncrypted :" + passwordEncrypted);
        System.out.println("****************************************");
      } else {
        System.out.println("*******************************************************");
        System.out.println("Only 2 argument allow in PasswordChange");
        System.out.println("Please input user and password to be encryted in first and second argument");
        System.out.println("*******************************************************");
      }
    } catch (Exception ex) {
      System.out.println("*****************************************");
      System.out.println("Error in encryption of password");
      System.out.println("******************************************");
    }
  }
}