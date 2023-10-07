import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUITask implements ActionListener {
    JFrame frame;
    JPanel buttonPanel, bigPanel;
    JMenuBar bar;
    GridLayout buttonLayout, menuLayout, bigPanelLayout;
    JButton north, south, east, west, reset;
    JMenu fontOptionsMenu, fontSizesMenu, textColorMenu, backgroundColorMenu, outlineColorMenu;
    JMenuItem[] options, sizes, textColors, backgroundColors, outlineColors;
    String[] fontNames, textColorNames, backgroundColorNames, outlineColorNames;
    JTextArea textArea = new JTextArea("Text");
    Color currTextColor, currBackgroundColor, currOutlineColor, defaultTextColor, defaultBackColor, defaultOutlineColor;
    Font currFont, defaultFont;
    int currFontSize, defaultSize;
    Font[] fonts;
    int[] fontSizesValues;
    Color[] textColorArray, textBackgroundArray, buttonOutlineColorArray;
    public GUITask(){
        textColorArray = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
        textBackgroundArray = new Color[]{Color.MAGENTA, Color.WHITE, Color.BLUE};
        //textOutlineColorArray = new Color[]{Color.PINK, Color.BLACK, Color.MAGENTA};
        buttonOutlineColorArray = new Color[]{Color.GRAY, Color.ORANGE, Color.CYAN, Color.GREEN};

        options = new JMenuItem[3];
        sizes = new JMenuItem[3];
        textColors = new JMenuItem[3];
        backgroundColors = new JMenuItem[3];
        outlineColors = new JMenuItem[4];

        fontOptionsMenu = new JMenu("Fonts");
        fontSizesMenu = new JMenu("Font Sizes");
        textColorMenu = new JMenu("Text Colors");
        backgroundColorMenu = new JMenu("Text Background Colors");
        outlineColorMenu = new JMenu("Button Outline Colors");


        fontNames = new String[]{"Arial", "Times New Roman", "Consolas"};
        textColorNames = new String[]{"Red", "Green", "Random"};
        backgroundColorNames = new String[]{"Magenta", "White", "Random"};
        outlineColorNames = new String[]{"None", "Orange", "Cyan", "Random"};

        fonts = new Font[3];
        fontSizesValues = new int[]{12, 24, 36};

        for(int i = 0; i< fonts.length; i++){
            fonts[i] = new Font(fontNames[i], Font.PLAIN, 12);
            options[i] = new JMenuItem(fontNames[i]);
            options[i].setFont(fonts[i]);
            options[i].addActionListener(this);
            fontOptionsMenu.add(options[i]);

            sizes[i] = new JMenuItem(fontSizesValues[i] + "");
            sizes[i].setFont(new Font(fontNames[0], Font.PLAIN, fontSizesValues[i]));
            sizes[i].addActionListener(this);
            fontSizesMenu.add(sizes[i]);

            textColors[i] = new JMenuItem(textColorNames[i]);
            textColors[i].setForeground(textColorArray[i]);
            textColors[i].addActionListener(this);
            textColorMenu.add(textColors[i]);

            backgroundColors[i] = new JMenuItem(backgroundColorNames[i]);
            backgroundColors[i].setForeground(textBackgroundArray[i]);
            backgroundColors[i].addActionListener(this);
            backgroundColorMenu.add(backgroundColors[i]);

            outlineColors[i] = new JMenuItem(outlineColorNames[i]);
            outlineColors[i].setForeground(buttonOutlineColorArray[i]);
            outlineColors[i].addActionListener(this);
            outlineColorMenu.add(outlineColors[i]);
        }
        outlineColors[3] = new JMenuItem(outlineColorNames[3]);
        outlineColors[3].setForeground(buttonOutlineColorArray[3]);
        outlineColors[3].addActionListener(this);
        outlineColorMenu.add(outlineColors[3]);

        currFont = fonts[0];
        currFontSize = fontSizesValues[0];
        currTextColor = textColorArray[0];
        currBackgroundColor = textBackgroundArray[0];
        currOutlineColor = buttonOutlineColorArray[0];
        defaultFont = fonts[0];
        defaultSize = fontSizesValues[0];
        defaultTextColor = textColorArray[0];
        defaultBackColor = textBackgroundArray[0];
        defaultOutlineColor = buttonOutlineColorArray[0];


        //border = new LineBorder();
        reset = new JButton("Reset");
        east = new JButton("East");
        south = new JButton("South");
        north = new JButton("North");
        west = new JButton("West");

        north.setBorder(new LineBorder(buttonOutlineColorArray[0]));
        south.setBorder(new LineBorder(buttonOutlineColorArray[0]));
        east.setBorder(new LineBorder(buttonOutlineColorArray[0]));
        west.setBorder(new LineBorder(buttonOutlineColorArray[0]));
        reset.setBorder(new LineBorder(buttonOutlineColorArray[0]));



        frame = new JFrame("GUI Task");
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());
        bar = new JMenuBar();
        menuLayout = new GridLayout(1, 6);
        bar.add(fontOptionsMenu);
        bar.add(fontSizesMenu);
        bar.add(textColorMenu);
        bar.add(backgroundColorMenu);
        bar.add(outlineColorMenu);
        bar.add(reset);

        bigPanel = new JPanel();
        bigPanelLayout = new GridLayout(1,2);
        bigPanel.setLayout(bigPanelLayout);

        buttonPanel = new JPanel();
        buttonLayout = new GridLayout(1, 4);
        buttonPanel.setLayout(buttonLayout);

        buttonPanel.add(north);
        buttonPanel.add(south);
        buttonPanel.add(east);
        buttonPanel.add(west);
        buttonPanel.add(reset);

        north.addActionListener(this);
        south.addActionListener(this);
        east.addActionListener(this);
        west.addActionListener(this);
        reset.addActionListener(this);

        bigPanel.add(buttonPanel);
        bigPanel.add(bar);
        frame.add(bigPanel, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);

        textArea.setBackground(textBackgroundArray[0]);
        textArea.setForeground(textColorArray[0]);
        textArea.setFont(fonts[0]);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        // North
        if(e.getSource() == north) {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigPanelLayout = new GridLayout(1, 2);
            menuLayout = new GridLayout(1, 6);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigPanelLayout);
            bar.setLayout(menuLayout);
            bigPanel.add(buttonPanel);
            bigPanel.add(bar);
            frame.add(bigPanel, BorderLayout.NORTH);
        }

        // South
        if(e.getSource() == south) {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigPanelLayout = new GridLayout(1, 2);
            menuLayout = new GridLayout(1, 6);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigPanelLayout);
            bar.setLayout(menuLayout);
            bigPanel.add(buttonPanel);
            bigPanel.add(bar);
            frame.add(bigPanel, BorderLayout.SOUTH);
        }

        // East
        if(e.getSource() == east) {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(4, 1);
            bigPanelLayout = new GridLayout(2, 1);
            menuLayout = new GridLayout(6, 1);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigPanelLayout);
            bar.setLayout(menuLayout);
            bigPanel.add(buttonPanel);
            bigPanel.add(bar);
            frame.add(bigPanel, BorderLayout.EAST);
        }

        // West
        if(e.getSource() == west) {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(4, 1);
            bigPanelLayout = new GridLayout(2, 1);
            menuLayout = new GridLayout(6, 1);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigPanelLayout);
            bar.setLayout(menuLayout);
            bigPanel.add(buttonPanel);
            bigPanel.add(bar);
            frame.add(bigPanel, BorderLayout.WEST);
        }

        // Reset
        if(e.getSource() == reset) {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigPanelLayout = new GridLayout(1, 2);
            menuLayout = new GridLayout(1, 6);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigPanelLayout);
            bar.setLayout(menuLayout);
            bigPanel.add(buttonPanel);
            bigPanel.add(bar);
            frame.add(bigPanel, BorderLayout.NORTH);

            textArea.setText("Text");
            textArea.setBackground(defaultBackColor);
            textArea.setForeground(defaultTextColor);
            textArea.setFont(new Font(defaultFont.toString(), Font.PLAIN, defaultSize));

            north.setBorder(new LineBorder(defaultOutlineColor));
            south.setBorder(new LineBorder(defaultOutlineColor));
            east.setBorder(new LineBorder(defaultOutlineColor));
            west.setBorder(new LineBorder(defaultOutlineColor));
            reset.setBorder(new LineBorder(defaultOutlineColor));
        }

        // Fonts
        for(int x = 0; x < options.length; x++) {
            if(e.getSource() == options[x]) {
                fontOptionsMenu.setFont(fonts[x]);
                fontSizesMenu.setFont(fonts[x]);
                sizes[0].setFont(fonts[x]);
                sizes[1].setFont(fonts[x]);
                sizes[2].setFont(fonts[x]);
                textColorMenu.setFont(fonts[x]);
                textColors[0].setFont(fonts[x]);
                textColors[1].setFont(fonts[x]);
                textColors[2].setFont(fonts[x]);
                backgroundColorMenu.setFont(fonts[x]);
                backgroundColors[0].setFont(fonts[x]);
                backgroundColors[1].setFont(fonts[x]);
                backgroundColors[2].setFont(fonts[x]);
                outlineColorMenu.setFont(fonts[x]);
                outlineColors[0].setFont(fonts[x]);
                outlineColors[1].setFont(fonts[x]);
                outlineColors[2].setFont(fonts[x]);
                north.setFont(fonts[x]);
                south.setFont(fonts[x]);
                east.setFont(fonts[x]);
                west.setFont(fonts[x]);
                reset.setFont(fonts[x]);
                Font[] temp = fonts;
                currFont = new Font(fontNames[x], Font.PLAIN, currFontSize);
                textArea.setFont(new Font(fontNames[x], Font.PLAIN, currFontSize));
            }


        }

        // Font Sizes
        for(int x = 0; x < sizes.length; x++) {
            if(e.getSource() == sizes[x]) {
                currFontSize = fontSizesValues[x];
                textArea.setFont(textArea.getFont().deriveFont((float) fontSizesValues[x]));
            }
        }

        // Text Colors
        for(int x = 0; x < textColors.length; x++) {
            if(e.getSource() == textColors[x]) {
                if(x == 2)
                    textArea.setForeground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
                else
                    textArea.setForeground(textColorArray[x]);
            }
        }

        // Text Background Colors
        for(int x = 0; x < backgroundColors.length; x++) {
            if(e.getSource() == backgroundColors[x]) {
                if(x == 2)
                    textArea.setBackground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
                else
                    textArea.setBackground(textBackgroundArray[x]);
            }
        }

        // Button Outline Colors
        for(int x = 0; x < outlineColors.length; x++) {
            if(e.getSource() == outlineColors[x]) {
                if(x == 3){
                    Color rand = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
                    north.setBorder(new LineBorder(rand));
                    south.setBorder(new LineBorder(rand));
                    east.setBorder(new LineBorder(rand));
                    west.setBorder(new LineBorder(rand));
                    reset.setBorder(new LineBorder(rand));
                }
                else {
                    north.setBorder(new LineBorder(buttonOutlineColorArray[x]));
                    south.setBorder(new LineBorder(buttonOutlineColorArray[x]));
                    east.setBorder(new LineBorder(buttonOutlineColorArray[x]));
                    west.setBorder(new LineBorder(buttonOutlineColorArray[x]));
                    reset.setBorder(new LineBorder(buttonOutlineColorArray[x]));
                }
            }
        }
        frame.revalidate();
    }



    public static void main(String[] args)
    {
        GUITask app = new GUITask();
    }


}
