package com.ssm.application.thread;

import java.lang.management.ThreadInfo;

import org.apache.log4j.Logger;


public class DeadlockThreadLogHandler implements DeadlockThreadHandler {
	private static final Logger LOGGER = Logger.getLogger(DeadlockThreadLogHandler.class);

	@Override
	public void handleDeadlock(final ThreadInfo[] deadlockedThreads) {
		if (deadlockedThreads != null) {
			LOGGER.error("Deadlock detected");

			for(ThreadInfo threadInfo : deadlockedThreads) {

				if (threadInfo != null) {

					for (Thread thread : Thread.getAllStackTraces().keySet()) {

						if (thread.getId() == threadInfo.getThreadId()) {
							//log each thread info
							LOGGER.error(threadInfo.toString().trim());

							for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
								//log the stack trace for each thread
								LOGGER.error("\n" + stackTraceElement.toString().trim());
							}
						}
					}
				}
			}
		}
	}
}
