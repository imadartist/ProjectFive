
/*
 * Programmer: Madison Leyens
 * Class: ProjectZeroMIDIMain
 * Date: 9.15.2020
 * Description: Main Class that holds all unit tests
 */
import processing.core.*;

import java.util.*;

//importing the JMusic stuff
import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;

//import javax.sound.midi.*;

public class ProjectZeroMIDIMain extends PApplet {

	MelodyPlayer player; // play a midi sequence
	MidiFileToNotes midiNotes; // read a midi file
	boolean stopPlayer = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("ProjectZeroMIDIMain"); 

	}

	// setting the window size 
	public void settings() {
		size(1000, 1000);

	}

	// doing all the setup stuff
	public void setup() {
		fill(120, 50, 240);

		// create generators for pitch and rhythm
		ProbabilityGenerator<Integer> pitchGenerator = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rhythmGenerator = new ProbabilityGenerator<Double>();

		MarkovGenerator<Integer> pitchMarkovGenerator = new MarkovGenerator<Integer>();
		MarkovGenerator<Double> rhythmMarkovGenerator = new MarkovGenerator<Double>();

		// returns a url
		String filePath = getPath("mid/gardel_por.mid");
		midiNotes = new MidiFileToNotes(filePath); // creates a new MidiFileToNotes
		midiNotes.setWhichLine(0); // which line to read in --> this object only reads one line (or ie, voice or
									// ie, one instrument)'s worth of data from the file

		// training the generators for pitch and rhythm to get the pitch and rhythm
		// arrays from the MIDI Notes object
		pitchGenerator.train(midiNotes.getPitchArray());
		rhythmGenerator.train(midiNotes.getRhythmArray());

		pitchMarkovGenerator.train(midiNotes.getPitchArray());
		rhythmMarkovGenerator.train(midiNotes.getRhythmArray());

		// enables use to generate and play a MIDI sequence file
		player = new MelodyPlayer(this, 100.0f);
		player.setup();
		//player.setMelody(pitchGenerator.generate(20));
		//player.setRhythm(rhythmGenerator.generate(20));
		player.setMelody(pitchMarkovGenerator.generate(20));
		player.setRhythm(rhythmMarkovGenerator.generate(20));
	}

	public void draw() {

		// start and stop boolean
		if (stopPlayer) {
		player.play(); // play each note in the sequence -- the player will determine whether is time
						// for a note onset
		} 


		textSize(12);
		fill(0, 102, 153);
		text("Press 1 to start Project 1 Unit Test 1 (Print Probability Generator Probabilities)", width / 4,
				height / 4);
		text("Press 2 to start Project 1 Unit Test 2 (Generate & Print a 20-Note Melody using Probability Generator Probabilities)",
				width / 4, height / 4 + 50);
		text("Press 3 to start Project 1 Unit Test 3 (Generate & Print 10,000 20-Note Melodies using Probability Generator Probabilities)",
				width / 4, height / 4 + 100);
		text("Press 4 to start Project 2 Unit Test 1 (Print the Markov Generator Transition Tables for the Test Melody)",
				width / 4, height / 4 + 150);
		text("Press 5 to start Project 2 Unit Test 2 (Generate & Print a 20-Note Melody using Test Melody and Markov Generator)",
				width / 4, height / 4 + 200);
		text("Press 6 to start Project 2 Unit Test 3 (Generate & Print 10,000 20-Note Melodies using Markov Generator)",
				width / 4, height / 4 + 250);
		text("Press 7 to start Project 5 Unit Test 1 (Print the Suffix Trees for 3 Strings and Mary Pitches)",
				width / 4, height / 4 + 300);
		text("Press 8 to start Project 5 Unit Test 0 (Test the AmIaSuffix method in Node)",
				width / 4, height / 4 + 350);


	}

	// this finds the absolute path of a file
	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	// this function is not currently called. you may call this from setup() if you
	// want to test
	// this just plays the midi file -- all of it via your software synth. You will
	// not use this function in upcoming projects
	// but it could be a good debug tool.
	void playMidiFile(String filename) {
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

	// this starts & restarts the melody.
	public void keyPressed() {

		// DECLARING GENERATORS
		// why do I have probability generators declared and training in setup?

		// Project 1 - this calls the Probability Generator class delineated by Pitch
		// and Rhythm
		ProbabilityGenerator<Integer> generatorPitch = new ProbabilityGenerator();
		ProbabilityGenerator<Double> generatorRhythm = new ProbabilityGenerator();

		// Project 2 - declaring Markov Generator array lists for pitches and rhythms
		MarkovGenerator<Integer> mPitches = new MarkovGenerator();
		MarkovGenerator<Double> mRhythms = new MarkovGenerator();

		// Project 3 - declaring Order M Markov Generator array lists for pitches and
		// rhythms;
		OrderM<Integer> pitchesOrderM = new OrderM(2);
		OrderM<Double> rhythmsOrderM = new OrderM(2);
		
		// Project 5 - declaring our PST Tree
		Tree<String> pstTreeOne = new Tree(3,0.1f);
		Tree<String> pstTreeTwo = new Tree(3,0.1f);
		Tree<String> pstTreeThree = new Tree(3, 0.1f);
		Tree<String> pstTreeFour = new Tree(3, 0.1f); //for testing AmIASuffix
		Tree<Integer> pstTreePitches = new Tree (3, 0.1f);

		// MidiNotesMary setup
		MidiFileToNotes midiNotesMary; // read a midi file
		String filePath = getPath("mid/MaryHadALittleLamb.mid"); // returns a url
		midiNotesMary = new MidiFileToNotes(filePath);// creates a new MidiFileToNotes
		midiNotesMary.setWhichLine(0); // which line to read in --> this object only reads one line (or ie, voice or
										// ie, one instrument)'s worth of data from the file

		// TRAINING GENERATORS
		// Project 1 - training the generators for pitch and rhythm to get the pitch and
		// rhythm arrays from the MIDI Notes Mary object
		generatorPitch.train(midiNotesMary.getPitchArray());
		generatorRhythm.train(midiNotesMary.getRhythmArray());

		// Project 2 - training the markov generators for pitch and rhythm to get the
		// pitch and rhythm arrays from the MIDI Notes Mary object
		mPitches.train(midiNotesMary.getPitchArray());
		mRhythms.train(midiNotesMary.getRhythmArray());

		// Project 3 - training the Order M markov generators for pitch and rhythm to get
		// the pitch and rhythm arrays from the MIDI Notes Mary object
		//pitchesOrderM.train(midiNotesMary.getPitchArray());
		//rhythmsOrderM.train(midiNotesMary.getRhythmArray());
		
		// Project 5 - training the Tree generator
		String[] firstList = {"a", "b", "r", "a", "c", "a", "d", "a", "b", "r", "a"};
		ArrayList<String> testOneList = new ArrayList(Arrays.asList(firstList)); 
		
		String[] secondList = {"a","c","a","d","a","a","c","b","d","a"};
		ArrayList<String> testTwoList = new ArrayList(Arrays.asList(secondList)); 
	
		String[] thirdList = {"a","b","c","c","c","d","a","a","d","c","d","a","a","b","c","a","d","a","d"};
		ArrayList<String> testThreeList = new ArrayList(Arrays.asList(thirdList)); 
		
		//for testing amIASuffix
		String[] suffixTestOneList = {"c", "a", "d", "a", "b", "r", "a"};
		ArrayList<String> testOneSuffixList = new ArrayList(Arrays.asList(suffixTestOneList)); 
		
		pstTreeOne.train(testOneList);
		pstTreeTwo.train(testTwoList);
		pstTreeThree.train(testThreeList);
		pstTreeFour.train(testOneSuffixList); //for testing amIASuffix
		pstTreePitches.train(midiNotesMary.getPitchArray());
		
		if (key == ' ') { 
			player.reset();
			stopPlayer = !stopPlayer;
			println("Melody started!");
		} else if (key == '1') {
			// runs Project 1 Unit Test 1 when the user presses "1" by printing both the
			// pitch and rhythm tokens & probabilities using ProbabilityGenerator
			generatorPitch.printProbabilityDistribution("Pitches:");
			generatorRhythm.printProbabilityDistribution("Rhythms:");

		} else if (key == '2') {
			// runs Project 1 Unit Test 2 test when the user presses "2" and calls the
			// generate function to make a melody with 20 notes and then print the pitch and
			// rhythm tokens & probabilities
			System.out.println(generatorPitch.generate(20));
			System.out.println(generatorRhythm.generate(20));

		} else if (key == '3') {
			// runs Project 1 Unit Test 3 when the user presses "3" and probabilities are
			// calculated and printed for melodies of 20 notes 10,000 times
			// creating new probability generators
			ProbabilityGenerator<Integer> probDistPitch = new ProbabilityGenerator();
			ProbabilityGenerator<Double> probDistRhythm = new ProbabilityGenerator();
			for (int i = 0; i < 10000; i++) { // looping through the following 10000 times
				// training the new probability generators on the existing generators generating
				// melodies of 20
				probDistPitch.train(generatorPitch.generate(20));
				probDistRhythm.train(generatorRhythm.generate(20));
			}
			// printing the pitch and rhythm tokens & probability distributions for 10000
			// 20-note melodies
			probDistPitch.printProbabilityDistribution("ProbDist Unit Test 3 Pitches:");
			probDistRhythm.printProbabilityDistribution("ProbDist Unit Test 3 Rhythms:");

		} else if (key == '4') {
			// runs Project 2 Unit Test 1 when the user presses "4" which prints our Markov
			// Generator transition table using Probability Generator initial tokens and
			// Mary Had a Little Lamb
			int initTokenOne = generatorPitch.generate(); // generating using Probability Generator an initial token to
															// use for pitches
			double initTokenTwo = generatorRhythm.generate(); // generating using Probability Generator an initial token
																// to use for rhythms

			mPitches.printTransitionTable("Markov Unit Test 1 Pitches:"); // printing the transition table of the Markov
																			// Generator pitches using Mary Had a Little
																			// Lamb
			mRhythms.printTransitionTable("Markov Unit Test 1 Rhythms:"); // printing the transition table of the Markov
																			// Generator rhythms using Mary Had a Little
																			// Lamb
			
		} else if (key == '5') {
			// runs Project 2 Unit Test 2 when the the user presses "5" which generates &
			// prints one 20-note melody from Mary Had a Little Lamb using our Markov
			// Generator
			System.out.println(mPitches.generate(20));
			System.out.println(mRhythms.generate(20));

		} else if (key == '6') {
			// runs Project 2 Unit Test 3 when the user presses "6" and probabilities are
			// calculated and printed for melodies of 20 notes 10,000 times
			// creating new markov generators
			MarkovGenerator<Integer> markovPitchesTestThree = new MarkovGenerator();
			MarkovGenerator<Double> markovRhythmsTestThree = new MarkovGenerator();

			// training the new probability generators on the existing generators generating
			// melodies of 20
			for (int i = 0; i < 10000; i++) {
				markovPitchesTestThree.train(mPitches.generate(20));
				markovRhythmsTestThree.train(mRhythms.generate(20));
			}
			// printing the pitch and rhythm transition tables for 10000
			// 20-note melodies
			markovPitchesTestThree.printTransitionTable("Markov Unit Test 3 Pitches:");
			markovRhythmsTestThree.printTransitionTable("Markov Unit Test 3 Rhythms:");
		} else if (key == '7') {
			//printing PST trees for different inputs that were trained on
			pstTreeOne.print("abracadabra: PST L=3");
			pstTreeTwo.print("acadaacbda: PST L=3");
			pstTreeThree.print("abcccdaadcdaabcadad: PST L=3");

		} else if (key == '8') {
			//testing amIaSuffix
			Node<String> nodeOne = new Node(testOneList);
			Node<String> nodeTwo = new Node(testTwoList); 
			//the smaller node needs to call amIASuffix
			System.out.println(nodeTwo.amIaSuffix(nodeOne)); //returns false
			
			Node<String> nodeThree = new Node(testOneSuffixList);
			System.out.println(nodeThree.amIaSuffix(nodeOne)); //returns true
		}
}
}
		
	
