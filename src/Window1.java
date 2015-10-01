import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

public class Window1 {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window1 window = new Window1();
			window.open();
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
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(789, 555);
		shell.setText("SWT Application");

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.OPEN);
				dialog.setFilterPath("c:\\temp");
				String result1 = dialog.open();
				String result2 = dialog.getFilterPath();
				System.out.println("result1: "+result1+"  result2: "+result2);
			}
		});
		btnNewButton.setBounds(84, 116, 75, 25);
		btnNewButton.setText("New Button");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		table.setBounds(464, 37, 275, 379);
		table.setLinesVisible(true);
		
		TableColumn tc1 = new TableColumn(table, SWT.NONE);
		tc1.setWidth(75);
		tc1.setText("FirstName");
		
		TableColumn tc2 = new TableColumn(table, SWT.NONE);
		tc2.setWidth(100);
		tc2.setText("LastName");
		
		TableColumn tc3 = new TableColumn(table, SWT.NONE);
		tc3.setWidth(100);
		tc3.setText("Address");
		
		Button btnTable = new Button(shell, SWT.NONE);
		btnTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			    TableItem item1 = new TableItem(table, SWT.NONE);
			    item1.setText(new String[] { "Tim", "Hatton", "Kentucky" });
			    TableItem item2 = new TableItem(table, SWT.NONE);
			    item2.setText(new String[] { "Caitlyn", "Warner", "Ohio" });
			    TableItem item3 = new TableItem(table, SWT.NONE);
			    item3.setText(new String[] { "Reese", "Miller", "Ohio" });
			}
		});
		btnTable.setBounds(158, 307, 75, 25);
		btnTable.setText("table");

	}
}
