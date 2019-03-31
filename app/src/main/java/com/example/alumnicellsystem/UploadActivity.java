package com.example.alumnicellsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class UploadActivity extends AppCompatActivity {

    private EditText filePath;
    private String path;
    private Uri fileURI;
    private Button upload;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        filePath = findViewById(R.id.file_path);

        filePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });
        progressBar = findViewById(R.id.progress_bar);

        upload = findViewById(R.id.upload_excel);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(path)){
                    Toast.makeText(UploadActivity.this, "Select File", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    multipartExcelUpload(path);
                }
            }
        });

        ActivityCompat.requestPermissions(this,
                new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                            showMessageOKCancel("You need to allow access to this permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{READ_EXTERNAL_STORAGE},
                                                        1);

                                            }
                                        }
                                    }, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });

                        } else if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                            showMessageOKCancel("You need to allow access to this permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{READ_EXTERNAL_STORAGE},
                                                        1);

                                            }
                                        }
                                    }, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });

                        }
                    }
                }

            }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(UploadActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }

    private void selectFile(){
        Intent intent = new Intent();
        // Set your required file type
        intent.setType("application/vnd.ms-excel");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1001);
    }

    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            Uri currFileURI = data.getData();
            path = currFileURI.getPath();
            fileURI = currFileURI;
            path = getPathFromURI(currFileURI);
            //Log.v("File path", path);
            filePath.setText(path);
        }
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.TITLE, MediaStore.Files.FileColumns.MIME_TYPE, };
        String selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("xlsx");
        String[] selectionArgsPdf = new String[]{ mimeType };
        Cursor cursor = getContentResolver().query(contentUri, null, selectionMimeType, selectionArgsPdf, null);
        //int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
        printArray(cursor);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public void printArray(Cursor cursor){
        Log.v("Column names", String.valueOf(cursor.getColumnCount()));
        for(int i=0;i<cursor.getColumnCount();i++){
            cursor.moveToFirst();
            Log.v(String.valueOf(i), cursor.getColumnName(i)!=null?cursor.getColumnName(i):" ");

        }
    }

    private void multipartExcelUpload(String path) {
        try {

            File file = new File(path);


            FileOutputStream fos = new FileOutputStream(file);
            fos.flush();
            fos.close();

            OkHttpClient client = new OkHttpClient();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.addInterceptor(new AddCookiesInterceptor(getApplicationContext()));
            builder.addInterceptor(new ReceivedCookiesInterceptor(getApplicationContext()));
            client = builder.build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.base_url))
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final UrlService apiService = retrofit.create(UrlService.class);

            RequestBody reqFile = RequestBody.create(MediaType.parse("application/vnd.ms-excel"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload");

            Call<ResponseBody> req = apiService.postExcel(body, name);
            req.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.code() == 200) {
                        Toast.makeText(UploadActivity.this, "Uploaded Succesfully", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(), response.code() + " ", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Request failed", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
