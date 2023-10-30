package com.example.musicapp;

import android.net.Uri;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.util.UUID;

public class FileUploadService {

    public void uploadFile(File file) {
        // Initialize FirebaseStorage
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a unique name for the file using UUID
        String uniqueFileName = UUID.randomUUID().toString();

        // Convert the File object to a Uri
        Uri fileUri = Uri.fromFile(file);

        // Create a reference to the location you want to upload the file
        StorageReference storageReference = storage.getReference().child("uploads/" + uniqueFileName);

        // Upload the file to Firebase Cloud Storage
        UploadTask uploadTask = storageReference.putFile(fileUri);

        // You can add listeners to track the upload progress and handle completion
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // File uploaded successfully
            // You can retrieve the download URL if needed: taskSnapshot.getMetadata().getReference().getDownloadUrl()
        }).addOnFailureListener(e -> {
            // Handle any errors during upload
        });
    }
}
