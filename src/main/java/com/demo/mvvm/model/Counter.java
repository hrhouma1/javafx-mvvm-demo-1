package com.demo.mvvm.model;

/**
 * MODEL
 * Représente la logique métier pure.
 * Pas de dépendance vers la View ou le ViewModel.
 * Contient uniquement la logique et les données.
 */
public class Counter {
    private int value;
    private final int minValue;
    private final int maxValue;

    public Counter() {
        this(0, -100, 100);
    }

    public Counter(int initialValue, int minValue, int maxValue) {
        this.value = initialValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Incrémente le compteur si possible
     * @return true si l'opération a réussi
     */
    public boolean increment() {
        if (value < maxValue) {
            value++;
            return true;
        }
        return false;
    }

    /**
     * Décrémente le compteur si possible
     * @return true si l'opération a réussi
     */
    public boolean decrement() {
        if (value > minValue) {
            value--;
            return true;
        }
        return false;
    }

    /**
     * Réinitialise le compteur à zéro
     */
    public void reset() {
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public boolean canIncrement() {
        return value < maxValue;
    }

    public boolean canDecrement() {
        return value > minValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}

