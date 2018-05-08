package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;

import edu.vt.middleware.password.AlphabeticalSequenceRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.NumericalSequenceRule;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.QwertySequenceRule;
import edu.vt.middleware.password.RepeatCharacterRegexRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.UppercaseCharacterRule;
import edu.vt.middleware.password.WhitespaceRule;

public class PasswordValidationUtils {
	
  private PasswordValidationUtils() {}
  
	public static PasswordValidator createPasswordValidator() {

		CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();
		charRule.getRules().add(new DigitCharacterRule(1));
		charRule.getRules().add(new NonAlphanumericCharacterRule(1));
		charRule.getRules().add(new UppercaseCharacterRule(1));
		charRule.getRules().add(new LowercaseCharacterRule(1));
		charRule.setNumberOfCharacteristics(3);

		List<Rule> ruleList = new ArrayList<>();
		ruleList.add(new LengthRule(8, 16));
		ruleList.add(new WhitespaceRule());
		ruleList.add(charRule);
		ruleList.add(new AlphabeticalSequenceRule());
		ruleList.add(new NumericalSequenceRule(3, true));
		ruleList.add(new QwertySequenceRule());
		ruleList.add(new RepeatCharacterRegexRule(4));
		
		return new PasswordValidator(ruleList);
	}
}
