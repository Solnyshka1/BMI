package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField height;

    @FXML
    private TextField weight;

    @FXML
    private TextField bmiResult;

    @FXML
    private TextField idealWeight;

    @FXML
    private TextField bmiCategory1;

    @FXML
    private TextField bodyFat;

    @FXML
    public void calculateBMI() {
        try {
            String heightText = height.getText().trim();
            String weightText = weight.getText().trim();

            // Parse height and weight
            double heightValue = 0.0;
            double weightValue = 0.0;

            if (!heightText.isEmpty() && !weightText.isEmpty()) {
                // Assuming height is in cm and weight in kg
                heightValue = Double.parseDouble(heightText);
                weightValue = Double.parseDouble(weightText);

                // Calculate BMI
                double bmi = weightValue / Math.pow(heightValue / 100, 2);
                bmiResult.setText(String.format("%.2f", bmi));

                // Determine BMI category
                String category = determineBMICategory(bmi);
                bmiCategory1.setText(category);

                // Calculate ideal weight (using the Robinson formula)
                double idealBodyWeight = calculateIdealWeight(heightValue);
                idealWeight.setText(String.format("%.2f kg", idealBodyWeight));

                // Optionally calculate body fat percentage
                double bodyFatPercentage = calculateBodyFatPercentage(bmi);
                bodyFat.setText(String.format("%.2f%%", bodyFatPercentage));
            } else {
                showAlert("Please enter valid height and weight values.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid input! Please enter numeric values for height and weight.");
        }
    }

    private String determineBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    private double calculateIdealWeight(double height) {
        // Using the Robinson formula: Ideal Weight (kg) = 52 + 1.9 * (height (inches) - 60)
        double heightInInches = height / 2.54; // Convert cm to inches
        return 52 + 1.9 * (heightInInches - 60);
    }

    private double calculateBodyFatPercentage(double bmi) {
        // Simple formula for body fat percentage based on BMI
        return (1.20 * bmi) + (0.23 * 25) - 16.2; // Assuming average age is 25
    }

    @FXML
    public void handleClear() {
        height.clear();
        weight.clear();
        bmiResult.clear();
        idealWeight.clear();
        bmiCategory1.clear();
        bodyFat.clear();
    }

    @FXML
    public void handleExit() {
        System.exit(0);
    }

    @FXML
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("BMI Calculator");
        alert.setContentText("This is a simple BMI Calculator application built with JavaFX.");
        alert.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
