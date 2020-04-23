package sn.thecells.command;

public interface ICommand {

	boolean isEnabled();
	boolean isVisible();
	void execute();
	void execute(String parm);
	String getLabel();
	public String[] getParameters();
	
}
