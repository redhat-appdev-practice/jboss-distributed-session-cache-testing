package com.redhat.consulting.pt.web;

import io.vertx.core.json.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@CommandLine.Command
public class Main implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	@CommandLine.Option(names = {"-t", "--threads"}, description = "Number of parallel threads to use", defaultValue = "10")
	int threads = 10;

	@CommandLine.Option(names = {"-s", "--servers"}, description = "A list of target servers and ports", defaultValue = "localhost:8080/test-distributed-cache-1.0-SNAPSHOT/api/hello-world")
	List<String> targetServers;
	
	public void run() {
		try {
			
			disableCertificateValidation();
			
			// Load example data from JSON file
			String jsonString = new Scanner(Main.class.getResourceAsStream("/user_array.json"), "UTF-8").useDelimiter("\\A").next();
			JsonArray userArray = new JsonArray(jsonString);
			
			int userCount = userArray.size();
			
			// Create poller threads
			List<Thread> threadList = IntStream.rangeClosed(1, threads)
					.mapToObj(i -> new PollerThread(i, threads, targetServers, userArray.getJsonObject(i%userCount)))
					.map(Thread::new)
          .peek(t -> t.setDaemon(false))
					.peek(Thread::start)
					.collect(Collectors.toList());
			Thread.sleep(1000);
			threadList.get(threadList.size() - 1).join(0);
		} catch (Exception ie) {
			throw new RuntimeException(ie);
		}
	}
	
	private void disableCertificateValidation() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext defaultContext = SSLContext.getInstance("TLS");
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
			}
			
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
			}
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				LOG.warn("Custom trust manager in use.");
				return null;
			}
		}};
		defaultContext.init(null, certs, new SecureRandom());
		SSLContext.setDefault(defaultContext);
	}
	
	public static void main(String... args) throws Exception {
		int exitCode = new CommandLine(new Main()).execute(args);
		System.exit(exitCode);
	}
}
