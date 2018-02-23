package at.survivalcraft.download;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public final class Downloader implements RBCWrapperDelegate {
    public Downloader(String localPath, String remoteURL) {
        FileOutputStream fos;
        ReadableByteChannel rbc;
        URL url;

        try {
            url = new URL(remoteURL);
            rbc = new RBCWrapper(Channels.newChannel(url.openStream()), contentLength(url), this);
            File f = new File(localPath);
            if (f.exists())
                f.delete();
            fos = new FileOutputStream(localPath);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Uh oh: " + e.getMessage());
        }
    }

    public void rbcProgressCallback(RBCWrapper rbc, double progress) {
        System.out.println(String.format("download progress %d bytes received, %.02f%%", rbc.getReadSoFar(), progress));
    }

    private int contentLength(URL url) {
        HttpURLConnection connection;
        int contentLength = -1;

        try {
            HttpURLConnection.setFollowRedirects(false);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            contentLength = connection.getContentLength();
        } catch (Exception e) {
        }

        return contentLength;
    }
}
