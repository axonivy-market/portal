package ch.ivyteam.ivy.project.portal.examples.service;

import java.util.regex.Pattern;

public class PasswordCheckingService {

  public boolean isPasswordStrongEnough(String password) {
    Pattern specialCharPattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Pattern upperCasePattern = Pattern.compile("[A-Z ]");
    Pattern lowerCasePattern = Pattern.compile("[a-z ]");
    Pattern digitCasePattern = Pattern.compile("[0-9 ]");

    return specialCharPattern.matcher(password).find() && upperCasePattern.matcher(password).find()
        && lowerCasePattern.matcher(password).find() && digitCasePattern.matcher(password).find();
  }
}
