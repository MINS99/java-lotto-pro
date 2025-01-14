package lotto.model;

import java.util.Arrays;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private final int countOfMatch;
    private final int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank getRank(int countOfMatch, boolean matchBonus) {
        Rank rank = Arrays.stream(Rank.values())
                .filter(value -> value.countOfMatch == countOfMatch)
                .findFirst()
                .orElse(Rank.MISS);

        if (rank == Rank.THIRD) {
            return matchBonusCheck(matchBonus);
        }
        return rank;
    }

    private static Rank matchBonusCheck(boolean matchBonus) {
        if (matchBonus) {
            return Rank.SECOND;
        }
        return Rank.THIRD;
    }
}
