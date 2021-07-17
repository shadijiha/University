/**
 *
 */

package com.engin.audio;

import com.engin.logger.*;

import javax.sound.sampled.*;
import java.io.*;

public abstract class AudioPlayer {

	private final String filepath;
	private long currentFrame;
	private Clip clip;
	private String status;
	private final long id;

	private AudioInputStream stream;

	private AudioPlayer(String filepath) {

		this.filepath = filepath;

		try {
			stream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());

			clip = AudioSystem.getClip();

			clip.open(stream);

		} catch (Exception e) {
			Log.error(e);
		}

		this.id = (long) (Math.random() * Long.MAX_VALUE);

	}

	public static AudioPlayer create(String path) {
		return new AudioPlayer(path) {
		};
	}

	// Method to play the audio
	public void play() {
		//start the clip
		clip.start();

		status = "play";
	}

	// Method to pause the audio
	public void pause() {
		if (status.equals("paused")) {
			Log.warn("audio is already paused");
			return;
		}
		this.currentFrame =
				this.clip.getMicrosecondPosition();
		clip.stop();
		status = "paused";
	}

	// Method to resume the audio
	public void resumeAudio() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		if (status.equals("play")) {
			Log.warn("Audio is already " +
					"being played");
			return;
		}
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}

	// Method to restart the audio
	public void restart() throws IOException, LineUnavailableException,
			UnsupportedAudioFileException {
		clip.stop();
		clip.close();
		resetAudioStream();
		currentFrame = 0L;
		clip.setMicrosecondPosition(0);
		this.play();
	}

	// Method to stop the audio
	public void stop() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		currentFrame = 0L;
		clip.stop();
		clip.close();
	}

	// Method to jump over a specific part
	public void jump(long c) throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {
		if (c > 0 && c < clip.getMicrosecondLength()) {
			clip.stop();
			clip.close();
			resetAudioStream();
			currentFrame = c;
			clip.setMicrosecondPosition(c);
			this.play();
		}
	}

	// Method to reset audio stream
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {
		stream = AudioSystem.getAudioInputStream(
				new File(filepath).getAbsoluteFile());
		clip.open(stream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public boolean isPlaying() {
		if (status == null)
			return false;
		return status.equalsIgnoreCase("play");
	}

	public boolean isFinished() {
		if (status == null)
			return false;
		return clip.getMicrosecondLength() == clip.getMicrosecondPosition();
	}

	public final long getId() {
		return this.id;
	}
}
