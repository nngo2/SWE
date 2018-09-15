package application.gui;

import java.awt.Component;

/**
 * Class Description: Each window that is accessed from another window should implement this interface. This supports navigation back to calling windows.
 */
public interface ParentWindow {
	public void setVisible(boolean b);
	/**
	 * @return
	 * @uml.property  name="parentWindow"
	 */
	public Component getParentWindow();
	/**
	 * @param w
	 * @uml.property  name="parentWindow"
	 */
	public void setParentWindow(Component w);

}
