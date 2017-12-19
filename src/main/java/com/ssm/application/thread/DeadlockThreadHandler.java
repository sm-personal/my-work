package com.ssm.application.thread;

import java.lang.management.ThreadInfo;

public interface DeadlockThreadHandler {
	void handleDeadlock(final ThreadInfo[] deadlockThreads);
}
