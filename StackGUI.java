package stackvisualizer;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class StackGUI extends JFrame {

    // Stack component
    private StackArray stack;

    // GUI components
    private JTextField inputField;
    private JPanel stackPanel;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JLabel sizeLabel;
    private JLabel topArrow;

    private boolean highlightTop = false;

    // Colors based on the Task 2 design
    private final Color BACKGROUND = new Color(190, 200, 187);
    private final Color PANEL_COLOR = new Color(218, 241, 241);
    private final Color CYAN = new Color(45, 190, 255);
    private final Color HEADER = new Color(85, 85, 85);

    public StackGUI() {

        stack = new StackArray();

        setTitle("Stack Visualizer");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Left: Stack Display
        JPanel leftPanel = createSection("Stack Display");
        leftPanel.setLayout(new BorderLayout(5, 5));

        stackPanel = new JPanel();
        stackPanel.setLayout(new BoxLayout(
                stackPanel, BoxLayout.Y_AXIS));
        stackPanel.setBackground(PANEL_COLOR);

        topArrow = new JLabel("TOP ----> ");
        topArrow.setFont(new Font("Arial", Font.BOLD, 12));
        topArrow.setHorizontalAlignment(JLabel.LEFT);

        leftPanel.add(topArrow, BorderLayout.NORTH);
        leftPanel.add(stackPanel, BorderLayout.CENTER);

        sizeLabel = new JLabel("Maximum Capacity: 10");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 11));
        sizeLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(sizeLabel, BorderLayout.SOUTH);

        leftPanel.setPreferredSize(new Dimension(180, 0));

        // Right side
        JPanel rightPanel = new JPanel(new BorderLayout(15, 15));
        rightPanel.setOpaque(false);

        // Input section
        JPanel inputPanel = createSection("Input");
        inputPanel.setLayout(new BorderLayout(5, 10));

        JPanel inputContent = new JPanel();
        inputContent.setLayout(new BoxLayout(
                inputContent, BoxLayout.Y_AXIS));
        inputContent.setBackground(PANEL_COLOR);

        JLabel inputLabel = new JLabel("Enter Value:");
        inputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        inputField = new JTextField();
        inputField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 32));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CYAN, 2, true),
                new EmptyBorder(4, 6, 4, 6)));

        inputContent.add(inputLabel);
        inputContent.add(Box.createVerticalStrut(5));
        inputContent.add(inputField);
        inputContent.add(Box.createVerticalStrut(12));

        // Six buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(PANEL_COLOR);

        JButton pushButton = createButton(
                "Push", new Color(119, 216, 83));

        JButton popButton = createButton(
                "Pop", new Color(255, 82, 89));

        JButton peekButton = createButton(
                "Peek", new Color(255, 218, 90));

        JButton clearButton = createButton(
                "Clear", new Color(45, 190, 255));

        JButton checkButton = createButton(
                "Check Stack", new Color(218, 155, 225));

        JButton randomButton = createButton(
                "Random", new Color(0, 82, 180));

        buttonPanel.add(pushButton);
        buttonPanel.add(popButton);
        buttonPanel.add(peekButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(randomButton);

        inputContent.add(buttonPanel);
        inputPanel.add(inputContent, BorderLayout.CENTER);

        // Status section
        statusPanel = createSection("Status Message");
        statusPanel.setLayout(new BorderLayout());

        statusLabel = new JLabel("Ready.");
        statusLabel.setVerticalAlignment(JLabel.TOP);
        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(8, 8, 8, 8));

        statusPanel.add(statusLabel, BorderLayout.CENTER);
        statusPanel.setPreferredSize(new Dimension(0, 85));

        rightPanel.add(inputPanel, BorderLayout.CENTER);
        rightPanel.add(statusPanel, BorderLayout.SOUTH);

        // Add left and right sections
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        add(mainPanel);

        // PUSH button
        pushButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(
                        inputField.getText().trim());

                String message = stack.push(value);

                highlightTop = false;
                statusLabel.setText(message);
                updateStackDisplay();

            } catch (NumberFormatException ex) {
                statusLabel.setText(
                        "Invalid input. Enter an integer.");
            }
        });

        // POP button
        popButton.addActionListener(e -> {
            String message = stack.pop();

            highlightTop = false;
            statusLabel.setText(message);
            updateStackDisplay();
        });

        // PEEK button
        peekButton.addActionListener(e -> {
            if (!stack.isEmpty()) {
                highlightTop = true;
            }

            statusLabel.setText(stack.peek());
            updateStackDisplay();
        });

        // CLEAR button
        clearButton.addActionListener(e -> {
            stack.clear();

            highlightTop = false;
            statusLabel.setText("Stack cleared.");
            updateStackDisplay();
        });

        // RANDOM button
        randomButton.addActionListener(e -> {
            String message = stack.random();

            highlightTop = false;
            statusLabel.setText(message);
            updateStackDisplay();
        });

        // CHECK STACK button
        checkButton.addActionListener(e -> {
            statusLabel.setText(stack.checkStack());
        });

        updateStackDisplay();
    }

    // Creates a panel with a dark header
    private JPanel createSection(String title) {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CYAN, 3, true),
                new EmptyBorder(5, 5, 5, 5)));

        JLabel header = new JLabel(" " + title);
        header.setOpaque(true);
        header.setBackground(HEADER);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 11));

        panel.add(header, BorderLayout.NORTH);

        return panel;
    }

    // Creates a colored button
    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setFocusPainted(false);
        button.setOpaque(true);

        return button;
    }

    // Refreshes the visual stack
    private void updateStackDisplay() {

        stackPanel.removeAll();

        for (int i = stack.getSize() - 1; i >= 0; i--) {

            int value = stack.getElement(i);

            JLabel element = new JLabel(
                    String.valueOf(value), JLabel.CENTER);

            element.setFont(
                    new Font("Arial", Font.BOLD, 12));

            element.setOpaque(true);
            element.setMaximumSize(new Dimension(100, 38));
            element.setPreferredSize(new Dimension(100, 38));
            element.setAlignmentX(Component.CENTER_ALIGNMENT);

            if (i == stack.getSize() - 1) {

                element.setBackground(
                        highlightTop ? Color.YELLOW : CYAN);

                if (highlightTop) {
                    element.setBorder(
                            new LineBorder(Color.ORANGE, 3));
                } else {
                    element.setBorder(
                            new LineBorder(CYAN, 2));
                }

            } else {
                element.setBackground(
                        new Color(143, 188, 245));

                element.setBorder(
                        new LineBorder(PANEL_COLOR, 2));
            }

            stackPanel.add(element);
            stackPanel.add(Box.createVerticalStrut(3));
        }

        if (stack.isEmpty()) {
            JLabel emptyLabel = new JLabel(
                    "Stack is Empty", JLabel.CENTER);

            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            stackPanel.add(Box.createVerticalGlue());
            stackPanel.add(emptyLabel);
            stackPanel.add(Box.createVerticalGlue());
        }

        sizeLabel.setText(
                "Maximum Capacity: " + stack.getCapacity()
                + " | Size: " + stack.getSize());

        stackPanel.revalidate();
        stackPanel.repaint();
    }
}