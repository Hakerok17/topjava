package ru.javawebinar.topjava.controller;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = getLogger(MealController.class);
    private static final String LIST_MEALS = "/meals.jsp";
    private static final String INSERT_OR_EDIT = "/edit.jsp";
    private final List dao;

    public MealController() {
        super();
        dao = MealDao.getMealsDao().getAllMeals();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");
        if (action == null)
            action = "listMeal";
        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            forward = LIST_MEALS;
            MealDao.getMealsDao().deleteMeal(mealId);
            req.setAttribute("meals", dao);
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt("mealId");
            MealTo mealto = MealDao.getMealsDao().getMealById(mealId);
            req.setAttribute("meal", mealto);
        } else if (action.equalsIgnoreCase("listMeal")) {
            forward = LIST_MEALS;
            req.setAttribute("meals", dao);
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MealTo meal = new MealTo();
        String mealId = req.getParameter("mealId");
        meal.setDateTime(LocalDateTime.parse(req.getParameter("dateTime")));
        meal.setDescription(req.getParameter("description"));
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
        if (mealId == null || mealId.isEmpty()) {
            MealDao.getMealsDao().addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            MealDao.getMealsDao().addMeal(meal);
        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_MEALS);
        req.setAttribute("meals", dao);
        view.forward(req, resp);
    }
}
