package utils;

import aquality.selenium.elements.interfaces.ILabel;

import java.util.List;

public class CheckBoxesProcessor {

    public static int findUnselectAllCheckBoxIndex(List<ILabel> checkBoxesNames) {

        for (int i = 0; i < checkBoxesNames.size(); i++) {
            String checkBoxText = checkBoxesNames.get(i).getText();
            if (checkBoxText.contains("Unselect all")) {
                return i;
            }
        }

        throw new RuntimeException("No element with given text");
    }

    public static int findSelectAllCheckBoxIndex(List<ILabel> checkBoxesNames) {

        for (int i = 0; i < checkBoxesNames.size(); i++) {
            String checkBoxText = checkBoxesNames.get(i).getText();
            if (checkBoxText.contains("Select all")) {
                return i;
            }
        }

        throw new RuntimeException("No element with given text");
    }

}
