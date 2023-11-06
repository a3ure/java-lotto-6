package lotto.validation;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constants.ExceptionMessages;

public class InputValidation {

    private final Set<String> set = new HashSet<>();

    public void validatePurchaseAmountInput(String input) {
        isExist(input);
        isDigit(input);
        isDivisibleBy1000(input);
    }

    public void validateWinningNumbersInput(List<String> input) {

        isNumbersExist(input);
        for (String item : input) {
            isDigit(item);
            isDuplicateNumber(item);
            isWithinLottoRange(item);
        }
        isValidLottoNumbersLength();

    }

    public void validateBonusNumberInput(String input) {
        isExist(input);
        isDigit(input);
        isWithinLottoRange(input);
        isBonusNumberInWinningNumbers(input);

    }

    public void isExist(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NONE_INPUT.getMessage());
        }
    }

    public void isNumbersExist(List<String> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NONE_INPUT.getMessage());
        }
    }

    public void isDigit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessages.NONE_DIGIT.getMessage());
        }
    }

    public void isDivisibleBy1000(String input) {
        if (Integer.parseInt(input) % 1000 != 0) {
            throw new IllegalArgumentException(ExceptionMessages.NON_MULTIPLE_OF_1000.getMessage());
        }
    }

    public void isDuplicateNumber(String input) {
        if (!set.add(input)) {
            throw new IllegalArgumentException(
                ExceptionMessages.DUPLICATE_LOTTO_NUMBERS.getMessage());
        }
    }

    public void isWithinLottoRange(String input) {
        int inputNumber = Integer.parseInt(input);
        if ((inputNumber < 1) || (inputNumber > 45)) {
            throw new IllegalArgumentException(ExceptionMessages.OUT_OF_RANGE.getMessage());
        }
    }

    public void isValidLottoNumbersLength() {
        if (set.size() != 6) {
            throw new IllegalArgumentException(
                ExceptionMessages.INVALID_LOTTO_NUMBERS_LENGTH.getMessage());
        }
    }

    public void isBonusNumberInWinningNumbers(String input) {
        if (set.contains(input)) {
            throw new IllegalArgumentException(
                ExceptionMessages.DUPLICATE_LOTTO_BONUS.getMessage());
        }
    }
}