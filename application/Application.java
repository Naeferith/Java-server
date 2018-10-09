package application;

import java.net.*;

/**
 * détermine l'adresse IP de la machine locale et l'affiche sur la console
 * */
public class Application
{
public static int conversionSansSigne(byte b)	 { return b>=0? b: b+256;}

public static void main(String[] args)throws Exception
{
InetAddress ici;

try{ici = InetAddress.getLocalHost();} //appel au serveur de noms local
catch(Exception e){throw new Exception("appel à getLocalHost a echoué");}
String sIP, sNom;
byte []b;
int i0, i1, i2, i3;

sIP = ici.getHostAddress();
sNom = ici.getHostName();
b = ici.getAddress(); // récupère le tableau des 4 octets définissant l'adresse IP
i0 = conversionSansSigne(b[0]);
i1 = conversionSansSigne(b[1]);
i2 = conversionSansSigne(b[2]);
i3 = conversionSansSigne(b[3]);

System.out.println("n° IP de cette machine : "+ sIP);
System.out.println("nom DNS de cette machine : "+ sNom);
System.out.println("n° IP de cette machine : "+ i0+"."+i1+"."+i2+"."+i3);
}
}
