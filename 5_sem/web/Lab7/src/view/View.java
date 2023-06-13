package view;

import logic.Logic;

/**
 * Class View
 */
public class View {

    public static void main(String[] args) throws Exception {

        Logic logic = new Logic();

        System.out.println("Validation: ");
        System.out.println("XML matches XSD : " + logic.makeValidation() +"\n");

        logic.startReaders();
    }
}
