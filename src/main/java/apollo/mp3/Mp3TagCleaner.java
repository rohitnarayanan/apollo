package apollo.mp3;

import java.nio.file.FileVisitResult;
import java.nio.file.Paths;

import accelerate.commons.utils.NIOUtils;

/**
 * PUT DESCRIPTION HERE
 * 
 * @version 1.0 Initial Version
 * @author Rohit Narayanan
 * @since December 20, 2018
 */
public class Mp3TagCleaner {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NIOUtils.walkFileTree(Paths.get("/Users/rohitnarayanan/Music/Library/Rock/Pink Floyd/The Division Bell"),
					null, null, null, (aPath -> "mp3".equals(NIOUtils.getFileExtn(aPath))), (aPath -> {
						Mp3Tag tag = new Mp3Tag(aPath);
						tag.addJsonIgnoreFields("artwork");

						System.out.println(aPath);
						System.out.println(tag.toJSON());
						System.out.println("*********************************");
						return FileVisitResult.CONTINUE;
					}), null);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}
