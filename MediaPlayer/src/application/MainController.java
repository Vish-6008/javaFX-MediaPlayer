/********************************************************************************************
 *   COPYRIGHT (C) 2024 CREVAVI TECHNOLOGIES PVT LTD
 *   The reproduction, transmission or use of this document/file or its
 *   contents is not permitted without written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *---------------------------------------------------------------------------
 *   Purpose:  MainController.java
 *   Project:  JavaFX Media Player with Volume Control
 *   Platform: Cross-platform (Windows, macOS, Linux)
 *   Compiler: JDK-22
 *   IDE:      Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *	 Version: 2024-03 (4.31.0)
 *   Build id: 20240307-1437
 ********************************************************************************************/



package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MainController implements Initializable {
	@FXML
	private MediaView mv;
	private MediaPlayer mp;
	private Media me;

	@FXML Slider volumeSlider;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*
		 * Below block of code Re-Present to Load and Auto-play the video--
		 * --with Width and Height.
		 */
		String path = new File("src/media/video.mp4").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		mv.setMediaPlayer(mp);
		 
		// mp.setAutoPlay(true);   //Optional if you want u can.
		
		
		DoubleProperty width = mv.fitWidthProperty();
		DoubleProperty height = mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
		volumeSlider.setValue(mp.getVolume() *100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			
			//Below block operates on volume up-to 100.
			@Override
			public void invalidated(Observable arg0) {
				mp.setVolume(volumeSlider.getValue()/100);
				
			}
		});

	}
	//Below block act's as play button 
	public void play(ActionEvent event) {
		mp.play();
	}
	//Below block stop's the video while playing.
	public void pause(ActionEvent event) {
		mp.pause();
	}
	
	//Below block Increase play-back speed.
	public void fast(ActionEvent event) {
		mp.setRate(2);
	}
	//Below block Decrease play-back speed.
	public void slow(ActionEvent event) {
		mp.setRate(.5);
	}
	
	//Below block restarts from Initial onwards.
	public void start(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
	}
	//Below block will take's directly end of the video.
	public void last(ActionEvent event) {
		mp.seek(mp.getTotalDuration());
	}
}
