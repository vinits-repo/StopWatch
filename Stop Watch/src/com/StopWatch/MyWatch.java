package com.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MyWatch {

    private JFrame frame;
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    private long startTime;
    private Timer timer;

    private boolean running;
    private long elapsedTime = 0;
    private JLabel heading;
    private Font font = new Font("Arial", Font.PLAIN, 40);

    public MyWatch() {
        frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("My StopWatch");
        frame.setSize(400, 400);
        frame.setLocation(600,200);
        frame.setLayout(new FlowLayout());


//        working on heading
        heading = new JLabel("My StopWatch");
        heading.setFont(font);
        frame.setLayout(new GridLayout(0, 1));
        frame.add(heading);

        timeLabel = new JLabel("0:00:00");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(stopButton);
        frame.add(resetButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!running) {
                    start();
                } else {
                    stop();
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        frame.setVisible(true);
    }

    private void start() {

        startTime = System.currentTimeMillis() - elapsedTime;
        running = true;
        startButton.setText("Pause");

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime = System.currentTimeMillis() - startTime;
                updateDisplay();
            }
        });
        timer.start();
    }

    private void stop() {
        if (running) {
            timer.stop();
            running = false;
            startButton.setText("Resume");
        }
    }

    private void reset() {
        if (running) {
            timer.stop();
            running = false;
        }
        elapsedTime = 0;
        updateDisplay();
        startButton.setText("Start");
    }

    private void updateDisplay() {
        int seconds = (int) (elapsedTime / 1000);
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        String time = String.format("%d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(time);
    }
}