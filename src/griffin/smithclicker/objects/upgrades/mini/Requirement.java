package griffin.smithclicker.objects.upgrades.mini;

import java.math.BigInteger;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.upgrades.Upgrade;
import griffin.smithclicker.util.StringUtils;

public abstract class Requirement {

	public abstract boolean checkCompleted();
	
	@Override
	public String toString() {
		return "Requirement";
	}
	
	/**
	 * 
	 * @author Griffin
	 * Checks the current amount of smiths to the cost requirement
	 */
	public static class CostReq extends Requirement{
		
		private BigInteger cost;
		
		public CostReq(BigInteger cost) {
			this.cost = cost;
		}
		
		@Override
		public boolean checkCompleted() {
			return GameManager.getTotalSmiths().compareTo(cost) >= 0;
		}
		
		@Override
		public String toString() {
			return "Has produced " + StringUtils.formatNumber(cost) + " smiths";
		}
		
	}
	
	/**
	 * 
	 * @author Griffin
	 * Checks how many smiths a specific upgrade has produced
	 */
	public static class UpgradeGainedReq extends Requirement{
		
		private Upgrade upgrade;
		private BigInteger amount;
		
		public UpgradeGainedReq(Upgrade upgrade, BigInteger amount) {
			this.upgrade = upgrade;
			this.amount = amount;
		}
		
		@Override
		public boolean checkCompleted() {
			return upgrade.getSmithsMade().compareTo(amount) >= 0;
		}
		
		@Override
		public String toString() {
			return "Upgrade " + upgrade.getName() + " has produced at least " + StringUtils.formatNumber(amount) + "  smiths";
		}
		
	}

	/**
	 * 
	 * @author Griffin
	 * Checks how many clicks have been created
	 */
	public static class ClickReq extends Requirement{
		
		private BigInteger clicks;
		
		public ClickReq(BigInteger clicks) {
			this.clicks = clicks;
		}
		
		@Override
		public boolean checkCompleted() {
			return GameManager.getTotalClicks().compareTo(clicks) >= 0;
		}
		
		@Override
		public String toString() {
			return "Clicked at least " + StringUtils.formatNumber(clicks) + " times";
		}
		
	}
	
}
