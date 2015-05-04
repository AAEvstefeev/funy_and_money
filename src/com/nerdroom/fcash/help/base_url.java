package com.nerdroom.fcash.help;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;

public class base_url {
public static String cat="categories/";

public static void downlud(Uri uri)
{
	DownloadManager.Request r = new DownloadManager.Request(uri);

	// This put the download in the same Download dir the browser uses
	r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "fileName");

	// When downloading music and videos they will be listed in the player
	// (Seems to be available since Honeycomb only)
	r.allowScanningByMediaScanner();

	// Notify user when download is completed
	// (Seems to be available since Honeycomb only)
	r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

	// Start download
//	DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//	dm.enqueue(r);	
}
}
