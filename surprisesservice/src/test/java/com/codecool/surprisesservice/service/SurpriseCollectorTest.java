package com.codecool.surprisesservice.service;

import com.codecool.surprisesservice.model.Surprise;
import com.codecool.surprisesservice.model.SurpriseType;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class SurpriseCollectorTest {
    @Mock
    private SurpriseServiceCaller surpriseServiceCaller;

    @InjectMocks
    private SurpriseCollector surpriseCollector;

    @Test
    public void collectsPositiveSurprises() {
        Surprise kittenSurprise = new Surprise("img", "src", "kitten");
        Surprise funnyimgsSurprise = new Surprise("img", "src", "funnyimg");
        when(surpriseServiceCaller.getSurprise(SurpriseType.KITTEN))
                .thenReturn(kittenSurprise);
        when(surpriseServiceCaller.getSurprise(SurpriseType.FUNNYIMGS))
                .thenReturn(funnyimgsSurprise);
        assertIterableEquals(new ArrayList<>(Arrays.asList(kittenSurprise, funnyimgsSurprise)),
                surpriseCollector.collectFor(true).getSurprises());
    }

    @Test
    public void collectsNegativeSurprises() {
        Surprise punishment = new Surprise("img", "src", "punishment");
        when(surpriseServiceCaller.getSurprise(SurpriseType.PUNISHMENT))
                .thenReturn(punishment);
        assertIterableEquals(new ArrayList<>(Arrays.asList(punishment)),
                surpriseCollector.collectFor(false).getSurprises());
    }
}