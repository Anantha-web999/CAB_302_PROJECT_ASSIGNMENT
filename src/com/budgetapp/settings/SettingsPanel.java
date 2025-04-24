import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.awt.geom.RoundRectangle2D;
import java.util.prefs.Preferences;

/**
 * Settings panel for the SpentWise budgeting application.
 * This panel provides a UI for users to configure account settings,
 * application preferences, notifications, and access help and about information.
 *
 * @author SpentWise Development Team
 * @version 1.2.0
 */
public class SettingsPanel extends JPanel {

    // Color scheme
    private static final Color LIGHT_BLUE = new Color(235, 245, 251);
    private static final Color ACCENT_BLUE = new Color(100, 181, 246);
    private static final Color DARK_BLUE = new Color(30, 136, 229);
    private static final Color BLACK = new Color(33, 33, 33);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color TEXT_COLOR = new Color(33, 33, 33);

    // Font definitions
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font SUBHEADER_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    // Preferences storage
    private Preferences prefs;

    // Main content panel that will hold different settings panels
    private JPanel contentPanel;
    private CardLayout cardLayout;

    // Sidebar buttons
    private JButton accountBtn;
    private JButton preferencesBtn;
    private JButton notificationsBtn;
    private JButton helpBtn;
    private JButton aboutBtn;

    // Font sizes for preference
    private static final String[] FONT_SIZES = {"Small", "Medium", "Large"};

    // Date formats for preference
    private static final String[] DATE_FORMATS = {"MM/DD/YYYY", "DD/MM/YYYY", "YYYY-MM-DD"};

    // Time formats for preference
    private static final String[] TIME_FORMATS = {"12-hour (AM/PM)", "24-hour"};

    // Languages for preference
    private static final String[] LANGUAGES = {"English", "Spanish", "French", "German", "Chinese", "Japanese"};

    /**
     * Constructs a new SettingsPanel with default settings.
     */
    public SettingsPanel() {
        prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        initializeUI();
    }

    /**
     * Initializes the UI components and layout.
     */
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(LIGHT_BLUE);

        // Create sidebar and content panels
        JPanel sidebarPanel = createSidebar();
        createContentPanel();

        // Add panels to main layout
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the sidebar panel with navigation buttons.
     *
     * @return JPanel containing the sidebar elements
     */
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBorder(new EmptyBorder(0, 0, 0, 1));
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.setBackground(BLACK);

        // Add logo panel at the top
        sidebar.add(createLogoPanel(), BorderLayout.NORTH);

        // Navigation buttons
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(BLACK);

        // Create icons
        ImageIcon accountIcon = createIcon("user", 16);
        ImageIcon preferencesIcon = createIcon("settings", 16);
        ImageIcon notificationsIcon = createIcon("bell", 16);
        ImageIcon helpIcon = createIcon("help-circle", 16);
        ImageIcon aboutIcon = createIcon("info", 16);

        accountBtn = createNavButton("Account", "account", accountIcon);
        preferencesBtn = createNavButton("App Preferences", "preferences", preferencesIcon);
        notificationsBtn = createNavButton("Notifications", "notifications", notificationsIcon);
        helpBtn = createNavButton("Help & Support", "help", helpIcon);
        aboutBtn = createNavButton("About Us", "about", aboutIcon);

        // Add navigation buttons to panel
        navPanel.add(accountBtn);
        navPanel.add(preferencesBtn);
        navPanel.add(notificationsBtn);
        navPanel.add(helpBtn);
        navPanel.add(aboutBtn);

        // Add filler to push everything to the top
        navPanel.add(Box.createVerticalGlue());

        sidebar.add(navPanel, BorderLayout.CENTER);

        // Version info at bottom
        JLabel versionLabel = new JLabel("v1.2.0");
        versionLabel.setForeground(Color.LIGHT_GRAY);
        versionLabel.setHorizontalAlignment(JLabel.CENTER);
        versionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        sidebar.add(versionLabel, BorderLayout.SOUTH);

