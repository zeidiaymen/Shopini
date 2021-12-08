
package com.example.demo.service;


import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;

import com.example.demo.models.Address;




public class LocationService {
	
	   public  static String MyIpAdress() throws SocketException {

	        String systemipaddress = "";
	        try {
	        	URL url = new URL("http://checkip.amazonaws.com/");
	    		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
	    		systemipaddress=br.readLine();
	    		
	        } catch (Exception e) {
	            systemipaddress = "Cannot Execute Properly";
	        }
	        return systemipaddress;
	    }

    public   static Address CurrentLocation(String ip) {
        ip = ip.trim();
        Address obj_Location_Use_Bean = new Address();
        try {
            if (ip.contains(",")) {
                String temp_ip[] = ip.split(",");
                ip = temp_ip[1].trim();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        URL url;
        URLConnection uc;

        try {

            url = new URL("http://api.ipinfodb.com/v3/ip-city/?key=bc72ff59eff8dbec3c813748cfe35bd68c3ba534ae321f464804cf643c0bd8cb&ip=" + ip);
            uc = url.openConnection();
            uc.addRequestProperty("User-Agent", 
            		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            uc.connect();
            uc.getInputStream();


            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String strTemp = "";
            String temporaray = "";
            String temp_array[] = null;

            while (null != (strTemp = br.readLine())) {
                temporaray = strTemp;

            }

            temp_array = temporaray.split(";");
            System.out.println("Str length is " + temp_array.length);
            int length = temp_array.length;

            /*while(i<length){
							System.out.println(i);
							System.out.println(temp_array[i]);
							i++;
						}*/
            if (length == 11) {

                obj_Location_Use_Bean.setIp_address(ip);

                if (temp_array[3] != null) {
                    obj_Location_Use_Bean.setCountry_code(temp_array[3]);
                }
                if (temp_array[4] != null) {
                    obj_Location_Use_Bean.setCountry(temp_array[4]);
                }

                if (temp_array[5] != null) {
                    obj_Location_Use_Bean.setState(temp_array[5]);
                }

                if (temp_array[6] != null) {
                    obj_Location_Use_Bean.setCity(temp_array[6]);
                }

                if (temp_array[7] != null) {
                    obj_Location_Use_Bean.setZip(temp_array[7]);
                }

                if (temp_array[8] != null) {
                    obj_Location_Use_Bean.setLat(temp_array[8]);
                }

                if (temp_array[9] != null) {
                    obj_Location_Use_Bean.setLon(temp_array[9]);
                }

                if (temp_array[10] != null) {
                    obj_Location_Use_Bean.setUtc_offset(temp_array[10]);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_Location_Use_Bean;
    }

 

}
