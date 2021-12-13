package tux.rgb.editor.gui;

import tux.rgb.editor.util.ColourUtils;
import tux.rgb.editor.util.SearchUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Tuxiscool
 * @since 10/12/21
 */

public class RgbGui implements ActionListener {

    private static JLabel success, Rval, Gval, Bval, Dirval;
    private static JTextField valHexR, valHexG, valHexB, valDir;

    private static JButton button;
    private static final JPanel panel = new JPanel();
    private static Image icon;

    public static void main(String[] args) {
        try {
            icon = ImageIO.read(RgbGui.class.getResource("/images/softwarelogo.jpg"));
        }catch(Exception e){
            System.out.println("Error finding logo");
        }
        JFrame frame = new JFrame("RGB Changer");
        frame.setSize(475, 315);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.add(panel);
        panel.setLayout(null);

        Rval = new JLabel("Red Value: ");
        Rval.setBounds(10, 30, 80, 25);
        Rval.setForeground(Color.WHITE);
        Rval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(Rval);

        Gval = new JLabel("Green Value: ");
        Gval.setBounds(10, 60, 80, 25);
        Gval.setForeground(Color.WHITE);
        Gval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(Gval);

        Bval = new JLabel("Blue Value: ");
        Bval.setBounds(10, 90, 80, 25);
        Bval.setForeground(Color.WHITE);
        Bval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(Bval);

        Dirval = new JLabel("Directory Value: ");
        Dirval.setBounds(10, 120, 80, 25);
        Dirval.setForeground(Color.WHITE);
        Dirval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(Dirval);

        valHexR = new JTextField(20);
        valHexR.setBounds(100, 30, 165, 25);
        valHexR.setBorder(BorderFactory.createLineBorder(new Color(ColourUtils.getRed() / 255 / 2, ColourUtils.getGreen() / 255 / 2, ColourUtils.getBlue() / 255 / 2)));
        panel.add(valHexR);

        valHexG = new JTextField(20);
        valHexG.setBounds(100, 60, 165, 25);
        valHexG.setBorder(BorderFactory.createLineBorder(new Color(ColourUtils.getRed() / 255 / 2, ColourUtils.getGreen() / 255 / 2, ColourUtils.getBlue() / 255 / 2)));
        panel.add(valHexG);

        valHexB = new JTextField(20);
        valHexB.setBounds(100, 90, 165, 25);
        valHexB.setBorder(BorderFactory.createLineBorder(new Color(ColourUtils.getRed() / 255 / 2, ColourUtils.getGreen() / 255 / 2, ColourUtils.getBlue() / 255 / 2)));
        panel.add(valHexB);

        valDir = new JTextField(20);
        valDir.setBounds(100, 120, 165, 25);
        panel.add(valDir);

        button = new JButton("CONFIRM: ");
        button.setBounds(100, 150, 165, 25);
        button.setFont(new Font("Consolas", Font.ITALIC, 12));
        button.addActionListener(new RgbGui());
        button.setBorder(BorderFactory.createLineBorder(new Color(0xFF00AEFF, true)));
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 170, 450, 25);
        success.setFont(new Font("SansSerif", Font.ITALIC, 12));
        success.setForeground(Color.WHITE);
        panel.add(success);
        panel.setBackground(new Color(38, 38, 38, 236));
        panel.setBorder(BorderFactory.createLineBorder(new Color(ColourUtils.getRed() / 255, ColourUtils.getGreen() / 255, ColourUtils.getBlue() / 255)));

        frame.setVisible(true);
        frame.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(" R: " + valHexR.getText() + " G: " + valHexG.getText() + " B: " + valHexB.getText());
        button.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));
        panel.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));

        valHexR.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));
        valHexG.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));
        valHexB.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));

        valDir.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));

        File filedir = new File(valDir.getText());
        for (File f : SearchUtil.getContaining(filedir)) {
            if (f.getName().endsWith(".png") || f.getName().endsWith(".PNG")) {
                System.out.println(f.getPath());
                success.setText("Found Directory: " + filedir);

                try {
                    BufferedImage image = ImageIO.read(f);
                    for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                            int pixel = image.getRGB(x, y);

                            int alpha = (pixel >> 24) & 0xff;
                            int red = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                            int blue = pixel & 0xff;

                            Float redfloat = Float.parseFloat(valHexR.getText());
                            Float greenfloat = Float.parseFloat(valHexG.getText());
                            Float bluefloat = Float.parseFloat(valHexB.getText());

                            int redint = redfloat.intValue();
                            int greenint = greenfloat.intValue();
                            int blueint = bluefloat.intValue();

                            pixel = (alpha << 24) | (redint * red / 100 << 16) | (greenint * green / 100 << 8) | (blueint * blue / 100);

                            success.setText("Attempting to modify images.");

                            image.setRGB(x, y, pixel);
                        }
                    }

                    File logfile = new File("log.txt");
                    if (logfile.createNewFile()) {
                        System.out.println("log.txt made.");
                    }

                    ImageIO.write(image, "png", new File(String.valueOf((f))));

                    FileWriter log = new FileWriter("log.txt");

                    log.write("_____   _____ ____        ______ _____ _____ _______ ____  _____   ");
                    log.write(System.lineSeparator() + "|  __ \\ / ____|  _ \\      |  ____|  __ \\_   _|__   __/ __ \\|  __ \\  ");
                    log.write(System.lineSeparator() + "| |__) | |  __| |_) |_____| |__  | |  | || |    | | | |  | | |__) | ");
                    log.write(System.lineSeparator() + "|  _  /| | |_ |  _ <______|  __| | |  | || |    | | | |  | |  _  /  ");
                    log.write(System.lineSeparator() + "| | \\ \\| |__| | |_) |     | |____| |__| || |_   | | | |__| | | \\ \\  ");
                    log.write(System.lineSeparator() + "|_|  \\_\\\\_____|____/      |______|_____/_____|  |_|  \\____/|_|  \\_\\");

                    log.write(System.lineSeparator() + "Values: "  + "RedValue: " + valHexR.getText() + " GreenValue: " + valHexG.getText() + " BlueValue: " + valHexB.getText() + System.lineSeparator() + "Directory: " + filedir);
                    log.close();

                } catch (IOException ex) {
                    success.setText("Failed to save.");
                    ex.printStackTrace();
                }

                success.setText("Finished!");
            }

        }

    }
}



