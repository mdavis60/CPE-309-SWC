package org.swp.scheduler;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.application.*;
import javafx.collections.*;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.*;
import org.swp.scheduler.database.models.*;

import javafx.collections.transformation.*;
import javafx.util.Callback;
import javafx.fxml.FXMLLoader;

@SuppressWarnings("restriction")
public class SectionComponentController extends AnchorPane {
	
	 public SectionComponentController()
	    {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
	    			"/fxml/SectionComponentTemplate.fxml"));
	    	fxmlLoader.setRoot(this);
	        fxmlLoader.setController(this);

	        try {
	            fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }
	    }
}
