import javax.swing.JOptionPane;

// Instead of hard-coding numbers into each of the loops I've used
// variables to keep track of how the arm is to be moved when moving
// blocks.

// This is by no means the only way of achieving the minimum number of
// moves for the robot - it is intended as an example only and it was not
// used as a guide when the assignments were marked. 

class RobotControl {
	private Robot r;

	public RobotControl(Robot r) {
		this.r = r;
		if (Robot.assessment == true) {

			r.speedUp(5);
		}
	}

	public void control(int barHeights[], int blockHeights[]) {

		// sampleControlMechanism(barHeights,blockHeights);

		// controlMechanismForScenarioA(barHeights, blockHeights);
		// controlMechanismForScenarioB(barHeights, blockHeights);
		controlMechanismForScenarioC(barHeights, blockHeights);

	}

	public void sampleControlMechanism(int barHeights[], int blockHeights[]) {
		// Internally the Robot object maintains the value for Robot height(h),
		// arm-width (w) and picker-depth (d).

		// These values are displayed for your convenience
		// These values are initialised as h=2 w=1 and d=0

		// When you call the methods up() or down() h will be changed
		// When you call the methods extend() or contract() w will be changed
		// When you call the methods lower() or raise() d will be changed

		// sample code to get you started
		// Try running this program with obstacle 555555 and blocks of height
		// 2222 (default)
		// It will work for fisrt block only
		// You are free to introduce any other variables

		int h = 2; // Initial height of arm 1
		int w = 1; // Initial width of arm 2
		int d = 0; // Initial depth of arm 3

		int sourceHt = 12;

		// For Parts (a) and (b) assume all four blocks are of the same height
		// For Part (c) you need to compute this from the values stored in the
		// array blockHeights
		// i.e. sourceHt = blockHeights[0] + blockHeights[1] + ... use a loop!

		int targetCol1Ht = 0; // Applicable only for part (c) - Initially empty
		int targetCol2Ht = 0; // Applicable only for part (c) - Initially empty

		// height of block just picked will be 3 for parts A and B
		// For part (c) this value must be extracing the topmost unused value
		// from the array blockHeights

		int blockHt = 3;

		// clearance should be based on the bars, the blocks placed on them,
		// the height of source blocks and the height of current block

		// Initially clearance will be determined by the blocks at source
		// (3+3+3+3=12)
		// as they are higher than any bar and block-height combined

		int clearence = 12;

		// Raise it high enough - assumed max obstacle = 4 < sourceHt

		// this makes sure robot goes high enough to clear any obstacles
		while (h < clearence + 1) {
			// Raising 1
			r.up();

			// Current height of arm1 being incremented by 1
			h++;
		}

		System.out.println("Debug 1: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// this will need to be updated each time a block is dropped off
		int extendAmt = 10;

		// Bring arm 2 to column 10
		while (w < extendAmt) {
			// moving 1 step horizontally
			r.extend();

			// Current width of arm2 being incremented by 1
			w++;
		}

		System.out.println("Debug 2: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// lowering third arm - the amount to lower is based on current height
		// and the top of source blocks

		// the position of the picker (bottom of third arm) is determined by h
		// and d
		while (h - d > sourceHt + 1) {
			// lowering third arm
			r.lower();

			// current depth of arm 3 being incremented
			d++;
		}

		// picking the topmost block
		r.pick();

		// topmost block is assumed to be 3 for parts (a) and (b)
		blockHt = 3;

		// When you pick the top block height of source decreases
		sourceHt -= blockHt;

		// raising third arm all the way until d becomes 0
		while (d > 0) {
			r.raise();
			d--;
		}

		System.out.println("Debug 3: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// decide to put the block on column (extendAmt - contrctAmt)
		int contractAmt = 7;

		// Must be a variable. Initially contract by 3 units to get to column 3
		// where the first bar is placed (from column 10)

		while (contractAmt > 0) {
			r.contract();
			contractAmt--;
		}

		System.out.println("Debug 4: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// You need to lower the third arm so that the block sits just above the
		// bar
		// For part (a) all bars are initially set to 7
		// For Parts (b) and (c) you must extract this value from the array
		// barHeights

		int currentBar = 0;

		// lowering third arm
		while ((h - 1) - d - blockHt > barHeights[currentBar]) {
			r.lower();
			d++;
		}

		System.out.println("Debug 5: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// dropping the block
		r.drop();

		// The height of currentBar increases by block just placed
		barHeights[currentBar] += blockHt;

		// raising the third arm all the way
		while (d > 0) {
			r.raise();
			d--;
		}
		System.out.println("Debug 6: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// This just shows the message at the end of the sample robot run -
		// you don't need to duplicate (or even use) this code in your program.

		/*
		 * JOptionPane.showMessageDialog(null,
		 * "You have moved one block from source " +
		 * "to the first bar position.\n" + "Now you may modify this code or " +
		 * "redesign the program and come up with " +
		 * "your own method of controlling the robot.", "Helper Code Execution",
		 * JOptionPane.INFORMATION_MESSAGE);
		 **/

		// You have moved one block from source to the first bar position.
		// You should be able to get started now.
	}

	public void controlMechanismForScenarioA(int barHeights[], int blockHeights[]) {
		// Bars have fixed height of 7; Blocks have fixed height of 3
		int h = 2; // Initial height of arm 1
		int w = 1; // Initial width of arm 2
		int d = 0; // Initial depth of arm 3

		int sourceHt = 12;

		// For Parts (a) and (b) assume all four blocks are of the same height
		// For Part (c) you need to compute this from the values stored in the
		// array blockHeights
		// i.e. sourceHt = blockHeights[0] + blockHeights[1] + ... use a loop!

		int targetCol1Ht = 0; // Applicable only for part (c) - Initially empty
		int targetCol2Ht = 0; // Applicable only for part (c) - Initially empty

		// height of block just picked will be 3 for parts A and B
		// For part (c) this value must be extracing the topmost unused value
		// from the array blockHeights

		int blockHt = 3;

		// decide to put the block on column (extendAmt - contrctAmt)
		int contractAmt = 7;

		final int SECOND_ARM_WIDTH = 1;

		while (h < sourceHt + 1) {
			// Raising 1
			r.up();

			// Current height of arm1 being incremented by 1
			h++;
		}

		System.out.println("Debug 1: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// this will need to be updated each time a block is dropped off
		int extendAmt = 10;

		// currentBar number 3 - 8
		int currentBar = 0;

		while (sourceHt > 0) {

			// Bring arm 2 to column 10
			while (w < extendAmt) {
				// moving 1 step horizontally
				r.extend();

				// Current width of arm2 being incremented by 1
				w++;
			}

			System.out.println("Debug 2: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			while (h - d > sourceHt + SECOND_ARM_WIDTH) {
				// lowering third arm
				r.lower();

				// current depth of arm 3 being incremented
				d++;
			}

			// picking the topmost block
			r.pick();

			// topmost block is assumed to be 3 for parts (a) and (b)
			blockHt = 3;

			// When you pick the top block height of source decreases
			sourceHt -= blockHt;

			// raising third arm to the optimistic height
			while (d > h - SECOND_ARM_WIDTH - barHeights[currentBar] - blockHt) {
				r.raise();
				d--;
			}

			System.out.println("Debug 3: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			while (contractAmt > 0) {
				r.contract();
				contractAmt--;
				w--;
			}

			// decide the distance need to contract backward next time
			contractAmt = extendAmt - w - 1;

			System.out.println("Debug 4: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			// lowering third arm
			while ((h - 1) - d - blockHt > barHeights[currentBar]) {
				r.lower();
				d++;
			}

			System.out.println("Debug 5: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			// dropping the block
			r.drop();

			// The height of currentBar increases by block just placed
			barHeights[currentBar] += blockHt;

			// raising the third arm all the way
			while (h - d > barHeights[currentBar] + SECOND_ARM_WIDTH) {
				r.raise(); // the function to raise after dropping the block
				d--;
			}
			System.out.println("Debug 6: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			currentBar++;
		}
	}

	public void controlMechanismForScenarioB(int barHeights[], int blockHeights[]) {
		// Bars have various heights; Blocks have fixed height of 3
		int h = 2; // Initial height of arm 1
		int w = 1; // Initial width of arm 2
		int d = 0; // Initial depth of arm 3

		int sourceHt = 12;

		// For Parts (a) and (b) assume all four blocks are of the same height
		// For Part (c) you need to compute this from the values stored in the
		// array blockHeights
		// i.e. sourceHt = blockHeights[0] + blockHeights[1] + ... use a loop!

		int targetCol1Ht = 0; // Applicable only for part (c) - Initially empty
		int targetCol2Ht = 0; // Applicable only for part (c) - Initially empty

		// height of block just picked will be 3 for parts A and B
		// For part (c) this value must be extracing the topmost unused value
		// from the array blockHeights

		int blockHt = 3;

		final int SECOND_ARM_DEPTH = 1;

		// decide to put the block on column (extendAmt - contrctAmt)
		int contractAmt = 7;

		while (h < sourceHt + 1) {
			// Raising 1
			r.up();

			// Current height of arm1 being incremented by 1
			h++;
		}

		System.out.println("Debug 1: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		// this will need to be updated each time a block is dropped off
		int extendAmt = 10;

		// currentBar number 3 - 8
		int currentBar = 0;

		while (sourceHt > 0) {

			// Bring arm 2 to column 10
			while (w < extendAmt) {
				// moving 1 step horizontally
				r.extend();

				// Current width of arm2 being incremented by 1
				w++;
			}

			System.out.println("Debug 2: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			while (h - d > sourceHt + 1) {
				// lowering third arm
				r.lower();

				// current depth of arm 3 being incremented
				d++;
			}

			// picking the topmost block
			r.pick();

			// topmost block is assumed to be 3 for parts (a) and (b)
			blockHt = 3;

			// When you pick the top block height of source decreases
			sourceHt -= blockHt;

			int maxBarHeightBackward = sourceHt;
			for (int pointerBar = currentBar; pointerBar < 6; pointerBar++) {
				if (barHeights[pointerBar] > maxBarHeightBackward) {
					maxBarHeightBackward = barHeights[pointerBar];
				}
			}

			// raising third arm to the optimistic height
			while (h - SECOND_ARM_DEPTH - maxBarHeightBackward < d + blockHt) {
				r.raise();
				d--;
			}

			System.out.println("Debug 3: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			while (contractAmt > 0) {
				r.contract();
				contractAmt--;
				w--;
			}

			// decide the distance need to contract backward next time
			contractAmt = extendAmt - w - 1;

			System.out.println("Debug 4: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			// lowering third arm
			while ((h - 1) - d - blockHt > barHeights[currentBar]) {
				r.lower();
				d++;
			}

			System.out.println("Debug 5: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			// dropping the block
			r.drop();

			// The height of currentBar increases by block just placed
			barHeights[currentBar] += blockHt;

			int maxBarHeightForward = sourceHt;
			for (int pointerBar = currentBar; pointerBar < 6; pointerBar++) {
				if (barHeights[pointerBar] > maxBarHeightForward) {
					maxBarHeightForward = barHeights[pointerBar];
				}
			}
			// raising the third arm by comparing to all the heights afterwards
			if (barHeights[currentBar] < maxBarHeightForward) {
				while (d > h - SECOND_ARM_DEPTH - maxBarHeightForward) {
					r.raise();
					d--;
				}
			}

			System.out.println("Debug 6: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			currentBar++;

		}
	}

	public void controlMechanismForScenarioC(int barHeights[], int blockHeights[]) {
		// Bars have various heights; Blocks have fixed height of 3
		int h = 2; // Initial height of arm 1
		int w = 1; // Initial width of arm 2
		int d = 0; // Initial depth of arm 3

		int targetCol1Ht = 0; // Applicable only for part (c) - Initially empty
		int targetCol2Ht = 0; // Applicable only for part (c) - Initially empty

		int currentBlock = 0;

		// height of block just picked will be 3 for parts A and B
		// For part (c) this value must be extracing the topmost unused value
		// from the array blockHeights

		int blockHt = blockHeights[currentBlock];

		// For Parts (a) and (b) assume all four blocks are of the same height
		// For Part (c) you need to compute this from the values stored in the
		// array blockHeights
		// i.e. sourceHt = blockHeights[0] + blockHeights[1] + ... use a loop!

		int sourceHt = 0;

		// System.out.println("blockHeights.length = " + blockHeights.length);
		while (currentBlock < blockHeights.length) {
			sourceHt += blockHeights[currentBlock];
			currentBlock++;
		}
		// System.out.println("Source Height for Senario C = " + sourceHt);

		// initial lastBlock to count from the top block down to bottom
		currentBlock--;

		while (h < sourceHt + 1) {
			// Raising 1
			r.up();

			// Current height of arm1 being incremented by 1
			h++;
		}

		System.out.println("Debug 1: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

		final int EXTEND_DISTANCE = 10;
		final int SECOND_ARM_DEPTH = 1;

		// currentBar number 3 - 8
		int currentBar = 0;

		while (currentBlock >= 0) {

			blockHt = blockHeights[currentBlock];
			// decide to put the block on column (extendAmt - contrctAmt)
			int contractAmt;
			switch (blockHt) {
			case 1:
				contractAmt = 9; // 9 means 10 - 1 (Column 1)
				break;
			case 2:
				contractAmt = 8; // 8 means 10 - 2 (Column 2)
				break;
			case 3:
				contractAmt = EXTEND_DISTANCE - currentBar - 3; // for blockHt =
																// 3, the Column
																// number is
																// dynamic which
																// is related to
																// currentBar
				break;
			default:
				contractAmt = 9;
				break;
			}

			// Bring arm 2 to column 10
			while (w < EXTEND_DISTANCE) {
				// moving 1 step horizontally
				r.extend();

				// Current width of arm2 being incremented by 1
				w++;
			}

			System.out.println("Debug 2: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			while (h - d > sourceHt + 1) {
				// lowering third arm
				r.lower();

				// current depth of arm 3 being incremented
				d++;
			}

			// picking the topmost block
			r.pick();

			// When you pick the top block height of source decreases
			sourceHt -= blockHt;

			int maxHtRaiseBackward = sourceHt;

			if (blockHt == 1) {
				for (int pointerBar = 0; pointerBar < 6; pointerBar++) {
					if (barHeights[pointerBar] > maxHtRaiseBackward) {
						maxHtRaiseBackward = barHeights[pointerBar];
					}
				}
				if (maxHtRaiseBackward < targetCol2Ht) {
					maxHtRaiseBackward = targetCol2Ht;
				}
				if (maxHtRaiseBackward < targetCol1Ht) {
					maxHtRaiseBackward = targetCol1Ht;
				}
			} else if (blockHt == 2) {
				for (int pointerBar = 0; pointerBar < 6; pointerBar++) {
					if (barHeights[pointerBar] > maxHtRaiseBackward) {
						maxHtRaiseBackward = barHeights[pointerBar];
					}
				}
				if (maxHtRaiseBackward < targetCol2Ht) {
					maxHtRaiseBackward = targetCol2Ht;
				}
			} else if (blockHt == 3) {
				for (int pointerBar = currentBar; pointerBar < 6; pointerBar++) {
					if (barHeights[pointerBar] > maxHtRaiseBackward) {
						maxHtRaiseBackward = barHeights[pointerBar];
					}
				}
			}

			// raising third arm to the optimistic height according to
			// maxHtRaiseBackward
			while (h - SECOND_ARM_DEPTH - maxHtRaiseBackward < d + blockHt) {
				r.raise();
				d--;
			}

			System.out.println("Debug 3: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			while (contractAmt > 0) {
				r.contract();
				contractAmt--;
				w--;
			}

			System.out.println("Debug 4: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			// lowering third arm
			if (blockHt == 1) {
				while ((h - 1) - d - blockHt > targetCol1Ht) {
					r.lower();
					d++;
				}

				// dropping the block
				r.drop();

				// The height of currentBar increases by block just placed
				targetCol1Ht += blockHt;

			} else if (blockHt == 2) {
				while ((h - 1) - d - blockHt > targetCol2Ht) {
					r.lower();
					d++;
				}

				// dropping the block
				r.drop();

				// The height of currentBar increases by block just placed
				targetCol2Ht += blockHt;

			} else if (blockHt == 3) {
				while ((h - 1) - d - blockHt > barHeights[currentBar]) {
					r.lower();
					d++;
				}

				// dropping the block
				r.drop();

				// The height of currentBar increases by block just placed
				barHeights[currentBar] += blockHt;
			}

			System.out.println("Debug 5: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			int maxHtRaiseForward = sourceHt;

			if (blockHt == 1) {
				for (int pointerBar = 0; pointerBar < 6; pointerBar++) {
					if (barHeights[pointerBar] > maxHtRaiseForward) {
						maxHtRaiseForward = barHeights[pointerBar];
					}
				}
				if (maxHtRaiseForward < targetCol2Ht) {
					maxHtRaiseForward = targetCol2Ht;
				}

				// raising the third arm to the optimistic height according to
				// maxHtRaiseForward
				if (targetCol1Ht < maxHtRaiseForward) {
					if (sourceHt > 0) {
						while (d > h - SECOND_ARM_DEPTH - maxHtRaiseForward) {
							r.raise();
							d--;
						}
					}
				}
			} else if (blockHt == 2) {
				for (int pointerBar = 0; pointerBar < 6; pointerBar++) {
					if (barHeights[pointerBar] > maxHtRaiseForward) {
						maxHtRaiseForward = barHeights[pointerBar];
					}
				}

				// raising the third arm to the optimistic height according to
				// maxHtRaiseForward
				if (targetCol2Ht < maxHtRaiseForward) {
					if (sourceHt > 0) {
						while (d > h - SECOND_ARM_DEPTH - maxHtRaiseForward) {
							r.raise();
							d--;
						}
					}
				}
			} else if (blockHt == 3) {
				for (int pointerBar = currentBar; pointerBar < 6; pointerBar++) {
					if (barHeights[pointerBar] > maxHtRaiseForward) {
						maxHtRaiseForward = barHeights[pointerBar];
					}
				}

				// raising the third arm to the optimistic height according to
				// maxHtRaiseForward
				if (barHeights[currentBar] < maxHtRaiseForward) {
					if (sourceHt > 0) {
						while (d > h - SECOND_ARM_DEPTH - maxHtRaiseForward) {
							r.raise();
							d--;
						}
					}
				}
			}

			System.out.println("Debug 6: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);

			// point to the next bar if block height = 3
			if (blockHt == 3) {
				currentBar++;
			}

			// point to the next block
			// topmost block is assumed to be 3 for parts (a) and (b)
			blockHt = blockHeights[currentBlock--];

		}
	}
}
