package software.ulpc.moneycalculator.arquitecture.model;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {
}
