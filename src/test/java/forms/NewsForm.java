package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsForm extends Form {

    public NewsForm() {
        super(By.className("ui_toggler_label"), "news form");
    }
}
