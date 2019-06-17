package com.amg.models;

import lombok.Getter;
import lombok.Setter;

public class Player {
    @Getter
    String naam;

    int overworkedHours;

    @Getter
    @Setter
    boolean burnout;
}
