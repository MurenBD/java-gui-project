package main;

import com.prosysopc.ua.ApplicationIdentity;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CertificatePopUpController {


    @FXML CheckBox ccPasswordEnableCheckBox;
    @FXML TextArea ccDomainNamesTextArea;
    @FXML TextArea ccIPAddressesTextArea;
    @FXML PasswordField ccPasswordField;
    @FXML PasswordField ccRepeatPasswordField;
    @FXML Button ccCancelButton;
    @FXML Button ccOKButton;
    @FXML ComboBox ccSignatureAlgorithmComboBox;
    @FXML ComboBox ccRSAKeyStrengthComboBox;
    @FXML ComboBox ccCertificateValidityComboBox;
    @FXML TextField ccStateTextField;
    @FXML TextField ccOrganisationUnitTextField;
    @FXML TextField ccOrganisationTextField;
    @FXML TextField ccLocalityTextField;
    @FXML TextField ccCountryTextField;
    @FXML TextField ccCommonNameTextField;
    @FXML TextField ccApplicationURITextField;
    

    public CertificatePopUpController(){

    }

    public void initialize(){
        //ApplicationIdentity
        System.out.println("CertificatePopUpIntialize");
    }

    @FXML private void ccOnOKButtonClicked(Event e){
        System.out.println("ccOnOKButtonClicked");
    }
}
