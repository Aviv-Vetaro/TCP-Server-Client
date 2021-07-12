import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private ServerAdapter executor;
    JLabel currencyFromTitle;
    JComboBox optionsFromCurrencySelectionMenu;
    JLabel currencyToTitle;
    JComboBox optionsToCurrencySelectionMenu;
    JLabel amountTitle;
    JTextField amountInput;
    JButton submitRequestButton;
    JLabel resultsOutput;
    public MainPanel(ServerAdapter executor)
    {
        this.executor = executor;
        //In an ideal world the options would be fetched from the server
        String[] currencyOptions = new String[]{"$", "£", "¥", "€", "₪"};
         currencyFromTitle = new JLabel("From:");
         optionsFromCurrencySelectionMenu = new JComboBox(currencyOptions);
         currencyToTitle = new JLabel("To:");
         optionsToCurrencySelectionMenu = new JComboBox(currencyOptions);
         amountTitle = new JLabel("Amount:");
         amountInput = new JTextField("1");
         submitRequestButton = new JButton("get rate");
         resultsOutput = new JLabel();
        
        submitRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request serverForexRequest = new Request(optionsFromCurrencySelectionMenu.getSelectedItem().toString(),
                        optionsToCurrencySelectionMenu.getSelectedItem().toString(),
                        Double.parseDouble(amountInput.getText()));
                try {
                    //onclick contract request and send it ot the server
                   double result = executor.sendServerForexRequest(serverForexRequest);
                   //display request result
                    resultsOutput.setText(result + "");
                }
                catch (Exception ex)
                {
                    resultsOutput.setText("Could not fetch result from server: (" + ex.getMessage() + ")");
                }
            }
        });



        this.add(currencyFromTitle);
        this.add(optionsFromCurrencySelectionMenu);
        this.add(currencyToTitle);
        this.add(optionsToCurrencySelectionMenu);
        this.add(amountTitle);
        this.add(amountInput);
        this.add(submitRequestButton);
        this.add(resultsOutput);

        revalidate();
        repaint();
    }

    @Override
    public void setVisible(boolean aFlag) {

        currencyFromTitle.setVisible(aFlag);
        optionsFromCurrencySelectionMenu.setVisible(aFlag);
        optionsToCurrencySelectionMenu.setVisible(aFlag);
        currencyToTitle.setVisible(aFlag);
        amountTitle.setVisible(aFlag);
        amountInput.setVisible(aFlag);
        amountTitle.setVisible(aFlag);
        submitRequestButton.setVisible(aFlag);
        resultsOutput.setVisible(aFlag);
        super.setVisible(aFlag);
        revalidate();
        repaint();
    }
}
