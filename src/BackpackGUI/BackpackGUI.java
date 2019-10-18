package BackpackGUI;

import Backpack.*;
import Figures3D.*;
import Figures3D.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.function.Supplier;

public class BackpackGUI {
    private JFrame frame;
    private Backpack backpack;

    private JButton delete;
    private JButton clear;
    private JTextField currentVolume;
    private DefaultListModel model;
    private JList list;

    public BackpackGUI() {
        frame = createFrame();
        backpack = createBackpack(frame);
        frame.add(createMainPanel());
        frame.add(createViewPanel());

        frame.pack();
        frame.setVisible(true);
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Backpack");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation((int) ((dimension.getWidth() - 350) / 2), (int) ((dimension.getHeight() - 350) / 2));
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    private static Backpack createBackpack(JFrame frame) {
        String string = JOptionPane.showInputDialog(frame, "Enter backpack volume:", "Backpack parameters", JOptionPane.PLAIN_MESSAGE);

        if (string == null || string.isEmpty()) {
            System.exit(0);
        }

        double volume = Double.parseDouble(string);

        if (volume > 0) {
            return new Backpack(volume);
        } else {
            return new Backpack(100);
        }
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = createPanel();

        mainPanel.add(createLabel("Events:"));
        mainPanel.add(createButtonsPanel());
        mainPanel.add(createLabel("Backpack volume:"));
        mainPanel.add(createTextField(String.valueOf(backpack.getBackpackVolume()), false));
        mainPanel.add(createLabel( "Current volume:"));

        currentVolume = (JTextField) mainPanel.add(createTextField(String.valueOf(backpack.getCurrentVolume()), false));

        return mainPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(140, 200));

        delete = createButton("Take out figure", actionEvent -> {
            while (list.getSelectedIndex() != -1) {
                backpack.takeOutFigure(list.getSelectedIndex());
                currentVolume.setText(String.valueOf(backpack.getCurrentVolume()));
                model.remove(list.getSelectedIndex());
            }

            if (list.getModel().getSize() == 0) {
                clear.setEnabled(false);
            }
        });
        delete.setEnabled(false);

        clear = createButton("Clear backpack", actionEvent -> {
            backpack.clearBackpack();
            currentVolume.setText(String.valueOf(backpack.getCurrentVolume()));
            model.removeAllElements();
            clear.setEnabled(false);
        });
        clear.setEnabled(false);

        buttonsPanel.add(createButton("Put figure", new ButtonClickListener()));
        buttonsPanel.add(delete);
        buttonsPanel.add(clear);
        JButton save = (JButton) buttonsPanel.add(createButton("Save backpack", actionEvent -> {}));
        JButton load = (JButton) buttonsPanel.add(createButton("Load backpack", actionEvent -> {}));

        save.setEnabled(false);
        load.setEnabled(false);

        return buttonsPanel;
    }

    private JPanel createViewPanel() {
        JPanel viewPanel = createPanel();

        viewPanel.add(createLabel("Backpack:"));

        model = new DefaultListModel();
        list = createList(model);

        viewPanel.add(createScrollPane(list));

        return viewPanel;
    }

    private JList createList(DefaultListModel model) {
        JList list = new JList(model);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setPreferredSize(new Dimension(80, 600));
        list.addListSelectionListener(listSelectionEvent -> {
            if (list.getSelectedIndex() == -1) {
                delete.setEnabled(false);
            } else {
                delete.setEnabled(true);
            }
        });

        return list;
    }

