/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ergasia;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.hp.hpl.jena.rdf.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Jimis
 */
public class MyFrame extends JFrame{
    
    private final JLabel osl=new JLabel("Operating System");
    private final JLabel biosl=new JLabel("BIOS");
    private final JLabel kl=new JLabel("Keyboard");
    private final JLabel ml=new JLabel("Mouse");
    private final JLabel appsl=new JLabel("Office Application");
    private final JLabel raml=new JLabel("RAM");
    private final JLabel cpul=new JLabel("CPU");
    private final JLabel monitl=new JLabel("Monitor");
    private final JLabel Hddl=new JLabel("HDD");
    private final JLabel cachel=new JLabel("Cache");
    private final JLabel pl=new JLabel("Printer");
    private final JLabel sl=new JLabel("Speaker");
    protected final ArrayList<JTextField> texts = new ArrayList<>();
    private int i=0;

    
    public MyFrame()
    {
        setTitle("RDF Ergasia!");
        setSize(380, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel cneeded=new JPanel(new GridLayout(0,1));
        JPanel bneeded=new JPanel(new FlowLayout());
        cneeded.setBackground(Color.CYAN);
        bneeded.setBackground(Color.YELLOW);
        JButton createButton = new JButton("Generate RDF File");
        createButton.setBackground(Color.black);
        createButton.setForeground(Color.white);
        ClickListener crListener = new ClickListener();
        createButton.addActionListener(crListener);
        createButton.setSize(100, 25);
        JPanel tempP1=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP2=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP3=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP4=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP5=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP6=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP7=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP8=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP9=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP10=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP11=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JPanel tempP12=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        //textfields doc listeners
        for (this.i = 0; this.i < 12; this.i++) {
            JTextField tempText = new JTextField(20);
            tempText.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                  changed();
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                  changed();
                }
                @Override
                public void insertUpdate(DocumentEvent e) {
                  changed();
                }

                public void changed() {
                    if(allFieldsFull()==true){
                        createButton.setEnabled(true);
                    }else{
                        createButton.setEnabled(false);
                    }
                }
            });
            texts.add(tempText);
        }
        //panels for the looks
        tempP1.add(osl);
        tempP1.add(texts.get(0));
        tempP2.add(biosl);
        tempP2.add(texts.get(1));
        tempP3.add(appsl);
        tempP3.add(texts.get(2));
        tempP4.add(ml);
        tempP4.add(texts.get(3));
        tempP5.add(kl);
        tempP5.add(texts.get(4));
        tempP6.add(raml);
        tempP6.add(texts.get(5));
        tempP7.add(Hddl);
        tempP7.add(texts.get(6));
        tempP8.add(cpul);
        tempP8.add(texts.get(7));
        tempP9.add(monitl);
        tempP9.add(texts.get(8));
        tempP10.add(sl);
        tempP10.add(texts.get(9));
        tempP11.add(cachel);
        tempP11.add(texts.get(10));
        tempP12.add(pl);
        tempP12.add(texts.get(11));
        //bg color for the lolz
        tempP1.setBackground(Color.CYAN);
        tempP2.setBackground(Color.CYAN);
        tempP3.setBackground(Color.CYAN);
        tempP4.setBackground(Color.CYAN);
        tempP5.setBackground(Color.CYAN);
        tempP6.setBackground(Color.CYAN);
        tempP7.setBackground(Color.CYAN);
        tempP8.setBackground(Color.CYAN);
        tempP9.setBackground(Color.CYAN);
        tempP10.setBackground(Color.CYAN);
        tempP11.setBackground(Color.CYAN);
        tempP12.setBackground(Color.CYAN);
        //add everything
        cneeded.add(tempP1);
        cneeded.add(tempP2);
        cneeded.add(tempP3);
        cneeded.add(tempP4);
        cneeded.add(tempP5);
        cneeded.add(tempP6);
        cneeded.add(tempP7);
        cneeded.add(tempP8);
        cneeded.add(tempP9);
        cneeded.add(tempP10);
        cneeded.add(tempP11);
        cneeded.add(tempP12);
        //button time
        createButton.setEnabled(false);
        bneeded.add(createButton);
        TitledBorder title=new TitledBorder("Please fill the form in order to save");
        title.setTitleColor(Color.red);
        cneeded.setBorder(title);
        add(BorderLayout.CENTER,cneeded);
        add(BorderLayout.SOUTH,bneeded);

    }
    
    public boolean allFieldsFull() {
        for (JTextField textbox : texts) {
            if ( textbox.getText().trim().isEmpty() ) {
                return false;   // one field is empty, so we can stop immediately
            }
        }
        return true;  // every field was non-empty (or else we'd have stopped earlier)
    }


