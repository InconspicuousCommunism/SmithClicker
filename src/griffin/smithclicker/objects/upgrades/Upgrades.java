package griffin.smithclicker.objects.upgrades;

import java.awt.Color;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import griffin.smithclicker.objects.upgrades.mini.ClickMU;
import griffin.smithclicker.objects.upgrades.mini.IncreaseType;
import griffin.smithclicker.objects.upgrades.mini.Miniupgrade;
import griffin.smithclicker.objects.upgrades.mini.Requirement;
import griffin.smithclicker.objects.upgrades.mini.UpgradeMU;
import griffin.smithclicker.util.ImageHelper;

public class Upgrades {
	
	public static Upgrades upgrades = new Upgrades();
	
	private ArrayList<Upgrade> upgradeList;
	private ArrayList<Miniupgrade> miniupgradeList;
	
	private Upgrade hemanSword;
	private Upgrade skeletor;
	private Upgrade cryptoFarm;
	private Upgrade basicMath;
	private Upgrade extraCredit;
	private Upgrade kyleCarry;
	private Upgrade mommyHat;
	private Upgrade parentEmail;
	private Upgrade poopVariables;
	private Upgrade theBigP;
	private Upgrade ticklingBoys;
	private Upgrade whiteBoard;
	
	public Upgrades() {
		upgradeList = new ArrayList<Upgrade>();
		miniupgradeList = new ArrayList<Miniupgrade>();
		//                          id,                         img,                       baseCost,                            costExp, baseMult, cps,               clickIncrease, name, (if music) sound
		hemanSword    = new Upgrade(0, ImageHelper.loadImage("/icons/hemansword.png"),    10,                                        2, 125, 0,                             1,     "Heman Sword");
		skeletor      = new MusicUpgrade(1, ImageHelper.loadImage("/icons/skeletor.png"), 100,                                       2, 125, 1,                             0,     "Skeletor", "heman.wav");
		cryptoFarm    = new Upgrade(2, ImageHelper.loadImage("/icons/crypto.jpg"),        10000,                                     3, 125, 10,                            0,     "Cryptocurrency Farm");
		basicMath     = new Upgrade(3, ImageHelper.loadImage("/icons/basicmath.jpg"),     1000000,                                   3, 125, 100,                           0,     "Basic Math");
		extraCredit   = new Upgrade(4, ImageHelper.loadImage("/icons/extracredit.gif"),   100000000,                                 4, 125, 1000,                          0,     "Extra Credit");
		kyleCarry     = new Upgrade(5, ImageHelper.loadImage("/icons/kyle.jpg"),          new BigInteger("10000000000"),             4, 125, 10000,                         0,     "Kyle Carry");
		mommyHat      = new Upgrade(6, ImageHelper.loadImage("/icons/mommyhat.JPG"),      new BigInteger("1000000000000"),           5, 125, 100000,                        0,     "Mommy Hat");
		parentEmail   = new Upgrade(7, ImageHelper.loadImage("/icons/email.png"),         new BigInteger("100000000000000"),         5, 125, 1000000,                       0,     "Parent Email");
		poopVariables = new Upgrade(8, ImageHelper.loadImage("/icons/poopvariable.jpg"),  new BigInteger("10000000000000000"),       6, 125, 10000000,                      0,     "Poop Variables");
		theBigP       = new Upgrade(9, ImageHelper.loadImage("/icons/thebigp.jpg"),       new BigInteger("1000000000000000000"),     6, 125, 100000000,                     0,     "The Big P");
		ticklingBoys  = new Upgrade(10, ImageHelper.loadImage("/icons/tickling.jpg"),     new BigInteger("100000000000000000000"),   7, 125, 1000000000,                    0,     "Tickling Boys");
		whiteBoard    = new Upgrade(11, ImageHelper.loadImage("/icons/whiteboard.JPG"),   new BigInteger("10000000000000000000000"), 7, 125, new BigInteger("10000000000"), 10000, "White Board");
		
		upgradeList.add(hemanSword);
		upgradeList.add(skeletor);
		upgradeList.add(cryptoFarm);
		upgradeList.add(basicMath);
		upgradeList.add(extraCredit);
		upgradeList.add(kyleCarry);
		upgradeList.add(mommyHat);
		upgradeList.add(parentEmail);
		upgradeList.add(poopVariables);
		upgradeList.add(theBigP);
		upgradeList.add(ticklingBoys);
		upgradeList.add(whiteBoard);
		
		//Miniupgrades
		initMinis();
		
		
		
		for(int i = 0; i < miniupgradeList.size(); i++) {
			miniupgradeList.get(i).changeListPosition(i);
		}
	}
	