        return sidebar;
    }

    /**
     * Creates a panel containing the SpentWise logo.
     *
     * @return JPanel containing the logo
     */
    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(BLACK);
        logoPanel.setBorder(new EmptyBorder(15, 10, 10, 10));

        // Try to load the logo image - if it fails, we'll use text instead
        JLabel logoLabel = new JLabel("SpentWise");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        try {
            // Load the SpentWise logo
            ImageIcon logoIcon = new ImageIcon("spentwise_logo.png");

            // Get the original image
            Image img = logoIcon.getImage();

            // Create a buffered image with transparency
            BufferedImage roundedImage = new BufferedImage(110, 110, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = roundedImage.createGraphics();

            // Set rendering hints for better quality
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Create a rounded rectangle shape
            g2.setClip(new RoundRectangle2D.Float(0, 0, 110, 110, 20, 20));

            // Draw the image
            g2.drawImage(img, 0, 0, 110, 110, null);
            g2.dispose();

            // Create new icon from rounded image
            logoIcon = new ImageIcon(roundedImage);

            logoLabel.setIcon(logoIcon);
            logoLabel.setText("");  // Remove text if we have an image
        } catch (Exception e) {
            System.out.println("Could not load logo image: " + e.getMessage());
            // We'll just use the text logo already set up
        }

        logoPanel.add(logoLabel, BorderLayout.CENTER);
        return logoPanel;
    }

    /**
     * Creates an icon with a specific color based on the icon name.
     * In a production app, this would load icons from resources.
     *
     * @param name The name of the icon to create
     * @param size The size of the icon in pixels
     * @return ImageIcon with the specified properties
     */
    private ImageIcon createIcon(String name, int size) {




        return new ImageIcon();
    }

    /**
     * Creates a styled navigation button for the sidebar.
     *
     * @param text The button text
     * @param cardName The name of the card to show when clicked
     * @param icon The icon to display on the button
     * @return Configured JButton
     */
    private JButton createNavButton(String text, String cardName, ImageIcon icon) {
        JButton button = new JButton(text);
        button.setFont(REGULAR_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(BLACK);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIcon(icon);
        button.setIconTextGap(10);
        button.setMaximumSize(new Dimension(200, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add left padding
        button.setBorder(new EmptyBorder(10, 20, 10, 10));

        // Add action listener
        button.addActionListener(e -> {
            highlightButton(button);
            cardLayout.show(contentPanel, cardName);
        });

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!button.isSelected()) {
                    button.setBackground(new Color(60, 60, 60));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.isSelected()) {
                    button.setBackground(BLACK);
                }
            }
        });

        return button;
    }

    /**
     * Highlights the selected navigation button and resets others.
     *
     * @param selectedButton The button to highlight
     */
    private void highlightButton(JButton selectedButton) {
        // Reset all buttons
        accountBtn.setBackground(BLACK);
        accountBtn.setForeground(Color.WHITE);
        preferencesBtn.setBackground(BLACK);
        preferencesBtn.setForeground(Color.WHITE);
        notificationsBtn.setBackground(BLACK);
        notificationsBtn.setForeground(Color.WHITE);
        helpBtn.setBackground(BLACK);
        helpBtn.setForeground(Color.WHITE);
        aboutBtn.setBackground(BLACK);
        aboutBtn.setForeground(Color.WHITE);

        // Highlight selected button
        selectedButton.setBackground(ACCENT_BLUE);
        selectedButton.setForeground(Color.WHITE);
    }

    /**
     * Creates the main content panel with card layout for different sections.
     */
    private void createContentPanel() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(LIGHT_BLUE);

        // Create each settings panel
        JPanel accountPanel = createAccountPanel();
        JPanel preferencesPanel = createPreferencesPanel();
        JPanel notificationsPanel = createNotificationsPanel();
        JPanel helpPanel = createHelpPanel();
        JPanel aboutPanel = createAboutPanel();

        // Add panels to card layout
        contentPanel.add(accountPanel, "account");
        contentPanel.add(preferencesPanel, "preferences");
        contentPanel.add(notificationsPanel, "notifications");
        contentPanel.add(helpPanel, "help");
        contentPanel.add(aboutPanel, "about");

        // Show account panel by default
        cardLayout.show(contentPanel, "account");
        highlightButton(accountBtn);
    }

    /**
     * Creates the account settings panel with user information fields.
     *
     * @return Configured account panel
     */
    private JPanel createAccountPanel() {
        JPanel panel = createPanelWithHeader("Account Settings", "Manage your personal information and preferences");

        // Create scroll pane for the form
        JPanel formPanelContainer = new JPanel(new BorderLayout());
        formPanelContainer.setBackground(LIGHT_BLUE);

        // Create form layout for input fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(LIGHT_BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        // Personal Information Section
        JLabel sectionLabel = new JLabel("Personal Information");
        sectionLabel.setFont(SUBHEADER_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(sectionLabel, gbc);

        // Add input fields for personal information
        String[] labels = {
                "Full Name:",
                "Date of Birth:",
                "Username:",
                "Email Address:",
                "Phone Number:",
                "Address:"
        };

        JComponent[] fields = new JComponent[labels.length];
        fields[0] = new JTextField(20); // Full Name

        // Date of Birth with date picker
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        dobPanel.setBackground(LIGHT_BLUE);

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        JComboBox<String> dayCombo = new JComboBox<>(days);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        JComboBox<String> monthCombo = new JComboBox<>(months);

        String[] years = new String[100];
        int currentYear = LocalDate.now().getYear();
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        JComboBox<String> yearCombo = new JComboBox<>(years);

        dobPanel.add(dayCombo);
        dobPanel.add(new JLabel(" / "));
        dobPanel.add(monthCombo);
        dobPanel.add(new JLabel(" / "));
        dobPanel.add(yearCombo);
        fields[1] = dobPanel;

        fields[2] = new JTextField(20); // Username
        fields[3] = new JTextField(20); // Email
        fields[4] = new JTextField(20); // Phone

        // Address with multiple lines
        JTextArea addressArea = new JTextArea(3, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        fields[5] = addressScrollPane;

        int gridy = 1;
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.gridwidth = 1;
            gbc.weightx = 0.3;
            JLabel fieldLabel = new JLabel(labels[i]);
            fieldLabel.setFont(REGULAR_FONT);
            formPanel.add(fieldLabel, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            if (fields[i] instanceof JTextField) {
                ((JTextField) fields[i]).setFont(REGULAR_FONT);
            }
            formPanel.add(fields[i], gbc);

            gridy++;
        }

        // Account created date (read-only)
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        JLabel createdLabel = new JLabel("Account Created:");
        createdLabel.setFont(REGULAR_FONT);
        formPanel.add(createdLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JLabel createdDateLabel = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        createdDateLabel.setFont(REGULAR_FONT);
        formPanel.add(createdDateLabel, gbc);

        gridy++;

        // Currency settings
        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 2;
        JLabel currencyLabel = new JLabel("Currency Settings");
        currencyLabel.setFont(SUBHEADER_FONT);
        formPanel.add(currencyLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        JLabel currencyFieldLabel = new JLabel("Default Currency:");
        currencyFieldLabel.setFont(REGULAR_FONT);
        formPanel.add(currencyFieldLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        String[] currencies = {"USD ($)", "EUR (€)", "GBP (£)", "JPY (¥)", "CAD ($)", "AUD ($)", "INR (₹)"};
        JComboBox<String> currencyCombo = new JComboBox<>(currencies);
        currencyCombo.setFont(REGULAR_FONT);
        formPanel.add(currencyCombo, gbc);

        gridy++;

        // Security Settings Section
        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 2;
        JLabel securityLabel = new JLabel("Security Settings");
        securityLabel.setFont(SUBHEADER_FONT);
        securityLabel.setBorder(new EmptyBorder(15, 0, 5, 0));
        formPanel.add(securityLabel, gbc);

        // Change Password Button
        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 2;
        JButton changePasswordBtn = new JButton("Change Password");
        changePasswordBtn.setFont(REGULAR_FONT);
        changePasswordBtn.setBackground(LIGHT_GRAY);
        changePasswordBtn.setForeground(BLACK);
        formPanel.add(changePasswordBtn, gbc);

        // Two-Factor Authentication
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        JLabel twoFactorLabel = new JLabel("Two-Factor Authentication:");
        twoFactorLabel.setFont(REGULAR_FONT);
        formPanel.add(twoFactorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JPanel twoFactorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        twoFactorPanel.setBackground(LIGHT_BLUE);

        JToggleButton twoFactorToggle = new JToggleButton();
        twoFactorToggle.setPreferredSize(new Dimension(60, 30));
        twoFactorToggle.setBackground(LIGHT_GRAY);
        twoFactorToggle.setForeground(BLACK);
        twoFactorToggle.addActionListener(e -> {
            if (twoFactorToggle.isSelected()) {
                twoFactorToggle.setBackground(ACCENT_BLUE);
            } else {
                twoFactorToggle.setBackground(LIGHT_GRAY);
            }
        });

        twoFactorPanel.add(twoFactorToggle);
        formPanel.add(twoFactorPanel, gbc);

        // Add buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(LIGHT_BLUE);

        JButton cancelButton = createSecondaryButton("Cancel");
        JButton saveButton = createPrimaryButton("Save Changes");

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Add components to container
        formPanelContainer.add(formPanel, BorderLayout.NORTH);

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(formPanelContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Add components to main panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the application preferences panel.
     *
     * @return Configured preferences panel
     */
    private JPanel createPreferencesPanel() {
        JPanel panel = createPanelWithHeader("App Preferences", "Customize the application to suit your needs");

        JPanel prefsPanel = new JPanel(new GridBagLayout());
        prefsPanel.setBackground(LIGHT_BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        // Appearance section
        JLabel appearanceLabel = new JLabel("Appearance");
        appearanceLabel.setFont(SUBHEADER_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        prefsPanel.add(appearanceLabel, gbc);

        // Dark mode toggle
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        JLabel darkModeLabel = new JLabel("Dark Mode:");
        darkModeLabel.setFont(REGULAR_FONT);
        prefsPanel.add(darkModeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.6;
        JPanel darkModePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        darkModePanel.setBackground(LIGHT_BLUE);

        JToggleButton darkModeToggle = new JToggleButton();
        darkModeToggle.setPreferredSize(new Dimension(60, 30));
        darkModeToggle.setBackground(LIGHT_GRAY);
        darkModeToggle.addActionListener(e -> {
            if (darkModeToggle.isSelected()) {
                darkModeToggle.setBackground(ACCENT_BLUE);
            } else {
                darkModeToggle.setBackground(LIGHT_GRAY);
            }
        });

        darkModePanel.add(darkModeToggle);
        prefsPanel.add(darkModePanel, gbc);

        // Font Size
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel fontSizeLabel = new JLabel("Font Size:");
        fontSizeLabel.setFont(REGULAR_FONT);
        prefsPanel.add(fontSizeLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> fontSizeCombo = new JComboBox<>(FONT_SIZES);
        fontSizeCombo.setFont(REGULAR_FONT);
        fontSizeCombo.setSelectedItem("Medium");
        prefsPanel.add(fontSizeCombo, gbc);

        // Language selection
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel langLabel = new JLabel("Language:");
        langLabel.setFont(REGULAR_FONT);
        prefsPanel.add(langLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> langCombo = new JComboBox<>(LANGUAGES);
        langCombo.setFont(REGULAR_FONT);
        prefsPanel.add(langCombo, gbc);

        // Date format
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel dateLabel = new JLabel("Date Format:");
        dateLabel.setFont(REGULAR_FONT);
        prefsPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> dateCombo = new JComboBox<>(DATE_FORMATS);
        dateCombo.setFont(REGULAR_FONT);
        prefsPanel.add(dateCombo, gbc);

        // Time format
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel timeLabel = new JLabel("Time Format:");
        timeLabel.setFont(REGULAR_FONT);
        prefsPanel.add(timeLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> timeCombo = new JComboBox<>(TIME_FORMATS);
        timeCombo.setFont(REGULAR_FONT);
        prefsPanel.add(timeCombo, gbc);

        // Startup Behavior section
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 8, 10);
        JLabel startupLabel = new JLabel("Startup Behavior");
        startupLabel.setFont(SUBHEADER_FONT);
        prefsPanel.add(startupLabel, gbc);

        // Checkboxes
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 10, 4, 10);
        JCheckBox startupCheckbox = new JCheckBox("Start application when system boots");
        startupCheckbox.setFont(REGULAR_FONT);
        startupCheckbox.setBackground(LIGHT_BLUE);
        prefsPanel.add(startupCheckbox, gbc);

        gbc.gridy = 8;
        JCheckBox minimizedCheckbox = new JCheckBox("Start minimized in system tray");
        minimizedCheckbox.setFont(REGULAR_FONT);
        minimizedCheckbox.setBackground(LIGHT_BLUE);
        prefsPanel.add(minimizedCheckbox, gbc);

        gbc.gridy = 9;
        JCheckBox backupCheckbox = new JCheckBox("Auto-backup data weekly");
        backupCheckbox.setFont(REGULAR_FONT);
        backupCheckbox.setBackground(LIGHT_BLUE);
        backupCheckbox.setSelected(true);
        prefsPanel.add(backupCheckbox, gbc);

        // Budget View Preferences section
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 8, 10);
        JLabel budgetViewLabel = new JLabel("Budget View Preferences");
        budgetViewLabel.setFont(SUBHEADER_FONT);
        prefsPanel.add(budgetViewLabel, gbc);

        // Default view
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 10, 8, 10);
        JLabel defaultViewLabel = new JLabel("Default View:");
        defaultViewLabel.setFont(REGULAR_FONT);
        prefsPanel.add(defaultViewLabel, gbc);

        gbc.gridx = 1;
        String[] views = {"Monthly", "Weekly", "Yearly", "Custom"};
        JComboBox<String> viewCombo = new JComboBox<>(views);
        viewCombo.setFont(REGULAR_FONT);
        prefsPanel.add(viewCombo, gbc);

        // Add buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(LIGHT_BLUE);

        JButton resetButton = createSecondaryButton("Reset to Defaults");
        JButton saveButton = createPrimaryButton("Save Preferences");

        buttonPanel.add(resetButton);
        buttonPanel.add(saveButton);

        // Create a scroll pane
        JScrollPane scrollPane = new JScrollPane(prefsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Add components to main panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the notifications settings panel.
     *
     * @return Configured notifications panel
     */

    private JPanel createNotificationsPanel() {
        JPanel panel = createPanelWithHeader("Notification Settings", "Control how and when you receive alerts and reminders");

        JPanel notifPanelContainer = new JPanel(new BorderLayout());
        notifPanelContainer.setBackground(LIGHT_BLUE);

        JPanel notifPanel = new JPanel(new GridBagLayout());
        notifPanel.setBackground(LIGHT_BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        // Notification types section
        JLabel typesLabel = new JLabel("Notification Types");
        typesLabel.setFont(SUBHEADER_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        notifPanel.add(typesLabel, gbc);

        // Create notification toggles with descriptions
        String[][] notificationTypes = {
                {"Bill Due Reminder", "Get reminded when your bills are coming due"},
                {"Subscription Tracking", "Track your recurring subscriptions and payments"},
                {"Weekly Summaries", "Receive summaries of your spending habits each week"},
                {"Overspending Nudges", "Be alerted when you're nearing your budget limits"},
                {"Motivational Boosts", "Get positive reinforcement when you meet your goals"}
        };

        int gridy = 1;
        for (String[] type : notificationTypes) {
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.gridwidth = 1;
            gbc.weightx = 0.1;

            JToggleButton toggle = new JToggleButton();
            toggle.setPreferredSize(new Dimension(50, 25));
            toggle.setBackground(ACCENT_BLUE);
            toggle.setSelected(true);
            toggle.addActionListener(e -> {
                if (toggle.isSelected()) {
                    toggle.setBackground(ACCENT_BLUE);
                } else {
                    toggle.setBackground(LIGHT_GRAY);
                }
            });

            notifPanel.add(toggle, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.3;
            JLabel titleLabel = new JLabel(type[0]);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            notifPanel.add(titleLabel, gbc);

            gbc.gridx = 2;
            gbc.weightx = 0.6;
            JLabel descLabel = new JLabel(type[1]);
            descLabel.setFont(REGULAR_FONT);
            descLabel.setForeground(new Color(100, 100, 100));
            notifPanel.add(descLabel, gbc);

            gridy++;
        }

        // Notification frequency section
        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 10, 8, 10);
        JLabel frequencyLabel = new JLabel("Notification Frequency");
        frequencyLabel.setFont(SUBHEADER_FONT);
        notifPanel.add(frequencyLabel, gbc);

        gbc.insets = new Insets(8, 10, 8, 10);

        // Create frequency options with radio buttons
        JPanel frequencyPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        frequencyPanel.setBackground(LIGHT_BLUE);

        ButtonGroup frequencyGroup = new ButtonGroup();

        JRadioButton dailyRadio = new JRadioButton("Daily - Receive notifications once per day");
        dailyRadio.setFont(REGULAR_FONT);
        dailyRadio.setBackground(LIGHT_BLUE);

        JRadioButton weeklyRadio = new JRadioButton("Weekly - Receive a weekly summary");
        weeklyRadio.setFont(REGULAR_FONT);
        weeklyRadio.setBackground(LIGHT_BLUE);
        weeklyRadio.setSelected(true);

        JRadioButton monthlyRadio = new JRadioButton("Monthly - Receive a monthly report");
        monthlyRadio.setFont(REGULAR_FONT);
        monthlyRadio.setBackground(LIGHT_BLUE);

        frequencyGroup.add(dailyRadio);
        frequencyGroup.add(weeklyRadio);
        frequencyGroup.add(monthlyRadio);

        frequencyPanel.add(dailyRadio);
        frequencyPanel.add(weeklyRadio);
        frequencyPanel.add(monthlyRadio);

        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 3;
        notifPanel.add(frequencyPanel, gbc);

        // Time of day section
        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.insets = new Insets(20, 10, 8, 10);
        JLabel timeOfDayLabel = new JLabel("Notification Time");
        timeOfDayLabel.setFont(SUBHEADER_FONT);
        notifPanel.add(timeOfDayLabel, gbc);

        gbc.insets = new Insets(8, 10, 8, 10);

        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        JLabel deliveryTimeLabel = new JLabel("Delivery Time:");
        deliveryTimeLabel.setFont(REGULAR_FONT);
        notifPanel.add(deliveryTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JPanel timeSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        timeSelectionPanel.setBackground(LIGHT_BLUE);

        String[] hours = new String[12];
        for (int i = 0; i < 12; i++) {
            hours[i] = String.format("%d", i + 1);
        }

        String[] minutes = new String[4];
        for (int i = 0; i < 4; i++) {
            minutes[i] = String.format("%02d", i * 15);
        }

        JComboBox<String> hourCombo = new JComboBox<>(hours);
        JComboBox<String> minuteCombo = new JComboBox<>(minutes);
        JComboBox<String> ampmCombo = new JComboBox<>(new String[]{"AM", "PM"});

        timeSelectionPanel.add(hourCombo);
        timeSelectionPanel.add(new JLabel(":"));
        timeSelectionPanel.add(minuteCombo);
        timeSelectionPanel.add(ampmCombo);

        notifPanel.add(timeSelectionPanel, gbc);

        gridy++;

        // Notification methods section
        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 10, 8, 10);
        JLabel methodsLabel = new JLabel("Notification Methods");
        methodsLabel.setFont(SUBHEADER_FONT);
        notifPanel.add(methodsLabel, gbc);

        gbc.insets = new Insets(8, 10, 8, 10);

        // Email notifications
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        JCheckBox emailCheckbox = new JCheckBox();
        emailCheckbox.setBackground(LIGHT_BLUE);
        emailCheckbox.setSelected(true);
        notifPanel.add(emailCheckbox, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9;
        JLabel emailLabel = new JLabel("Email notifications");
        emailLabel.setFont(REGULAR_FONT);
        notifPanel.add(emailLabel, gbc);

        gridy++;

        // Desktop notifications
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        JCheckBox desktopCheckbox = new JCheckBox();
        desktopCheckbox.setBackground(LIGHT_BLUE);
        desktopCheckbox.setSelected(true);
        notifPanel.add(desktopCheckbox, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9;
        JLabel desktopLabel = new JLabel("Desktop notifications");
        desktopLabel.setFont(REGULAR_FONT);
        notifPanel.add(desktopLabel, gbc);

        gridy++;

        // Push notifications
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        JCheckBox pushCheckbox = new JCheckBox();
        pushCheckbox.setBackground(LIGHT_BLUE);
        pushCheckbox.setSelected(false);
        notifPanel.add(pushCheckbox, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9;
        JLabel pushLabel = new JLabel("Mobile push notifications (requires mobile app)");
        pushLabel.setFont(REGULAR_FONT);
        notifPanel.add(pushLabel, gbc);

        gridy++;

        // Quiet hours section
        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 10, 8, 10);
        JLabel quietLabel = new JLabel("Quiet Hours");
        quietLabel.setFont(SUBHEADER_FONT);
        notifPanel.add(quietLabel, gbc);

        gbc.insets = new Insets(8, 10, 8, 10);

        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        JCheckBox quietHoursCheckbox = new JCheckBox();
        quietHoursCheckbox.setBackground(LIGHT_BLUE);
        notifPanel.add(quietHoursCheckbox, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9;
        JLabel quietHoursLabel = new JLabel("Enable quiet hours");
        quietHoursLabel.setFont(REGULAR_FONT);
        notifPanel.add(quietHoursLabel, gbc);

        gridy++;

        // Time selection
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePanel.setBackground(LIGHT_BLUE);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(REGULAR_FONT);

        String[] fullHours = new String[24];
        for (int i = 0; i < 24; i++) {
            fullHours[i] = String.format("%02d:00", i);
        }

        JComboBox<String> fromCombo = new JComboBox<>(fullHours);
        fromCombo.setSelectedItem("22:00");

        JLabel toLabel = new JLabel("   To:");
        toLabel.setFont(REGULAR_FONT);

        JComboBox<String> toCombo = new JComboBox<>(fullHours);
        toCombo.setSelectedItem("07:00");

        timePanel.add(fromLabel);
        timePanel.add(fromCombo);
        timePanel.add(toLabel);
        timePanel.add(toCombo);

        gbc.gridx = 0;
        gbc.gridy = gridy++;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 30, 8, 10);
        notifPanel.add(timePanel, gbc);

        // Add panel to container
        notifPanelContainer.add(notifPanel, BorderLayout.NORTH);

        // Add buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(LIGHT_BLUE);

        JButton cancelButton = createSecondaryButton("Cancel");
        JButton saveButton = createPrimaryButton("Save Notifications");

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(notifPanelContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Add components to main panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the help and support panel with FAQs and contact form.
     *
     * @return Configured help panel
     */
    private JPanel createHelpPanel() {
        JPanel panel = createPanelWithHeader("Help & Support", "Get assistance and answers to your questions");

        JPanel helpContent = new JPanel();
        helpContent.setLayout(new BoxLayout(helpContent, BoxLayout.Y_AXIS));
        helpContent.setBackground(LIGHT_BLUE);
        helpContent.setBorder(new EmptyBorder(0, 20, 20, 20));

        // FAQ Section
        JLabel faqLabel = new JLabel("Frequently Asked Questions");
        faqLabel.setFont(SUBHEADER_FONT);
        faqLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        faqLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        helpContent.add(faqLabel);

        // FAQ Items
        String[][] faqItems = {
                {"How do I create a new budget category?",
                        "To create a new budget category, navigate to the 'Budgets' tab and click the '+' button. " +
                                "Enter a name for your category, set a monthly limit, and choose a color. You can also add " +
                                "subcategories if needed. Click 'Save' when done."},
                {"How can I export my financial data?",
                        "To export your data, go to 'Settings > Account' and scroll down to find the 'Export Data' " +
                                "button. You can choose between CSV, PDF, or Excel formats. Your data will be securely " +
                                "packaged and downloaded to your computer."},
                {"How do I set up automatic bill payments?",
                        "SpentWise doesn't directly process payments but can remind you when bills are due. " +
                                "Go to 'Bills > Add Bill' and enter the details including payment amount, due date, and " +
                                "recurrence. Enable notifications to get reminders before the due date."},
                {"Can I sync data across multiple devices?",
                        "Yes! SpentWise automatically syncs your data across all your devices. Simply log in with " +
                                "the same account on each device, and your budgets, expenses, and settings will be " +
                                "synchronized in real-time."},
                {"How do I recover my password?",
                        "If you've forgotten your password, click on the 'Forgot Password' link on the login screen. " +
                                "Enter your email address, and we'll send you a password reset link. For security reasons, " +
                                "this link expires after 24 hours."},
                {"Is my financial data secure?",
                        "Absolutely. SpentWise uses bank-level encryption to protect your data. We never store " +
                                "your bank account passwords and use secure connections for all data transfers. Your " +
                                "privacy and security are our top priorities."}
        };

        for (String[] item : faqItems) {
            JPanel faqItem = createCollapsiblePanel(item[0], item[1]);
            helpContent.add(faqItem);
            helpContent.add(Box.createVerticalStrut(5));
        }

        // Tutorials Section
        JLabel tutorialsLabel = new JLabel("Video Tutorials");
        tutorialsLabel.setFont(SUBHEADER_FONT);
        tutorialsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tutorialsLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        helpContent.add(tutorialsLabel);

        // Tutorial links
        String[] tutorials = {
                "Getting Started with SpentWise",
                "Creating Your First Budget",
                "Tracking Expenses Efficiently",
                "Setting and Achieving Financial Goals",
                "Advanced Reporting and Analysis"
        };

        for (String tutorial : tutorials) {
            JButton tutorialBtn = new JButton(tutorial);
            tutorialBtn.setFont(REGULAR_FONT);
            tutorialBtn.setBackground(LIGHT_GRAY);
            tutorialBtn.setForeground(BLACK);
            tutorialBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            tutorialBtn.setBorderPainted(false);
            tutorialBtn.setFocusPainted(false);
            tutorialBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            tutorialBtn.setMaximumSize(new Dimension(500, 40));
            tutorialBtn.setHorizontalAlignment(SwingConstants.LEFT);

            // Add icon
            ImageIcon playIcon = createIcon("play", 16);
            tutorialBtn.setIcon(playIcon);
            tutorialBtn.setIconTextGap(10);

            helpContent.add(tutorialBtn);
            helpContent.add(Box.createVerticalStrut(5));
        }

        // Support section
        JLabel supportLabel = new JLabel("Contact Support");
        supportLabel.setFont(SUBHEADER_FONT);
        supportLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        supportLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        helpContent.add(supportLabel);

        // Support description
        JLabel supportDesc = new JLabel("Having trouble? Send us a message and we'll get back to you within 24 hours.");
        supportDesc.setFont(REGULAR_FONT);
        supportDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        supportDesc.setBorder(new EmptyBorder(0, 0, 10, 0));
        helpContent.add(supportDesc);

        // Support form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(LIGHT_BLUE);
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(600, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        JLabel issueTypeLabel = new JLabel("Issue Type:");
        issueTypeLabel.setFont(REGULAR_FONT);
        formPanel.add(issueTypeLabel, gbc);

        gbc.gridy = 1;
        String[] issueTypes = {"Technical Problem", "Account Issue", "Billing Question", "Feature Request", "Other"};
        JComboBox<String> issueTypeCombo = new JComboBox<>(issueTypes);
        issueTypeCombo.setFont(REGULAR_FONT);
        formPanel.add(issueTypeCombo, gbc);

        gbc.gridy = 2;
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(REGULAR_FONT);
        formPanel.add(subjectLabel, gbc);

        gbc.gridy = 3;
        JTextField subjectField = new JTextField(20);
        subjectField.setFont(REGULAR_FONT);
        formPanel.add(subjectField, gbc);

        gbc.gridy = 4;
        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setFont(REGULAR_FONT);
        formPanel.add(messageLabel, gbc);

        gbc.gridy = 5;
        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setFont(REGULAR_FONT);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        formPanel.add(scrollPane, gbc);

        gbc.gridy = 6;
        JPanel attachmentPanel = new JPanel(new BorderLayout());
        attachmentPanel.setBackground(LIGHT_BLUE);
        JButton attachButton = new JButton("Attach Screenshot");
        attachButton.setFont(REGULAR_FONT);
        attachButton.setBackground(LIGHT_GRAY);
        attachButton.setForeground(BLACK);
        attachmentPanel.add(attachButton, BorderLayout.WEST);
        formPanel.add(attachmentPanel, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(15, 0, 5, 0);
        JButton submitButton = createPrimaryButton("Submit Request");
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(submitButton, gbc);

        helpContent.add(formPanel);

        // Add to main panel with scroll capability
        JScrollPane scrollableContent = new JScrollPane(helpContent);
        scrollableContent.setBorder(null);
        scrollableContent.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollableContent, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the about panel with information about SpentWise.
     *
     * @return Configured about panel
     */
    private JPanel createAboutPanel() {
        JPanel panel = createPanelWithHeader("About Us", "Learn more about SpentWise");

        JPanel aboutContent = new JPanel();
        aboutContent.setLayout(new BoxLayout(aboutContent, BoxLayout.Y_AXIS));
        aboutContent.setBackground(LIGHT_BLUE);
        aboutContent.setBorder(new EmptyBorder(20, 30, 20, 30));

        // App logo
        JLabel logoLabel = new JLabel(); // In a real app, this would be an ImageIcon
        logoLabel.setText("SPENTWISE");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoLabel.setForeground(DARK_BLUE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutContent.add(logoLabel);

        // App version
        JLabel versionLabel = new JLabel("Version 1.2.0");
        versionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        versionLabel.setBorder(new EmptyBorder(5, 0, 20, 0));
        aboutContent.add(versionLabel);

        // About text
        String aboutText = "SpentWise is a comprehensive personal finance application designed " +
                "to help you take control of your finances. Whether you're saving for a " +
                "big purchase, trying to pay off debt, or just want to know where your " +
                "money is going, SpentWise provides the tools you need to succeed.";
        JLabel aboutLabel = createWrappedTextLabel(aboutText);
        aboutLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutContent.add(aboutLabel);

        // Collapsible sections
        String[][] sections = {
                {"Our Mission",
                        "At SpentWise, our mission is to demystify personal finance and empower individuals " +
                                "to achieve financial wellness through intuitive tools, education, and actionable insights. " +
                                "We believe everyone deserves access to simple yet powerful financial management tools " +
                                "that adapt to their unique needs and goals."},

                {"Our Story",
                        "SpentWise began in 2022 when our founder, frustrated with existing budgeting tools, " +
                                "decided to build something better. What started as a simple expense tracker has evolved " +
                                "into a comprehensive financial management platform used by over 100,000 people worldwide. " +
                                "Our team of finance experts and software engineers continues to innovate and improve the " +
                                "platform based on user feedback and emerging financial trends."},

                {"Our Team",
                        "Our diverse team brings together expertise in finance, technology, and user experience design. " +
                                "Led by CEO Jane Smith, former financial advisor and tech entrepreneur, our team is committed " +
                                "to building tools that make financial management accessible and stress-free. We're based in " +
                                "Seattle with remote team members across North America and Europe."},

                {"Privacy & Security",
                        "Your financial data is sensitive, and we treat it with the utmost care. SpentWise uses " +
                                "bank-level encryption (256-bit AES) for all data storage and transfer. We never sell your " +
                                "personal information to third parties, and you maintain complete control over your data at " +
                                "all times. Our security practices are regularly audited by independent experts to ensure " +
                                "compliance with industry standards."}
        };

        for (String[] section : sections) {
            JPanel sectionPanel = createCollapsiblePanel(section[0], section[1]);
            sectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            sectionPanel.setMaximumSize(new Dimension(600, 1000));
            aboutContent.add(sectionPanel);
            aboutContent.add(Box.createVerticalStrut(5));
        }

        // Features
        JLabel featuresTitle = new JLabel("Key Features:");
        featuresTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        featuresTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        featuresTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        aboutContent.add(featuresTitle);

        String[] features = {
                "Intuitive budget creation and tracking",
                "Detailed spending analysis and reports",
                "Bill payment reminders and scheduling",
                "Financial goal setting and monitoring",
                "Secure data encryption and backup"
        };

        JPanel featurePanel = new JPanel(new GridLayout(features.length, 1, 0, 5));
        featurePanel.setBackground(LIGHT_BLUE);
        featurePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        featurePanel.setMaximumSize(new Dimension(500, features.length * 30));

        for (String feature : features) {
            JLabel featureLabel = new JLabel("• " + feature);
            featureLabel.setFont(REGULAR_FONT);
            featureLabel.setHorizontalAlignment(JLabel.CENTER);
            featurePanel.add(featureLabel);
        }

        aboutContent.add(featurePanel);

        // Contact info
        JLabel contactTitle = new JLabel("Contact Information:");
        contactTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contactTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        aboutContent.add(contactTitle);

        JPanel contactPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        contactPanel.setBackground(LIGHT_BLUE);
        contactPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactPanel.setMaximumSize(new Dimension(400, 90));

        JLabel emailLabel = new JLabel("Email: support@spentwise.com");
        emailLabel.setFont(REGULAR_FONT);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel websiteLabel = new JLabel("Website: www.spentwise.com");
        websiteLabel.setFont(REGULAR_FONT);
        websiteLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel socialLabel = new JLabel("Social: @SpentWise");
        socialLabel.setFont(REGULAR_FONT);
        socialLabel.setHorizontalAlignment(JLabel.CENTER);

        contactPanel.add(emailLabel);
        contactPanel.add(websiteLabel);
        contactPanel.add(socialLabel);

        aboutContent.add(contactPanel);

        // Copyright info
        JLabel copyrightLabel = new JLabel("© 2025 SpentWise, Inc. All rights reserved.");
        copyrightLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        copyrightLabel.setBorder(new EmptyBorder(30, 0, 10, 0));
        aboutContent.add(copyrightLabel);

        // Add to main panel with scroll capability
        JScrollPane scrollableContent = new JScrollPane(aboutContent);
        scrollableContent.setBorder(null);
        scrollableContent.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollableContent, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates a collapsible panel with a title and content.
     *
     * @param title The title of the panel
     * @param content The content to display when expanded
     * @return Configured collapsible panel
     */
    private JPanel createCollapsiblePanel(String title, String content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BLUE);
        panel.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
                new EmptyBorder(5, 0, 5, 0)
        ));

        JButton titleBtn = new JButton(title);
        titleBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleBtn.setHorizontalAlignment(SwingConstants.LEFT);
        titleBtn.setBorderPainted(false);
        titleBtn.setContentAreaFilled(false);
        titleBtn.setFocusPainted(false);
        titleBtn.setIcon(new ArrowIcon(true));
        titleBtn.setIconTextGap(10);

        // Content panel (initially hidden)
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(LIGHT_BLUE);
        contentPanel.setBorder(new EmptyBorder(10, 20, 5, 0));
        contentPanel.setVisible(false);
        // Content text
        JLabel contentLabel = new JLabel("<html><p style='width:500px;'>" + content + "</p></html>");
        contentLabel.setFont(REGULAR_FONT);
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Toggle action
        titleBtn.addActionListener(e -> {
            contentPanel.setVisible(!contentPanel.isVisible());
            titleBtn.setIcon(new ArrowIcon(!contentPanel.isVisible()));
        });

        panel.add(titleBtn, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates a panel with a header containing title and subtitle.
     *
     * @param title The main title text
     * @param subtitle The subtitle text
     * @return Panel with configured header
     */
    private JPanel createPanelWithHeader(String title, String subtitle) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BLUE);

        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(LIGHT_BLUE);
        headerPanel.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                new EmptyBorder(20, 30, 20, 30)
        ));

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Subtitle
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(REGULAR_FONT);
        subtitleLabel.setForeground(TEXT_COLOR);
        subtitleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);

        // Add header to panel
        panel.add(headerPanel, BorderLayout.NORTH);

        return panel;
    }

    /**
     * Creates a text label with automatic word wrapping.
     *
     * @param text The text content to display
     * @return Configured label with wrapped text
     */
    private JLabel createWrappedTextLabel(String text) {
        JLabel label = new JLabel("<html><p style='width:500px;'>" + text + "</p></html>");
        label.setFont(REGULAR_FONT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(0, 0, 10, 0));
        return label;
    }

    /**
     * Creates a styled primary button with hover effects.
     *
     * @param text The button text
     * @return Configured primary button
     */
    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(DARK_BLUE);
        button.setForeground(Color.BLACK); // Changed from Color.WHITE to Color.BLACK
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(21, 101, 192)); // Slightly darker blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(DARK_BLUE);
            }
        });

        return button;
    }

    /**
     * Creates a styled secondary button with hover effects.
     *
     * @param text The button text
     * @return Configured secondary button
     */
    private JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(LIGHT_GRAY);
        button.setForeground(BLACK);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(220, 220, 220)); // Slightly darker gray
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(LIGHT_GRAY);
            }
        });

        return button;
    }

    /**
     * Custom arrow icon for collapsible panels.
     * Draws either a right-pointing or down-pointing arrow
     * depending on the state of the panel.
     */
    private static class ArrowIcon implements Icon {
        private final boolean pointRight;

        /**
         * Creates a new arrow icon.
         *
         * @param pointRight If true, arrow points right; otherwise, points down
         */
        public ArrowIcon(boolean pointRight) {
            this.pointRight = pointRight;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(BLACK);

            int size = 8;
            int[] xPoints = new int[3];
            int[] yPoints = new int[3];

            if (pointRight) {
                xPoints[0] = x;
                xPoints[1] = x + size;
                xPoints[2] = x;

                yPoints[0] = y;
                yPoints[1] = y + size / 2;
                yPoints[2] = y + size;
            } else {
                xPoints[0] = x;
                xPoints[1] = x + size;
                xPoints[2] = x + size;

                yPoints[0] = y + size / 2;
                yPoints[1] = y;
                yPoints[2] = y + size;
            }

            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.dispose();
        }

        @Override
        public int getIconWidth() {
            return 8;
        }

        @Override
        public int getIconHeight() {
            return 8;
        }
    }

    /**
     * Main method for running the settings panel as a standalone application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SpentWise - Settings");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new SettingsPanel());
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}