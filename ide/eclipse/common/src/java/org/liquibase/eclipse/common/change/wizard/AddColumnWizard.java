package org.liquibase.eclipse.common.change.wizard;

import java.sql.Connection;

import liquibase.migrator.change.AddColumnChange;
import liquibase.migrator.change.Change;
import liquibase.migrator.change.ColumnConfig;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.jface.wizard.IWizardPage;
import org.liquibase.eclipse.common.change.wizard.page.AddColumnWizardPage;

public class AddColumnWizard extends BaseRefactorWizard {

	private Table table;

	private Object model;
	private AddColumnWizardPage page1;

	public AddColumnWizard(Database database, Connection connection, Table table) {
		super(database, connection);
		this.table = table;
	}

	@Override
	protected Change[] createChanges() {
		AddColumnChange change = new AddColumnChange();

		change.setTableName(table.getName());

		ColumnConfig columnConfig = new ColumnConfig();
		columnConfig.setName(page1.getColumnName());
		columnConfig.setType(page1.getDataType());
		columnConfig.setDefaultValue(page1.getDefaultValue());

		change.setColumn(columnConfig);

		return new Change[] { change };
	}

	@Override
	protected IWizardPage[] createPages() {
		page1 = new AddColumnWizardPage(table.getName());

		return new IWizardPage[] { page1, };
	}
	
	@Override
	protected void refresh() {
		((JDBCTable)table).refresh();		
	}

}
