package SideeXWebServiceAPI;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;

public class Main {
	public static void main(String[] args) {
		SideeXWebServiceAPI sideeXWebServiceAPI = new SideeXWebServiceAPI();
		File file = new File("D:\\web\\Selab-web\\sideex-webservice-api\\inputs.zip");
		String url = "http://127.0.0.1:50000/";
		try {
//			System.out.println(sideeXWebServiceAPI.runTestSuite(url + "sideex-webservice", file));

//			System.out.println(sideeXWebServiceAPI.getState(url+"sideex-webservice-state", "4fc42199-4a3f-4f91-b33d-9781617c6a30"));

			sideeXWebServiceAPI.download(url+"sideex-webservice-reports", "0a02b723-d80e-4e80-ad9f-194b97d6ada2", 0);
			sideeXWebServiceAPI.download(url+"sideex-webservice-logs", "0a02b723-d80e-4e80-ad9f-194b97d6ada2", 1);

//			System.out.println(sideeXWebServiceAPI.deleteReport(url+"sideex-webservice-delete", "4fc42199-4a3f-4f91-b33d-9781617c6a30"));
//			sideeXWebServiceAPI.deleteReport(url+"sideex-webservice-delete", "4fccf763-deec-40ab-afe1-7ca3c18734b8");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
