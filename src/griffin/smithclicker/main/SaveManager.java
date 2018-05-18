package griffin.smithclicker.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import griffin.smithclicker.objects.upgrades.Upgrade;
import griffin.smithclicker.objects.upgrades.Upgrades;
import griffin.smithclicker.objects.upgrades.mini.Miniupgrade;

public class SaveManager {
	
	public static final String FILE_NAME = "SmithClicker.save";
	
	public static void saveCurrentGameState() {
		try {
			File file = new File(FILE_NAME);
			file.delete();
			FileWriter fw = new FileWriter(file);
			fw.write("Smiths-" + GameManager.getSmiths() + "\n");
			fw.write("Click_Smiths-" + GameManager.getClickSmiths() + "\n");
			fw.write("Total_Clicks-" + GameManager.getTotalClicks() + "\n");
			fw.write("Upgrade_Smiths-" + GameManager.getUpgradeSmiths() + "\n");
			for(Upgrade u : Upgrades.upgrades.getUpgrades()) {
				fw.write(u.getName() + "-" + u.getAmountBought() + "-" + u.getSmithsMade() + "\n");
			}
			for(Miniupgrade u : Upgrades.upgrades.getMiniupgrades()) {
				fw.write("\n");
				fw.write(u.getName() + "-" + u.isBought());
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("Error on saving!");
			e.printStackTrace();
		}
	}
	
	public static void loadFromGameFile() {
		try {
			File file = new File(FILE_NAME);
			if(file.exists()) {
				Scanner s = new Scanner(file);
				while(s.hasNextLine()) {
					String line = s.nextLine();
					String[] split = line.split("-");
					BigInteger b;
					switch(split[0]) {
					case "Smiths":
						b = new BigInteger(split[1]);
						GameManager.getManager().addSmiths(b);
						break;
					case "Click_Smiths":
						b = new BigInteger(split[1]);
						GameManager.getManager().addClickSmiths(b);
						break;
					case "Upgrade_Smiths":
						b = new BigInteger(split[1]);
						GameManager.getManager().addUpgradeSmiths(b);
						break;
					case "Total_Clicks":
						b = new BigInteger(split[1]);
						GameManager.getManager().addClicks(b);
						break;
					default:
						for(Upgrade u : Upgrades.upgrades.getUpgrades()) {
							if(split[0].equals(u.getName())) {
								int bought = Integer.parseInt(split[1]);
								BigInteger smithsMade = new BigInteger(split[2]);
								u.setAmountBought(bought);
								u.setAmountMade(smithsMade);
							}
						}
						for(Miniupgrade u : Upgrades.upgrades.getMiniupgrades()) {
							if(split[0].equals(u.getName())) {
								u.setBought(Boolean.getBoolean(split[1]));
							}
						}
					}
				}
				s.close();
			}
		}catch(IOException e) {
			System.out.println("Error on loading from file!");
			e.printStackTrace();
		}
	}
	
}
