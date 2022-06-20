package utils;

import aquality.selenium.elements.interfaces.ILabel;

import java.util.List;

public class CheckBoxesProcessor {

    public static int findCheckBoxIndexByName(List<ILabel> checkBoxesNames, String searchedText) {

        for (int i = 0; i < checkBoxesNames.size(); i++) {
            String checkBoxText = checkBoxesNames.get(i).getText();
            if (checkBoxText.contains(searchedText)) {
                return i;
            }
        }
        String exceptionMessage = String.format("No element with given text, expected text: %s", searchedText);
        throw new RuntimeException(exceptionMessage);
    }

}
