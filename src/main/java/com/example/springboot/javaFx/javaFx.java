// package com.example.springboot.javaFx;


// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.VBox;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;

// import java.io.File;

// public class ImageUploaderApp extends Application {

//     private javax.swing.text.html.ImageView;

//     public static void main(String[] args) {
//         launch(args);
//     }

//     @Override
//     public void start(Stage primaryStage) {
//         primaryStage.setTitle("Image Uploader");

//         imageView = new ImageView();
//         Button uploadButton = new Button("Upload Image");

//         uploadButton.setOnAction(e -> {
//             FileChooser fileChooser = new FileChooser();
//             fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//             File selectedFile = fileChooser.showOpenDialog(primaryStage);

//             if (selectedFile != null) {
//                 // Load and display the selected image
//                 Image image = new Image(selectedFile.toURI().toString());
//                 imageView.setImage(image);
//             }
//         });

//         VBox vBox = new VBox(imageView, uploadButton);
//         Scene scene = new Scene(vBox, 400, 300);

//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }
// }
