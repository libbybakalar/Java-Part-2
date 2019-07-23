import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;


public class DisplayAllForm extends Dialog{

	protected Object result;
	protected Shell shlDisplayAll;
	private Text txtData;

	/**
	 * Creates the dialog.
	 * @param parent
	 * @param style
	 */
	
	public DisplayAllForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	
	public Object open() {
		createContents();
		shlDisplayAll.open();
		shlDisplayAll.layout();
		Display display = getParent().getDisplay();
		while (!shlDisplayAll.isDisposed()) {
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
		shlDisplayAll = new Shell();
		shlDisplayAll.setSize(638, 496);
		shlDisplayAll.setText("Display All Patients");
				
		String str = "";
		for(BMI b : MainForm.bmiList) {
        	str += b.toString() + "\n";
		}
		
		Label label = new Label(shlDisplayAll, SWT.NONE);
		label.setBounds(41, 71, 553, 307);
		
		label.setText(str);
		
		Button btnReturn = new Button(shlDisplayAll, SWT.NONE);
		btnReturn.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDisplayAll.close();
			}
		});
		btnReturn.setText("Return");
		btnReturn.setBounds(501, 395, 105, 35);
		
		Label lblName = new Label(shlDisplayAll, SWT.NONE);
		lblName.setBounds(36, 29, 56, 25);
		lblName.setText("Name: ");
		
		Label lblHeight = new Label(shlDisplayAll, SWT.NONE);
		lblHeight.setBounds(114, 29, 66, 25);
		lblHeight.setText("Height:");
		
		Label lblWeight = new Label(shlDisplayAll, SWT.NONE);
		lblWeight.setBounds(190, 29, 81, 25);
		lblWeight.setText("Weight:");
		
		Label lblType = new Label(shlDisplayAll, SWT.NONE);
		lblType.setBounds(289, 29, 56, 25);
		lblType.setText("Type: ");
		
		Label lblBmi = new Label(shlDisplayAll, SWT.NONE);
		lblBmi.setBounds(370, 29, 39, 25);
		lblBmi.setText("BMI:");
		
		Label lblStatus = new Label(shlDisplayAll, SWT.NONE);
		lblStatus.setBounds(465, 29, 81, 25);
		lblStatus.setText("Status: ");

	}

}
