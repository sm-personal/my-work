package com.ssm.application.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadlockThreadDetector {

	private final DeadlockThreadHandler deadlockThreadHandler;
	private final long duration;
	private final TimeUnit timeUnit;
	private final ThreadMXBean mxbean = ManagementFactory.getThreadMXBean();
	private final ScheduledExecutorService scheduler = 
			Executors.newScheduledThreadPool(1);

	final Runnable deadlockCheck = new Runnable() {
		@Override
		public void run() {
			final long[] deadlockedThreadIds = DeadlockThreadDetector.this.mxbean.findDeadlockedThreads();

			if (deadlockedThreadIds != null) {
				final ThreadInfo[] threadInfos = 
						DeadlockThreadDetector.this.mxbean.getThreadInfo(deadlockedThreadIds);

				DeadlockThreadDetector.this.deadlockThreadHandler.handleDeadlock(threadInfos);
			}
		}
	};

	public DeadlockThreadDetector(final DeadlockThreadHandler deadlockHandler, 
			final long duration, final TimeUnit timeUnit) {
		this.deadlockThreadHandler = deadlockHandler;
		this.duration = duration;
		this.timeUnit = timeUnit;
	}

	public void start() {
		//schedule the deadlock check
		this.scheduler.scheduleAtFixedRate(
				this.deadlockCheck, this.duration, this.duration, this.timeUnit);
	}
}