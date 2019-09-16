package seng202.teamsix.GUI;

import com.sun.xml.bind.XmlAccessorFactory;
import com.sun.xml.fastinfoset.algorithm.UUIDEncodingAlgorithm;
import javafx.beans.NamedArg;
import javafx.scene.control.Button;
import seng202.teamsix.data.UUID_Entity;

public class SmartButton extends Button {

    String entity;

    public SmartButton(@NamedArg("entity") String entity) {
        super();
        this.entity = entity;
    }
}
