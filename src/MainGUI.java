import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private final JPanel mainPanel;
    private final JPanel devicePanel;
    private final JLabel statusLabel;
    private final Map<Integer, Device> devices; // Map to store devices
    private final Map<Integer, String> deviceStatus; // Map to store status of each device

    public MainGUI() {
        super("Smart Home Control");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        devices = new HashMap<>();
        deviceStatus = new HashMap<>();

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());

        // Create device panel
        devicePanel = new JPanel(new GridLayout(5, 1));
        devicePanel.setBorder(BorderFactory.createTitledBorder("Choose a device"));

        // Add buttons for each device
        addDeviceButton("Fridge", 1);
        addDeviceButton("Oven", 2);
        addDeviceButton("Alarm", 3);
        addDeviceButton("Lights", 4);
        addDeviceButton("Exit", 0);

        mainPanel.add(devicePanel, BorderLayout.WEST); // add device panel to main panel

        // Create status label
        statusLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel); // add main panel to frame

        setVisible(true);
    }

    private void addDeviceButton(String deviceName, int choice) {
        JButton button = new JButton(deviceName);
        button.addActionListener(e -> handleDeviceSelection(choice));
        button.setPreferredSize(new Dimension(150, 40)); // Set desired button size
        devicePanel.add(button);
    }

    private void configureButtonSize(JButton button) {
        Dimension buttonSize = new Dimension(200, 80);
        button.setPreferredSize(buttonSize);
    }

    private void handleDeviceSelection(int choice) {
        if (choice == 0) {
            dispose();
            System.exit(0);
        }

        mainPanel.removeAll();
        mainPanel.add(devicePanel, BorderLayout.WEST);

        JPanel deviceActionsPanel = new JPanel(new BorderLayout());

        Device currentDevice = null;
        String deviceName = "";
        switch (choice) {
            case 1:
                currentDevice = devices.getOrDefault(choice, new Fridge());
                deviceName = "Fridge";
                break;
            case 2:
                currentDevice = devices.getOrDefault(choice, new Oven());
                deviceName = "Oven";
                break;
            case 3:
                currentDevice = devices.getOrDefault(choice, new Alarm());
                deviceName = "Alarm";
                break;
            case 4:
                currentDevice = devices.getOrDefault(choice, new Lights());
                deviceName = "Lights";
                break;
            default:
                break;
        }

        if (currentDevice != null) {
            String status = deviceStatus.getOrDefault(choice, getStatus(currentDevice)); // get the status from the map if the device exists
            devices.put(choice, currentDevice);
            deviceStatus.put(choice, status); // Store or update the status
            updateStatusLabel(status);
            addDeviceActions(deviceActionsPanel, deviceName, currentDevice, choice);
        }
        mainPanel.add(deviceActionsPanel, BorderLayout.CENTER); // place deviceActionsPanel in the center
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void addDeviceActions(JPanel deviceActionsPanel, String deviceName, Device device, int choice) {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel statusLabel = new JLabel("", JLabel.CENTER);

        // Set initial status for the device
        updateStatusLabel(statusLabel, deviceStatus.get(choice));

        if (device instanceof Fridge){
            addButton("Increase temperature", buttonsPanel, e -> {
                ((Fridge) device).increaseTemperature();
                String newStatus = "Temperature: " + ((Fridge) device).getTemperature() + "ºC";
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus); // update the status in the map
            });

            addButton("Decrease temperature", buttonsPanel, e -> {
                ((Fridge) device).decreaseTemperature();
                String newStatus = "Temperature: " + ((Fridge) device).getTemperature() + "ºC";
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });
        } else if (device instanceof Oven) {
            addButton("Increase temperature", buttonsPanel, e -> {
                ((Oven) device).increaseTemperature();
                String newStatus = "Temperature: " + ((Oven) device).getTemperature() + "ºC";
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });

            addButton("Decrease temperature", buttonsPanel, e -> {
                ((Oven) device).decreaseTemperature();
                String newStatus = "Temperature: " + ((Oven) device).getTemperature() + "ºC";
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });
        } else if (device instanceof Alarm) {
            addButton("Activate alarm", buttonsPanel, e -> {
                ((Alarm) device).activateAlarm();
                String newStatus = "Alarm is " + ((Alarm) device).getActiveStatus();
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });

            addButton("Deactivate alarm", buttonsPanel, e -> {
                ((Alarm) device).deactivateAlarm();
                String newStatus = "Alarm is " + ((Alarm) device).getActiveStatus();
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });
        } else if (device instanceof Lights) {
            addButton("Toggle kitchen lights", buttonsPanel, e -> {
                ((Lights) device).toggleLights(1);
                String newStatus = ((Lights) device).getLightsStatus();
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });

            addButton("Toggle living room lights", buttonsPanel, e -> {
                ((Lights) device).toggleLights(2);
                String newStatus = ((Lights) device).getLightsStatus();
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });

            addButton("Toggle bedroom lights", buttonsPanel, e -> {
                ((Lights) device).toggleLights(3);
                String newStatus = ((Lights) device).getLightsStatus();
                updateStatusLabel(statusLabel, newStatus);
                deviceStatus.put(choice, newStatus);
            });
        }

        deviceActionsPanel.add(statusLabel, BorderLayout.SOUTH);

        JLabel actionLabel = new JLabel("Actions for " + deviceName);
        actionLabel.setFont(new Font(actionLabel.getFont().getName(), Font.PLAIN, 16));
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        actionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deviceActionsPanel.add(actionLabel, BorderLayout.NORTH);
        deviceActionsPanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    private void addButton(String text, JPanel panel, ActionListener listener){
        JButton button = new JButton(text);
        button.addActionListener(listener);
        configureButtonSize(button);
        panel.add(button);
    }

    private void updateStatusLabel (String status) {
        statusLabel.setText(status);
    }

    private void updateStatusLabel(JLabel label, String status){
        label.setText(status);
    }

    // Method to get the status of the device
    private static String getStatus(Device device) {
        if (device instanceof Fridge) {
            return "Temperature: " + ((Fridge) device).getTemperature() + "ºC";
        } else if (device instanceof Oven) {
            return "Temperature: " + ((Oven) device).getTemperature() + "ºC";
        } else if (device instanceof Alarm) {
            return "Alarm is " + ((Alarm) device).getActiveStatus();
        } else if (device instanceof Lights){
            return ((Lights) device).getLightsStatus();
        } else {
            return "Status not available";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}