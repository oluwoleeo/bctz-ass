package com.wole;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private String username;
    private String email;
    private String password;
    private String dateOfBirth;

    private final List<ValidationException> validationExceptions = new ArrayList<>(4);

    public void readInputs(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter username: ");
        username = input.next();

        System.out.print("Enter email: ");
        email = input.next();

        System.out.print("Enter password: ");
        password = input.next();

        System.out.print("Enter date of birth (DD/MM/YYYY): ");
        dateOfBirth = input.next();
    };

    public String validateInput(){
        String validationResult;

        CompletableFuture<Boolean> isValidUsernameTask = CompletableFuture.supplyAsync(() -> isValidUsername(username));
        CompletableFuture<Boolean> isValidEmailTask = CompletableFuture.supplyAsync(() -> isValidEmail(email));
        CompletableFuture<Boolean> isValidPasswordTask = CompletableFuture.supplyAsync(() -> isValidPassword(password));
        CompletableFuture<Boolean> isValidDateTask = CompletableFuture.supplyAsync(() -> isValidDate(dateOfBirth));

        CompletableFuture.allOf(isValidUsernameTask, isValidEmailTask, isValidPasswordTask, isValidDateTask).join();


        if (!validationExceptions.isEmpty()){
            StringBuilder sb = new StringBuilder();

            for (ValidationException validationException: validationExceptions) {
                sb.append(validationException.getField()).append(validationException.getDescription());
                sb.append("\n");
            }
            validationResult = sb.toString();
        }
        else{
            validationResult = "Validations passed";
        }

        return validationResult;
    }

    public boolean isValidUsername(String toValidate){
        ValidationException ex;

        if (!toValidate.isBlank()){
            if (toValidate.length() >= 4){
                return true;
            }
            ex = new ValidationException("Username ", "below 4 digits");
        }
        else {
            ex = new ValidationException("Username ", "is blank");
        }

        validationExceptions.add(ex);
        return false;
    }

    public boolean isValidEmail(String toValidate){
        ValidationException ex;

        if (!toValidate.isBlank()){
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(toValidate);

            boolean isValid = matcher.matches();

            if (!isValid){
                ex = new ValidationException("Email ", "not valid");
            }
            else{
                return isValid;
            }
        }
        else {
            ex = new ValidationException("Email ", "is blank");
        }

        validationExceptions.add(ex);
        return false;
    }

    public boolean isValidPassword(String toValidate) {
        ValidationException ex;

        if (!toValidate.isBlank()){
            String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
            boolean isValid = toValidate.matches(passwordRegex);

            if (!isValid){
                ex = new ValidationException("Password ", "not strong enough");
            }
            else{
                return isValid;
            }
        }
        else{
            ex = new ValidationException("Password ", "is blank");
        }

        validationExceptions.add(ex);
        return false;
    }

    public boolean isValidDate(String toValidate) {
        ValidationException ex;

        if (!toValidate.isBlank()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            try{
                LocalDate dateOfBirth = LocalDate.parse(toValidate, formatter);

                LocalDate now = LocalDate.now();

                int compare = now.compareTo(dateOfBirth);

                if (compare > 0){
                    int yearsInBetween = Period.between(dateOfBirth, now).getYears();

                    if (yearsInBetween >= 16){
                        return true;
                    }
                    else{
                        ex = new ValidationException("Date of birth ", "less than 16 years");
                    }
                }
                else{
                    ex = new ValidationException("Date of birth ", "is a future date");
                }
            }
            catch (DateTimeParseException dateTimeParseException){
                ex = new ValidationException("Date of birth ", "not valid");
            }
        }
        else{
            ex = new ValidationException("Date of birth ", "is blank");
        }

        validationExceptions.add(ex);
        return false;
    }

    public List<ValidationException> getValidationExceptions() {
        return validationExceptions;
    }
}