    private static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension(140, 350));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return panel;
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(130, 20));

        return label;
    }

    private static JTextField createTextField(String text, boolean q) {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setText(text);
        textField.setPreferredSize(new Dimension(130, 20));
        textField.setEnabled(q);

        return textField;
    }

    private static JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 30));
        button.addActionListener(actionListener);

        return button;
    }

    private static JScrollPane createScrollPane(JList list) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(130, 300));
        scrollPane.setViewportView(list);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        return scrollPane;
    }

    private class ButtonClickListener implements ActionListener {
        private final String CUBE = "Cube";
        private final String CYLINDER = "Cylinder";
        private final String SPHERE = "Sphere";

        private JDialog dialog;
        private JPanel cards;
        private JComboBox comboBox;
        private HashMap<String, Supplier<Shape>> map;

        private JTextField cubeEdge;
        private JTextField cylinderRadius;
        private JTextField cylinderHeight;
        private JTextField sphereRadius;

        public void actionPerformed(ActionEvent actionEvent) {
            frame.setEnabled(false);
            dialog = createDialog();
            cards = createCards();
            map = createMap();

            dialog.add(createComboBoxPanel(), BorderLayout.NORTH);
            dialog.add(cards, BorderLayout.CENTER);
            dialog.add(createButtonsPanel(), BorderLayout.SOUTH);

            dialog.setVisible(true);
        }

        private JDialog createDialog() {
            JDialog dialog = new JDialog(frame, "Put figure");

            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

            dialog.setSize(320, 190);
            dialog.setLocation((int) ((dimension.getWidth() - 320) / 2), (int) ((dimension.getHeight() - 190) / 2));
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    dialog.setVisible(false);
                    dialog.dispose();
                    frame.setEnabled(true);
                    frame.toFront();
                }
            });


            return dialog;
        }

        private JPanel createCards() {
            JPanel cards = new JPanel(new CardLayout());
            cards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel cubeCard = new JPanel();
            JPanel cylinderCard = new JPanel();
            JPanel sphereCard = new JPanel();

            cubeCard.add(createLabel("Edge value: "));
            cubeEdge = (JTextField) cubeCard.add(createTextField("", true));

            cylinderCard.add(createLabel("Radius value:"));
            cylinderRadius = (JTextField) cylinderCard.add(createTextField("", true));
            cylinderCard.add(createLabel("Height value:"));
            cylinderHeight = (JTextField) cylinderCard.add(createTextField("", true));

            sphereCard.add(createLabel("Radius value:"));
            sphereRadius = (JTextField) sphereCard.add(createTextField("", true));

            cards.add(cubeCard, CUBE);
            cards.add(cylinderCard, CYLINDER);
            cards.add(sphereCard, SPHERE);

            return cards;
        }

        private JPanel createComboBoxPanel() {
            JPanel comboPanel = new JPanel();
            comboPanel.add(createLabel("Select figure:"));

            String[] figures = {CUBE, CYLINDER, SPHERE};
            comboBox = new JComboBox(figures);
            comboBox.setPreferredSize(new Dimension(130, 20));
            comboBox.addItemListener(event -> {
                CardLayout card = (CardLayout) (cards.getLayout());
                card.show(cards, (String) event.getItem());
            });

            comboPanel.add(comboBox);

            return comboPanel;
        }

        private HashMap<String, Supplier<Shape>> createMap() {
            HashMap<String, Supplier<Shape>> map = new HashMap<>();

            map.put(CUBE, () -> {
                String edgeString = cubeEdge.getText();
                Cube cube = new Cube();

                if (edgeString != null && !edgeString.isEmpty() && Double.parseDouble(edgeString) > 0) {
                    cube = new Cube(Double.parseDouble(edgeString));
                }

                return cube;
            });

            map.put(CYLINDER, () -> {
                String radiusString = cylinderRadius.getText();
                String heightString = cylinderHeight.getText();
                Cylinder cylinder = new Cylinder();

                if (radiusString != null && !radiusString.isEmpty() && Double.parseDouble(radiusString) >= 0 && heightString != null && !heightString.isEmpty() && Double.parseDouble(heightString) >= 0) {
                    cylinder = new Cylinder(Double.parseDouble(radiusString), Double.parseDouble(heightString));
                }

                return cylinder;
            });

            map.put(SPHERE, () -> {
                String radiusStringS = sphereRadius.getText();
                Sphere sphere = new Sphere();

                if (radiusStringS != null && !radiusStringS.isEmpty() && Double.parseDouble(radiusStringS) > 0) {
                    sphere = new Sphere(Double.parseDouble(radiusStringS));
                }

                return sphere;
            });

            return map;
        }

        private JPanel createButtonsPanel() {
            JPanel buttons = new JPanel();

            buttons.add(createButton("Put", actionEvent1 -> {
                Shape figure = map.get(comboBox.getSelectedItem()).get();

                if (figure.getVolume() != 0) {
                    try {
                        int index = backpack.putFigure(figure);

                        currentVolume.setText(String.valueOf(backpack.getCurrentVolume()));
                        model.add(index, figure.toString());
                        clear.setEnabled(true);
                    } catch (BackpackFullException exception) {
                        JOptionPane.showMessageDialog(frame, exception.getMessage(), "Backpack Full Exception", JOptionPane.ERROR_MESSAGE);
                    }
                }

                dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
            }));

            buttons.add(createButton( "Cancel", actionEvent2 -> {
                dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
            }));

            return buttons;
        }
    }
}
