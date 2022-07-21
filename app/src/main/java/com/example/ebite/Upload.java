package com.example.ebite;

public class Upload {
    String description, imageUri;

    public Upload() {
    }

    public Upload(String description, String imageUri) {

        if (description.trim().equals("")) {
            description = "No description";
        }

        this.description = description;
        this.imageUri = imageUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
