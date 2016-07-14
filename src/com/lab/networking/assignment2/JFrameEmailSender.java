/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab.networking.assignment2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Raphson
 */
public class JFrameEmailSender extends JFrame {

    private Container pane;
    private JLabel lblFrom, lblTo, lblSubject, lblMessage;
    private JTextField fromTextField, toTextField, subjectTextField;
    private JButton btnSend, btnClear, btnQuit;
    private JTextArea messageTextArea;
    
    public JFrameEmailSender() {
        super("MyMailClient");
        generateDisplayWindow();
    }
    
    private void generateDisplayWindow(){
        setLayout(null);
        
        pane = this.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        
        JPanel pnlMail = new JPanel(null);
        pnlMail.setBounds(0, 0, 450, 418);
        
        lblFrom = new JLabel("From :");
        lblFrom.setBounds(10, 15, 60, 28);
        lblFrom.setVerticalAlignment(SwingConstants.CENTER);
        lblFrom.setHorizontalAlignment(SwingConstants.LEFT);
        pnlMail.add(lblFrom);
        
        fromTextField = new JTextField();
        fromTextField.setBounds(75, 12, 360, 28);
        pnlMail.add(fromTextField);
        
        lblTo = new JLabel("To :");
        lblTo.setBounds(10, 46, 60, 28);
        lblTo.setVerticalAlignment(SwingConstants.CENTER);
        lblTo.setHorizontalAlignment(SwingConstants.LEFT);
        pnlMail.add(lblTo);
        
        toTextField = new JTextField();
        toTextField.setBounds(75, 46, 360, 28);
        pnlMail.add(toTextField);
        
        lblSubject = new JLabel("Subject :");
        lblSubject.setBounds(10, 80, 60, 28);
        lblSubject.setVerticalAlignment(SwingConstants.CENTER);
        lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
        pnlMail.add(lblSubject);
        
        subjectTextField = new JTextField();
        subjectTextField.setBounds(75, 80, 360, 28);
        pnlMail.add(subjectTextField);
        
        lblMessage = new JLabel("Message ");
        lblMessage.setBounds(10, 114, 420, 28);
        lblMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblMessage.setHorizontalAlignment(SwingConstants.LEFT);
        pnlMail.add(lblMessage);
        
        messageTextArea = new JTextArea(10, 30);
        messageTextArea.setBounds(10, 148, 430, 188);
        pnlMail.add(messageTextArea);
        
        btnSend = new JButton("Send");
        btnSend.setBounds(10, 342, 135, 40);
        btnSend.addActionListener((ActionEvent event) -> {
            buttonSendActionPerformed(event);
        });
        pnlMail.add(btnSend);
        
        btnClear = new JButton("Clear");
        btnClear.setBounds(154, 342, 135, 40);
        btnClear.addActionListener((ActionEvent event) -> {
            buttonClearActionPerformed(event);
        });
        pnlMail.add(btnClear);
        
        btnQuit = new JButton("Quit");
        btnQuit.setBounds(295, 342, 135, 40);
        btnQuit.addActionListener((ActionEvent event) -> {
            buttonQuitActionPerformed(event);
        });
        pnlMail.add(btnQuit);
        
        
        pane.add(pnlMail);
    }
    
    private boolean validateFields() {
        if (fromTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter From address!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            fromTextField.requestFocus();
            return false;
        }
        
        if (toTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter To address!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            toTextField.requestFocus();
            return false;
        }
         
        if (subjectTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter subject!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            subjectTextField.requestFocus();
            return false;
        }
         
        if (messageTextArea.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter message!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            messageTextArea.requestFocus();
            return false;
        }
         
        return true;
    }
    
    public void buttonSendActionPerformed(ActionEvent e){
        if (!validateFields()) {
            return;
        }
        
        String fromAddress = fromTextField.getText();
        String toAddress = toTextField.getText();
        String subject = subjectTextField.getText();
        String message = messageTextArea.getText();
        
        try {
            EmailUtilities.sendEmail(fromAddress, toAddress, subject, message);
             
            JOptionPane.showMessageDialog(this,
                    "The e-mail has been sent successfully!");
             
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error while sending the e-mail: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buttonClearActionPerformed(ActionEvent e){
        fromTextField.setText("");
        toTextField.setText("");
        subjectTextField.setText("");
        messageTextArea.setText("");
    }
    public void buttonQuitActionPerformed(ActionEvent e){
        this.dispose();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrameEmailSender appSender = new JFrameEmailSender();
                appSender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                appSender.setSize(450, 418);
                appSender.setResizable(false);
                appSender.setLocationByPlatform(true);
                appSender.setVisible(true);
            }
        });
    }
}
