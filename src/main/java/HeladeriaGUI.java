import com.tutofox.ApiRestHelados.entity.Heladeria;
import com.tutofox.ApiRestHelados.services.HeladeriaService;
import com.tutofox.ApiRestHelados.services.HeladeriaServiceImplement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HeladeriaGUI extends JFrame {

    private JTextField idField;
    private JTextField saborField;
    private JTextField precioField;
    private JTextArea resultArea;
    private JButton getAllButton;
    private JButton getByIdButton;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;

    private final HeladeriaService heladeriaService;

    public HeladeriaGUI(HeladeriaService heladeriaService) {
        this.heladeriaService = heladeriaService;

        setTitle("Heladería GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Sabor:"));
        saborField = new JTextField();
        inputPanel.add(saborField);
        inputPanel.add(new JLabel("Precio:"));
        precioField = new JTextField();
        inputPanel.add(precioField);

        JPanel buttonPanel = new JPanel();
        getAllButton = new JButton("Obtener todos");
        getByIdButton = new JButton("Obtener por ID");
        createButton = new JButton("Crear");
        updateButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");
        buttonPanel.add(getAllButton);
        buttonPanel.add(getByIdButton);
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(resultArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Agregar ActionListener para los botones
        getAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Heladeria> sabores = heladeriaService.findAll();
                displayFlavors(sabores);
            }
        });

        getByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long id = Long.parseLong(idField.getText());
                    Heladeria sabor = heladeriaService.findById(id);
                    displayFlavor(sabor);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Por favor, ingrese un ID válido.");
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sabor = saborField.getText();
                double precio = Double.parseDouble(precioField.getText());
                Heladeria newHelado = new Heladeria();
                newHelado.setSabor(sabor);
                newHelado.setPrecio(precio);
                Heladeria createdHelado = heladeriaService.save(newHelado);
                displayFlavor(createdHelado);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long id = Long.parseLong(idField.getText());
                    String sabor = saborField.getText();
                    double precio = Double.parseDouble(precioField.getText());
                    Heladeria updatedHelado = heladeriaService.findById(id);
                    if (updatedHelado != null) {
                        updatedHelado.setSabor(sabor);
                        updatedHelado.setPrecio(precio);
                        Heladeria updated = heladeriaService.save(updatedHelado);
                        displayFlavor(updated);
                    } else {
                        resultArea.setText("Sabor de helado no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    resultArea.setText("Por favor, ingrese un ID válido.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long id = Long.parseLong(idField.getText());
                    Heladeria currentHeladeria = heladeriaService.findById(id);
                    if (currentHeladeria != null) {
                        heladeriaService.delete(currentHeladeria);
                        resultArea.setText("Sabor eliminado con éxito.");
                    } else {
                        resultArea.setText("Sabor de helado no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    resultArea.setText("Por favor, ingrese un ID válido.");
                }
            }
        });
    }

    private void displayFlavors(List<Heladeria> sabores) {
        if (sabores != null && !sabores.isEmpty()) {
            resultArea.setText("Sabores de helado:\n");
            for (Heladeria sabor : sabores) {
                resultArea.append("ID: " + sabor.getId() + ", Sabor: " + sabor.getSabor() + ", Precio: " + sabor.getPrecio() + "\n");
            }
        } else {
            resultArea.setText("No se encontraron sabores de helado.");
        }
    }

    private void displayFlavor(Heladeria sabor) {
        if (sabor != null) {
            resultArea.setText("Sabor de helado:\n");
            resultArea.append("ID: " + sabor.getId() + ", Sabor: " + sabor.getSabor() + ", Precio: " + sabor.getPrecio() + "\n");
        } else {
            resultArea.setText("Sabor de helado no encontrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Reemplaza "HeladeriaServiceImplement" con la implementación adecuada
                HeladeriaService heladeriaService = new HeladeriaServiceImplement();
                new HeladeriaGUI(heladeriaService).setVisible(true);
            }
        });
    }
}
