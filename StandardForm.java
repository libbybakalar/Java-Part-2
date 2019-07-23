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

public class StandardForm extends Dialog{

	protected Object result;
	protected Shell shlStand;
	private Text txtHeight;
	private Text txtWeight;
	private Text txtName;

	/**
	 * Launch the application.
	 * @param parent
	 * @param style
	 */
	
	public StandardForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the window.
	 */
	
	public Object open() {
		createContents();
		shlStand.open();
		shlStand.layout();
		Display display = getParent().getDisplay();
		while (!shlStand.isDisposed()) {
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
		shlStand = new Shell();
		shlStand.setSize(514, 320);
		shlStand.setText("Standard Entry");
		
		Button btnSubmit = new Button(shlStand, SWT.NONE);
		btnSubmit.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int option = 2;
					MainForm.bmiList.add(new BMI(txtName.getText().toLowerCase(), Integer.parseInt(txtWeight.getText()), 
							Integer.parseInt(txtHeight.getText()), option));
				}
				catch(Exception e1){
					MessageBox messageBox = new MessageBox(shlStand, 0);
				    messageBox.setText("Standard Entry Error");
				    messageBox.setMessage("Invalid Standard Entry, values were defaulted");
				    messageBox.open();
				    int option = 2;
				    MainForm.bmiList.add(new BMI("Unknown", 154, 67, option));
				}
			}
		});
		btnSubmit.setText("Submit");
		btnSubmit.setBounds(43, 202, 105, 35);
		
		Label lblHeightInInches = new Label(shlStand, SWT.NONE);
		lblHeightInInches.setText("Height in inches: ");
		lblHeightInInches.setBounds(53, 86, 149, 25);
		
		Label lblWeightInPounds = new Label(shlStand, SWT.NONE);
		lblWeightInPounds.setText("Weight in pounds: ");
		lblWeightInPounds.setBounds(43, 142, 155, 25);
		
		txtHeight = new Text(shlStand, SWT.BORDER);
		txtHeight.setBounds(276, 83, 162, 31);
		
		txtWeight = new Text(shlStand, SWT.BORDER);
		txtWeight.setBounds(276, 139, 162, 31);
		
		txtName = new Text(shlStand, SWT.BORDER);
		txtName.setBounds(276, 29, 162, 31);
		
		Label lblName = new Label(shlStand, SWT.NONE);
		lblName.setText("Name: ");
		lblName.setBounds(85, 32, 63, 25);
		
		Button btnReturn = new Button(shlStand, SWT.NONE);
		btnReturn.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlStand.close();
			}
		});
		btnReturn.setText("Return");
		btnReturn.setBounds(299, 202, 105, 35);
		shlStand.setTabList(new Control[]{txtName, txtHeight, txtWeight, btnSubmit, btnReturn});

	}

}
