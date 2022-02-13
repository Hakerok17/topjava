package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MealDao {
    private static MealDao mealsDao;

    private MealDao() {
    }

    public static MealDao getMealsDao() {
        if (mealsDao == null) mealsDao = new MealDao();
        return mealsDao;
    }

    private final Map<Integer, MealTo> mealToMap = fill();

    private Map<Integer, MealTo> fill() {
        Map<Integer, MealTo> meal = new TreeMap<>();
        for (MealTo mealTo : MealsUtil.createToList()) {
            meal.put(mealTo.getId(), mealTo);
        }
        return meal;
    }

    public void addMeal(MealTo mealTo) {
        mealToMap.put(mealTo.getId(), mealTo);
    }

    public void deleteMeal(int userId) {
        mealToMap.remove(userId);
    }

    public void updateMeal(MealTo mealTo) {
        MealTo mealTo1 = mealToMap.get(mealTo.getId());
        mealTo1.setDateTime(mealTo.getDateTime());
        mealTo1.setCalories(mealTo.getCalories());
        mealTo1.setDescription(mealTo.getDescription());
    }

    public List<MealTo> getAllMeals() {
        List<MealTo> mealTo = new ArrayList<>();
        for (Map.Entry<Integer, MealTo> mealToEntry : mealToMap.entrySet()) {
            mealTo.add(mealToEntry.getValue());
        }
        return mealTo;
    }

    public MealTo getMealById(int userId) {
        return mealToMap.get(userId);
    }
}
