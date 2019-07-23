/*
 * Libby Bakalar 
 * April 12, 2019
 * This program is a GUI based BMI calculator.  
 * This program will read and write objects to a file 
 */

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Control;

public class MainForm {

	protected Shell shell;
	public static List<BMI> bmiList = new ArrayList<BMI>();
	private final static String FILENAME = "bmi.ser";
	private File file;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainForm window = new MainForm();
			window.open();
			try
	        {
	            FileOutputStream outFile = new FileOutputStream(FILENAME, false); 
				ObjectOutputStream outStream = new ObjectOutputStream(outFile); 	              	               
	            outStream.writeObject(bmiList);	          
	            outStream.close(); 
	            outFile.close();
	        } 
	        catch(IOException ex) 
	        { 
	            System.out.println("Error writing BMI to file" + ex.getMessage()); 
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.addListener(SWT.Close, event -> doShowMessageBox(event));
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void doShowMessageBox(Event event) {
		// TODO Auto-generated method stub
		 int style = SWT.APPLICATION_MODAL | SWT.ICON_QUESTION | SWT.YES
	                | SWT.NO;

	        MessageBox messageBox = new MessageBox(shell, style);
	        messageBox.setText("Information");
	        messageBox.setMessage("Really close application?");
	        event.doit = messageBox.open() == SWT.YES;
	}

	/**
	 * Create contents of the window.
	 */
	@SuppressWarnings("unchecked")
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(528, 412);
		shell.setText("BMI Calculator");
		
		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnExit.setBounds(379, 300, 105, 35);
		btnExit.setText("Exit");
		
		try
        {
			file = new File(FILENAME);
			if(file.exists()) {
				FileInputStream inFile = new FileInputStream(FILENAME);
				ObjectInputStream inStream = new ObjectInputStream(inFile);            
	            bmiList = (List<BMI>)inStream.readObject();            
	            inStream.close(); 
	            inFile.close();  
			}
        } 
        catch(IOException ex) 
        { 
            System.out.println("Error reading Song file"); 
        } 
		catch(ClassNotFoundException ex) 
        { 
            
        }
		
		Button btnDisplaySinglePatient = new Button(shell, SWT.NONE);
		btnDisplaySinglePatient.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnDisplaySinglePatient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DisplaySingleForm ds = new DisplaySingleForm(shell, 0);
				ds.open();
			}
		});
		btnDisplaySinglePatient.setBounds(108, 226, 254, 35);
		btnDisplaySinglePatient.setText("Display Single Patient BMI");
		
		Button btnDisplayAllPatients = new Button(shell, SWT.NONE);
		btnDisplayAllPatients.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnDisplayAllPatients.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DisplayAllForm da = new DisplayAllForm(shell, 0);
				da.open();
			}
		});
		btnDisplayAllPatients.setBounds(108, 163, 254, 35);
		btnDisplayAllPatients.setText("Display all patient's BMI data");
		
		Button btnEnterInMetric = new Button(shell, SWT.NONE);
		btnEnterInMetric.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnEnterInMetric.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MetricForm m = new MetricForm(shell, 0);
				m.open();
			}
		});
		btnEnterInMetric.setBounds(42, 69, 150, 35);
		btnEnterInMetric.setText("Enter in metric");
		
		Button btnEnterInStandard = new Button(shell, SWT.NONE);
		btnEnterInStandard.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnEnterInStandard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StandardForm s = new StandardForm(shell, 0);
				s.open();
			}
		});
		btnEnterInStandard.setBounds(257, 69, 166, 35);
		btnEnterInStandard.setText("Enter in standard");
		shell.setTabList(new Control[]{btnEnterInMetric, btnEnterInStandard, btnDisplayAllPatients, btnDisplaySinglePatient, btnExit});

	}
}
