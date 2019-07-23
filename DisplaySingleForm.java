import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.swt.SWTResourceManager;

public class DisplaySingleForm extends Dialog{

	protected Object result;
	protected Shell shlDisplaySingle;
	private Text txtName;

	/**
	 * Launch the application.
	 * @param parent
	 * @param style
	 */
	
	public DisplaySingleForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the window.
	 */

	public Object open() {
		createContents();
		shlDisplaySingle.open();
		shlDisplaySingle.layout();
		Display display = getParent().getDisplay();
		while (!shlDisplaySingle.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDisplaySingle = new Shell();
		shlDisplaySingle.setSize(708, 314);
		shlDisplaySingle.setText("Display Single Patient");		
		
		Label lblSingleDisplay = new Label(shlDisplaySingle, SWT.NONE);
		lblSingleDisplay.setBounds(40, 100, 559, 80);
		
		Button btnReturn = new Button(shlDisplaySingle, SWT.NONE);
		btnReturn.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDisplaySingle.close();
			}
		});
		btnReturn.setBounds(571, 213, 105, 35);
		btnReturn.setText("Return");
		
		Label lblPatientName = new Label(shlDisplaySingle, SWT.NONE);
		lblPatientName.setBounds(20, 22, 114, 25);
		lblPatientName.setText("Patient Name: ");
		
		txtName = new Text(shlDisplaySingle, SWT.BORDER);
		txtName.setBounds(139, 22, 145, 31);
		
		Label lblStatus = new Label(shlDisplaySingle, SWT.NONE);
		lblStatus.setText("Status: ");
		lblStatus.setBounds(439, 69, 81, 25);
		
		Label lblBMI = new Label(shlDisplaySingle, SWT.NONE);
		lblBMI.setText("BMI:");
		lblBMI.setBounds(364, 69, 39, 25);
		
		Label lblType = new Label(shlDisplaySingle, SWT.NONE);
		lblType.setText("Type: ");
		lblType.setBounds(283, 69, 56, 25);
		
		Label lblWeight = new Label(shlDisplaySingle, SWT.NONE);
		lblWeight.setText("Weight:");
		lblWeight.setBounds(184, 69, 81, 25);
		
		Label lblHeight = new Label(shlDisplaySingle, SWT.NONE);
		lblHeight.setText("Height:");
		lblHeight.setBounds(108, 69, 66, 25);
		
		Label lblName = new Label(shlDisplaySingle, SWT.NONE);
		lblName.setText("Name: ");
		lblName.setBounds(30, 69, 56, 25);
		
		Button btnSearchName = new Button(shlDisplaySingle, SWT.NONE);
		btnSearchName.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnSearchName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BMI var = new BMI();
				try {
					String name = txtName.getText();
					for(int i = 0; i < MainForm.bmiList.size(); i++) {
						var = MainForm.bmiList.get(i);
						if(var.getName().toLowerCase().equals(name)){ 
							break;
						}
					}
					if(var.getName().toLowerCase().equals(name)){ 
						lblSingleDisplay.setText(var.toString());
					}
					else {
						MessageBox messageBox = new MessageBox(shlDisplaySingle, 0);
					    messageBox.setText("Patient name Error");
					    messageBox.setMessage("Patient name not found");
					    messageBox.open();
					}
				}
				catch(Exception exc) {
					MessageBox messageBox = new MessageBox(shlDisplaySingle, 0);
				    messageBox.setText("Patient name Error");
				    messageBox.setMessage("Patient name not found");
				    messageBox.open();
				}				
			}
		});
		btnSearchName.setBounds(309, 20, 123, 35);
		btnSearchName.setText("Search Name");
		shlDisplaySingle.setTabList(new Control[]{txtName, btnSearchName, btnReturn});
	}
}
