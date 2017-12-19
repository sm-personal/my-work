package com.ssm.application.controller;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.application.thread.DeadlockThreadDetector;
import com.ssm.application.thread.DeadlockThreadLogHandler;

@RestController
@RequestMapping
public class DeadlockThreadController {

	private static final Logger LOGGER = Logger.getLogger(DeadlockThreadController.class);

	//Use Http verb GET
	@GetMapping(value = "/helloWorld/deadlock")
	public void findDeadlockThreads() {
		//instantiate a deadlockThreadDetector object
		DeadlockThreadDetector deadlockthreadDetector = new DeadlockThreadDetector(new DeadlockThreadLogHandler(), 5, TimeUnit.SECONDS);
		deadlockthreadDetector.start();

		//thread 1 that acquires lock on Integer and String classes in the specified order
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (Integer.class) {
					LOGGER.info("Thread1 acquired lock1");
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException interruptedException) {
						LOGGER.error(interruptedException.getMessage());
					}
					synchronized (String.class) {
						LOGGER.info("Thread1 acquired lock2");
					}
				}
			}

		});
		thread1.start();

		//thread 2 that acquires lock on String and Integer classes in the specified order
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (String.class) {
					LOGGER.info("Thread2 acquired lock2");
					synchronized (Integer.class) {
						LOGGER.info("Thread2 acquired lock1");
					}
				}
			}
		});
		thread2.start();	

		return;

	}

}