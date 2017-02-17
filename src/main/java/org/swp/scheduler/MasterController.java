package org.swp.scheduler;

import org.swp.scheduler.database.models.Section;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MasterController {
	
	private static MasterController INSTANCE = new MasterController();
    private static ObservableList<Section> masterData = FXCollections.observableArrayList();	
	
	private MasterController() {
		
		
	}
	
	public static MasterController getInstance() {
		return INSTANCE;
	}
	
	public void addToData(Section section) {
		masterData.add(section);
	}
	
	public static ObservableList<Section> getMasterData() {
		return masterData;
	}

}
