package Main;

import PDF.generator;
import UI.myeditor;
import UI.myrenderer;
import datos.ReaderCSV;
import datos.WriterCSV;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import models.Material;
import models.Registro;

/**
 *
 * @author Ronaldo
 */
public class Main extends javax.swing.JFrame implements ActionListener {

    /**
     * s
     * Creates new form Main
     */
    DefaultTableModel modeloProductos = new DefaultTableModel(null,
            new String[]{
                "Cantidad", "Nombre"
            });

    List<Material> materialesExistentes = null;
    generator generadorPDF;

    DefaultListModel<String> model;
    WriterCSV writer = new WriterCSV();
    private List<Material> materialesNuevos = new ArrayList<>();
    List<models.Registro> registros;

    public Main() {
        initComponents();
        recomendaciones.setOpaque(false);
        recomendaciones.setSize(0, recomendaciones.getHeight());;
        model = new DefaultListModel<>();
        recomendaciones.setModel(model);
        generadorPDF = new generator();
        SpinnerNumberModel modelSpinner = (SpinnerNumberModel) CantidadMaterial.getModel();
        modelSpinner.setMinimum(0);
        CantidadMaterial.setModel(modelSpinner);
        CantidadMaterial1.setModel(modelSpinner);
        NombreMaterialExtra.setVisible(false);
        Titulo_NombreMaterialExtra.setVisible(false);

        if (isFileExists(new File("materiales.csv"))) {
            try {
                rellenarComboBoxMateriales();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        } else {
            NombreMaterial.addItem("Otro");
            NombreMaterialExtra.setVisible(true);
            Titulo_NombreMaterialExtra.setVisible(true);
        }
        try {
            rellenarTablaDeRegistros();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ah ocurrido un error al rellenar la tabla de registro", "Oh no", JOptionPane.WARNING_MESSAGE);
        }
        update(); 

        //Recomendations
        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
    }

    public void update() {
        recomendaciones.setSize(200, recomendaciones.getHeight());;
        // Obtiene el texto actual del campo de texto
        String text = name.getText();

        // Crea una lista de recomendaciones basada en el texto
        List<String> recommendations = new ArrayList<>();
        for (Material item : materialesExistentes) {
            if (item.getNombre().toLowerCase().startsWith(text.toLowerCase())
                    || item.getNombre().toLowerCase().contains(text.toLowerCase())) {
                recommendations.add(item.getNombre());
            }
        }

        // Actualiza el modelo de la lista con las recomendaciones
        model.clear();
        for (String item : recommendations) {
            model.addElement(item);
        }
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public void rellenarTablaDeRegistros() throws IOException {
        File file = new File("Registros.csv");
        if (file.exists()) {
            ReaderCSV reader = new ReaderCSV("Registros.csv");
            registros = reader.registros();
            DefaultTableModel modelo = (DefaultTableModel) Registros.getModel();
            TableColumn agregarColumn = Registros.getColumnModel().getColumn(3);
            TableColumn agregarColumn2 = Registros.getColumnModel().getColumn(4);
            modelo.setRowCount(0);
            agregarColumn.setCellEditor(new myeditor(Registros, "see_more", registros, this));
            agregarColumn2.setCellEditor(new myeditor(Registros, "print", registros, this));

            for (int i = registros.size() - 1; i >= 0; i--) {
                modelo.addRow(new Object[]{registros.get(i).getMovimiento(), registros.get(i).getFecha(), registros.get(i).getHora()});
                //Botton

                agregarColumn.setCellRenderer(new myrenderer(false, "Ver mas"));
                agregarColumn2.setCellRenderer(new myrenderer(false, "Imprimir"));
            }
            reader.close();
        }

    }

    public void rellenarComboBoxMateriales() throws FileNotFoundException, IOException {
        //Crear CSV materiales para poder crear el reporte final
        NombreMaterial.setModel(new DefaultComboBoxModel<>()); //Sustituto al removeallItems

        ReaderCSV reader = new ReaderCSV("materiales.csv");
        materialesExistentes = reader.getMateriales();

        Collections.sort(materialesExistentes);

        for (Material material : materialesExistentes) {
            NombreMaterial.addItem(material.getNombre());
        }
        NombreMaterial.addItem("Otro");
        reader.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        generar = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        proovedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        almacenista = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        CantidadMaterial = new javax.swing.JSpinner();
        NombreMaterialExtra = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        productos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        important = new javax.swing.JLabel();
        NombreMaterial = new javax.swing.JComboBox<>();
        Titulo_NombreMaterialExtra = new javax.swing.JLabel();
        required = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        entrego = new javax.swing.JTextField();
        recibio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CantidadMaterial1 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        productos1 = new javax.swing.JTable();
        important1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        observaciones1 = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        required2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        recomendaciones = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Registros = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane3FocusGained(evt);
            }
        });
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseReleased(evt);
            }
        });
        jTabbedPane3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane3KeyPressed(evt);
            }
        });

        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel3KeyTyped(evt);
            }
        });

        generar.setText("Generar");
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre*");

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        jLabel2.setText("Proovedor*");

        jLabel3.setText("Almacenista*");

        jLabel4.setText("Observaciones (Opcional)");

        observaciones.setColumns(20);
        observaciones.setRows(5);
        jScrollPane1.setViewportView(observaciones);

        CantidadMaterial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CantidadMaterialStateChanged(evt);
            }
        });
        CantidadMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CantidadMaterialKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CantidadMaterialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadMaterialKeyTyped(evt);
            }
        });

        NombreMaterialExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreMaterialExtraActionPerformed(evt);
            }
        });

        jButton1.setText("Añadir a la lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Nombre", ""
            }
        ));
        productos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        productos.setRowHeight(30);
        jScrollPane2.setViewportView(productos);
        if (productos.getColumnModel().getColumnCount() > 0) {
            productos.getColumnModel().getColumn(1).setPreferredWidth(797);
            productos.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        jLabel5.setText("Cantidad");

        jLabel6.setText("Material");

        NombreMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreMaterialActionPerformed(evt);
            }
        });

        Titulo_NombreMaterialExtra.setText("Ingrese el nombre");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(almacenista))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(proovedor))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CantidadMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(important)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Titulo_NombreMaterialExtra)))
                            .addGap(36, 36, 36)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(NombreMaterialExtra)
                                .addComponent(jLabel6)
                                .addComponent(NombreMaterial, 0, 459, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(required, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(472, 472, 472))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(494, 494, 494))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(proovedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(almacenista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CantidadMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(required))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(important))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NombreMaterialExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Titulo_NombreMaterialExtra))))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Entrada", jPanel3);

        jToggleButton2.setText("Generar");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Entregó");

        jLabel8.setText("Recibió");

        CantidadMaterial1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CantidadMaterial1StateChanged(evt);
            }
        });
        CantidadMaterial1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CantidadMaterial1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CantidadMaterial1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadMaterial1KeyTyped(evt);
            }
        });

        jLabel9.setText("Cantidad");

        jButton4.setText("Añadir a la lista");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        productos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Nombre", ""
            }
        ));
        productos1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        productos1.setRowHeight(30);
        jScrollPane3.setViewportView(productos1);
        if (productos1.getColumnModel().getColumnCount() > 0) {
            productos1.getColumnModel().getColumn(1).setPreferredWidth(797);
            productos1.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        jLabel11.setText("Observaciones (Opcional)");

        observaciones1.setColumns(20);
        observaciones1.setRows(5);
        jScrollPane4.setViewportView(observaciones1);

        jLabel10.setText("Material");

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameKeyTyped(evt);
            }
        });

        recomendaciones.setBorder(null);
        recomendaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recomendacionesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(recomendaciones);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CantidadMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(important1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(440, 440, 440)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(recibio, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                                    .addComponent(entrego)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(492, 492, 492)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(479, 479, 479)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(required2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(entrego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recibio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(CantidadMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(required2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addGap(36, 36, 36)
                        .addComponent(important1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jTabbedPane3.addTab("Salida", jPanel2);

        Registros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Movimiento", "Fecha", "Hora", "", ""
            }
        ));
        Registros.setRowHeight(40);
        jScrollPane5.setViewportView(Registros);

        jButton5.setText("Generar Reporte");

        jButton2.setText("Ver materiales");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(236, 236, 236)
                        .addComponent(jButton3)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(81, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Registros", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ((int) CantidadMaterial.getValue() == 0 || (NombreMaterialExtra.getText().isEmpty() && NombreMaterial.getSelectedItem().equals("Otro"))) {
            required.setText("Campos Requeridos");
            required.setForeground(Color.RED);
        } else {
            required.setText("                 ");
            DefaultTableModel modelo = (DefaultTableModel) productos.getModel();

            if (NombreMaterial.getSelectedItem().equals("Otro")) {
                materialesNuevos.add(new Material((int) CantidadMaterial.getValue(), NombreMaterialExtra.getText()));
                modelo.addRow(new Object[]{CantidadMaterial.getValue(), NombreMaterialExtra.getText()});
            } else {
                modelo.addRow(new Object[]{CantidadMaterial.getValue(), NombreMaterial.getSelectedItem()});
            }
            productos.setModel(modelo);
            TableColumn agregarColumn;
            agregarColumn = productos.getColumnModel().getColumn(2);

            agregarColumn.setCellEditor(new myeditor(productos, "edit", null, this));
            agregarColumn.setCellRenderer(new myrenderer(false, "Eliminar"));

            CantidadMaterial.setValue(0);
            NombreMaterialExtra.setText("");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void CantidadMaterialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadMaterialKeyTyped
        System.out.println(evt.getKeyChar());
    }//GEN-LAST:event_CantidadMaterialKeyTyped

    private void CantidadMaterialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadMaterialKeyPressed
    }//GEN-LAST:event_CantidadMaterialKeyPressed

    private void CantidadMaterialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadMaterialKeyReleased
    }//GEN-LAST:event_CantidadMaterialKeyReleased

    private void CantidadMaterialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_CantidadMaterialStateChanged
        //Evento para el jspinner ocupe los btoones de aumentar y reducir
    }//GEN-LAST:event_CantidadMaterialStateChanged

    private void generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarActionPerformed
        TableModel modelo = productos.getModel();
        if (nombre.getText().isEmpty() || proovedor.getText().isEmpty() || almacenista.getText().isEmpty() || modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ingresar todo los campos");
        } else {
            //Recopilacion de materiales 
            String lineaMateriales = "";
            List<Material> materiales = new ArrayList<>();//Este es para el registro
            List<Material> materialesNuevos = new ArrayList<>();//Este es para los materiales
            for (int i = 0; i < productos.getRowCount(); i++) {
                lineaMateriales += modelo.getValueAt(i, 0) + " " + (String) modelo.getValueAt(i, 1) + "║";
                materiales.add(new Material((int) modelo.getValueAt(i, 0), (String) modelo.getValueAt(i, 1)));
                //Añadimos materiales que aun no tengamos
                boolean bandera = true;
                if (materialesExistentes != null) {

                    for (Material materialExistente : materialesExistentes) {
                        if (materialExistente.getNombre().equals((String) modelo.getValueAt(i, 1))) {
                            bandera = false;
                            break;
                        }
                    }

                }
                if (bandera) {
                    materialesNuevos.add(new Material((int) modelo.getValueAt(i, 0), (String) modelo.getValueAt(i, 1)));
                }
            }

            try {
                //Escritura del CSV

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat dateFormat2 = new SimpleDateFormat("hh:mm:ss");
                String date = dateFormat.format(Calendar.getInstance().getTime());
                String hora = dateFormat2.format(Calendar.getInstance().getTime());
                System.out.println("Linea de Materiales " + lineaMateriales);
                writer.escribir(new String[]{"Entrada", date, hora, nombre.getText(), proovedor.getText(), almacenista.getText(), (observaciones.getText().isEmpty()) ? "Sin Observaciones" : observaciones.getText(),
                    lineaMateriales}, "Registros.csv");

                //Almacenamos los nuevos materiales
                if (!materialesNuevos.isEmpty()) {
                    System.out.println("Almacenando material nuevo");
                    for (Material materialNuevo : materialesNuevos) {
                        writer.escribir(new String[]{materialNuevo.getNombre(), String.valueOf(materialNuevo.getCantidad())}, "materiales.csv");
                    }
                }

                //Actualizamos la tabla de registros
                rellenarTablaDeRegistros();

                //Generamos el PDF
                generadorPDF(new models.Registro("Entrada", date, hora, nombre.getText(), proovedor.getText(), almacenista.getText(), observaciones.getText(),
                        materiales));

                //Limpiamos
                limpiar();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Agegar nuevos materiales
            try {
                rellenarComboBoxMateriales();

                //TEST 
            } catch (IOException ex) {
                System.err.print("Fallo al rellenar el Jcombobox");
            }
        }


    }//GEN-LAST:event_generarActionPerformed

    public void generadorPDF(models.Registro registro) throws FileNotFoundException, MalformedURLException {
        if (registro.getMovimiento().equals("Entrada")) {
            generadorPDF.generarEntrada(registro);
        } else {
            generadorPDF.generarSalida(registro);
        }
        //Abrimos el PDF
        try {
            File file = new File(registro.getMovimiento().equals("Entrada") ? "Entrada.pdf" : "Salida.pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar(String[] datos) {

    }

    public void WindowsOpened(WindowEvent e) {

    }

    //Limpia los espacios de la UI
    private void limpiar() {
        nombre.setText("");
        proovedor.setText("");
        almacenista.setText("");
        observaciones.setText("");
        CantidadMaterial.setValue(0);
        NombreMaterialExtra.setText("");
        DefaultTableModel model = (DefaultTableModel) productos.getModel();
        model.setRowCount(0);
    }

    private void CantidadMaterial1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_CantidadMaterial1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadMaterial1StateChanged

    private void CantidadMaterial1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadMaterial1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadMaterial1KeyPressed

    private void CantidadMaterial1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadMaterial1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadMaterial1KeyReleased

    private void CantidadMaterial1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadMaterial1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadMaterial1KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if ((int) CantidadMaterial1.getValue() == 0 || (name.getText().isEmpty())) {
            required2.setText("Campos Requeridos");
            required2.setForeground(Color.RED);
        } else {
            required2.setText("                 ");
            DefaultTableModel modelo = (DefaultTableModel) productos1.getModel();

//            if (NombreMaterial2.getSelectedItem().equals("Otro")) {
//                materialesNuevos.add(new Material((int) CantidadMaterial1.getValue(), NombreMaterialExtra2.getText()));
//                modelo.addRow(new Object[]{CantidadMaterial1.getValue(), NombreMaterialExtra2.getText()});
//            } else {
//                modelo.addRow(new Object[]{CantidadMaterial1.getValue(), NombreMaterial2.getSelectedItem()});
//            }
            modelo.addRow(new Object[]{CantidadMaterial1.getValue(), name.getText()});
           
            productos1.setModel(modelo);
            TableColumn agregarColumn;
            agregarColumn = productos1.getColumnModel().getColumn(2);

            agregarColumn.setCellEditor(new myeditor(productos1, "edit", null, this));
            agregarColumn.setCellRenderer(new myrenderer(false, "Eliminar"));

            CantidadMaterial1.setValue(0);
            name.setText("");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        TableModel modelo = productos1.getModel();
        if (recibio.getText().isEmpty() || entrego.getText().isEmpty() || modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ingresar todo los campos");
        } else {
            //Recopilacion de materiales y almacenado de nuevos materiales
            String lineaMateriales = "";
            List<Material> materiales = new ArrayList<>();
            List<Material> materialesNuevos = new ArrayList<>();
            for (int i = 0; i < productos1.getRowCount(); i++) {
                lineaMateriales += modelo.getValueAt(i, 0) + " " + (String) modelo.getValueAt(i, 1) + "║";
                materiales.add(new Material((int) modelo.getValueAt(i, 0), (String) modelo.getValueAt(i, 1)));
                //Añadimos materiales que aun no tengamos
                boolean bandera = true;
                if (materialesExistentes != null) {

                    for (Material materialExistente : materialesExistentes) {
                        if (materialExistente.getNombre().equals((String) modelo.getValueAt(i, 1))) {
                            bandera = false;
                            break;
                        }
                    }

                }
                if (bandera) {
                    materialesNuevos.add(new Material((int) modelo.getValueAt(i, 0), (String) modelo.getValueAt(i, 1)));
                }
            }
            //Escritura del CSV
            try {
                DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
                DateFormat dateFormat2 = new SimpleDateFormat("hh:mm:ss");
                String date = dateFormat.format(Calendar.getInstance().getTime());
                String hora = dateFormat2.format(Calendar.getInstance().getTime());
                writer.escribir(new String[]{"Salida", date, hora, recibio.getText(), "-------", entrego.getText(), (observaciones1.getText().isEmpty()) ? "Sin Observaciones" : observaciones1.getText(),
                    lineaMateriales}, "Registros.csv");

                //Almacenamos los nuevos materiales
                if (!materialesNuevos.isEmpty()) {
                    System.out.println("Almacenando material nuevo");
                    for (Material materialNuevo : materialesNuevos) {
                        writer.escribir(new String[]{materialNuevo.getNombre(), String.valueOf(materialNuevo.getCantidad())}, "materiales.csv");
                    }
                }

                rellenarTablaDeRegistros();

                //PDF
                generadorPDF(new models.Registro("Salida", date, hora, recibio.getText(), "-------", entrego.getText(), observaciones1.getText(),
                        materiales));

                limpiar2();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Agegar nuevos materiales
            try {
                rellenarComboBoxMateriales();
            } catch (IOException ex) {
                System.err.print("Fallo al rellenar el Jcombobox");
            }
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void NombreMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreMaterialActionPerformed
        if (NombreMaterial.getSelectedItem().equals("Otro")) {
            NombreMaterialExtra.setVisible(true);
            Titulo_NombreMaterialExtra.setVisible(true);
        } else {
            NombreMaterialExtra.setVisible(false);
            Titulo_NombreMaterialExtra.setVisible(false);
        }
    }//GEN-LAST:event_NombreMaterialActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Materiales materiales = new Materiales(materialesExistentes);
        materiales.setVisible(true);
        materiales.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.out.println("TAMANIO DE LOS REGISTROS: " + this.registros.size());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTabbedPane3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane3FocusGained
    }//GEN-LAST:event_jTabbedPane3FocusGained

    private void jTabbedPane3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseReleased
    }//GEN-LAST:event_jTabbedPane3MouseReleased

    private void jTabbedPane3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane3KeyPressed
        if (evt.getKeyCode() == 10) {
            generarActionPerformed(null);
        }
    }//GEN-LAST:event_jTabbedPane3KeyPressed

    private void jPanel3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyTyped
        System.out.println("Tecla tipeada" + evt.getExtendedKeyCode());
    }//GEN-LAST:event_jPanel3KeyTyped

    private void NombreMaterialExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreMaterialExtraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreMaterialExtraActionPerformed

    private void nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyTyped
//        System.out.println("Hola");
//        System.out.println(name.getText() + evt.getKeyChar());
//        DefaultListModel<String> modelo = new DefaultListModel<>();     
//        
//        String textoIntroducido = name.getText().toLowerCase();
//        if (!name.getText().isEmpty()) {
//            List<Material> materialesFiltrados = materialesExistentes.stream()
//                    .filter(s -> s.getNombre().toLowerCase().contains(textoIntroducido) ||
//                            s.getNombre().toLowerCase().startsWith(textoIntroducido))
//                    .collect(Collectors.toList());
//            for (Material materialFiltrado : materialesFiltrados) {
//                modelo.addElement(materialFiltrado.getNombre());
//            }
//            
//        }
//        recomendaciones.setModel(modelo);

    }//GEN-LAST:event_nameKeyTyped

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void recomendacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recomendacionesMouseClicked
        name.setText(recomendaciones.getSelectedValue());
    }//GEN-LAST:event_recomendacionesMouseClicked

    private void limpiar2() {
        recibio.setText("");
        entrego.setText("");
        observaciones1.setText("");
        DefaultTableModel model = (DefaultTableModel) productos1.getModel();
        model.setRowCount(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }

    /*
    * Añade materiales que no esten repetidos en la lista principal
    */
    public boolean added(Material material){
        return false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner CantidadMaterial;
    private javax.swing.JSpinner CantidadMaterial1;
    private javax.swing.JComboBox<String> NombreMaterial;
    private javax.swing.JTextField NombreMaterialExtra;
    private javax.swing.JTable Registros;
    private javax.swing.JLabel Titulo_NombreMaterialExtra;
    private javax.swing.JTextField almacenista;
    private javax.swing.JTextField entrego;
    private javax.swing.JToggleButton generar;
    private javax.swing.JLabel important;
    private javax.swing.JLabel important1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JTextArea observaciones1;
    private javax.swing.JTable productos;
    private javax.swing.JTable productos1;
    private javax.swing.JTextField proovedor;
    private javax.swing.JTextField recibio;
    private javax.swing.JList<String> recomendaciones;
    private javax.swing.JLabel required;
    private javax.swing.JLabel required2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        System.out.println(e.getID());
    }

}
