package com.ipl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static final int MATCH_ID = 0;
	public static final int SEASON = 1;
	// public static final int CITY=2;
	// public static final int DATE=3;
	// public static final int TEAM1=4;
	// public static final int TEAM2=5;
	public static final int TOSS_WINNER = 6;
	// public static final int TOSS_DECISSION=7;
	// public static final int RESULT=8;
	// public static final int DL_APPLIED=9;
	public static final int WINNER = 10;
	// public static final int WIN_BY_RUNS=11;
	// public static final int WIN_BY_WICKETS=12;
	// public static final int PLYAER_OF_MATCH=13;
	// public static final int VENUE=14;
	// public static final int UMPIRE1=15;
	// public static final int UMPIRE2=16;
	// public static final int UMPIRE3=17;

	public static final int DELIVERY_MATCH_ID = 0;
	public static final int INNIGS = 1;
	public static final int BATTING_TEAM = 2;
	public static final int BOWLING_TEAM = 3;
	public static final int OVER = 4;
	public static final int BALL = 5;
	public static final int BATSMAN = 6;
	public static final int NON_STRIKER = 7;
	public static final int BOWLER = 8;
	public static final int IS_SUPER_OVER = 9;
	public static final int WIDE_RUN = 10;
	public static final int BYE_RUN = 11;
	public static final int LEG_BYE_RUN = 12;
	public static final int NO_BALL = 13;
	public static final int PENALITY_RUNS = 14;
	public static final int BATSMAN_RUNS = 15;
	public static final int EXTRA_RUNS = 16;
	public static final int TOTAL_RUNS = 17;
	public static final int PLAYER_DISMISSED = 18;
	public static final int DISMISSED_KIND = 19;
	public static final int FIELDER = 20;

	static Map<String, Integer> noMatchesPerYear = new HashMap<>();
	static Map<String, Integer> noMatchesOwnPerTeam = new HashMap<>();
	static Map<String, Integer> teamsConscedRuns = new HashMap<>();
	static Map<String, Integer> noTimesTossWonPerTeam = new HashMap<>();
	static Map<String, Integer> bowlerGivenRuns = new HashMap<>();
	static Map<String, Integer> numberOfbowledBalls = new HashMap<>();
	static Map<String, Double> bowlerEconomy = new HashMap<>();

	static List<Integer> matchIds2016 = new ArrayList<>();
	static List<Integer> matchIds2015 = new ArrayList<>();
	static List<Integer> deliveryIds2016 = new ArrayList<>();

	public static void main(String[] args) {
		List<Match> matches = getMatchesData();
		List<Delivery> deliveries = getDeliveriesData();

		findNumberOfMatchesPlayedPerTeam(matches);
		findNumberOfMatchesWonPerTeamInAllSeasons(matches);
		findExtrarunsConcededPerTeamIn2016(matches, deliveries);
		findMostEconomicalBowlerIn2015(matches, deliveries);
		findNumberOfTimesTeamOwnTOss(matches);

	}

	private static List<Delivery> getDeliveriesData() {
		List<Delivery> deliveries = new ArrayList<>();

		String deliveryFilePath = "/home/venky/Desktop/IplProject/deliveries.csv";

		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(deliveryFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			int skipFirstLine = 0;
			while ((line = reader.readLine()) != null) {
				if (skipFirstLine == 0) {
					skipFirstLine = 1;
					continue;
				}
				String[] fields = line.split(",");

				Delivery delivery = new Delivery();

				delivery.setDeliveryMatchId(fields[DELIVERY_MATCH_ID]);
				delivery.setInnings(fields[INNIGS]);
				delivery.setBattingTeam(fields[BATTING_TEAM]);
				delivery.setBowlingTeam(fields[BOWLING_TEAM]);
				// delivery.setOver(fields[OVER]);
				// delivery.setBall(fields[BALL]);
				// delivery.setBatsMan(fields[BATSMAN]);
				// delivery.setNonStriker(fields[NON_STRIKER]);
				delivery.setBowler(fields[BOWLER]);
				// delivery.setIsSuperOver(fields[IS_SUPER_OVER]);
				delivery.setWideRuns(fields[WIDE_RUN]);
				// delivery.setByRuns(fields[BYE_RUN]);
				// delivery.setLegByRuns(fields[LEG_BYE_RUN]);
				delivery.setNoBall(fields[NO_BALL]);
				// delivery.setPenaltyRuns(fields[PENALITY_RUNS]);
				// delivery.setBatsmanRuns(fields[BATSMAN_RUNS]);
				delivery.setExtraRuns(fields[EXTRA_RUNS]);
				delivery.setTotalRuns(fields[TOTAL_RUNS]);
				// delivery.setPlayeDismissed(fields[PLAYER_DISMISSED]);
				// delivery.setDismissedKind(fields[DISMISSED_KIND]);
				// delivery.setFileder(FIELDER);

				deliveries.add(delivery);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return deliveries;
	}

	private static List<Match> getMatchesData() {
		List<Match> matches = new ArrayList<>();

		String matchFilePath = "/home/venky/Desktop/IplProject/matches.csv";

		BufferedReader reader = null;
		String line = "";
		int skipFirstLine = 0;

		try {
			reader = new BufferedReader(new FileReader(matchFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((line = reader.readLine()) != null) {
				if (skipFirstLine == 0) {
					skipFirstLine = 1;
					continue;
				}
				String[] fields = line.split(",");

				Match match = new Match();
				match.setMatchId(fields[MATCH_ID]);
				match.setSeason(fields[SEASON]);
				// match.setCity(fields[CITY]);
				// match.setDate(fields[DATE]);
				// match.setTeam1(fields[TEAM1]);
				// match.setTeam2(fields[TEAM2]);
				match.setTossWinner(fields[TOSS_WINNER]);
				// match.setTossDecission(fields[TOSS_DECISSION]);
				// match.setResult(fields[RESULT]);
				// match.setDlAppalied(fields[DL_APPLIED]);
				match.setWinner(fields[WINNER]);
				// match.setWinByRuns(fields[WIN_BY_RUNS]);
				// match.setWinByWickes(fields[WIN_BY_WICKETS]);
				// match.setPlayerOfMatch(fields[PLYAER_OF_MATCH]);
				// match.setVenue(fields[VENUE]);
				// match.setUmpire1(fields[UMPIRE1]);
				// match.setUmpire2(fields[UMPIRE2]);
				// match.setUmpire3(fields[UMPIRE3]);

				matches.add(match);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return matches;
	}

	private static void findNumberOfMatchesPlayedPerTeam(List<Match> matches) {

		for (Match match : matches) {
			if (noMatchesPerYear.containsKey(match.getSeason())) {
				noMatchesPerYear.put(match.getSeason(), noMatchesPerYear.get(match.getSeason()) + 1);
			} else {

				noMatchesPerYear.put(match.getSeason(), 1);
			}
		}
		 System.out.println("Number of matches played per year: ");
		 System.out.println(noMatchesPerYear);

	}

	private static void findNumberOfMatchesWonPerTeamInAllSeasons(List<Match> matches) {

		for (Match match : matches) {
			if (match.getWinner() != "") {
				if (noMatchesOwnPerTeam.containsKey(match.getWinner())) {
					noMatchesOwnPerTeam.put(match.getWinner(), noMatchesOwnPerTeam.get(match.getWinner()) + 1);
				} else {
					noMatchesOwnPerTeam.put(match.getWinner(), 1);
				}
			}
		}
			
		System.out.println("Number of matches own a team in a all seasons: ");
		 System.out.println(noMatchesOwnPerTeam);

	}

	private static void findExtrarunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {

		for (Match match : matches) {
			if (match.getSeason().equals("2016")) {
				int matchId = Integer.parseInt(match.getMatchId());
				matchIds2016.add(matchId);
			}

		}

		for (Delivery deliver : deliveries) {
			for (Integer matchId : matchIds2016) {
				int index = Integer.parseInt(deliver.getDeliveryMatchId());
				if (matchId == index) {
					int extras = Integer.parseInt(deliver.getExtraRuns());
					if (teamsConscedRuns.containsKey(deliver.getBattingTeam())) {
						teamsConscedRuns.put(deliver.getBattingTeam(),
								teamsConscedRuns.get(deliver.getBattingTeam()) + extras);
					} else {
						teamsConscedRuns.put(deliver.getBattingTeam(), extras);
					}
				}
				;

			}
		}
		System.out.println("Each team consced runs in 2016 season: ");
		 System.out.println(teamsConscedRuns);

	}

	private static void findMostEconomicalBowlerIn2015(List<Match> matches, List<Delivery> deliveries) {

		for (Match match : matches) {
			if (match.getSeason().equals("2015")) {
				int matchId = Integer.parseInt(match.getMatchId());
				matchIds2015.add(matchId);
			}

		}
		for (Delivery deliver : deliveries) {

			int wide = Integer.parseInt(deliver.getWideRuns());
			int noBall = Integer.parseInt(deliver.getNoBall());
			int numBalls = 0;

			if ((wide == 0) && (noBall == 0)) {
				numBalls = 1;
			}
			
			for (Integer matchId : matchIds2015) {
				
				int deliveryId = Integer.parseInt(deliver.getDeliveryMatchId());
				
				if (matchId == deliveryId) {
					
					int totalRuns = Integer.parseInt(deliver.getTotalRuns());
					
					if (bowlerGivenRuns.containsKey(deliver.getBowler()))
					{
						bowlerGivenRuns.put(deliver.getBowler(), bowlerGivenRuns.get(deliver.getBowler()) + totalRuns);
					} 
					else
					{
						bowlerGivenRuns.put(deliver.getBowler(), totalRuns);
					}
					
					if (numberOfbowledBalls.containsKey(deliver.getBowler())) 
					{
						numberOfbowledBalls.put(deliver.getBowler(),
						numberOfbowledBalls.get(deliver.getBowler()) + numBalls);
					}
					else
					{
						numberOfbowledBalls.put(deliver.getBowler(), numBalls);
					}
				}
			}

		}

		for (String bowler : numberOfbowledBalls.keySet()) {
			
			numberOfbowledBalls.put(bowler, numberOfbowledBalls.get(bowler) / 6); 
			bowlerEconomy.put(bowler, 0.0);
			
		}
		for (String bowler : numberOfbowledBalls.keySet()) {
			
			double economy = bowlerGivenRuns.get(bowler) / numberOfbowledBalls.get(bowler);
			bowlerEconomy.put(bowler, economy);
		}
		
		
		Set<Entry<String, Double>> entrySet = bowlerEconomy.entrySet();
		List<Entry<String,Double>> sortingList = new ArrayList<>(entrySet);
		
		Collections.sort(sortingList, new Comparator<Entry<String, Double>>(){

			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
			
		});
		System.out.println("Economy of bowels");
		System.out.println(sortingList);
	}

	private static void findNumberOfTimesTeamOwnTOss(List<Match> matches) {
		
		for (Match match : matches) {
			
			if (noTimesTossWonPerTeam.containsKey(match.getTossWinner())) {
				noTimesTossWonPerTeam.put(match.getTossWinner(), noTimesTossWonPerTeam.get(match.getTossWinner()) + 1);
			} else {
				noTimesTossWonPerTeam.put(match.getTossWinner(), 1);
			}
		}
		 System.out.println("Number of times toss won by each team: ");
		 System.out.println(noTimesTossWonPerTeam);
	}

}
