package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final LottoGame lottoGame;
    private final List<Rank> rankList;
    private static final double BENEFIT_REFERENCE_VALUE = 1;

    public GameResult(LottoGame lottoGame, WinningLotto winningLotto) {
        this.lottoGame = lottoGame;
        this.rankList = calculateRank(lottoGame.getUserLotto(), winningLotto);
    }

    public Map<Rank, Integer> gameResult() {
        Map<Rank, Integer> mappedByRank = new LinkedHashMap<>();
        for (Rank value : Rank.values()) {
            mappedByRank.put(value, (int) rankList.stream().filter(result -> result == value).count());
        }

        mappedByRank.remove(Rank.MISS);
        return Collections.unmodifiableMap(mappedByRank);
    }

    public double calculateBenefit() {
        return rankList.stream().mapToLong(Rank::getWinningMoney).sum() / lottoGame.getDeposit();
    }

    public double referenceValue() {
        return BENEFIT_REFERENCE_VALUE;
    }

    private List<Rank> calculateRank(List<Lotto> userLotto, WinningLotto winningLotto) {
        List<Rank> rankList = new ArrayList<>();
        for (Lotto lotto : userLotto) {
            int countOfMatch = winningLotto.countOfMatchNumber(lotto);
            boolean matchBonus = winningLotto.containsBonusNumber(lotto);

            rankList.add(Rank.getRank(countOfMatch, matchBonus));
        }
        return rankList;
    }
}
