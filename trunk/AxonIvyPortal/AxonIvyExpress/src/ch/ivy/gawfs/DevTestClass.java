package ch.ivy.gawfs;

public class DevTestClass {

	public static void main(String[] args) {

		DragAndDropController dragAndDropController = new DragAndDropController();
		DynaFormController dynaFormController = new DynaFormController(dragAndDropController);
	}

}
