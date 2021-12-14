package tux.rgb.editor.gui;

import tux.rgb.editor.util.ColourUtil;
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
import java.util.Objects;

/**
 * @author Tuxiscool
 * @since 10/12/21
 */

public class RgbGui implements ActionListener {

    private static JLabel success;
    private static JTextField valHexR, valHexG, valHexB, valDir;

    private static JButton button;
    private static final JPanel panel = new JPanel();

    private static Image icon;

    public static void main(String[] args) throws IOException, FontFormatException {
        System.out.println("Starting " + ColourUtil.RED + "R" + ColourUtil.GREEN + "G" + ColourUtil.BLUE + "B" + ColourUtil.RESET + " Changer");

        try {
            icon = ImageIO.read(Objects.requireNonNull(RgbGui.class.getResource("/images/softwarelogo.jpg")));
        } catch (Exception e) {
            System.out.println(ColourUtil.RED + "Error finding logo");
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\resources\\fonts\\Comfortaa.ttf")));

        JFrame frame = new JFrame("RGB Changer");

        frame.setSize(475, 315);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.add(panel);
        panel.setLayout(null);

        JLabel rval = new JLabel("Red Value: ");
        rval.setBounds(10, 30, 100, 35);
        rval.setForeground(Color.WHITE);
        rval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(rval);

        JLabel gval = new JLabel("Green Value: ");
        gval.setBounds(10, 60, 100, 35);
        gval.setForeground(Color.WHITE);
        gval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(gval);

        JLabel bval = new JLabel("Blue Value: ");
        bval.setBounds(10, 90, 100, 35);
        bval.setForeground(Color.WHITE);
        bval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(bval);

        JLabel dirval = new JLabel("Directory Value: ");
        dirval.setBounds(10, 120, 100, 35);
        dirval.setForeground(Color.WHITE);
        dirval.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(dirval);

        valHexR = new JTextField(20);
        valHexR.setBounds(100, 30, 165, 25);
        valHexR.setBorder(BorderFactory.createLineBorder(new Color(255, 220, 228), 2, true));
        valHexR.setFont(new Font("Comfortaa", Font.BOLD, 12));
        panel.add(valHexR);

        valHexG = new JTextField(20);
        valHexG.setBounds(100, 60, 165, 25);
        valHexG.setBorder(BorderFactory.createLineBorder(new Color(228, 246, 223), 2, true));
        valHexG.setFont(new Font("Comfortaa", Font.BOLD, 12));
        panel.add(valHexG);

        valHexB = new JTextField(20);
        valHexB.setBounds(100, 90, 165, 25);
        valHexB.setBorder(BorderFactory.createLineBorder(new Color(195, 239, 255), 2, true));
        valHexB.setFont(new Font("Comfortaa", Font.BOLD, 12));
        panel.add(valHexB);

        valDir = new JTextField(20);
        valDir.setBounds(100, 120, 165, 25);

        panel.add(valDir);

        button = new JButton("CONFIRM: ");
        button.setBounds(100, 150, 165, 25);
        button.setFont(new Font("Comfortaa", Font.BOLD, 12));
        button.addActionListener(new RgbGui());
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 174, 255)));
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 175, 450, 25);
        success.setFont(new Font("SansSerif", Font.ITALIC, 12));
        success.setForeground(Color.WHITE);
        panel.add(success);
        panel.setBackground(new Color(38, 38, 38, 236));
        panel.setBorder(BorderFactory.createLineBorder(new Color(ColourUtil.getRed() / 255, ColourUtil.getGreen() / 255, ColourUtil.getBlue() / 255), 2, false));

        frame.setVisible(true);
        frame.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        float redfloat = Float.parseFloat(valHexR.getText());
        float greenfloat = Float.parseFloat(valHexG.getText());
        float bluefloat = Float.parseFloat(valHexB.getText());

        int redint = (int) redfloat;
        int greenint = (int) greenfloat;
        int blueint = (int) bluefloat;

        if (redint <= 255 && greenint <= 255 && blueint <= 255) {
            if (redint >= 0 && greenint >= 0 && blueint >= 0) {

                System.out.println(" R: " + valHexR.getText() + " G: " + valHexG.getText() + " B: " + valHexB.getText());
                button.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255), 2, false));
                panel.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));

                valHexR.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));
                valHexG.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));
                valHexB.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));

                valDir.setBorder(BorderFactory.createLineBorder(new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255)));

                File filedir = new File(valDir.getText());
                for (File f : SearchUtil.getContaining(filedir)) {
                    if (f.getName().endsWith(".png") || f.getName().endsWith(".PNG")) {
                        success.setText("Found Directory: " + filedir);

                        try {
                            success.setText("Attempting to modify image: " + f.getName());
                            System.out.println(ColourUtil.YELLOW + "Attempting to modify image: " + f.getName());

                            BufferedImage image = ImageIO.read(f);
                            for (int y = 0; y < image.getHeight(); y++) {
                                for (int x = 0; x < image.getWidth(); x++) {
                                    int pixel = image.getRGB(x, y);

                                    int alpha = (pixel >> 24) & 0xff;
                                    int red = (pixel >> 16) & 0xff;
                                    int green = (pixel >> 8) & 0xff;
                                    int blue = pixel & 0xff;


                                    pixel = (alpha << 24) | (redint * red / 100 << 16) | (greenint * green / 100 << 8) | (blueint * blue / 100);


                                    image.setRGB(x, y, pixel);
                                }
                            }

                            File logfile = new File("log.txt");
                            if (logfile.createNewFile()) {
                                System.out.println(ColourUtil.GREEN + "log.txt made.");
                            }

                            ImageIO.write(image, "png", new File(String.valueOf((f))));

                            FileWriter log = new FileWriter("log.txt");

                            log.write("_____   _____ ____        ______ _____ _____ _______ ____  _____   ");
                            log.write(System.lineSeparator() + "|  __ \\ / ____|  _ \\      |  ____|  __ \\_   _|__   __/ __ \\|  __ \\  ");
                            log.write(System.lineSeparator() + "| |__) | |  __| |_) |_____| |__  | |  | || |    | | | |  | | |__) | ");
                            log.write(System.lineSeparator() + "|  _  /| | |_ |  _ <______|  __| | |  | || |    | | | |  | |  _  /  ");
                            log.write(System.lineSeparator() + "| | \\ \\| |__| | |_) |     | |____| |__| || |_   | | | |__| | | \\ \\  ");
                            log.write(System.lineSeparator() + "|_|  \\_\\\\_____|____/      |______|_____/_____|  |_|  \\____/|_|  \\_\\");

                            Color rgbtohex = new Color(Float.parseFloat(valHexR.getText()) / 255, Float.parseFloat(valHexG.getText()) / 255, Float.parseFloat(valHexB.getText()) / 255);
                            String hex = "#" + Integer.toHexString(rgbtohex.getRGB()).substring(2);

                            log.write(System.lineSeparator() + "Values: " + "RedValue: " + valHexR.getText() + " GreenValue: " + valHexG.getText() + " BlueValue: " + valHexB.getText() + System.lineSeparator() + "HexValue: " + hex + System.lineSeparator() + "Directory: " + filedir);
                            log.close();

                        } catch (IOException ex) {
                            success.setText("Failed to save." + f.getName());
                            System.out.println(ColourUtil.RED + "Failed to modify image: " + f.getName());
                            ex.printStackTrace();
                        }

                        success.setText("Finished modifying images.");
                        System.out.println(ColourUtil.GREEN + "Finished modifying image: " + f.getName());
                    }
                }

                System.out.println(ColourUtil.PURPLE + "Finished editing images.");
            } else {
                success.setText("Values are below 0.");
                System.out.println(ColourUtil.RED + "Values are below 0.");
                try {
                    Thread.sleep(2000);
                    success.setText("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            success.setText("Values are over 255.");
            System.out.println(ColourUtil.RED + "Values are over 255.");

        }


    }
}



