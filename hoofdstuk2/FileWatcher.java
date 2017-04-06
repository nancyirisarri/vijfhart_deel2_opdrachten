import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

class FileWatcher {

	public static void main(String args[]) {
		Path path = Paths.get("").toAbsolutePath();

		WatchService watchService = null;

		try {
			watchService = path.getFileSystem().newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (;;) {
			WatchKey key = null;
			try {
				key = watchService.poll(2, TimeUnit.SECONDS);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			if (key != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					switch (event.kind().name()) {
					case "OVERFLOW": {
						System.out.println("overflow");
						break;
					}
					case "ENTRY_MODIFY": {
						Path changed = (Path) event.context();

						if (changed.endsWith("werknemers_kantoren.txt")) {
							System.out.println("modified");
							new WerknemersKantoren().maakObjecten();
							new WerknemersKantoren().leesObjecten();
							System.out.println("remade table file");
						}
						break;
					}
					}
				}
				key.reset();
			}
		}
	}

}
