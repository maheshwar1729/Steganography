package com.rttc.whatsappfbstego;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivityAudio extends AppCompatActivity implements View.OnClickListener {

    //Declaring views
    private Button buttonChoose,buttonChooseAudio;
    private Button buttonUpload,btnShare;
    private VideoView videoView;
    private EditText editText;
    private File selectedFile;
    EmbProcess emb;
    //Image request code
    private int PICK_VIDEO_REQUEST = 1;
    private int PICK_AUDIO_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    String path,totPath,name;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_audio);

        //Requesting storage permission
        requestStoragePermission();

        //Initializing views
        buttonChoose = (Button) findViewById(R.id.buttonChooseAVid);
        buttonUpload = (Button) findViewById(R.id.buttonUploadAH);
        btnShare = (Button) findViewById(R.id.btn_shareA);
        videoView = (VideoView) findViewById(R.id.videoViewHideA);
        editText = (EditText) findViewById(R.id.editTextNameA);
        buttonChooseAudio = (Button) findViewById(R.id.btn_selaud);

        //Setting clicklistener
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        buttonChooseAudio.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /*
    * This is the method responsible for image upload
    * We need the full image path and the name for the image in this method
    * */
    public void uploadMultipart() {
        //getting name for the image

        Toast.makeText(getApplicationContext(),path+", "+name,Toast.LENGTH_LONG).show();
        emb = new EmbProcess();
        //getting the actual path of the image
        // path = path;
            //path = getPath(filePath);
        //Uploading code
        try {
            /* String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request


            new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload */
            emb.emb(path, selectedFile.getPath());


        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }    //method to show file chooser
    private void showFileChooser() throws IOException {
       // File f = new File(Environment.getExternalStorageDirectory().getPath()+"/text.txt");
      // if(!f.exists())
      //     f.createNewFile();
      //  name = editText.getText().toString().trim();
       // FileOutputStream fos = new FileOutputStream(f);
       // byte[] content = name.getBytes();
      //  fos.write(content);
      //  fos.flush();
      //  fos.close();
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST);
    }

    //method to show filechooser for Audio File to be Hidden

    private void showAudioFileChooser() throws IOException {

        Intent ia = new Intent(MainActivityAudio.this, FilePicker.class);

        startActivityForResult(ia,PICK_AUDIO_REQUEST);
    }


    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(),requestCode+" "+resultCode,Toast.LENGTH_LONG).show();
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
           // totPath = getPath(filePath);
           int pathindex = filePath.toString().lastIndexOf('/');
            path = filePath.getPath().toString();
                   // Environment.getExternalStorageDirectory().getPath()+"/"+filePath.toString().substring(pathindex + 1);
            //Toast.makeText(getApplicationContext(), filePath.toString().substring(pathindex + 1), Toast.LENGTH_LONG).show();
            try {

                Cursor vidCursor = this.getContentResolver().query(filePath, null, null,
                        null, null);
                if (vidCursor.moveToFirst())
                {
                    int column_index =
                            vidCursor .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                    Uri filePathUri = Uri.parse(vidCursor.getString(column_index));
                    String video_name =  filePathUri.getLastPathSegment().toString();
                    String file_path=filePathUri.getPath();
                    path = file_path;
                    Toast.makeText(getApplicationContext(),video_name+",    "+file_path,Toast.LENGTH_LONG).show();
                   // Log.i("TAG", video_name + "\b" file_path);
                }
                VideoView video_player_view = (VideoView) findViewById(R.id.videoViewHide);
                MediaController media_Controller = new MediaController(this);
                DisplayMetrics dm = new DisplayMetrics();
                this.getWindowManager().getDefaultDisplay().getMetrics(dm);
                int height = dm.heightPixels;
                int width = dm.widthPixels;
                video_player_view.setMinimumWidth(width);
                video_player_view.setMinimumHeight(height);
                video_player_view.setMediaController(media_Controller);
                video_player_view.setVideoPath(path);
                video_player_view.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_AUDIO_REQUEST && resultCode == -1) {
            if(data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {

                selectedFile = new File
                        (data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
                editText.setText(selectedFile.getPath());
                Toast.makeText(getApplicationContext(),selectedFile.getPath(),Toast.LENGTH_LONG).show();
            }
        }

    }

    //method to get the file path from uri
  /*  public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Video.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path1 = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path1;



}*/


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            try {
                showFileChooser();
            }catch (IOException iex){

            }
        }
        if (v== buttonChooseAudio){
            try {
                showAudioFileChooser();
            }catch (IOException iex){

            }
        }
        if (v == buttonUpload) {
            uploadMultipart();
        }
        if (v==btnShare){

            Intent shareIntent = new Intent(Intent.ACTION_SEND);




            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hai I want to Share this Video With You....Pls Chk");
           // shareIntent.putExtra(Intent.EXTRA_TEXT, "http://maps.google.com/maps?daddr="+cLatLong.getString(cLatLong.getColumnIndex("glat"))+","+ cLatLong.getString(cLatLong.getColumnIndex("glong")));


            shareIntent.setType("video/mp4");
            File f = new File(Environment.getExternalStorageDirectory().getPath()+"/temp.doc");
            Toast.makeText(getApplicationContext(),Environment.getExternalStorageDirectory().getPath()+"/temp.doc",Toast.LENGTH_LONG).show();
            shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
           // shareIntent.setPackage("com.whatsapp");
            startActivity(shareIntent);
            try {
                startActivity(Intent.createChooser(shareIntent, "Share Video..."));
                finish();

            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivityAudio.this,
                        "Could Not Share the Video", Toast.LENGTH_SHORT).show();
            }finally{


            }

        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
      /*  Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://net.simplifiedcoding.androidimageupload/http/host/path")
        );
       // AppIndex.AppIndexApi.start(client, viewAction);*/
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://net.simplifiedcoding.androidimageupload/http/host/path")
        );
       // AppIndex.AppIndexApi.end(client, viewAction);
       // client.disconnect();*/
    }
}
