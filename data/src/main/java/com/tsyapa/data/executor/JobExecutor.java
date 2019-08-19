package com.tsyapa.data.executor;

import com.tsyapa.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class JobExecutor implements ThreadExecutor {
	private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			3,
			5,
			10,
			TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(),
			new JobThreadFactory()
	);

	@Inject
	public JobExecutor() { }

	@Override
	public void execute(@NonNull final Runnable runnable) { threadPoolExecutor.execute(runnable); }

	private static class JobThreadFactory implements ThreadFactory {

		private int jobCounter = 0;

		@Override
		public Thread newThread(@NonNull final Runnable runnable) {
			return new Thread(runnable, "clean_job_" + jobCounter++);
		}
	}
}