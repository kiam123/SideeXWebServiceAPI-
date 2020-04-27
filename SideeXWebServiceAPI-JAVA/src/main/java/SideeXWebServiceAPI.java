

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import okhttp3.*;

public class SideeXWebServiceAPI {
	final OkHttpClient httpClient = new OkHttpClient();

	public String runTestSuite(String serverURL, File file) throws Exception {
		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
				.addFormDataPart("file", file.getName(), RequestBody.create(file, MediaType.parse("application/zip")))
				.build();

		Request request = new Request.Builder().url(serverURL).post(requestBody).build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			return response.body().string();
		}

	}

	public String getState(String serverURL, String token) throws Exception {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(serverURL).newBuilder();
		urlBuilder.addQueryParameter("token", token);
		String url = urlBuilder.build().toString();

		Request request = new Request.Builder().url(url).get().build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			return response.body().string();
		}
	}

	public void download(String serverURL, String token, int option) throws Exception {
//		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//				.addFormDataPart("token", token).build();
		HttpUrl.Builder urlBuilder = HttpUrl.parse(serverURL).newBuilder();
		urlBuilder.addQueryParameter("token", token);
		String url = urlBuilder.build().toString();

//		Request request = new Request.Builder().url(serverURL).post(requestBody).build();
		Request request = new Request.Builder().url(url).get().build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			if (response.headers("content-disposition") == null) {
				System.out.println(response.body());
			} else {
				for (int i = 0; i < 2; i++) {
					if (i == 1) {
						FileOutputStream fos;
						if(option == 0) {
							fos = new FileOutputStream("./reports.zip");
						} else {
							fos = new FileOutputStream("./logs.zip");
						}
						fos.write(response.body().bytes());
						fos.close();
						System.out.println("finish");
					}
				}
			}
		}
	}
	public void downloadReport(String serverURL, String token) throws Exception {
//		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//				.addFormDataPart("token", token).build();
		HttpUrl.Builder urlBuilder = HttpUrl.parse(serverURL).newBuilder();
		urlBuilder.addQueryParameter("token", token);
		String url = urlBuilder.build().toString();

//		Request request = new Request.Builder().url(serverURL).post(requestBody).build();
		Request request = new Request.Builder().url(url).get().build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			if (response.headers("content-disposition") == null) {
				System.out.println(response.body());
			} else {
				String line = (response.headers("Content-Disposition").toString());
				Pattern regx = Pattern.compile("[^\"][^\"]+[^\"]");
				Matcher match = regx.matcher(line);

				for (int i = 0; i < 2; i++) {
					match.find();
					if (i == 1) {
						FileOutputStream fos = new FileOutputStream("./" + match.group());
						fos.write(response.body().bytes());
						fos.close();
						System.out.println("finish");
					}
				}
			}
		}
	}

	public void downloadLog(String serverURL, String token) throws Exception {
//		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//		.addFormDataPart("token", token).build();
		HttpUrl.Builder urlBuilder = HttpUrl.parse(serverURL).newBuilder();
		urlBuilder.addQueryParameter("token", token);
		String url = urlBuilder.build().toString();

//		Request request = new Request.Builder().url(serverURL).post(requestBody).build();
		Request request = new Request.Builder().url(url).get().build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			if (response.headers("content-disposition") == null) {
				System.out.println(response.body());
			} else {
//				String line = (response.headers("Content-Disposition").toString());
//				Pattern regx = Pattern.compile("[^\"][^\"]+[^\"]");
//				Matcher match = regx.matcher(line);

				FileOutputStream fos = new FileOutputStream("./" + "logs.zip");
				fos.write(response.body().bytes());
				fos.close();
				System.out.println("finish");
//				for (int i = 0; i < 2; i++) {
//					match.find();
//					if (i == 1) {
//						FileOutputStream fos = new FileOutputStream("./" + "logs.zip");
//						fos.write(response.body().bytes());
//						fos.close();
//						System.out.println("finish");
//					}
//				}
			}
		}
	}

	public String deleteReport(String serverURL, String token) throws Exception {
		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
				.addFormDataPart("token", token).build();

		Request request = new Request.Builder().url(serverURL).post(requestBody).build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			return response.body().string();
		}
	}
}
