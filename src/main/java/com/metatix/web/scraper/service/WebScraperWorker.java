package com.metatix.web.scraper.service;

import java.util.LinkedList;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metatix.web.scraper.common.AppConstants;
import com.metatix.web.scraper.common.ApplicationProperties;
/**
 * @author S.Sathishkumar
 *
 */
@Service
public class WebScraperWorker {

	private final LinkedList<Callable<Boolean>> jobQueue = new LinkedList<Callable<Boolean>>();
	public static int numberOfJobsCurrentlyRunning=0;
	private WorkerNode[] threads;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@PostConstruct
	private void startService() {
		Integer numberOfParallelJob=Integer.parseInt(applicationProperties.getProperty(AppConstants.NUMBER_OF_JOBS_RUN_PARALLEL,"2"));
		threads = new WorkerNode[numberOfParallelJob];
		for(int i=0; i<numberOfParallelJob; i++) {
			threads[i]=new WorkerNode();
			threads[i].start();
		}
	}
	
	public void submitJob(Callable<Boolean> r) {
		synchronized (jobQueue) {
			jobQueue.addLast(r);
			jobQueue.notify();
		}
	}

	public int getCurrentJobQueueSize() {
		return jobQueue.size();
	}

	private class WorkerNode extends Thread {
		public void run() {
			Callable<Boolean> r;
			while (true) {
				synchronized (jobQueue) {
					while (jobQueue.isEmpty()) {
						try {
							jobQueue.wait();
						} catch (InterruptedException ignore) {
						}
					}
					r= jobQueue.removeFirst();
				}
				try {
					numberOfJobsCurrentlyRunning++;
					r.call();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					numberOfJobsCurrentlyRunning--;
				}
			}
		}
	}
	
}
