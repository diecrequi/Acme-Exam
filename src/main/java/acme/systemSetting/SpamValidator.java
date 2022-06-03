package acme.systemSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpamValidator {
	
	
	public static Boolean validate(final String txt, final Integer weakSpamThreshold , final Integer strongSpamThreshold , 
		final String weakSpam, final String strongSpam ) {
		

		final List<String> strongSpamList = new ArrayList<String>();
		final List<String> weakSpamList = new ArrayList<String>();

		
		final String[] strongTrim = strongSpam.trim().split(",");
		for(final String s : strongTrim) {
			strongSpamList.add(s);
		}
		
		final String[] weakTrim = weakSpam.trim().split(",");
		for(final String s : weakTrim) {
			weakSpamList.add(s);
		}
		
		
		final String myRegexStrong = SpamValidator.expressions(strongSpamList);
		final String myRegexWeak =  SpamValidator.expressions(weakSpamList);
		
		
		final Double strongSpamPercentage = SpamValidator.percentageSpamInText(myRegexStrong, txt);
		final Double weakSpamPercentage = SpamValidator.percentageSpamInText(myRegexWeak, txt);
		
		

		final Boolean weakValidate = weakSpamPercentage<=(double) weakSpamThreshold;
		final Boolean strongValidate =  strongSpamPercentage<=(double) strongSpamThreshold ;
		
		
		
		
		return weakValidate && strongValidate;
	}
	
	
	private static String expressions(final List<String> spam) {
		final StringBuilder regex =  new StringBuilder();
		
		for(int i= 0; i<spam.size(); i++) {
			final  String word= spam.get(i);
			
			final String[] parts= word.split(" ");
			
			if(parts.length>1) {
				final StringBuilder auxString = new StringBuilder();
				
				for(int j= 0; j<parts.length; j++) {
					if(j== parts.length - 1) {
						auxString.append(parts[j]);
					}else {
						auxString.append(parts[j]+ "[^\\w]*");
					}
				}
				
				if(i == spam.size() - 1) {
					regex.append(auxString.toString());
				}else {
					regex.append(auxString.toString() + "|");
				}
				
				
		
				
				
			} else {
				if (i== spam.size() - 1) {
					regex.append(word);
				}else {
					regex.append(word + "|");
				}
			}
			
		}
		
		return regex.toString();
		
	}
	
	
	
	private static double percentageSpamInText(final String regex, final String txt) {
		
		final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		final Matcher match = pattern.matcher(txt);
		
		final double numberWords = SpamValidator.countWordsText(txt);
		final double matches = SpamValidator.totalMatches(match);
		
		
		return (matches/ numberWords)*100.0;
	}
	
//	private static int countWordsText(final String txt) {
//		final String [] result= Arrays.stream(txt.split(" ")).filter(e->e.trim().length()>0).toArray(String[]:: new);
//		return result.length ;
//	}
	
	private static int countWordsText(final String txt ) {
		return txt.split(" ").length;
	}
	
	
	
	private static int totalMatches(final Matcher match) {
		int res = 0;
		
		while (match.find()) {
			res++;
		}
		
		return res;
	}
	
	
	
	

}
