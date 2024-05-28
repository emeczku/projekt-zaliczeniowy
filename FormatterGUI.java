import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FormatterGUI extends JFrame {
    private JButton openButton;
    private JButton formatButton;
    private JFileChooser fileChooser;
    private File selectedFile;

    public FormatterGUI() {
        setTitle("Format Text");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        openButton = new JButton("Open File");
        formatButton = new JButton("Format");
        fileChooser = new JFileChooser();

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(FormatterGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                }
            }
        });

        formatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        StringBuilder contentBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            contentBuilder.append(line).append("\n");
                        }
                        reader.close();

                        String formattedContent = Formatter.formatContent(contentBuilder.toString());

                        File outputFile = new File("output.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                        writer.write(formattedContent);
                        writer.close();

                        JOptionPane.showMessageDialog(FormatterGUI.this, "File formatted and saved as output.txt");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(FormatterGUI.this, "Please select a file first.");
                }
            }
        });

        setLayout(new FlowLayout());
        add(openButton);
        add(formatButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormatterGUI().setVisible(true);
            }
        });
    }
}
