import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class GDesktopProb {
	private final static int N_CONSUMERS = 4;

	//it starts here
  // The put(e) and take() methods of a BlockingQueue
  // will the block the thread indefinitely
  // if the queue is full or empty (ie. the operation
  // cannot be performed)
	public static void startIndexing (File[] roots) {
		BlockingQueue<File> queue = new LinkedList<File>();
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {return true;}
		};

		for (File root : roots) {
			(new FileCrawlerProb(queue, filter, root)).start();;
		}

		for (int i = 0; i < N_CONSUMERS; i++) {
			(new IndexerProb(queue)).start();
		}
	}
}

class FileCrawlerProb extends Thread {
	private final BlockingQueue<File> fileQueue;
	private final FileFilter fileFilter;
	private final File root;

	FileCrawlerProb (BlockingQueue<File> queue, FileFilter filter, File root) {
		this.fileQueue = queue;
		this.fileFilter = filter;
		this.root = root;
	}

	public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);

		if (entries != null) {
			for (File entry : entries) {
				if (entry.isDirectory()) {
					crawl(entry);
				}
				else {
					fileQueue.put(entry);
				}
			}
		}
	}
}

class IndexerProb extends Thread {
	private final BlockingQueue<File> queue;

	public IndexerProb (BlockingQueue<File> queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			indexFile(queue.take());
		}
	}

	private void indexFile(File file) {
		// code for analyzing the context of the file is skipped for simplicity
	}
}
