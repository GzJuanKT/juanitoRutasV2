/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package juandavidramos.ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.sql.*;
import juandavidramos.daos.*;
import juandavidramos.daos.exceptions.*;
import juandavidramos.entidades.*;

/**
 *
 * @author juand
 */
public class VentanaCRUDBuses extends javax.swing.JFrame {

    private Bus bus = new Bus();
    
    /**
     * Creates new form VentanaCRUDBuses
     */
    public VentanaCRUDBuses() {
        initComponents();
        cargarDatosOpcBarrios();
        cargarDatosOpcColegios();
        cargarDatosOpcHorarios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        opcionesHorarios = new javax.swing.JComboBox<>();
        campoIdEstudiante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        opcionesBarrios = new javax.swing.JComboBox<>();
        opcionesColegios = new javax.swing.JComboBox<>();
        campoPlacaBus = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        botonBuscar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos Del Bus", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("mononoki NF", 0, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jLabel2.setText("Nombre");

        campoNombre.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        campoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellido");

        campoApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoApellidoActionPerformed(evt);
            }
        });

        jLabel4.setText("Colegio");

        jLabel5.setText("Barrio");

        jLabel6.setText("Horario");

        opcionesHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesHorariosActionPerformed(evt);
            }
        });

        campoIdEstudiante.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        campoIdEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIdEstudianteActionPerformed(evt);
            }
        });

        jLabel7.setText("ID Estudiante");

        opcionesBarrios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesBarriosActionPerformed(evt);
            }
        });

        opcionesColegios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesColegiosActionPerformed(evt);
            }
        });

        campoPlacaBus.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        campoPlacaBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPlacaBusActionPerformed(evt);
            }
        });

        jLabel11.setText("Placa Bus");

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(opcionesHorarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(opcionesBarrios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(opcionesColegios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDatosLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoPlacaBus, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelDatosLayout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(campoIdEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panelDatosLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(campoApellido))
                                    .addGroup(panelDatosLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(447, 447, 447))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(campoPlacaBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(campoIdEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(opcionesColegios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opcionesBarrios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(opcionesHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("mononoki NF", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORMULARIO PARA BUSES");

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(413, 413, 413))
            .addGroup(layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonLimpiar)
                    .addComponent(botonBuscar)
                    .addComponent(botonEliminar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreActionPerformed

    private void campoApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoApellidoActionPerformed

    private void opcionesHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesHorariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionesHorariosActionPerformed

    private void campoIdEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIdEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIdEstudianteActionPerformed

    private void opcionesBarriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesBarriosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionesBarriosActionPerformed

    private void opcionesColegiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesColegiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionesColegiosActionPerformed

    private void campoPlacaBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPlacaBusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPlacaBusActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        // TODO add your handling code here:          
        String placaBus = campoPlacaBus.getText();
        
        if (placaBus.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Se requiere la placa para buscar a una ruta escolar.");
            return;
        } else {
            EntityManagerFactory conexion = Persistence.createEntityManagerFactory("juanitoVersionDosPU");
            DaoBus objetoDao = new DaoBus(conexion);
            
            try {
                Bus rutaEscolar = objetoDao.buscarBus(placaBus);
                
                if (rutaEscolar == null){
                    JOptionPane.showMessageDialog(this, "La ruta escolar a buscar no existe.");
                    return;
                }
                else {
                
                    Colegio colegios = rutaEscolar.getColegiosidColegios();
                    Barrio barrios = rutaEscolar.getBarriosidBarrios();
                    Horario horarios = rutaEscolar.getHorariosidHorarios(); 
                    String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conection = DriverManager.getConnection(url,"root","J@nda.1110");
                        Statement st = conection.createStatement();
                        String sqlQuery = String.format("SELECT idEstudiantes, Nombre, Apellido\n" +
                                            "FROM mydb.estudiantes\n" +
                                            "JOIN mydb.buses ON mydb.estudiantes.Buses_idBuses = mydb.buses.idBuses\n" +
                                            "WHERE mydb.buses.idBuses = '%s';", placaBus);
                        ResultSet rs = st.executeQuery(sqlQuery);

                        while(rs.next()){
                            campoIdEstudiante.setText(rs.getString("idEstudiantes"));
                            campoNombre.setText(rs.getString("Nombre"));
                            campoApellido.setText(rs.getString("Apellido"));
                        }
                        rs.close();
                        st.close();

                    }catch (Exception e){

                    }

                    opcionesHorarios.setSelectedItem(horarios.getJornada());
                    opcionesColegios.setSelectedItem(colegios.getColegio());
                    opcionesBarrios.setSelectedItem(barrios.getBarrio());
                    
                    String titulo = getTitle();
                    if (titulo.indexOf("Editar") != -1){
                        campoNombre.setEditable(true);
                        campoApellido.setEditable(true);
                        opcionesHorarios.setEditable(true);
                        opcionesBarrios.setEditable(true);
                        opcionesColegios.setEditable(true);
                    }
                }

            } catch (NumberFormatException e) { 
                JOptionPane.showMessageDialog(this, "El ID debe ser un numero entero.");
                return;
            }
            
        }
    }//GEN-LAST:event_botonBuscarActionPerformed
    
    public void crearReporte(){
        String idEstudiante = campoIdEstudiante.getText();
        String placaBus = campoPlacaBus.getText();
        
        EntityManagerFactory conexion = Persistence.createEntityManagerFactory("TioJuanitoBusesPU");
        
        DaoEstudiante daoEstu = new DaoEstudiante(conexion); 
        DaoBus daoRutaEscolar = new DaoBus(conexion);  
        DaoReporte daoContrato = new DaoReporte(conexion);

        Estudiante estudiante = daoEstu.buscarEstudiante(Integer.valueOf(idEstudiante));
        Bus rutaEscolar = daoRutaEscolar.buscarBus(placaBus);
        
        if (estudiante != null & rutaEscolar != null) {
            Reporte objetoReporte = new Reporte();
            
            objetoReporte.setIdReportes(Hashes.crearHashReporte());
            objetoReporte.setEstudiantesidEstudiantes(estudiante);
            objetoReporte.setBusesidBuses(rutaEscolar);
            objetoReporte.setPagoDeServicio(100.00);
            
            Date fechaActual = new Date();
            objetoReporte.setFechaReporte(fechaActual);
            
            try {
                daoContrato.agregar(objetoReporte);
                JOptionPane.showMessageDialog(this, "El reporte se ha creado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se ha encontrado el estudiante o la ruta escolar.");
            }
        }
    }


    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        // TODO add your handling code here:
        campoIdEstudiante.setText("");
        campoNombre.setText("");
        campoApellido.setText("");

        opcionesBarrios.setSelectedIndex(0);
        opcionesColegios.setSelectedIndex(0);
        opcionesHorarios.setSelectedIndex(0);

    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        // TODO add your handling code here:
        String idPlacaBus = bus.getIdBuses(); 
        int respuesta = JOptionPane.showConfirmDialog(this, "Seguro que desea eliminar este estudiante?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION){    
            EntityManagerFactory conexion = Persistence.createEntityManagerFactory("TioJuanitoBusesPU");
            DaoBus daoBus = new DaoBus(conexion); 
            
            try {
                daoBus.eliminar(idPlacaBus);
            } catch (IllegalOrphanException | NonexistentEntityException ex) {
                Logger.getLogger(VentanaCRUDBuses.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int totalBuses = daoBus.getTotalBuses();
            JOptionPane.showMessageDialog(this, "Se ha eliminado al bus "+bus.getIdBuses()+"\nTotal de buses: "+totalBuses);
            botonLimpiarActionPerformed(evt);
        }  
    }//GEN-LAST:event_botonEliminarActionPerformed

      
    
    private void cargarDatosOpcHorarios(){
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","J@nda.1110");
            Statement st = conexion.createStatement();
            String sqlQuery = "SELECT * FROM tiojuanito.horarios";
            ResultSet rs = st.executeQuery(sqlQuery);

            while(rs.next()){
                opcionesHorarios.addItem(rs.getString("Jornada"));
            }
            rs.close();
            st.close();

        }catch (Exception e){
            
        }
    }
    
    private void cargarDatosOpcBarrios(){
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","J@nda.1110");
            Statement st = conexion.createStatement();
            String sqlQuery = "SELECT * FROM tiojuanito.barrios";
            ResultSet rs = st.executeQuery(sqlQuery);

            while(rs.next()){
                opcionesBarrios.addItem(rs.getString("barrio"));
            }
            rs.close();
            st.close();

        }catch (Exception e){
            
        }
    }
    
       
    private void cargarDatosOpcColegios(){
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","J@nda.1110");
            Statement st = conexion.createStatement();
            String sqlQuery = "SELECT * FROM tiojuanito.colegios";
            ResultSet rs = st.executeQuery(sqlQuery);

            while(rs.next()){
                opcionesColegios.addItem(rs.getString("colegio"));
            }
            rs.close();
            st.close();

        }catch (Exception e){
            
        }
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
            java.util.logging.Logger.getLogger(VentanaCRUDBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCRUDBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCRUDBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCRUDBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCRUDBuses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoIdEstudiante;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoPlacaBus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> opcionesBarrios;
    private javax.swing.JComboBox<String> opcionesColegios;
    private javax.swing.JComboBox<String> opcionesHorarios;
    private javax.swing.JPanel panelDatos;
    // End of variables declaration//GEN-END:variables

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }

    public JTextField getCampoApellido() {
        return campoApellido;
    }

    public JTextField getCampoIdEstudiante() {
        return campoIdEstudiante;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoRutaEscolar() {
        return campoPlacaBus;
    }

    public JComboBox<String> getOpcionesBarrios() {
        return opcionesBarrios;
    }

    public JComboBox<String> getOpcionesColegios() {
        return opcionesColegios;
    }

    public JComboBox<String> getOpcionesHorarios() {
        return opcionesHorarios;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JTextField getCampoPlacaBus() {
        return campoPlacaBus;
    }
    
    
    


}


