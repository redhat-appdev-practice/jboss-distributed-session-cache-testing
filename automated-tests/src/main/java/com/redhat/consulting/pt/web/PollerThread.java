package com.redhat.consulting.pt.web;

import io.vertx.core.json.JsonObject;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PollerThread implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private static final int SECONDS_PER_MINUTE = 60;
	private static final int MILLIS_PER_SECOND = 1000;
	public static final String RANDOMUSER_ME = "https://randomuser.me/api/";
	
	int threadId;
	
	int totalThreads;
	
	List<String> targetServers;
	private int sleepTime;
	private JsonObject localUserCache;
	
	private HttpClient client;
	
	private String jSessionId = null;
	
	public PollerThread(int threadId, int totalThreads, List<String> targetServers, JsonObject user) {
		super();
		this.threadId = threadId;
		this.targetServers = targetServers;
		this.totalThreads = totalThreads;
		this.localUserCache = user;
	}
	
	@Override
	public void run() {
		LOG.warn("Starting thread {}", threadId);

		// Distribute threads across different sleep times based on their modulus
		int tempSleepTime = 1000;
		if (threadId <= (totalThreads*0.5)) {
			// Make a web request every 5 seconds for 50% of threads
			tempSleepTime = 5000;
		} else if (threadId <= (totalThreads*0.75)) {
			// Make a web request every 10 seconds for 25% of threads
			tempSleepTime = 10000;
		} else if (threadId < (totalThreads*0.95)) {
			// Make a web request every 5 minues for 20% of threads
			tempSleepTime = 5 * SECONDS_PER_MINUTE * MILLIS_PER_SECOND;
		} else {
			// Make a web request every 21 minues for 5% of threads
			tempSleepTime = 21 * SECONDS_PER_MINUTE * MILLIS_PER_SECOND;
		}
		
		int randomization = Double.valueOf(Math.random() * 500).intValue();
		
		sleepTime = tempSleepTime + randomization;
		
		this.startPollLoop();
	}
	
	void startPollLoop() {
		CookieStore cookieManager = new BasicCookieStore();
		client = HttpClients.custom().setDefaultCookieStore(cookieManager).build();
		// client = HttpClient.newBuilder().cookieHandler(cookieManager).followRedirects(HttpClient.Redirect.ALWAYS).build();
		
		// Initialize increment counter for user data
		if (!localUserCache.containsKey("counter")) {
			localUserCache.put("counter", 0);
		}
		
		boolean keepRunning = true;
		
		LOG.warn("Entering polling loop for thread: {}", threadId);
		int counter = 0;
		while (keepRunning) {
			
			// Use modulus distribution to choose the target server
			int serverOffset = threadId%targetServers.size();
			int serverIndex = (counter + serverOffset) % targetServers.size();
			
			String targetServer = targetServers.get(serverIndex);
			
			LOG.debug("Target %s from thread {}", targetServer, threadId);
			
			try {
				URI pollUri = new URI(targetServer);
				
				HttpPost pollReq = new HttpPost(pollUri);
				pollReq.setHeader("Content-Type", "application/json");
				pollReq.setHeader("Accept", "application/json");
				pollReq.setEntity(new StringEntity(localUserCache.encodePrettily()));
				
				HttpResponse pollResponse = client.execute(pollReq);

				String text = new BufferedReader(
						new InputStreamReader(pollResponse.getEntity().getContent(), StandardCharsets.UTF_8))
						              .lines()
						              .collect(Collectors.joining("\n"));
				
				JsonObject pollBody = new JsonObject(text);
				
				// Compare increment values to see if the value is correct
				if (!pollBody.getInteger("counter").equals(localUserCache.getInteger("counter") + 1)) {
					LOG.warn("Counter increment failed: {} : {} : {} : {}",targetServer, threadId, pollBody.getInteger("counter"), localUserCache.getInteger("counter"));
				} else {
					LOG.debug("Successful poll of {} from thread {}", targetServer, threadId);
				}
				
				counter++;
				
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			} catch (Exception e) {
				LOG.warn("Caught an exception", e);
				keepRunning = false;
			}
		}
	}
	
}