	public ArrayList<Upgrade> getUpgrades(){
		return upgradeList;
	}
	
	public ArrayList<Miniupgrade> getMiniupgrades(){
		return miniupgradeList;
	}
	
	public void reorderMinis() {
		int pos = 0;
		for(Miniupgrade m : miniupgradeList) {
			if(!m.isBought()) {
				m.changeListPosition(pos);
				pos++;
			}
		}
	}
	
	public void orderMinis() {
		int pos = 0;
		ArrayList<Miniupgrade> upList = new ArrayList<Miniupgrade>();
		for(Miniupgrade m : miniupgradeList) {
			if(m.canBeBought()) {
				m.changeListPosition(pos);
				upList.add(m);
				pos++;
			}
		}
		for(Miniupgrade m : miniupgradeList) {
			if(!m.canBeBought()) {
				m.changeListPosition(pos);
				upList.add(m);
				pos++;
			}
		}
		this.miniupgradeList = upList;
	}
	
	public void initMinis() {
		//Heman Sword
		miniupgradeList.add(new UpgradeMU("Sharpened Edge", "Doubles The Effectiveness of the Heman Sword", new BigInteger("10"),
				ImageHelper.loadImage("/icons/hemansword.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				hemanSword, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Double Edged Sword", "Doubles The Effectiveness of the Heman Sword", new BigInteger("1000"),
				ImageHelper.loadImage("/icons/hemansword.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				hemanSword, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("What's going on?", "Doubles The Effectiveness of the Heman Sword", new BigInteger("100000"),
				ImageHelper.loadImage("/icons/hemansword.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				hemanSword, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("I said HEYEAYEAYEA", "Doubles The Effectiveness of the Heman Sword", new BigInteger("10000000"),
				ImageHelper.loadImage("/icons/hemansword.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				hemanSword, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Godly Heman", "Doubles The Effectiveness of the Heman Sword", new BigInteger("1000000000"),
				ImageHelper.loadImage("/icons/hemansword.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				hemanSword, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
				
		//Skeletor
		miniupgradeList.add(new UpgradeMU("Stong Bones", "Doubles The Effectiveness of Skeletor",
				new BigInteger("1000"),
				ImageHelper.loadImage("/icons/skeletor.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				skeletor, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Milk", "Doubles The Effectiveness of Skeletor",
				new BigInteger("100000"),
				ImageHelper.loadImage("/icons/skeletor.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				skeletor, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Heman's Worst Nightmare", "Doubles The Effectiveness of Skeletor",
				new BigInteger("10000000"),
				ImageHelper.loadImage("/icons/skeletor.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				skeletor, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Kingdom Takeover", "Doubles The Effectiveness of Skeletor",
				new BigInteger("1000000000"),
				ImageHelper.loadImage("/icons/skeletor.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				skeletor, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Lord Skeletor", "Doubles The Effectiveness of Skeletor",
				new BigInteger("100000000000"),
				ImageHelper.loadImage("/icons/skeletor.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				skeletor, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
				
		//Crypto Farm
		miniupgradeList.add(new UpgradeMU("Spatial Upgrade", "Doubles The Effectiveness of Crypto Farms",
				new BigInteger("100000"),
				ImageHelper.loadImage("/icons/crypto.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				cryptoFarm, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Increased Graphics", "Doubles The Effectiveness of Crypto Farms",
				new BigInteger("10000000"),
				ImageHelper.loadImage("/icons/crypto.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				cryptoFarm, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Enhanced Setup", "Doubles The Effectiveness of Crypto Farms",
				new BigInteger("10000000"),
				ImageHelper.loadImage("/icons/crypto.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				cryptoFarm, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Dual GPUs", "Doubles The Effectiveness of Crypto Farms",
				new BigInteger("1000000000"),
				ImageHelper.loadImage("/icons/crypto.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				cryptoFarm, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Golden Crypto Farms", "Doubles The Effectiveness of Crypto Farms",
				new BigInteger("10000000000"),
				ImageHelper.loadImage("/icons/crypto.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				cryptoFarm, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//Basic Math
		miniupgradeList.add(new UpgradeMU("Average 5 Numbers By Diving By 2", "Doubles The Effectiveness of Basic Math",
				new BigInteger("10000000"),
				ImageHelper.loadImage("/icons/basicmath.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				basicMath, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("3/2 = 1", "Doubles The Effectiveness of Basic Math",
				new BigInteger("1000000000"),
				ImageHelper.loadImage("/icons/basicmath.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				basicMath, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Prime Number Means Not Divisible By 2", "Doubles The Effectiveness of Basic Math",
				new BigInteger("100000000000"),
				ImageHelper.loadImage("/icons/basicmath.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				basicMath, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("How Do I add 5 Numbers", "Doubles The Effectiveness of Basic Math",
				new BigInteger("10000000000000"),
				ImageHelper.loadImage("/icons/basicmath.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				basicMath, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Two Years Ahead", "Doubles The Effectiveness of Basic Math",
				new BigInteger("1000000000000000"),
				ImageHelper.loadImage("/icons/basicmath.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				basicMath, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//Extra Credit
		miniupgradeList.add(new UpgradeMU("Doing None Of the Labs", "Doubles The Effectiveness of Extra Credit",
				new BigInteger("1000000000"),
				ImageHelper.loadImage("/icons/extracredit.gif"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				extraCredit, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Failing Every Test", "Doubles The Effectiveness of Extra Credit",
				new BigInteger("100000000000"),
				ImageHelper.loadImage("/icons/extracredit.gif"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				extraCredit, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Not Installing Java At Home", "Doubles The Effectiveness of Extra Credit",
				new BigInteger("10000000000000"),
				ImageHelper.loadImage("/icons/extracredit.gif"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				extraCredit, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Playing On Phone", "Doubles The Effectiveness of Extra Credit",
				new BigInteger("1000000000000000"),
				ImageHelper.loadImage("/icons/extracredit.gif"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				extraCredit, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Asking For Extra Credit During Finals", "Doubles The Effectiveness of Extra Credit",
				new BigInteger("100000000000000000"),
				ImageHelper.loadImage("/icons/extracredit.gif"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				extraCredit, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//Kyle Carry
		miniupgradeList.add(new UpgradeMU("Helping Out The Team", "Doubles The Effectiveness of Kyle",
				new BigInteger("100000000000"),
				ImageHelper.loadImage("/icons/kyle.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				kyleCarry, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Doing Every Printout", "Doubles The Effectiveness of Kyle",
				new BigInteger("10000000000000"),
				ImageHelper.loadImage("/icons/kyle.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				kyleCarry, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Carrying The Team", "Doubles The Effectiveness of Kyle",
				new BigInteger("100000000000000000"),
				ImageHelper.loadImage("/icons/kyle.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				kyleCarry, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Stealing Printouts From Grayson's Whiteboard", "Doubles The Effectiveness of Kyle",
				new BigInteger("10000000000000000000"),
				ImageHelper.loadImage("/icons/kyle.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000")), new Requirement.UpgradeGainedReq(whiteBoard, new BigInteger("1"))}),
				kyleCarry, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Chicken Juice", "Doubles The Effectiveness of Kyle",
				new BigInteger("1000000000000000000000"),
				ImageHelper.loadImage("/icons/kyle.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				kyleCarry, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//Mommy Hat
		miniupgradeList.add(new UpgradeMU("I'm Not Your Mom", "Doubles The Effectiveness of the Mommy Hat",
				new BigInteger("10000000000000"),
				ImageHelper.loadImage("/icons/mommyhat.JPG"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				mommyHat, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Looks like Someone Needs Mommy To Help Them", "Doubles The Effectiveness of the Mommy Hat",
				new BigInteger("1000000000000000"),
				ImageHelper.loadImage("/icons/mommyhat.JPG"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				mommyHat, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Mommy's Sending Emails", "Doubles The Effectiveness of the Mommy Hat",
				new BigInteger("100000000000000000"),
				ImageHelper.loadImage("/icons/mommyhat.JPG"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				mommyHat, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Do I Look Like Your Mom?", "Doubles The Effectiveness of the Mommy Hat",
				new BigInteger("10000000000000000000"),
				ImageHelper.loadImage("/icons/mommyhat.JPG"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				mommyHat, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("This Is A Problem For The Mommy Hat", "Doubles The Effectiveness of the Mommy Hat",
				new BigInteger("1000000000000000000000"),
				ImageHelper.loadImage("/icons/mommyhat.JPG"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				mommyHat, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//Parent Email
		miniupgradeList.add(new UpgradeMU("My Kid Is Special", "Doubles The Effectiveness of Parent Emails",
				new BigInteger("1000000000000000"),
				ImageHelper.loadImage("/icons/email.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				parentEmail, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Why Is He Failing?", "Doubles The Effectiveness of Parent Emails",
				new BigInteger("100000000000000000"),
				ImageHelper.loadImage("/icons/email.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				parentEmail, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("You're Failing My Kid!", "Doubles The Effectiveness of Parent Emails",
				new BigInteger("10000000000000000000"),
				ImageHelper.loadImage("/icons/email.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				parentEmail, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Why Aren't You Teaching?", "Doubles The Effectiveness of Parent Emails",
				new BigInteger("1000000000000000000000"),
				ImageHelper.loadImage("/icons/email.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				parentEmail, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("My Kid Is GOLDEN", "Doubles The Effectiveness of Parent Emails",
				new BigInteger("100000000000000000000000"),
				ImageHelper.loadImage("/icons/email.png"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				parentEmail, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//Poop Variables
		miniupgradeList.add(new UpgradeMU("Poop", "Doubles The Effectiveness of Poop Variables",
				new BigInteger("100000000000000000"),
				ImageHelper.loadImage("/icons/poopvariable.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				poopVariables, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Int Poop", "Doubles The Effectiveness of Poop Variables",
				new BigInteger("10000000000000000000"),
				ImageHelper.loadImage("/icons/poopvariable.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				poopVariables, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("ArrayList Poop", "Doubles The Effectiveness of Poop Variables",
				new BigInteger("1000000000000000000000"),
				ImageHelper.loadImage("/icons/poopvariable.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				poopVariables, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Poop[]", "Doubles The Effectiveness of Poop Variables",
				new BigInteger("100000000000000000000000"),
				ImageHelper.loadImage("/icons/poopvariable.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				poopVariables, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Poop[][]", "Doubles The Effectiveness of Poop Variables",
				new BigInteger("10000000000000000000000000"),
				ImageHelper.loadImage("/icons/poopvariable.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				poopVariables, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//The Big P
		miniupgradeList.add(new UpgradeMU("Blue Pelican Java", "Doubles The Effectiveness of Blue Pelican Java",
				new BigInteger("10000000000000000000"),
				ImageHelper.loadImage("/icons/thebigp.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				theBigP, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("The Pelican", "Doubles The Effectiveness of Blue Pelican Java",
				new BigInteger("1000000000000000000000"),
				ImageHelper.loadImage("/icons/thebigp.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				theBigP, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("The Pelly", "Doubles The Effectiveness of Blue Pelican Java",
				new BigInteger("100000000000000000000000"),
				ImageHelper.loadImage("/icons/thebigp.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				theBigP, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("The Big P", "Doubles The Effectiveness of Blue Pelican Java",
				new BigInteger("10000000000000000000000000"),
				ImageHelper.loadImage("/icons/thebigp.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				theBigP, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("The P", "Doubles The Effectiveness of Blue Pelican Java",
				new BigInteger("1000000000000000000000000000"),
				ImageHelper.loadImage("/icons/thebigp.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				theBigP, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//Tickling Boys
		miniupgradeList.add(new UpgradeMU("Stop Tickling", "Doubles The Effectiveness of Tickling",
				new BigInteger("1000000000000000000000"),
				ImageHelper.loadImage("/icons/tickling.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				ticklingBoys, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Stop Tickling", "Doubles The Effectiveness of Tickling",
				new BigInteger("100000000000000000000000"),
				ImageHelper.loadImage("/icons/tickling.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				ticklingBoys, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Really, Just Stop", "Doubles The Effectiveness of Tickling",
				new BigInteger("10000000000000000000000000"),
				ImageHelper.loadImage("/icons/tickling.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				ticklingBoys, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("What Are You Doing?", "Doubles The Effectiveness of Tickling",
				new BigInteger("1000000000000000000000000000"),
				ImageHelper.loadImage("/icons/tickling.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				ticklingBoys, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Again The Boys Are Tickling", "Doubles The Effectiveness of Tickling",
				new BigInteger("100000000000000000000000000000"),
				ImageHelper.loadImage("/icons/tickling.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				ticklingBoys, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
		
		//White board
		miniupgradeList.add(new UpgradeMU("Enhanced Whiteboard", "Doubles The Effectiveness of Whiteboards",
				new BigInteger("100000000000000000000000"),
				ImageHelper.loadImage("/icons/whiteboard.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100"))}),
				whiteBoard, IncreaseType.multiply_add,  new BigInteger("2"), Color.blue));
		miniupgradeList.add(new UpgradeMU("Rolling Whiteboard", "Doubles The Effectiveness of Whiteboards",
				new BigInteger("10000000000000000000000000"),
				ImageHelper.loadImage("/icons/whiteboard.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000"))}),
				whiteBoard, IncreaseType.multiply_add,  new BigInteger("2"), Color.yellow));
		miniupgradeList.add(new UpgradeMU("Wall Whiteboard", "Doubles The Effectiveness of Whiteboards",
				new BigInteger("1000000000000000000000000000"),
				ImageHelper.loadImage("/icons/whiteboard.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("1000000"))}),
				whiteBoard, IncreaseType.multiply_add,  new BigInteger("2"), Color.red));
		miniupgradeList.add(new UpgradeMU("Cheating Off Whiteboards", "Doubles The Effectiveness of Whiteboards",
				new BigInteger("100000000000000000000000000000"),
				ImageHelper.loadImage("/icons/whiteboard.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("100000000"))}),
				whiteBoard, IncreaseType.multiply_add,  new BigInteger("2"), Color.magenta));
		miniupgradeList.add(new UpgradeMU("Whiteboard IDE", "Doubles The Effectiveness of Whiteboards",
				new BigInteger("10000000000000000000000000000000"),
				ImageHelper.loadImage("/icons/whiteboard.jpg"), Arrays.asList(new Requirement[] {new Requirement.CostReq(new BigInteger("10000000000"))}),
				whiteBoard, IncreaseType.multiply_add,  new BigInteger("2"), Color.white));
	}

}
