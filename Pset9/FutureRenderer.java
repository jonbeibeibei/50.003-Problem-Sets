package Week11;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import Week10.ImageData;
import Week10.ImageInfo;

public class FutureRenderer {
	private final ExecutorService executor = new ScheduledThreadPoolExecutor (100);

	void renderPage (CharSequence source) throws Exception {
		final List<ImageInfo> imageInfos = scanForImageInfo(source);

		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
			public List<ImageData> call () {
				List<ImageData> result = new ArrayList<ImageData>();
				for (ImageInfo imageInfo : imageInfos) {
					result.add(imageInfo.downloadImage());
				}

				return result;
			}
		};

		Future<List<ImageData>> future = executor.submit(task);
		renderText(source);

		try {
			List<ImageData> imageData = future.get();
			for (ImageData data: imageData) {
				renderImage(data);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // what's this for
      // what does currentThread refer to
      // if interrupted during future.get(),
      // the thread that is executing the task in the executor
			future.cancel(true);
		}
	}

	private void renderImage(ImageData data) {
		// TODO Auto-generated method stub

	}

	private void renderText(CharSequence source) {
		// TODO Auto-generated method stub

	}

	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		// TODO Auto-generated method stub
		return null;
	}
}
