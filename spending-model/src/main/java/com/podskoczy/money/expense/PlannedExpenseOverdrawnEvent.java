package com.podskoczy.money.expense;

import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@AllArgsConstructor(staticName = "of")
@Value
public class PlannedExpenseOverdrawnEvent {

    private RefId refId;

    private LocalDate from;

    private LocalDate to;

    private Category category;



}
