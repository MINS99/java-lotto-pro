package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningStatistics {
	private static final int PERCENT_COEFFICIENT = 100;

	private final Lottos lottos;
	private final LottoNumbers lastWinningNumbers;
	private final LottoNumber bonusNumber;
	private final Money money;
	private final WinningRecords winningRecords;

	private WinningStatistics(Lottos lottos, LottoNumbers lastWinningNumbers, LottoNumber bonusNumber, Money money) {
		this.lottos = lottos;
		this.lastWinningNumbers = lastWinningNumbers;
		this.bonusNumber = bonusNumber;
		this.money = money;
		this.winningRecords = WinningRecords.createDefault();
	}

	public static WinningStatistics createBy(Lottos lottos,
											 LottoNumbers lastWinningNumbers,
											 LottoNumber bonusNumber,
											 Money money) {
		return new WinningStatistics(lottos, lastWinningNumbers, bonusNumber, money);
	}

	public void buildStatistics() {
		for (WinningRank winningRank : WinningRank.getPlaceRanks()) {
			int count = countNumberOfWinningRank(winningRank);
			this.winningRecords.add(WinningRecord.of(winningRank, count));
		}
	}

	public double getRoundedTotalProfitRate() {
		double totalProfitRate = calculateTotalProfitRate();
		return Math.round(totalProfitRate * PERCENT_COEFFICIENT) / (double) PERCENT_COEFFICIENT;
	}

	private int countNumberOfWinningRank(WinningRank winningRank) {
		return (int) this.lottos.getValues()
								.stream()
								.filter(lotto -> isSameWinningRank(lotto, winningRank))
								.count();
	}

	private boolean isSameWinningRank(Lotto lotto, WinningRank winningRank) {
		return winningRank.equals(WinningRank.valueOf(lotto.countWinningNumbers(lastWinningNumbers),
													  lotto.hasBonusNumber(bonusNumber)));
	}

	private double calculateTotalProfitRate() {
		double totalPrizeMoney = (double) calculateTotalPrizeMoney();
		double spentMoney = this.money.getValue();

		return totalPrizeMoney / spentMoney;
	}

	private long calculateTotalPrizeMoney() {
		long totalProfile = 0L;
		for (WinningRecord winningRecord : this.winningRecords.getValues()) {
			totalProfile += winningRecord.getTotalPrizeMoney();
		}

		return totalProfile;
	}

	public WinningRecords getWinningRecords() {
		return winningRecords;
	}
}