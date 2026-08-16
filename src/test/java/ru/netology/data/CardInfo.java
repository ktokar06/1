package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
    private String number;
    private String month;
    private String year;
    private String holder;
    private String cvc;
}