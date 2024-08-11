package com.sos.searchtool1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Searchtool {

    // Define URLs for each button
    private static final String[] URLs = {
        "https://electric-company.integ.amazon.com/search/5684",
        "https://electric-company.integ.amazon.com/search/5685",
        "",
        ""
    };

    // Define button names
    private static final String[] BUTTON_NAMES = {
        "commsqa-android",
        "commsqa-ios",
        "RC-ios",
        "RC-android"
    };

    public static void main(String[] args) {    

        // Create the frame for the UI
        JFrame frame = new JFrame("Navigate to build");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(6, 1)); // 6 rows, 1 column layout

        // Create a text field for user input
        JTextField queryField = new JTextField(40);
        frame.add(queryField);

        // Create labels for instructions
        JLabel instructionLabel = new JLabel("Enter build number:");
        frame.add(instructionLabel);

        // Create buttons for different URLs
        for (int i = 0; i < BUTTON_NAMES.length; i++) {
            final String url = URLs[i];
            final String buttonName = BUTTON_NAMES[i];
            JButton button = new JButton(buttonName);
            frame.add(button);

            // Add action listener to each button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String partialText = queryField.getText();
                    if (partialText.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter partial text.");
                        return;
                    }

                    // Open the browser and perform the search
                    performSearch(url, partialText);
                }
            });
        }

        // Make the frame visible
        frame.setVisible(true);
    }
   
    // Method to perform search using Selenium WebDriver
    private static void performSearch(String url, String partialText) {
        WebDriver driver = null;

        try {
           // Set path to your chromedriver
            driver = new ChromeDriver();
            driver.get(url);

            // Find and click on the element that contains the partial text
            // Adjust the locator based on the website structure
            // This example assumes a list of elements you need to search through
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + partialText + "')]"));
            if (element != null) {
                element.click();
            } else {
                JOptionPane.showMessageDialog(null, "Element containing partial text not found.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        } finally {
            if (driver != null) {
                
            }
        }
    }
}

