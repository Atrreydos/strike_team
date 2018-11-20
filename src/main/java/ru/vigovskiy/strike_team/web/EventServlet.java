package ru.vigovskiy.strike_team.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.vigovskiy.strike_team.model.Event;
import ru.vigovskiy.strike_team.web.rest.OldEventController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@WebServlet("/events")
public class EventServlet extends HttpServlet {

    private OldEventController eventController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        eventController = springContext.getBean(OldEventController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id;
        Event event;

        switch (action == null ? "all" : action) {
            case "get":
                id = getId(request);
                event = eventController.get(id);
                request.setAttribute("event", event);
                request.getRequestDispatcher("/event.jsp").forward(request, response);
                break;
            case "delete":
                id = getId(request);
                eventController.delete(id);
                response.sendRedirect("events");
                break;
            case "create":
                event = new Event();
                request.setAttribute("event", event);
                request.getRequestDispatcher("/eventForm.jsp").forward(request, response);
                break;
            case "update":
                event = eventController.get(getId(request));
                request.setAttribute("event", event);
                request.getRequestDispatcher("/eventForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("events", eventController.getAll());
                request.getRequestDispatcher("/events.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Integer id = request.getParameter("id").isEmpty() ? null : getId(request);
            Event event = new Event(id, request.getParameter("name"), request.getParameter("description"));

            if (id == null) {
                eventController.create(event);
            } else {
                eventController.update(event);
            }
            response.sendRedirect("events");

        }
//        else if ("filter".equals(action)) {
//            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
//            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
//            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
//            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
//            request.setAttribute("meals", eventController.getBetween(startDate, startTime, endDate, endTime));
//            request.getRequestDispatcher("/events.jsp").forward(request, response);
//        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