private class ClickListener implements ActionListener{

    private FileWriter out=null;
    private final Model m = ModelFactory.createDefaultModel();
    private final String nsA = "http://my_work/Computer#";
    private final Resource root = m.createResource( nsA + "myComputer" );
    private final Property p_hardware = m.createProperty( nsA + "hasHardware" );
    private final Property p_software = m.createProperty( nsA + "hasSoftware" ); 
    private final Resource software = m.createResource( nsA + "software" );
    private final Resource hardware = m.createResource( nsA + "hardware" );
    private final Resource bios = m.createResource( nsA + "BIOS" );
    private final Resource os = m.createResource( nsA + "OS" );
    private final Resource application = m.createResource( nsA + "application" );
    private final Resource input = m.createResource( nsA + "input" );
    private final Resource output = m.createResource( nsA + "output" );
    private final Resource process_unit = m.createResource( nsA + "process_unit" );
    private final Resource mouse = m.createResource( nsA + "mouse" );
    private final Resource keyboard = m.createResource( nsA + "keyboard" );
    private final Resource hdd = m.createResource( nsA + "HDD" );
    private final Resource cpu = m.createResource( nsA + "CPU" );
    private final Resource RAM = m.createResource( nsA + "RAM" );
    private final Resource cache = m.createResource( nsA + "cache" );
    private final Resource screen = m.createResource( nsA + "screen" );
    private final Resource printer = m.createResource( nsA + "printer" );
    private final Resource speaker = m.createResource( nsA + "speaker" );
    
    public ClickListener(){
    }

    // Καλείται όταν γίνεται κλικ στο κουμπί
    @Override
    public void actionPerformed(ActionEvent e)
    {

        m.add( root, p_hardware, hardware )
                .add( root, p_software, software )
                .add( software, p_software, bios )
                .add(software, p_software, os)
                .add(software, p_software, application)
                .add(os, p_software, texts.get(0).getText())
                .add(bios, p_software, texts.get(1).getText())
                .add(application, p_software, texts.get(2).getText())
                .add(hardware, p_hardware, input)
                .add(hardware, p_hardware, process_unit)
                .add(hardware, p_hardware, output)
                .add(input, p_hardware, mouse)
                .add(input, p_hardware, keyboard)
                .add(keyboard, p_hardware, texts.get(4).getText())
                .add(mouse, p_hardware, texts.get(3).getText())
                .add(process_unit, p_hardware, hdd)
                .add(process_unit, p_hardware, cpu)
                .add(process_unit, p_hardware, cache)
                .add(process_unit, p_hardware, RAM)
                .add(hdd, p_hardware, texts.get(6).getText())
                .add(cpu, p_hardware, texts.get(7).getText())
                .add(cache, p_hardware, texts.get(10).getText())
                .add(RAM, p_hardware, texts.get(5).getText())
                .add(output, p_hardware, screen)
                .add(output, p_hardware, printer)
                .add(output, p_hardware, speaker)
                .add(screen, p_hardware, texts.get(8).getText())
                .add(printer, p_hardware, texts.get(11).getText())
                .add(speaker, p_hardware, texts.get(9).getText());

        System.out.println( "# -- no special prefixes defined" );
        //m.write( System.out );
        System.out.println( "# -- nsA defined" );
        m.setNsPrefix( "nsA", nsA );
        //m.write( System.out );
        System.out.println( "# -- nsA and cat defined" );
        //m.setNsPrefix( "cat", nsB );
        //m.write( System.out );
        System.out.println("RDF creation status: OK");
        String fileName = "test.rdf";
        try {
            out = new FileWriter( fileName ,false);
           m.write( out, "RDF/XML-ABBREV" );
        }catch(Exception ex){
            //ignore
        }
        finally {
            try {
                out.close();
            } catch (IOException ex) {
                //ignore
            }
        }
    }
}
}