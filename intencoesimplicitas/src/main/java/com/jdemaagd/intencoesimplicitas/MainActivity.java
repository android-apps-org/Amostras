package com.jdemaagd.intencoesimplicitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    private String photoFileName = "photo.jpg";

    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Opens website specified by URL represented by variable urlAsString using implicit Intents
     *
     * @param v Button that was clicked
     */
    public void onClickOpenWebPage(View v) {
        String urlAsString = "https://github.com/jdemaagd?tab=repositories";
        openWebPage(urlAsString);
    }

    /**
     * Opens map to location represented by variable addressString using implicit Intents
     *
     * @param v Button that was clicked
     */
    public void onClickOpenAddress(View v) {
        String addressString = "343 South Fifth Avenue, Ann Arbor, MI 48104";

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .appendQueryParameter("q", addressString);
        Uri addressUri = builder.build();

        showMap(addressUri);
    }

    /**
     * Share text contained within String textThatYouWantToShare
     *
     * @param v Button that was clicked
     */
    public void onClickShareText(View v) {
        String textThatYouWantToShare =
                "Sharing the coolest thing I've learned so far!";
        shareText(textThatYouWantToShare);
    }

    /**
     * Fire off implicit Intent
     *
     * @see <http://developer.android.com/guide/components/intents-common.html/>
     *
     * @param v Button that was clicked
     */
    public void onClickTakePhoto(View v) {
        Toast.makeText(this,
                "Create Your Own Implicit Intent",
                Toast.LENGTH_SHORT)
                .show();

        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(MainActivity.this, "com.codepath.fileprovider.intencoesimplicitas", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // startActivityForResult() using an intent that no app can handle will crash app
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    // returns photo file stored on disk given fileName
    private File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories
        // then do not need to request external read/write runtime permissions
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), LOG_TAG);

        // create storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(LOG_TAG, "failed to create directory");
        }

        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    /**
     * Fires off an implicit Intent to open a web page
     *
     * @param url Url of web page to open
     */
    private void openWebPage(String url) {
        Uri webPage = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Share text (implicit intent) via chooser to choose app to handle Intent
     *
     * @param textToShare Text that will be shared
     */
    private void shareText(String textToShare) {
        String mimeType = "text/plain";

        String title = "Learning How to Share";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }

    /**
     * Fires off implicit Intent to view a location on a map
     *
     * @param geoLocation The Uri representing the location that will be opened in the map
     */
    private void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}