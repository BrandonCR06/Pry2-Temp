/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MiniPC.view;

import MiniPC.controller.PCController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileReader;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultCaret;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author Administrador
 */
public final class ProcessManager extends javax.swing.JFrame {

    private File[] loadedFileList;
    int memorySize = 128;
    int diskSize = 512;
    private JTable jTableCPU2;
    int keys = 0;
    private PCController PC = new PCController();
    int input = 0;
    
    /**
     * Creates new form ProcessManager
     */
    public ProcessManager() {
           initComponents(); 
        loadJSONfile();        
        modifyProcessExecutionTable(10);
        loadMemoryTable();
        loadDiskTable();
        loadProcessTable();
        loadCPU2Table();
        loadKeyboardTable();
    }
      public void reset(){
          int i,j;
          
          
          
          
          boolean lineread= true;
          for(i  =0 ;lineread ;i++){
              lineread= false;
             for(j = 1;j<this.memorySize*5;j++ ) {                 
                 if(jTableProcessExecution1.getValueAt(i,j)!=null){
                     lineread= true;
                 }
                 jTableProcessExecution1.setValueAt(null, i, j);              
             }
             
            
          }
              
          
         
       
            
     
        this.cleanLables();
        this.resetKeys();
        
        
        
    }
    
    private void cleanLables(){
        javax.swing.JLabel[] l1 = this.getTextLabelsCpu("CPU1");
        javax.swing.JLabel[] l2 = this.getTextLabelsCpu("CPU2");
        int k = 0;
        for(javax.swing.JLabel l: l1){
            l.setText("___");
            l2[k].setText("___");
            k++;
        }
            
    }
    public void  modifyProcessExecutionTable(int n){
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int j) {return false;};
        };
        DefaultTableModel model2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int j) {return false;};
        };       
        model.setRowCount(n);
        model2.setRowCount(n);
        int r  = this.memorySize*5;
        for(int i = 0 ; i < r ;  i++){            
            if(model.getColumnCount()< i+1){                
                model.addColumn(i);                
                
            }
            if(model2.getColumnCount()< i+1){                
                model2.addColumn(i);                
                
            }
            
                                     
        }
        
        
        this.jTableProcessExecution.setModel(model2);            
        this.jTableProcessExecution1.setModel(model);
        this.jTableProcessExecution1.getColumnModel().getColumn(0).setMinWidth(70);       
        this.jTableProcessExecution1.getColumnModel().getColumn(0).setMaxWidth(70);       
        this.jTableProcessExecution.getColumnModel().getColumn(0).setMinWidth(70);       
        this.jTableProcessExecution.getColumnModel().getColumn(0).setMaxWidth(70);       
        for(int i = 1 ; i< jTableProcessExecution.getColumnCount(); i ++){
            this.jTableProcessExecution.getColumnModel().getColumn(i).setMinWidth(10);       
            this.jTableProcessExecution.getColumnModel().getColumn(i).setMaxWidth(60);       
            this.jTableProcessExecution1.getColumnModel().getColumn(i).setMinWidth(10);       
            this.jTableProcessExecution1.getColumnModel().getColumn(i).setMaxWidth(60);       
        }
        
        
        //this.jTableProcessExecution.getColumnModel().getColumn(0).setResizable(false);
        this.jTableProcessExecution1.getColumnModel().getColumn(0).setHeaderValue("CPU 1");
        this.jTableProcessExecution.getColumnModel().getColumn(0).setHeaderValue("CPU 2");       
        for (int i = 0; i < n; i++) {
            jTableProcessExecution.setValueAt("P"+(i+1), i, 0);   
            jTableProcessExecution1.setValueAt("P"+(i+1), i, 0);   
            
            
        }
        //JScrollPane js=new JScrollPane(this);
        //JScrollPane.setViewportView(jTableProcessExecution);
        //this.add(js);
          
        
        
        
        
    }
    public JTable getDiskTable(){
            return this.jTableDisk;
        }
    
    public JTable getJTableMemory(){
        return this.jTableMemory;
    }
    
    public JTable getJTableDisk(){
        return this.jTableDisk;
    }
    
    public JTable getJTableKeyboard() {
        return this.jTableKeyboard;
    }
    
    
    
    public JTable[] getExecutionTables(){
        JTable[] tables= {this.jTableProcessExecution1,this.jTableProcessExecution};
        return tables;
    }
    
    public File[] getLoadedFileList(){
        return this.loadedFileList;
    }
    public void loadJSONfile() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(new FileReader("test/config.json"));
            this.memorySize = (int) (long)obj.get("MemorySize");
            this.diskSize = (int) (long)obj.get("DiskSize");
        } catch (Exception ex) {
            System.out.println("Exception: "+ ex.getMessage());
        }
    }
    public int getMemSize(){
        return this.memorySize;
    }
    
    public int getDiskSize(){
        return this.diskSize;
    }
    
    
    public void loadMemoryTable() {
        DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int j) {return false;};
        };
        model.setRowCount(memorySize);
        model.addColumn("Pos");
        model.addColumn("Valor en Memoria");
        this.jTableMemory.setModel(model);    
        this.jTableMemory.getColumnModel().getColumn(0).setMaxWidth(60);
        this.jTableMemory.getColumnModel().getColumn(0).setResizable(false);
        this.jTableMemory.getColumnModel().getColumn(1).setPreferredWidth(330);
        this.jTableMemory.getColumnModel().getColumn(1).setResizable(true);
        for (int i = 0; i < this.memorySize; i++) {
            jTableMemory.setValueAt(i+1, i, 0);
        }
    }
    
    public void loadDiskTable() {
        DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int j) {return false;};
        };
        model.setRowCount(diskSize);
        model.addColumn("Pos");
        model.addColumn("Valor en Disco");
        this.jTableDisk.setModel(model);    
        this.jTableDisk.getColumnModel().getColumn(0).setMaxWidth(60);
        this.jTableDisk.getColumnModel().getColumn(0).setResizable(false);
        this.jTableDisk.getColumnModel().getColumn(1).setPreferredWidth(330);
        this.jTableDisk.getColumnModel().getColumn(1).setResizable(true);
        for (int i = 0; i < this.diskSize; i++) {
            jTableDisk.setValueAt(i+1, i, 0);
        }
    }
    
    public void loadProcessTable() {
        DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int j) {return false;};
        };
        model.setRowCount(5);
        model.addColumn("Procesos");
        model.addColumn("Estados");
        this.jTableProcess.setModel(model);    
        this.jTableProcess.getColumnModel().getColumn(0).setMaxWidth(60);
        this.jTableProcess.getColumnModel().getColumn(0).setResizable(false);
        for (int i = 0; i < 5; i++) {
            jTableDisk.setValueAt(i+1, i, 0);
        }
    }
    
    public void loadKeyboardTable() {
        DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int j) {return false;};
        };
        model.setRowCount(memorySize);
        model.addColumn("");
    }
    
    
    public void loadCPU1Table() {
        DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int j) {return false;};
        };
        model.setRowCount(5);
        model.addColumn("CPU1");
        for (int i = 0; i < 5; i++) {
            model.addColumn(i+1);
        }
        model.addColumn("P1");
        for (int i = 0; i < 5; i++) {
            model.addColumn(i+1);
        }
        model.addColumn("PX");
        for (int i = 0; i < 5; i++) {
            model.addColumn(i+1);
        }
        this.jTableCPU2.setModel(model);
    }
    
    
    
    
    
    private static class HeaderRenderer implements TableCellRenderer {

        TableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
        }
    }
    public JTable getProcessTable(){
        return this.jTableProcess;
    }
    public void loadCPU2Table() {
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        btnExecute = new javax.swing.JButton();
        btnStepByStep = new javax.swing.JButton();
        btnStats = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProcess = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAX1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBX1 = new javax.swing.JLabel();
        txtCX1 = new javax.swing.JLabel();
        txtDX1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtPC1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIR1 = new javax.swing.JLabel();
        txtAC1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableDisk = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Object value = getModel().getValueAt(row,column);
                if (value == null || column==0) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                } else if (value=="|"){
                    c.setBackground(Color.LIGHT_GRAY);
                    c.setForeground(Color.LIGHT_GRAY);

                } else if(value =="-") {
                    c.setBackground(Color.cyan);
                    c.setForeground(Color.cyan);
                } else if(value.toString().substring(8, 9).equals("|")) {
                    c.setBackground(Color.LIGHT_GRAY);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.cyan);
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        }
        ;
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtAX2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtBX2 = new javax.swing.JLabel();
        txtCX2 = new javax.swing.JLabel();
        txtDX4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtPC2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtIR2 = new javax.swing.JLabel();
        txtAC2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMemory = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Object value = getModel().getValueAt(row,column);
                if (value == null || column==0) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                } else if (value=="|"){
                    c.setBackground(Color.LIGHT_GRAY);
                    c.setForeground(Color.LIGHT_GRAY);

                } else if(value =="-") {
                    c.setBackground(Color.cyan);
                    c.setForeground(Color.cyan);
                } else if(value.toString().substring(8, 9).equals("|")) {
                    c.setBackground(Color.LIGHT_GRAY);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.cyan);
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        }
        ;
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jInputKeyboard = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableKeyboard = new javax.swing.JTable();
        btmLoad = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableProcessExecution = new javax.swing.JTable() {

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Object value = getModel().getValueAt(row,column);
                if (value == null || column == 0) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.BLUE);
                    c.setForeground(Color.BLACK);
                }
                return c;

            }
        };
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableProcessExecution1 = new javax.swing.JTable() {

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Object value = getModel().getValueAt(row,column);
                if (value == null || column == 0) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.BLUE);
                    c.setForeground(Color.BLACK);
                }
                return c;

            }
        };

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto1-SO");
        setBackground(java.awt.Color.blue);
        setExtendedState(0);

        btnClear.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/clean.png"))); // NOI18N
        btnClear.setText("Limpiar");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnExecute.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnExecute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/administration.png"))); // NOI18N
        btnExecute.setText("Ejecutar");

        btnStepByStep.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnStepByStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/climb.png"))); // NOI18N
        btnStepByStep.setText("Paso a Paso");
        btnStepByStep.setToolTipText("");

        btnStats.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnStats.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/analytics.png"))); // NOI18N
        btnStats.setText("Estadísticas");
        btnStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatsActionPerformed(evt);
            }
        });

        jTableProcess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableProcess);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("DX");

        txtAX1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAX1.setText("___");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("BX");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("CX");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("AX");

        txtBX1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtBX1.setText("___");

        txtCX1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCX1.setText("___");

        txtDX1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtDX1.setText("___");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("PC");

        txtPC1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPC1.setText("___");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("IR");

        txtIR1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtIR1.setText("___");

        txtAC1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAC1.setText("___");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("AC");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAX1)
                    .addComponent(txtBX1)
                    .addComponent(txtCX1)
                    .addComponent(txtDX1)
                    .addComponent(txtPC1)
                    .addComponent(txtIR1)
                    .addComponent(txtAC1))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtAX1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBX1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCX1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDX1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPC1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIR1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAC1)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTableDisk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDisk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(jTableDisk);

        jLabel2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel2.setText("BPC actual CPU1");

        jLabel9.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel9.setText("BPC actual CPU2");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("DX");

        txtAX2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAX2.setText("___");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("BX");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("CX");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("AX");

        txtBX2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtBX2.setText("___");

        txtCX2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCX2.setText("___");

        txtDX4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtDX4.setText("___");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("PC");

        txtPC2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPC2.setText("___");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("IR");

        txtIR2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtIR2.setText("___");

        txtAC2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAC2.setText("___");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("AC");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAX2)
                    .addComponent(txtBX2)
                    .addComponent(txtCX2)
                    .addComponent(txtDX4)
                    .addComponent(txtPC2)
                    .addComponent(txtIR2)
                    .addComponent(txtAC2))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAX2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBX2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCX2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDX4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPC2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIR2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAC2)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTableMemory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }

        ));
        jTableMemory.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(jTableMemory);

        jLabel17.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel17.setText(">>Ingresar valor:");

        jInputKeyboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInputKeyboardActionPerformed(evt);
            }
        });

        jTableKeyboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},
                {null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},
                {null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},
                {null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},
                {null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},
                {null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},
                {null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null},{null}
            },
            new String [] {
                ""
            }
        ));
        jScrollPane6.setViewportView(jTableKeyboard);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jInputKeyboard))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jInputKeyboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addGap(38, 38, 38))
        );

        btmLoad.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btmLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/file (1).png"))); // NOI18N
        btmLoad.setText("Cargar Archivos .asm");
        btmLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmLoadActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel18.setText("Pantalla");

        jTableProcessExecution.setAutoCreateRowSorter(true);
        jTableProcessExecution.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableProcessExecution.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jTableProcessExecution.setAutoResizeMode(0);
        jTableProcessExecution.setShowGrid(true);
        jScrollPane7.setViewportView(jTableProcessExecution);

        jTableProcessExecution1.setAutoCreateRowSorter(true);
        jTableProcessExecution1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableProcessExecution1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jTableProcessExecution1.setAutoResizeMode(0);
        jTableProcessExecution1.setShowGrid(true);
        jScrollPane8.setViewportView(jTableProcessExecution1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btmLoad)
                                .addGap(58, 58, 58))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnExecute, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnStepByStep)
                                            .addComponent(btnStats)
                                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btmLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStepByStep, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExecute, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStats, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)))
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmLoadActionPerformed

      
       
    }//GEN-LAST:event_btmLoadActionPerformed
    public JButton getButtonLoad(){
        return this.btmLoad;
    }
    private void jInputKeyboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInputKeyboardActionPerformed
        /*
        System.out.println(evt.getActionCommand());
        jInputKeyboard.setText("");
        jTableKeyboard.setValueAt(evt.getActionCommand(), this.keys++, 0);
        */
        System.out.println("enter action");
        System.out.println(evt.getActionCommand());
        this.input = Integer.parseInt(evt.getActionCommand());
        jInputKeyboard.setText("");
        setKeys(this.keys++);
        jTableKeyboard.setValueAt(evt.getActionCommand(), this.keys, 0);
    }//GEN-LAST:event_jInputKeyboardActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatsActionPerformed

    public javax.swing.JButton getStepByStep(){
        return this.btnStepByStep;
    }
    public javax.swing.JButton getBtnExeAll(){
        return this.btnExecute;
    }
    public javax.swing.JButton getButtonClear(){
        return this.btnClear;
    }
    
    public javax.swing.JButton getButtonStats(){
        return this.btnStats;
    }
    
    public JTextField getTextField() {
        return this.jInputKeyboard;
    }
    
    public int getInput() {
        return this.input;
    }
    
    public int getKeys(){
        return this.keys;
    }
    
    public void setKeys(int k){
        this.keys = k;
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
            java.util.logging.Logger.getLogger(ProcessManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcessManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcessManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcessManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new ProcessManager().setVisible(true);
            }
        });
    }
    public javax.swing.JButton  getLoadBtn(){        
        return this.btmLoad;
    }
    public void resetKeys(){
        for(int i = 0 ; i < this.keys; i++){
            this.jTableKeyboard.setValueAt(null, i, 0);
        }
        this.setKeys(0);
    }
    public javax.swing.JLabel[] getTextLabelsCpu(String cpuName){
        
        if(cpuName.equals("CPU1")){
            javax.swing.JLabel[] label = {this.txtAX1,this.txtBX1,this.txtCX1,this.txtDX1,this.txtAC1,this.txtIR1,this.txtPC1};;            
            return label;
        
        }else {
            javax.swing.JLabel[] label = {this.txtAX2,this.txtBX2,this.txtCX2,this.txtDX4,this.txtAC2,this.txtIR2,this.txtPC2};;            
            return label;
        }
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmLoad;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExecute;
    private javax.swing.JButton btnStats;
    private javax.swing.JButton btnStepByStep;
    private javax.swing.JTextField jInputKeyboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableDisk;
    private javax.swing.JTable jTableKeyboard;
    private javax.swing.JTable jTableMemory;
    private javax.swing.JTable jTableProcess;
    private javax.swing.JTable jTableProcessExecution;
    private javax.swing.JTable jTableProcessExecution1;
    private javax.swing.JLabel txtAC1;
    private javax.swing.JLabel txtAC2;
    private javax.swing.JLabel txtAX1;
    private javax.swing.JLabel txtAX2;
    private javax.swing.JLabel txtBX1;
    private javax.swing.JLabel txtBX2;
    private javax.swing.JLabel txtCX1;
    private javax.swing.JLabel txtCX2;
    private javax.swing.JLabel txtDX1;
    private javax.swing.JLabel txtDX4;
    private javax.swing.JLabel txtIR1;
    private javax.swing.JLabel txtIR2;
    private javax.swing.JLabel txtPC1;
    private javax.swing.JLabel txtPC2;
    // End of variables declaration//GEN-END:variables
}
