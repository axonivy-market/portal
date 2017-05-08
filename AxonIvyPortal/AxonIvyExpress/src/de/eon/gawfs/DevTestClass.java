package de.eon.gawfs;

public class DevTestClass {

	public static void main(String[] args) {

		DragAndDropController dragAndDropController = new DragAndDropController();
		DynaFormController dynaFormController = new DynaFormController(dragAndDropController);
	}

}
