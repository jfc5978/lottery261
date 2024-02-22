package d3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class View extends JFrame
{
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 500;

    private static final int AREA_ROWS = 20;
    private static final int AREA_COLUMNS = 60;

    private static final String INPUT_SPECIFIER = "\nEnter just six different "
            + "integers from 1 through 60, separated by one or more spaces";
    
    private static final String NUM_DRAWINGS = "\nEnter number of drawings "
            + "(an integer from 1 through 100000)";

    private JLabel labelInput;
    private JLabel labelDrawing;
    private JTextField textFieldInput;
    private JTextField textFieldDrawing;
    
    private JButton button;
    private final JTextArea resultArea;
    private final Controller cntl;

    public View(Controller controller) 
    {
        super("Lottery");
        cntl = controller;
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setEditable(false);
        resultArea.setText("");

        createTextFieldSpecifier();
        createTextFieldDrawings();
        createButton();
        createPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null); // centers frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // quits when frame closed
        //https://stackoverflow.com/questions/13731710/allowing-the-enter-key-to-press-the-submit-button-as-opposed-to-only-using-mo
        getRootPane().setDefaultButton(button);
    }

    private void createTextFieldSpecifier() 
    {
        labelInput = new JLabel(INPUT_SPECIFIER);
        final int FIELD_WIDTH = 10;
        textFieldInput = new JTextField(FIELD_WIDTH);
    }
    
    private void createTextFieldDrawings() 
    {
        labelDrawing = new JLabel(NUM_DRAWINGS);
        final int FIELD_WIDTH = 10;
        textFieldDrawing = new JTextField(FIELD_WIDTH);
    }

    private void createButton() 
    {
        button = new JButton("Draw Lottery");
        button.addActionListener(event -> showResults(textFieldInput.getText(), textFieldDrawing.getText()));
    }

    private void createPanel() 
    {
        JPanel panel = new JPanel();
        panel.add(labelInput);
        panel.add(textFieldInput);
        panel.add(labelDrawing);
        panel.add(textFieldDrawing);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        panel.add(button);
        add(panel);
    }

    public void displaySelf() 
    {
        this.setVisible(true);
    }

    private void showResults(String input, String numDraw) 
    {
        if (cntl.isValid(input, numDraw)) 
        {
            resultArea.setText("");
            resultArea.append(cntl.getLottoResults(input, numDraw));
        } 
        else 
        {
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cntl.getErrorMessages());
            // http://www.java2s.com/Tutorial/Java/0240__Swing/SetthefocusonaparticularJTextField.htm
            textFieldInput.requestFocus();
        }
    }
}
